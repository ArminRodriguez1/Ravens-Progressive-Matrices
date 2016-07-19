package ravensproject;

import java.io.File;
import javax.imageio.*;
import java.awt.image.*;

public class VisualApproach {

	private RavensProblem problem;

	private int tempAns;

	//create array of answer image names
	private Integer [] answersChoices = new Integer [] {1,2,3,4,5,6,7,8};

	//these two floating points for horizontal, vertical and diagonal similarities
	private final float HIGH_SIMILARITY_LOWER_LIMIT = 0.998f;
	private final float HIGH_SIMILARITY_UPPER_LIMIT = 1.006f;
	
	//for horizontal similarity for cropped images, D-04
	private final float HIGH_SIMILARITY_CROPPED_LOWER_LIMIT = 0.98f;
	private final float HIGH_SIMILARITY_CROPPED_UPPER_LIMIT = 1.0147f;

	//these two floating points are for horizontal and vertical similarities when the similarity is lower
	//D-05
	private final float LOW_SIMILARITY_LOWER_LIMIT = 0.970f;
	private final float LOW_SIMILARITY_UPPER_LIMIT = 0.974f;

	//similarity for C=B-A or G=D-A
	private final float SIMILARITY_DIFF_LOW1 = 0.99f;
	private final float SIMILARITY_DIFF_HIGH1 = 1.0f;
	
	//where to start and end the crop
	private final int CROP_START = 50;
	private final int CROP_END = 130;
	
	//this is to check if third image is sum of two images
	private float SIMILARITY_SUM1 = 0.98f;

	public VisualApproach(RavensProblem problem){
		this.problem = problem;
	}

	public int VisualApproachResults() {
		tempAns = -1;
		//figure C is sum of figure A and B
		System.out.println("Sim A and B: " + SimilarityCropImageFig1Fig2("A", "B", CROP_START, CROP_END));
		System.out.println("Sim A and C: " + SimilarityCropImageFig1Fig2("A", "C", CROP_START, CROP_END));
		System.out.println("***********");
		System.out.println("Sim G and H: " + SimilarityCropImageFig1Fig2("G", "H", CROP_START, CROP_END));
		System.out.println("Sim G and 1: " + SimilarityCropImageFig1Fig2("G", "1", CROP_START, CROP_END));
		System.out.println("Sim H and 1: " + SimilarityCropImageFig1Fig2("H", "1", CROP_START, CROP_END));
		System.out.println("***********");
		try {
			if (CheckABCEquality1("A", "B", "C")) { //Case D-01
				System.out.println("Solved using A, B and C are equal logic, high match");
				tempAns = FigureABCEqualHighSimilarityAns("G", "H");
			} 
			else if (CheckABCEquality1("A", "D", "G")) { //Analogous to D-01 vertically
				System.out.println("Solved using A, D and G are equal logic, high match");
				tempAns = FigureABCEqualHighSimilarityAns("C", "F");
			} 
			else if (CheckEqualityCropped("A", "B", "C")){ //check equal ABC when cropped
				System.out.println("Solved using cropped figures are same across ABC");
				tempAns = CheckEqualityCroppedAns("G", "H");
			}
			else if (CheckDiagonalEquality("A", "E")) {// diagonals are equal
				System.out.println("Solved using diagonals are equal logic");
				tempAns = DiagonalEqualityAns("A");
			} 
			else if (CheckDiagonalEqualityCropped("A", "E")) {//equal diagonals when cropped
				System.out.println("Solved using cropped diagonals A and E are equal");
				tempAns = CheckDiagonalEqualityCroppedAns("E");
			}
			else if (CheckACEquality2("A", "G")) { //D-05
				System.out.println("Solved using A and G are equal logic");
				tempAns = FigureADGEqual2("C");
			}
			else if (CheckACEquality2("A", "C")) { //Analogous D-05
				System.out.println("Solved using A and C are equal logic");
				tempAns = FigureADGEqual2("G");
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tempAns;
	}
	
	/*
	 * Returns answer for cases when figure A, D and G are same, D-05
	 */
	private int FigureADGEqual2(String figA){
		int ans = -1;
		try {
			for (int i = 0; i < 8; i++){
				if(CheckACEquality2(figA, answersChoices[i].toString())){
					ans = answersChoices[i];
				}
			}
		}  catch (Exception ex) {
			ex.printStackTrace();
		}
		return ans;
	}
	
	/*
	 *When A and G have lower similarity, D-05 
	 */
	private boolean CheckACEquality2(String fig1, String fig2) {
		float similar12 = 0.0f;
		Boolean bool = null;

		try {
			similar12 = SimilarityAllPixelsFig1Fig2(fig1, fig2);
			if (similar12 >= LOW_SIMILARITY_LOWER_LIMIT && similar12 <= LOW_SIMILARITY_UPPER_LIMIT) {
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
	 * Returns answer if figure diagonals are same
	 * D-02, D-03
	 */
	private int DiagonalEqualityAns(String figA){
		int ans = -1;
		try {
			for (int i = 0; i < 8; i++){
				if(CheckDiagonalEquality(figA, answersChoices[i].toString())){
					ans = answersChoices[i];
				}
			}
		}  catch (Exception ex) {
			ex.printStackTrace();
		}
		return ans;
	}

	/*
	 * Check if diagonals are equal or not, D-02, D0-3
	 */
	private boolean CheckDiagonalEquality(String fig1, String fig2) {
		float similar12 = 0.0f;
		Boolean bool = null;

		try {
			similar12 = SimilarityAllPixelsFig1Fig2(fig1, fig2);
			if (similar12 >= HIGH_SIMILARITY_LOWER_LIMIT && similar12 <= HIGH_SIMILARITY_UPPER_LIMIT) {
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
	 * Returns answer if figure A, B and C are same or figure A, D and G are same
	 * D-01
	 */
	private int FigureABCEqualHighSimilarityAns(String figA, String figB){
		int ans = -1;
		try {
			for (int i = 0; i < 8; i++){
				if(CheckABCEquality1(figA, figB, answersChoices[i].toString())){
					ans = answersChoices[i];
				}
			}
		}  catch (Exception ex) {
			ex.printStackTrace();
		}
		return ans;
	}
	
	/*
	 * Check if three figures are equal or not, D-01
	 */
	private boolean CheckABCEquality1(String fig1, String fig2, String fig3) {
		float similar12 = 0.0f;
		float similar13 = 0.0f;
		Boolean bool = null;

		try {
			similar12 = SimilarityAllPixelsFig1Fig2(fig1, fig3);
			similar13 = SimilarityAllPixelsFig1Fig2(fig2, fig3);
			if (similar12 >= HIGH_SIMILARITY_LOWER_LIMIT && similar12 <= HIGH_SIMILARITY_UPPER_LIMIT &&
					similar13 >= HIGH_SIMILARITY_LOWER_LIMIT && similar13 <= HIGH_SIMILARITY_UPPER_LIMIT) {
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
	 * returns answer when cropped ABC are equal
	 */

	private int CheckEqualityCroppedAns(String figA, String figB){
		int ans = -1;
		try {
			for (int i = 0; i < 8; i++){
				if(CheckEqualityCropped(figA, figB, answersChoices[i].toString())){
					return answersChoices[i];
				}
			}
		}  catch (Exception ex) {
			ex.printStackTrace();
		}
		return ans;
	}
	
	
	/*
	 * compares cropped image ABC
	 */
	
	private boolean CheckEqualityCropped(String fig1, String fig2, String fig3) {
		float similar12 = 0.0f;
		float similar13 = 0.0f;
		Boolean bool = null;

		try {
			similar12 = SimilarityCropImageFig1Fig2(fig1, fig3, CROP_START, CROP_END);
			similar13 = SimilarityCropImageFig1Fig2(fig2, fig3, CROP_START, CROP_END);
			if (similar12 >= HIGH_SIMILARITY_CROPPED_LOWER_LIMIT && similar12 <= HIGH_SIMILARITY_CROPPED_UPPER_LIMIT &&
					similar13 >= HIGH_SIMILARITY_CROPPED_LOWER_LIMIT && similar13 <= HIGH_SIMILARITY_CROPPED_UPPER_LIMIT) {
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
	 * Returns answer when cropped diagonals are equal, D-06
	 */
	
	private int CheckDiagonalEqualityCroppedAns(String figA){
		int ans = -1;
		try {
			for (int i = 0; i < 8; i++){
				if(CheckDiagonalEqualityCropped(figA, answersChoices[i].toString())){
					return answersChoices[i];
				}
			}
		}  catch (Exception ex) {
			ex.printStackTrace();
		}
		return ans;
	}
	
	/*
	 * Check if two cropped figures are equal or not diagonally, D-06
	 */
	private boolean CheckDiagonalEqualityCropped(String fig1, String fig2) {
		float similar12 = 0.0f;
		Boolean bool = null;

		try {
			similar12 = SimilarityCropImageFig1Fig2(fig1, fig2, CROP_START, CROP_END);
			if (similar12 >= HIGH_SIMILARITY_LOWER_LIMIT && similar12 <= HIGH_SIMILARITY_UPPER_LIMIT) {
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
	 * finds similarity between two figures A and B when cropped
	 * D-04, D-06
	 */
	private float SimilarityCropImageFig1Fig2(String fig1, String fig2, int start, int end) {

		int pixelDiffFig1Fig2 = 0;
		float similarFig1Fig2 = 0.0f;
		int totalPixelsInFig = 0;
		try {
			int [][] pixelsFig1 = CreateProblemArray(fig1);
			int [][] pixelsFig2 = CreateProblemArray(fig2);
			for(int i = start ; i < end ; i++) {
				for(int j = start ; j < end ; j++) {
					pixelDiffFig1Fig2 += (pixelsFig1[i][j] - pixelsFig2[i][j]);
				}
			}

			totalPixelsInFig = (end-start)*(end-start);

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
	


	/*
	 * finds similarity between two figures A and B taking all pixels
	 */
	private float SimilarityAllPixelsFig1Fig2(String fig1, String fig2) {

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

	//sums the black pixels in a figure
	@SuppressWarnings("unused")
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
