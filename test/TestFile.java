package steganography.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import steganography.pixel.Pixel;
import java.io.BufferedReader;
import java.io.FileReader;

import steganography.image.Steganography;



public class TestFile{

	public static void main(String args[]) throws IOException,FileNotFoundException{

		BufferedImage img;
		img=ImageIO.read(new File(args[0]));


		Steganography.setMsg(img,args[1],args[2],args[3]);
		img=ImageIO.read(new File(args[2]));
		Steganography.getMsg(img);
		
	}

}