package ravensproject;

import java.io.File;
import javax.imageio.*;
import java.awt.image.*;

public class VisualApproach {

	private RavensProblem problem;

	private int tempAns;
	//these two floating points for horizontal, vertical and diagonal similarities
	private float SIMILARITY_LOW1 = 0.990f;
	private float SIMILARITY_HIGH1 = 1.00372f;

	//these two floating points are for horizontal and vertical similarities when the similarity is lower
	//B-05
	private float SIMILARITY_LOW2 = 0.960f;
	private float SIMILARITY_HIGH2 = 0.980f;
	
	//these two floating points are for horizontal and vertical similarities when the similarity is lower
	//B-06
	private float SIMILARITY_LOW3 = 0.962f;
	private float SIMILARITY_HIGH3 = 1.0384f;

	public VisualApproach(RavensProblem problem){
		this.problem = problem;
	}

	public int VisualApproachResults() {
		tempAns = -1;
		if (CheckABCEquality1("A", "B", "C")) { //Case D-01
			tempAns = FigureABCEqual1();
		} else if (CheckABCEquality1("A", "D", "G")){ //Analogous to D-01
			//System.out.println("true");
			tempAns = FigureADGEqual1();
		} 
		else if (CheckABCEquality3("A", "B", "C")) {//D-06
			System.out.println("enters 3");
			tempAns = FigureABCEqual3();
		}
		else if (CheckABEquality2("A", "G")) { //D-05
			tempAns = FigureADGEqual2();;
		} 
		else if (DiagonalsEqual("A", "E")) {
			tempAns = FigureAEEqual();
		}
		
		System.out.println("sum of black pixels A: " + SumBlackPixels("A"));
		System.out.println("sum of black pixels B: " + SumBlackPixels("B"));
		System.out.println("sum of black pixels C: " + SumBlackPixels("C"));
		
		
		
		
				
		return tempAns;
	}

	//return answer if figure A and E are the same
	//Case D-02, D-03
	private int FigureAEEqual(){
		int ans = -1;
		try {
			if(DiagonalsEqual("E", "1")){
				ans = 1;
			} else if(DiagonalsEqual("E", "2")){
				ans = 2;
			} else if(DiagonalsEqual("E", "3")){
				ans = 3;
			} else if(DiagonalsEqual("E", "4")){
				ans = 4;
			} else if(DiagonalsEqual("E", "5")){
				ans = 5;
			} else if(DiagonalsEqual("E", "6")){
				ans = 6;
			} else if(DiagonalsEqual("E", "7")){
				ans = 7;
			} else if(DiagonalsEqual("E", "8")){
				ans = 8;
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ans;
	}

	//Check if the diagonals are the same, figure A and E
	//Case D-02, D-03, D-11
	//takes the figure name with "" i.e CreateProblemArray("A")
	private boolean DiagonalsEqual(String fig1, String fig2) {
		float similar12 = 0.0f;
		similar12 = SimilarityFig1Fig2(fig1, fig2);
		if (similar12 >= SIMILARITY_LOW1 && similar12 <= SIMILARITY_HIGH1) {
			return true;
		} else {
			return false;
		}
	}

	//Return answer for cases when figure A, D and G are same
	//Analogous to D-01
	private int FigureADGEqual2(){
		int ans = -1;
		try {
			if(CheckABEquality2("C", "1")){
				ans = 1;
			} else if(CheckABEquality2("C", "2")){
				ans = 2;
			} else if(CheckABEquality2("C", "3")){
				ans = 3;
			} else if(CheckABEquality2("C", "4")){
				ans = 4;
			} else if(CheckABEquality2("C", "5")){
				ans = 5;
			} else if(CheckABEquality2("C", "6")){
				ans = 6;
			} else if(CheckABEquality2("C", "7")){
				ans = 7;
			} else if(CheckABEquality2("C", "8")){
				ans = 8;
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ans;
	}

	//Return answer for cases when figure A, D and G are same
	//Analogous to D-01
	private int FigureADGEqual1(){
		int ans = -1;
		try {
			if(CheckABCEquality1("C", "F", "1")){
				ans = 1;
			} else if(CheckABCEquality1("C", "F", "2")){
				ans = 2;
			} else if(CheckABCEquality1("C", "F", "3")){
				ans = 3;
			} else if(CheckABCEquality1("C", "F", "4")){
				ans = 4;
			} else if(CheckABCEquality1("C", "F", "5")){
				ans = 5;
			} else if(CheckABCEquality1("C", "F", "6")){
				ans = 6;
			} else if(CheckABCEquality1("C", "F", "7")){
				ans = 7;
			} else if(CheckABCEquality1("C", "F", "8")){
				ans = 8;
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ans;
	}

	
	//Return answer if figure A, B, C are same
	//D-06
	private int FigureABCEqual3(){
		int ans = -1;
		try {
			if(CheckABCEquality3("G", "H", "1")){
				ans = 1;
			} else if(CheckABCEquality3("G", "H", "2")){
				ans = 2;
			} else if(CheckABCEquality3("G", "H", "3")){
				ans = 3;
			} else if(CheckABCEquality3("G", "H", "4")){
				ans = 4;
			} else if(CheckABCEquality3("G", "H", "5")){
				ans = 5;
			} else if(CheckABCEquality3("G", "H", "6")){
				ans = 6;
			} else if(CheckABCEquality3("G", "H", "7")){
				ans = 7;
			} else if(CheckABCEquality3("G", "H", "8")){
				ans = 8;
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ans;
	}
	
	
	//Return answer if figure A, B, C are same
	//D-01
	private int FigureABCEqual1(){
		int ans = -1;
		try {
			if(CheckABCEquality1("G", "H", "1")){
				ans = 1;
			} else if(CheckABCEquality1("G", "H", "2")){
				ans = 2;
			} else if(CheckABCEquality1("G", "H", "3")){
				ans = 3;
			} else if(CheckABCEquality1("G", "H", "4")){
				ans = 4;
			} else if(CheckABCEquality1("G", "H", "5")){
				ans = 5;
			} else if(CheckABCEquality1("G", "H", "6")){
				ans = 6;
			} else if(CheckABCEquality1("G", "H", "7")){
				ans = 7;
			} else if(CheckABCEquality1("G", "H", "8")){
				ans = 8;
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ans;
	}

	//returns if the three figures are similarity
	//takes the figure name with "" i.e CreateProblemArray("A")
	private boolean CheckABCEquality1(String fig1, String fig2, String fig3) {
		float similar12 = 0.0f;
		float similar13 = 0.0f;
		Boolean test = true;

		try {
			similar12 = SimilarityFig1Fig2(fig1, fig2);
			similar13 = SimilarityFig1Fig2(fig1, fig3);
			if (similar12 >= SIMILARITY_LOW1 && similar12 <= SIMILARITY_HIGH1 && 
					similar13 >= SIMILARITY_LOW1 && similar13 <= SIMILARITY_HIGH1) {
				test = true;
			} else {
				test = false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return test;
	}

	//returns if the three figures are similarity
	//takes the figure name with "" i.e CreateProblemArray("A")
	//for D-05

	private boolean CheckABEquality2(String fig1, String fig2) {
		float similar12 = 0.0f;
		Boolean test = true;
		
		try {
			similar12 = SimilarityFig1Fig2(fig1, fig2);
			if (similar12 >= SIMILARITY_LOW2 && similar12 <= SIMILARITY_HIGH2) {
				test = true;
			} else {
				test = false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return test;
	}
	
	//returns if the three figures are similarity
	//takes the figure name with "" i.e CreateProblemArray("A")
	//B-06
	private boolean CheckABCEquality3(String fig1, String fig2, String fig3) {
		float similar12 = 0.0f;
		float similar13 = 0.0f;
		Boolean test = true;

		try {
			similar12 = SimilarityFig1Fig2(fig1, fig2);
			similar13 = SimilarityFig1Fig2(fig1, fig3);
			if (similar12 >= SIMILARITY_LOW3 && similar12 <= SIMILARITY_HIGH3 && 
					similar13 >= SIMILARITY_LOW3 && similar13 <= SIMILARITY_HIGH3) {
				test = true;
			} else {
				test = false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return test;
	}

	//finds the similarity between two figures A and B
	//takes the figure name with "" i.e CreateProblemArray("A")
	private float SimilarityFig1Fig2(String fig1, String fig2) {
		
		int pixelDiffFig1Fig2 = 0;
		float similarFig1Fig2 = 0.0f;

		//int pixelDiffFig1Fig2;
		//difference between figure 1 and figure 2 on pixel basis
		//both pixelsFig1 and pixelsFig2 have the same dimensions
		try {
			int [][] pixelsFig1 = CreateProblemArray(fig1);
			int [][] pixelsFig2 = CreateProblemArray(fig2);
			for(int i = 0 ; i < pixelsFig1.length ; i++) {
				for(int j = 0 ; j < pixelsFig1.length ; j++) {
					pixelDiffFig1Fig2 += pixelsFig1[i][j] - pixelsFig2[i][j];
				}
			}
			similarFig1Fig2 = (float) (1.0 - ((float) pixelDiffFig1Fig2/(float) (pixelsFig1.length*pixelsFig1.length)));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return similarFig1Fig2;
	}

	//sums the black pixels in a figure
	//takes the figure name with "" i.e CreateProblemArray("A")
	private int SumBlackPixels(String figureName) {
		
		int totalBlackPixels = 0;

		//sum for the black pixels in a figure
		try {
			int [][] pixelsFigure = CreateProblemArray(figureName);
			for(int i = 0 ; i < pixelsFigure.length ; i++) {
				for(int j = 0 ; j < pixelsFigure.length ; j++) {
					totalBlackPixels += pixelsFigure[i][j]; //white pixels are 0 while black pixels are 1
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return totalBlackPixels;
	}

	//read the figure and return a 2D array of the values of white and black pixels
	//takes the figure name with "" i.e CreateProblemArray("A")
	private int[][] CreateProblemArray(String figureName) {

		BufferedImage figureImage = null;
		try {
			RavensFigure figure = problem.getFigures().get(figureName);
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
		try {
			for(int i = 0 ; i < width ; i++) {
				for(int j = 0 ; j < height ; j++) {
					pixels[i][j] = (figureImage.getRGB(i, j) == 0xFFFFFFFF ? 0 : 1); 	
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return pixels;
	}
}
