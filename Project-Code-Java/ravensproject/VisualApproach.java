package ravensproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.awt.Image;
import java.io.File;
import javax.imageio.*;
import java.awt.image.*;

public class VisualApproach {

	private RavensProblem problem;
	//Figure names
	private RavensFigure figureA, figureB, figureC, figureD, figureE, figureF, figureG, figureH; 
	//buffered image for each figure
	private BufferedImage figureAImage, figureBImage, figureCImage, figureDImage, figureEImage, figureFImage, figureGImage, figureHImage;
	//2D array of black and white pixel for images
	//private int [][] pixelsA;
	private int tempAns;
	
	private float SIMILARITY = 0.998f;

	public VisualApproach(RavensProblem problem){
		this.problem = problem;
	}

	public int visualApproach() {
		CheckABCEquality();

		return -1;
	}


	//returns if A, B and C are the same figures
	private boolean CheckABCEquality() {

		//int [][] pixels = new int[width][height];
		int [][] pixelsA = CreateProblemArray("A");
		int [][] pixelsB = CreateProblemArray("B");
		int [][] pixelsC = CreateProblemArray("C");

		//System.out.println("Print array A: " + Arrays.deepToString(pixelsA));

		//		System.out.println("print from A: " + pixelsA[183][183]);
		//		System.out.println("print from B: " + pixelsB[183][183]);
		//		System.out.println("print from C: " + pixelsC[183][183]);
		int pixelDiffAB = 0; //A and B
		int pixelDiffBC = 0; //for B and C

		//difference between figure A and B on pixel basis
		for(int i = 0 ; i < pixelsA.length ; i++) {
			for(int j = 0 ; j < pixelsA.length ; j++) {
				pixelDiffAB += pixelsA[i][j] - pixelsB[i][j];
			}	
		}

		//difference between figure B and C on pixel basis
		for(int i = 0 ; i < pixelsB.length ; i++) {
			for(int j = 0 ; j < pixelsB.length ; j++) {
				pixelDiffBC += pixelsB[i][j] - pixelsC[i][j];
			}	
		}
		
		//similarity between A and B (1- sum of pixel diff/totalpixelcount)
		
		float similarityAB = (float) (1.0 - ((float) pixelDiffAB/(float) (pixelsA.length*pixelsA.length)));
		float similarityAC = (float) (1.0 - ((float) pixelDiffBC/(float) (pixelsB.length*pixelsB.length)));

		System.out.println("value of diff AB: " + pixelDiffAB);
		System.out.println("Total pixels in A: " + pixelsA.length*pixelsA.length);
		System.out.println("Similarity of A and B: " + similarityAB);
		System.out.println("*******");

		System.out.println("value of diff BC: " + pixelDiffBC);
		System.out.println("Total pixels in B: " + pixelsB.length*pixelsB.length);
		System.out.println("Similarity of B and C: " + similarityAC);
		System.out.println("*******");

		if (similarityAB >= SIMILARITY && similarityAC >= SIMILARITY) {
			System.out.println("true");
			return true;
		} else {
			System.out.println("false");
			return false;
		}
	}


	//read the figure and return a 2D array of the values of white and black pixels
	//takes the figure name with "" i.e CreateProblemArray("A")
	private int[][] CreateProblemArray(String figureName) {

		RavensFigure figure = problem.getFigures().get(figureName);
		BufferedImage figureImage = null;
		try {
			figureImage = ImageIO.read(new File(figure.getVisual()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// from here: http://stackoverflow.com/questions/15002706/convert-an-image-to-a-2d-array-and-then-get-the-image-back-in-java
		Raster raster = figureImage.getData();

		int width = raster.getWidth();
		int height = raster.getHeight();
		int [][] pixels = new int[width][height];
		System.out.println("width: " + width + ", " + " height: " + height);

		//0xFFFFFFFF is white. Store white as 0 and black as 1
		for(int i = 0 ; i < width ; i++) {
			for(int j = 0 ; j < height ; j++) {
				pixels[i][j] = (figureImage.getRGB(i, j) == 0xFFFFFFFF ? 0 : 1); //http://stackoverflow.com/questions/5925426/java-how-would-i-load-a-black-and-white-image-into-binary
				//System.out.println(pixels[0][0]);
			}	
		}
		//System.out.println(pixels[183][183]);
		return pixels;
	}
}


