package steganography.test;


import steganography.pixel.Pixel;
import steganography.image.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import steganography.utility.CreateImage;

class TestCreateImage{
	public static void main(String []args){
		CreateImage.steganographic("blackTaj.csv","BlackTaj.png","png");
	}
}