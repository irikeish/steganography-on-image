package steganography.utility;


import steganography.pixel.Pixel;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;



public class CreateImage{

	public static void steganographic(final String rgb,final String newImage,final String imgExt){
		BufferedReader br = null;
		String line = ""; 
		BufferedImage img=null;
		int height;
		int width;
		int type;
		File imageFile=null;
		try{
			br = new BufferedReader(new FileReader(rgb));
			String data[]=br.readLine().split(",");
			
			height=Integer.parseInt(data[0]);
			width=Integer.parseInt(data[1]);
			type=Integer.parseInt(data[2]);

			img = new BufferedImage(width,height,type);
	
			for (int x = 0;x<width;x++)
		    	for (int y = 0;y<height;y++){

		    		Pixel pixel=new Pixel(br.readLine());
		    		int pixelVal=pixel.getPixel();
		        	img.setRGB(x,y,pixelVal);
		        	//System.out.println(pixelVal);

		    	}
		    System.out.println(newImage+" to open");
		    imageFile = new File(newImage);
		    ImageIO.write(img,imgExt,imageFile);
		    System.out.println(newImage+" opened");

		}catch (ArrayIndexOutOfBoundsException e){
			e.printStackTrace();

		}catch (FileNotFoundException e) {
        	e.printStackTrace();
    	} catch (IOException e) {
        	e.printStackTrace();
    	}finally{
        	if (br != null) {
            	try {
                	br.close();
           		 } catch (IOException e) {
                e.printStackTrace();
            	}
        	}
  		}
			     
	}


}