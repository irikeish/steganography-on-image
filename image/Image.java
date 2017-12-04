package steganography.image;


import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import steganography.pixel.Pixel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;



public class Image{

	private BufferedImage img;
	private int height;
	private int width;
	private int pixel[][];
	private int type;

	public Image(final String img){
		try{

			this.img = ImageIO.read(new File(img));
			this.height = this.img.getHeight();
			this.width = this.img.getWidth();
			this.type = this.img.getType();

		}catch (IOException e) {
			e.printStackTrace();
		}
	}


	public int[][] getPixel(){

		if(pixel == null){

			pixel = new int[width][height];

			for(int x = 0;x<width;x++)
				for(int y = 0;y<height;y++)
					pixel[x][y] = img.getRGB(x,y);

		}

		return pixel;
	}

	



	public int getHeight(){

		return height;

	}


	public int getWidth(){

		return width;

	}

	public BufferedImage getImage(){

		return img;

	}


	public int getType(){

		return type;
	}

	


	@Override
    public String toString(){
        return String.format(height+ "  " + width + " " +type);
    }

} 