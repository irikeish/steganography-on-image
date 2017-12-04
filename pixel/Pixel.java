package steganography.pixel;

public class Pixel{

	
	public int red;
	public int blue;
	public int green;
	


	public Pixel(final int pixel){

		this.red = (pixel >> 16) & 0xff;
		this.blue = (pixel >> 8) & 0xff;
		this.green = pixel & 0xff;

	}

	public Pixel(final String rgb){

		String rgbCode[]=rgb.split(",");
		this.red=Integer.parseInt(rgbCode[0]);
		this.blue=Integer.parseInt(rgbCode[1]);
		this.green=Integer.parseInt(rgbCode[2]);
			
	}


	


	public Pixel(final int r,final int g,final int b){

		this.red = r;
		this.green = g;
		this.blue = b;

		

	}


	public int getPixel(){

		int pixel = 255;
		pixel = (pixel << 8) | this.red;
		pixel = (pixel << 8) | this.blue ; 
		pixel = (pixel << 8) | this.green;

		return pixel;
	}

	public int getGreyPixel(){
		return (red+green+blue)/2;
	}


	@Override
    public String toString(){
        return String.format(this.red + "," +this.blue + "," +  this.green );
    }



    
	public static int encodeMsg(final int pixel,final byte  data){
		int rgb=pixel;
		int a=((rgb >> 24) & 0xff);

		int r = (((rgb >> 16) & 0xff)>>3)<<3;
		r=r|(data>>5);

		int g = (((rgb >> 8) & 0xff)>>3)<<3;
		g=g|((data>>2)& 7);

		int b = ((rgb & 0xff)>>2)<<2;
		b=b|(data&3);


		rgb=0;
		rgb=(rgb|(a<<24));
		rgb=(rgb|(r<<16));
		rgb=(rgb|(g<<8));

		rgb=(rgb|b);
		return rgb;
	}

	public static int decodeMsg(final int pixel){
		
		int charCode = (((pixel >> 16) & 0x7) << 5);
		System.out.println(charCode);
		charCode |=  (((pixel >> 8) & 0x7) << 2);
		System.out.println(charCode);
		charCode |=  ((pixel & 0x3));
		System.out.println(charCode);
		
		return charCode;

	}


}