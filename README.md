# steganography-on-image

#################################################################################################################

Details of step 3 embedding data to pixel:

We first get the length of msg length and encode into 0,0 pixels.
choose one offset value it's specify the position of pixels which will contains data for better distrubution of message over Entire Image. we can increase decrease the offset value as we need.


for encoding we use least significant bit manuplation algorithm as following:

int rgb=pixel;
int a=((rgb >> 24) & 0xff);//alpha value of pixels

int r = (((rgb >> 16) & 0xff)>>3)<<3; //red pixels contains three bit of message
r=r|(data>>5);

int g = (((rgb >> 8) & 0xff)>>3)<<3; //green pixels contains next three bit of messsage
g=g|((data>>2)& 7);

int b = ((rgb & 0xff)>>2)<<2; //blue pixels contains next remaining two bit of message
b=b|(data&3);


rgb=a;
rgb=(rgb|(a<<24));
rgb=(rgb|(r<<16));
rgb=(rgb|(g<<8));

rgb=(rgb|b); // final encoded pixel value 




e.g.:

Let's we have pixel value rgb(base 2) = 10110111 01110100 01000111 11100100   and data = 10010001

	alpha = 10110111 (by 24 bit right shifting of pixel(rgb) and  doing and operator on this by 255(0xff) )
	red = 01110100  (by 16 bit right shifting of pixel(rgb) and  doing and operator on this by 255(0xff) )
	blue = 01000111  (by 8 bit right shifting of pixel(rgb) and  doing and operator on this by 255(0xff) )
	green = 11100100  (by doing and operator on pixel(rgb) by 255(0xff) )

	now we have to add three bit of data in red pixel, we get three bit of data and do or to the red pixel , for this we have to clear the least three bit of red pixel

	for clearing least three bit of red pixel we first do 3 bit right shift and then three bit left shift of it 
	red=(red>>3)<<3 , it gives final red pixel = 01110000
	now we have red pixel least 3 bit clear, we can embedd data on this red pixel by following:

	red = red|(data>>5) [ data>>5 == 100  and by doing or to red it gives final red pixel which have three bit of data embedded ]

	now final red pixel = 01110000 or 100 = 01110100


	now we have 5 bit of data is left to embedd , we again do above repeated task on blue and green to embedd rest 5 bit (3 bit on blue pixel and last two bit on green pixel )

	by embedding all bit of data we convert red,green,blue pixel ito whole 32 bit pixel value by following

	rgb=alpha;
	rgb=(rgb|(alpha<<24));
	rgb=(rgb|(red<<16));
	rgb=(rgb|(green<<8));
	rgb=rgb|blue


	


***********************************************************************************************#	

Note:

Here we are embedding 1 data into an one pixel, for better distribution and less recognizable we can use set of pixel to embedd 1 data.

as e.g: 

	we can use three pixel to embedd on data...
	3 bit of data into first pixel(distributed 3 bit in red,blue,green)
	next 3 bit into second pixel ((distributed 3 bit in red,blue,green)) 
	next to bit into third pixel ((distributed 2 bit in blue,green))



******************************************************************************************************#


################################################################################################

Step to decode the message from stego image is following:
1. Get pixels of Stego Image.
2. Get message from of pixels by apply reverse algorithm that we use in encoding of pixel as following:


int charCode = (((pixel >> 16) & 0x7) << 5);//red pixels message

charCode |=  (((pixel >> 8) & 0x7) << 2); // blue pixels message  or with red pixel messsage

charCode |=  ((pixel & 0x3)); // green pixels messsage or with previous pixels messsage
 
charCode; //return char code of message contains in one specific pixels
