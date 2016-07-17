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

	//second similarity for diagonal case
	private float SIMILARITY_DIAGONAL_LOW2 = 0.96f;
	private float SIMILARITY_DIAGONAL_HIGH2 = 1.08f;

	//similarity for C=B-A or G=D-A
	private float SIMILARITY_DIFF_LOW1 = 0.99f;
	private float SIMILARITY_DIFF_HIGH1 = 1.0f;

	//these two floating points are for horizontal and vertical similarities when the similarity is lower
	//B-05
	private float SIMILARITY_LOW2 = 0.960f;
	private float SIMILARITY_HIGH2 = 0.980f;

	//these two floating points are for horizontal and vertical similarities when the similarity is lower
	//B-06
	private float SIMILARITY_LOW3 = 0.962f;
	private float SIMILARITY_HIGH3 = 1.0384f;

	//this is to check if third image is sum of two images
	private float SIMILARITY_SUM1 = 0.98f;

	public VisualApproach(RavensProblem problem){
		this.problem = problem;
	}

	public int VisualApproachResults() {
		tempAns = -1;
		//figure C is sum of figure A and B
		try {
			if (FigureCIsSumOfAandB("A", "B", "C")) {
				System.out.println("Solved by the figure C is sum of A and B method");
				tempAns = FigureAnsIsSumGandH();
			} else if (FigureCIsSumOfAandB("A", "D", "G")){ //figure G is sum of A and D
				System.out.println("Solved by the figure G is sum of A and D method");
				tempAns = FigureAnsIsSumCandF();
			} 
			else if (FigureCIsDiffAandB("A", "B", "C")) {//C=A-B
				System.out.println("Solved by C=A-B");
				tempAns = FigureCIsAMinusB();
			} else if (FigureCIsDiffAandB("A", "D", "G")) {//G=A-D
				System.out.println("Solved by G=A-D");
				tempAns = FigureGIsAMinusD();
			}

			else if (CheckABCEquality1("A", "B", "C")) { //Case D-01
				System.out.println("Solved using A, B and C are equal logic");
				tempAns = FigureABCEqual1();
			} else if (CheckABCEquality1("A", "D", "G")){ //Analogous to D-01
				System.out.println("Solved using A, D and G are equal logic");
				//System.out.println("true");
				tempAns = FigureADGEqual1();
			} else if (CheckABCEquality3("A", "B", "C")) {//D-06
				System.out.println("Solved using A, B and C are equal (lower equality)");
				tempAns = FigureABCEqual3();
			}
			else if (CheckABEquality2("A", "G")) { //D-05
				System.out.println("Solved using A and C are equal logic");
				tempAns = FigureADGEqual2();;
			} 
			else if (DiagonalsEqual("A", "E")) {
				System.out.println("Solved using diagonal logic");
				tempAns = FigureAEEqual();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

				System.out.println("C=A-B " + TwoFiguresDiffSimilarity2("A", "B", "C"));
		//		System.out.println("G-H = 1 " + TwoFiguresDiffSimilarity2("H", "G", "7"));
		//		System.out.println("G-H = 5 " + TwoFiguresDiffSimilarity2("G", "H", "5"));
		//		System.out.println("G-H = 2 " + TwoFiguresDiffSimilarity2("G", "H", "2"));
		//		System.out.println("G-H = 4 " + TwoFiguresDiffSimilarity2("G", "H", "4"));


		return tempAns;
	}

	//to find the answer when G = A-D
	private int FigureGIsAMinusD(){
		int ans = -1;
		try {
			if(FigureCIsDiffAandB("C", "F", "1")){
				ans = 1;
			} else if(FigureCIsDiffAandB("C", "F", "2")){
				ans = 2;
			} else if(FigureCIsDiffAandB("C", "F", "3")){
				ans = 3;
			} else if(FigureCIsDiffAandB("C", "F", "4")){
				ans = 4;
			} else if(FigureCIsDiffAandB("C", "F", "5")){
				ans = 5;
			} else if(FigureCIsDiffAandB("C", "F", "6")){
				ans = 6;
			} else if(FigureCIsDiffAandB("C", "F", "7")){
				ans = 7;
			} else if(FigureCIsDiffAandB("C", "F", "8")){
				ans = 8;
			}  
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ans;
	}

	//to find the answer when C = A-B
	private int FigureCIsAMinusB(){
		int ans = -1;
		try {
			if(FigureCIsDiffAandB("G", "H", "8")){
				ans = 8;
			} else if(FigureCIsDiffAandB("G", "H", "2")){
				ans = 2;
			} else if(FigureCIsDiffAandB("G", "H", "3")){
				ans = 3;
			} else if(FigureCIsDiffAandB("G", "H", "4")){
				ans = 4;
			} else if(FigureCIsDiffAandB("G", "H", "5")){
				ans = 5;
			} else if(FigureCIsDiffAandB("G", "H", "6")){
				ans = 6;
			} else if(FigureCIsDiffAandB("G", "H", "7")){
				ans = 7;
			} else if(FigureCIsDiffAandB("G", "H", "1")){
				ans = 1;
			}  
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ans;
	}
	//to check if figure C is sum of figure A and B
	private boolean FigureCIsDiffAandB(String figA, String figB, String figC) {
		float diffFig1Fig2 = 0.0f;
		Boolean bool = null;
		diffFig1Fig2 = TwoFiguresDiffSimilarity(figA, figB, figC);
		if (diffFig1Fig2 >= SIMILARITY_DIFF_LOW1) {
			bool = true;
		}  else {
			bool = false;
		}
		return bool;
	}

	//to find the answer when figure G is the sum of figure A and D
	private int FigureAnsIsSumCandF(){
		int ans = -1;
		try {
			if(FigureCIsSumOfAandB("C", "F", "1")){
				ans = 1;
			} else if(FigureCIsSumOfAandB("C", "F", "2")){
				ans = 2;
			} else if(FigureCIsSumOfAandB("C", "F", "3")){
				ans = 3;
			} else if(FigureCIsSumOfAandB("C", "F", "4")){
				ans = 4;
			} else if(FigureCIsSumOfAandB("C", "F", "5")){
				ans = 5;
			} else if(FigureCIsSumOfAandB("C", "F", "6")){
				ans = 6;
			} else if(FigureCIsSumOfAandB("C", "F", "7")){
				ans = 7;
			} else if(FigureCIsSumOfAandB("C", "F", "8")){
				ans = 8;
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ans;
	}

	//to find the answer when figure C is the sum of figure A and B
	private int FigureAnsIsSumGandH(){
		int ans = -1;
		try {
			if(FigureCIsSumOfAandB("G", "H", "1")){
				ans = 1;
			} else if(FigureCIsSumOfAandB("G", "H", "2")){
				ans = 2;
			} else if(FigureCIsSumOfAandB("G", "H", "3")){
				ans = 3;
			} else if(FigureCIsSumOfAandB("G", "H", "4")){
				ans = 4;
			} else if(FigureCIsSumOfAandB("G", "H", "5")){
				ans = 5;
			} else if(FigureCIsSumOfAandB("G", "H", "6")){
				ans = 6;
			} else if(FigureCIsSumOfAandB("G", "H", "7")){
				ans = 7;
			} else if(FigureCIsSumOfAandB("G", "H", "8")){
				ans = 8;
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ans;
	}

	//to check if figure C is sum of figure A and B
	private boolean FigureCIsSumOfAandB(String figA, String figB, String figC) {
		float sumFig1Fig2 = 0.0f;
		Boolean bool = null;
		sumFig1Fig2 = TwoFiguresSumSimilarity(figA, figB, figC);
		if (sumFig1Fig2 >= SIMILARITY_SUM1) {
			bool = true;
		}  else {
			bool = false;
		}
		return bool;
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

	/*
	 * Check if the diagonals are the same (figure A and E)
	 * Case D-02, D-03, D-11
	 */

	private boolean DiagonalsEqual(String fig1, String fig2) {
		float similar12 = 0.0f;
		Boolean bool = null;
		similar12 = SimilarityFig1Fig2(fig1, fig2);
		if (similar12 >= SIMILARITY_LOW1 && similar12 <= SIMILARITY_HIGH1) {
			bool = true;
		} 
		//		else if (similar12 >= SIMILARITY_LOW1 && similar12 <= SIMILARITY_DIAGONAL_HIGH2) {
		//			bool = true;
		//		} 
		//		else if (similar12 >= SIMILARITY_DIAGONAL_LOW2 && similar12 <= SIMILARITY_DIAGONAL_HIGH2) {
		//			bool = true;
		//		}
		else {
			bool = false;
		}
		return bool;
	}

	/*
	 * Returns answer for cases when figure A, D and G are same
	 * D-01
	 */
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

	/*
	 * Returns answer for cases when figure A, D and G are same
	 * Analogous to D-01, lower similarity case
	 */
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

	/*
	 * Returns answer if figure A, B and C are same
	 * D-06, lower similarity case
	 */
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

	/*
	 * Returns answer if figure A, B and C are same
	 * D-01
	 */
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

	/* 
	 * returns answer if the three figures are similar
	 * takes the figure name like "A"
	 */
	private boolean CheckABCEquality1(String fig1, String fig2, String fig3) {
		float similar12 = 0.0f;
		float similar13 = 0.0f;
		Boolean bool = null;

		try {
			similar12 = SimilarityFig1Fig2(fig1, fig2);
			similar13 = SimilarityFig1Fig2(fig1, fig3);
			if (similar12 >= SIMILARITY_LOW1 && similar12 <= SIMILARITY_HIGH1 && 
					similar13 >= SIMILARITY_LOW1 && similar13 <= SIMILARITY_HIGH1) {
				bool = true;
			} else {
				bool = false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bool;
	}

	/*
	 * returns true if the three figures are similar
	 * takes the figure name as "A"
	 * D-05
	 */
	private boolean CheckABEquality2(String fig1, String fig2) {
		float similar12 = 0.0f;
		Boolean bool = null;

		try {
			similar12 = SimilarityFig1Fig2(fig1, fig2);
			if (similar12 >= SIMILARITY_LOW2 && similar12 <= SIMILARITY_HIGH2) {
				bool = true;
			} else {
				bool = false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bool;
	}


	/*
	 * returns true if the three figures are similar
	 * takes the figure name with "A"
	 * B-06
	 */
	private boolean CheckABCEquality3(String fig1, String fig2, String fig3) {
		float similar12 = 0.0f;
		float similar13 = 0.0f;
		Boolean bool = null;

		try {
			similar12 = SimilarityFig1Fig2(fig1, fig2);
			similar13 = SimilarityFig1Fig2(fig1, fig3);
			if (similar12 >= SIMILARITY_LOW3 && similar12 <= SIMILARITY_HIGH3 && 
					similar13 >= SIMILARITY_LOW3 && similar13 <= SIMILARITY_HIGH3) {
				bool = true;
			} else {
				bool = false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bool;
	}

	/*
	 * finds similarity between two figures A and B
	 */
	private float SimilarityFig1Fig2(String fig1, String fig2) {

		int pixelDiffFig1Fig2 = 0;
		float similarFig1Fig2 = 0.0f;
		int totalPixelsInFig = 0;
		try {
			int [][] pixelsFig1 = CreateProblemArray(fig1);
			int [][] pixelsFig2 = CreateProblemArray(fig2);
			for(int i = 0 ; i < pixelsFig1.length ; i++) {
				for(int j = 0 ; j < pixelsFig1.length ; j++) {
					pixelDiffFig1Fig2 += (pixelsFig1[i][j] - pixelsFig2[i][j]);
				}
			}

			totalPixelsInFig = pixelsFig1.length*pixelsFig1.length;

			if (pixelDiffFig1Fig2 < totalPixelsInFig) {
				similarFig1Fig2 = (float) (1.0 - ((float) pixelDiffFig1Fig2/(float) (totalPixelsInFig)));
			} else {
				similarFig1Fig2 = (float) (1.0 - ((float) (totalPixelsInFig)/(float) pixelDiffFig1Fig2));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return similarFig1Fig2;
	}

	@SuppressWarnings("unused")
	private int PixelDifference(String figureA, String figureB) {
		int pixelDiffFig1Fig2 = 0;

		try {
			int [][] pixelsFig1 = CreateProblemArray(figureA);
			int [][] pixelsFig2 = CreateProblemArray(figureB);
			for(int i = 0 ; i < pixelsFig1.length ; i++) {
				for(int j = 0 ; j < pixelsFig1.length ; j++) {
					pixelDiffFig1Fig2 += (pixelsFig1[i][j] - pixelsFig2[i][j]);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return pixelDiffFig1Fig2;
	}

	
	/*
	 * this method returns similarity between difference between fig A and B (A-B)
	 * and C. 
	 * E-05
	 */
	
	private float TwoFiguresDiffSimilarity2(String figA, String figB, String figC) {
		float similarity = 0.0f;
		int pixelDiffFig1Fig2 = 0;
		int totalPixelsInFig = 0;

		try {
			int [][] pixelsFigA = CreateProblemArray(figA);
			int [][] pixelsFigB = CreateProblemArray(figB);
			int [][] pixelsFigC = CreateProblemArray(figC);
			int [][] pixelsADiffB = pixelsFigA;

			for(int i = 0 ; i < pixelsFigA.length ; i++) {
				for(int j = 0 ; j < pixelsFigB.length ; j++) {
					if (pixelsFigA[i][j] == 1 && pixelsFigB[i][j] == 1) {
						pixelsADiffB[i][j] = 0;
					}
				}
			}
			
			for(int i = 0 ; i < pixelsFigC.length ; i++) {
				for(int j = 0 ; j < pixelsADiffB.length ; j++) {
					pixelDiffFig1Fig2 += (pixelsFigC[i][j] - pixelsADiffB[i][j]);
				}
			}

			totalPixelsInFig = pixelsFigA.length*pixelsFigA.length;

			if (pixelDiffFig1Fig2 < totalPixelsInFig) {
				similarity = (float) (1.0 - ((float) pixelDiffFig1Fig2/(float) (totalPixelsInFig)));
			} else {
				similarity = (float) (1.0 - ((float) (totalPixelsInFig)/(float) pixelDiffFig1Fig2));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return similarity;
	}
	
	
	//this method helps to check if the figC is diff of figure A and B
	@SuppressWarnings("unused")
	private float TwoFiguresDiffSimilarity(String figA, String figB, String figC) {
		float similarBlackPixelFig1Fig2 = 0.0f;
		int totalBlackPixelsFigA = 0;
		int totalBlackPixelsFigB = 0;
		int totalBlackPixelsFigC = 0;
		int totalBlackPixelsDiffAB = 0; //sum of pixels in figure A and B

		try {
			totalBlackPixelsFigA = SumBlackPixels(figA);
			totalBlackPixelsFigB = SumBlackPixels(figB);
			totalBlackPixelsFigC = SumBlackPixels(figC);

			totalBlackPixelsDiffAB = totalBlackPixelsFigA - totalBlackPixelsFigB;

			if (totalBlackPixelsDiffAB <= totalBlackPixelsFigC) {
				similarBlackPixelFig1Fig2 = ((float) totalBlackPixelsDiffAB) / ((float) totalBlackPixelsFigC);
			} else {
				similarBlackPixelFig1Fig2 = ((float) totalBlackPixelsFigC) / ((float) totalBlackPixelsDiffAB);
			}	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return similarBlackPixelFig1Fig2;
	}

	//this method helps to check if the figC is the sum of figA and figB
	private float TwoFiguresSumSimilarity(String figA, String figB, String figC) {
		float similarBlackPixelFig1Fig2 = 0.0f;
		int totalBlackPixelsFigC = 0;
		int totalBlackPixelsSumAB = 0; //sum of pixels in figure A and B

		try {
			totalBlackPixelsFigC = SumBlackPixels(figC);

			int [][] pixelsFigA = CreateProblemArray(figA);
			int [][] pixelsFigB = CreateProblemArray(figB);

			for(int i = 0 ; i < pixelsFigA.length ; i++) {
				for(int j = 0 ; j < pixelsFigB.length ; j++) {
					if (pixelsFigA[i][j] != pixelsFigB[i][j]) {
						totalBlackPixelsSumAB += 1;
					} else if (pixelsFigA[i][j] == 1 && pixelsFigB[i][j] == 1) {
						totalBlackPixelsSumAB += 1;
					}
				}
			}

			if (totalBlackPixelsSumAB <= totalBlackPixelsFigC) {
				similarBlackPixelFig1Fig2 = ((float) totalBlackPixelsSumAB) / ((float) totalBlackPixelsFigC);
			} else {
				similarBlackPixelFig1Fig2 = ((float) totalBlackPixelsFigC) / ((float) totalBlackPixelsSumAB);
			}	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return similarBlackPixelFig1Fig2;
	}

	//sums the black pixels in a figure
	private int SumBlackPixels(String figureName) {

		int totalBlackPixels = 0;

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
	private int[][] CreateProblemArray(String figureName) {

		BufferedImage figureImage = null;
		try {
			RavensFigure figure = problem.getFigures().get(figureName);
			figureImage = ImageIO.read(new File(figure.getVisual()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		//http://stackoverflow.com/questions/15002706/convert-an-image-to-a-2d-array-and-then-get-the-image-back-in-java

		Raster raster = figureImage.getData();
		int width = raster.getWidth();
		int height = raster.getHeight();
		int [][] pixels = new int[width][height];

		//0xFFFFFFFF is white. Store white as 0 and black as 1
		//http://stackoverflow.com/questions/5925426/java-how-would-i-load-a-black-and-white-image-into-binary
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