package steganography.image;


import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import steganography.pixel.Pixel;
import java.io.FileInputStream;


public class Steganography{


	final static int OFFSET = 1;


	public static void setMsg(BufferedImage img,final String msgFile,final String out,final String ext)throws IOException{

		int w=img.getWidth();
		int h=img.getHeight();
		File msg=new File(msgFile);
		FileInputStream fileInputStream = new FileInputStream(msg);

		int msgLength=(int)msg.length();

		System.out.println("message="+msg+" length="+msgLength);
		if(msgLength>255*255){
			System.out.println("msg is too big");
		}

		else if( msgLength*OFFSET >w*h){
			System.out.println("Image is too small");    
		}
		else{

			byte[] msgbytes= new byte[msgLength];
			fileInputStream.read(msgbytes,0,msgLength);
			int msglen = msgLength/255;
			msgLength=msgLength%255;
			int msgLenEncode= (img.getRGB(0,0)>>16)<<8;
			msgLenEncode |= msglen;
			msgLenEncode = (msgLenEncode<<8);
			msgLenEncode |= msgLength;
			img.setRGB(0, 0,msgLenEncode );

			
			for(int i=1,msgpos=0,y=0,j=0;   y<h   ;y++  ){
				
				for(int x=0;x<w && j<msgbytes.length ;x++,i++ ){      

					if (i%OFFSET==0) {

						int rgb = Pixel.encodeMsg(img.getRGB(x,y),msgbytes[msgpos]);

						img.setRGB(x,y,rgb);

						msgpos++;
						j++;
					}

				}
			}

			try {

				File outputfile = new File(out);
				ImageIO.write(img,ext,outputfile);
			} catch (IOException e) {
				System.out.println("error in saving image ");
			}
		}  

	}

	public static void getMsg(BufferedImage img) {

		System.out.println("msg decoding");

		int w=img.getWidth(),h=img.getHeight();

		int msgLenData=img.getRGB(0, 0);
		int msgLength = 255*((msgLenData>>8) & 0xff) +(msgLenData & 0xff) ;

		System.out.println("Message Length="+msgLength);


		String msg="";
		for(int y=0,j=0,i=1;   y<h   ;y++  ){ 
			for(int x=0;x<w && j<msgLength ;x++ ,i++){

				if (i%OFFSET==0) {
					
					msg =msg + ((char) Pixel.decodeMsg(img.getRGB(x,y)));

					j++;
				}
			}
		} 

		System.out.println("msg is \""+msg+"\"");
	}
}