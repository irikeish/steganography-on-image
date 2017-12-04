package steganography.test;


import steganography.pixel.Pixel;
import steganography.image.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import steganography.utility.CreateImage;


public class Test{
	
	public static void main(String args[]){
		Image image = new Image(args[0]);
		int pixel[][] = image.getPixel();
		int height = image.getHeight();
		int width = image.getWidth();
		int type = image.getType();
		String imgExt=args[2];
		String newImage=args[1];
		String colorFile=args[3];
    	
		if (pixel==null)
			System.out.println("pixel not found null");

		System.out.println(image);
// show pixel rgb value
		File pixelFile = new File(colorFile);
		try{

			PrintWriter pw = new PrintWriter(pixelFile);
			pw.write(height+","+width+","+type);
			System.out.println(height+","+width+","+type);

			for(int x=0;x<width;x++)
				for(int y=0;y<height;y++){
					//System.out.println(pixel[x][y]);
					Pixel color = new Pixel(pixel[x][y]);
					pw.write("\n");
					pw.write(color.toString());

				}		
			pw.close();

		}catch(FileNotFoundException e){
			e.printStackTrace();
		}

		
		CreateImage.steganographic(colorFile,newImage,imgExt);

	}

}