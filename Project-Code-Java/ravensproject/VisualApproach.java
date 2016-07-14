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

	private float SIMILARITY_LOW = 0.990f;
	private float SIMILARITY_HIGH = 1.00372f;

	public VisualApproach(RavensProblem problem){
		this.problem = problem;
	}

	public int visualApproach() {
		tempAns = -1;
		if (CheckABCEquality("A", "B", "C")) { //Case D-01
			//System.out.println("true");
			tempAns = FigureABCEqual();
		} else if (CheckABCEquality("A", "D", "G")){ //Analogous to D-02
			//System.out.println("true");
			tempAns = FigureADGEqual();
		} else if (DiagonalsEqual("A", "E")) {
			tempAns = FigureAEEqual();
		}
		return tempAns;
	}
	
	//return answer if figure A and E are the same
	//Case D-02, D-03
	private int FigureAEEqual(){
		int ans = -1;
		try {
			if(DiagonalsEqual("E", "1")){
				return 1;
			} else if(DiagonalsEqual("E", "2")){
				return 2;
			} else if(DiagonalsEqual("E", "3")){
				return 3;
			} else if(DiagonalsEqual("E", "4")){
				return 4;
			} else if(DiagonalsEqual("E", "5")){
				return 5;
			} else if(DiagonalsEqual("E", "6")){
				return 6;
			} else if(DiagonalsEqual("E", "7")){
				return 7;
			} else if(DiagonalsEqual("E", "8")){
				return 8;
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ans;
	}
	
	//Check if the diagonals are the same, figure A and E
	//Case D-02, D-03
	//takes the figure name with "" i.e CreateProblemArray("A")
	private boolean DiagonalsEqual(String fig1, String fig2) {
		float similar12 = 0.0f;

		similar12 = SimilarityFig1Fig2(fig1, fig2);
		//System.out.println("Diagonal A and E for D 11: " + similar12);

		if (similar12 >= SIMILARITY_LOW && similar12 <= SIMILARITY_HIGH) {
			return true;
		} else {
			return false;
		}
	}
	
	//Return answer for cases when figure A, D and G are same
	//Analogous to D-01
	private int FigureADGEqual(){
		int ans = -1;
		try {
			if(CheckABCEquality("C", "F", "1")){
				return 1;
			} else if(CheckABCEquality("C", "F", "2")){
				return 2;
			} else if(CheckABCEquality("C", "F", "3")){
				return 3;
			} else if(CheckABCEquality("C", "F", "4")){
				return 4;
			} else if(CheckABCEquality("C", "F", "5")){
				return 5;
			} else if(CheckABCEquality("C", "F", "6")){
				return 6;
			} else if(CheckABCEquality("C", "F", "7")){
				return 7;
			} else if(CheckABCEquality("C", "F", "8")){
				return 8;
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ans;
	}

	//Return answer if figure A, B, C are same
	//D-01
	private int FigureABCEqual(){
		int ans = -1;
		try {
			if(CheckABCEquality("G", "H", "1")){
				return 1;
			} else if(CheckABCEquality("G", "H", "2")){
				return 2;
			} else if(CheckABCEquality("G", "H", "3")){
				return 3;
			} else if(CheckABCEquality("G", "H", "4")){
				return 4;
			} else if(CheckABCEquality("G", "H", "5")){
				return 5;
			} else if(CheckABCEquality("G", "H", "6")){
				return 6;
			} else if(CheckABCEquality("G", "H", "7")){
				return 7;
			} else if(CheckABCEquality("G", "H", "8")){
				return 8;
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ans;
	}

	//returns if the three figures are similarity
	//takes the figure name with "" i.e CreateProblemArray("A")
	private boolean CheckABCEquality(String fig1, String fig2, String fig3) {
		float similar12 = 0.0f;
		float similar13 = 0.0f;

		similar12 = SimilarityFig1Fig2(fig1, fig2);
		similar13 = SimilarityFig1Fig2(fig1, fig3);

		if (similar12 >= SIMILARITY_LOW && similar12 <= SIMILARITY_HIGH && 
				similar13 >= SIMILARITY_LOW && similar13 <= SIMILARITY_HIGH) {
			return true;
		} else {
			return false;
		}
	}

	//finds the similarity between two figures A and B
	//takes the figure name with "" i.e CreateProblemArray("A")
	private float SimilarityFig1Fig2(String fig1, String fig2) {
		int [][] pixelsFig1 = CreateProblemArray(fig1);
		int [][] pixelsFig2 = CreateProblemArray(fig2);
		int pixelDiffFig1Fig2 = 0;
		float similarFig1Fig2 = 0.0f;

		//int pixelDiffFig1Fig2;
		//difference between figure 1 and figure 2 on pixel basis
		//both pixelsFig1 and pixelsFig2 have the same dimensions
		for(int i = 0 ; i < pixelsFig1.length ; i++) {
			for(int j = 0 ; j < pixelsFig1.length ; j++) {
				pixelDiffFig1Fig2 += pixelsFig1[i][j] - pixelsFig2[i][j];
			}
		}
		similarFig1Fig2 = (float) (1.0 - ((float) pixelDiffFig1Fig2/(float) (pixelsFig1.length*pixelsFig1.length)));
		return similarFig1Fig2;
	}

	//sums the black pixels in a figure
	//takes the figure name with "" i.e CreateProblemArray("A")
	private int SumBlackPixels(String figureName) {
		int [][] pixelsFigure = CreateProblemArray(figureName);
		int totalBlackPixels = 0;

		//sum for the black pixels in a figure
		for(int i = 0 ; i < pixelsFigure.length ; i++) {
			for(int j = 0 ; j < pixelsFigure.length ; j++) {
				totalBlackPixels += pixelsFigure[i][j]; //white pixels are 0 while black pixels are 1
			}
		}
		return totalBlackPixels;
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
		// http://stackoverflow.com/questions/5925426/java-how-would-i-load-a-black-and-white-image-into-binary

		Raster raster = figureImage.getData();

		int width = raster.getWidth();
		int height = raster.getHeight();
		int [][] pixels = new int[width][height];

		//0xFFFFFFFF is white. Store white as 0 and black as 1
		for(int i = 0 ; i < width ; i++) {
			for(int j = 0 ; j < height ; j++) {
				pixels[i][j] = (figureImage.getRGB(i, j) == 0xFFFFFFFF ? 0 : 1); 	
			}
		}
		return pixels;
	}
}
