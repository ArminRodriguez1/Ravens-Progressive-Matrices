package ravensproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class solves for the RPM that have only one object
 * in a figure. This class only deals with 2 by 2 RPMs.
 */


public class TwoByTwoOneObject {

	private RavensProblem problem;
	private int tempAns;

	//hash map for Ravens Figure
	private HashMap<String, RavensFigure> RF;
	//Ravens figure
	private RavensFigure RFA, RFB, RFC, RF1, RF2, RF3, RF4, RF5, RF6;
	//Hash map of Ravens object
	private HashMap<String, RavensObject> ROA, ROB, ROC, RO1, RO2, RO3, RO4, RO5, RO6;
	//Ravens object
	private RavensObject ROa, ROb,ROc, ROd, ROe, ROf, ROg, ROh, ROi;
	//attributes for Ravens object
	private HashMap<String, String> ROaa, ROba, ROca, ROda, ROea, ROfa, ROga, ROha, ROia;
	//map of the Ravens object and its attributes in a list
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> resultAttribute = new ArrayList(); //array list for the result object attributes

	private String shapeC, fillC, shapeB, fillB; //for the object c
	private String shapeAns, fillAns, shapeAlignmentAns; //potential result

	@SuppressWarnings("unused")
	private String angleA, angleB, angleC, angleAns, shapeAlignmentA, shapeAlignmentC, shapeAlignmentB; //angle of C for rotation cases
	private int angleP_int, angleQ_int, diffAB, diffAC; //angle of C for rotation cases

	public TwoByTwoOneObject(RavensProblem problem) {
		this.problem = problem;
	}


	public int oneObjectRPM() {
		resultAttribute = getObjectAttributes(problem);
		shapeChecker();

		try {
			if (ROaa.equals(ROba)) {
				tempAns = figureAEqualsB();
			} else if (ROaa.equals(ROca)) {
				tempAns = figureAEqualsC();
			} else if (ROaa.containsKey("alignment") && ROaa.get("fill").equals(ROca.get("fill"))) {
				tempAns = differentFigReflection();
			} else if (ROaa.containsKey("alignment") && !ROaa.get("fill").equals(ROca.get("fill"))) {
				tempAns = differentFigReflectionDiffFill();
			} else if ((ROaa.get("shape")).equals(ROba.get("shape")) && !ROaa.containsKey("angle")) { // for same shape from A to B but different fill
				//System.out.println("Shape of ROaa " + ROaa.get("shape") + " and shape of ROba " + ROba.get("shape"));
				tempAns = figureAEqualsBDiffFill();
			} else if ((ROaa.get("shape")).equals(ROca.get("shape")) && !ROaa.containsKey("angle")) { // for same shape from A to C but different fill
				tempAns = figureAEqualsCDiffFill();
			} else if (ROaa.containsKey("angle")){ //case of reflection
				if (!(ROaa.get("fill").equals(ROca.get("fill"))) || !(ROaa.get("fill").equals(ROba.get("fill")))) { //case of reflection of same figures with different fill
					tempAns = figureReflectionDiffFill();
				} else { //case of reflection of same figures with same fill
					tempAns = figureReflection();
				}
			} 
			else {
				tempAns = -1;
			}	
		} catch(NullPointerException e){
			System.out.println("Exception thrown  :" + e);
		}
		return tempAns;
	}

	private int differentFigReflection(){ //reflection along x axis but different object on A and B (B-05) same fill on A and C
		int ans = 0;
		if ((ROaa.get("alignment").equals("bottom-right") && (ROca.get("alignment").equals("top-right")))) {
			shapeAlignmentB = ROba.get("alignment");
			if (shapeAlignmentB.equals("bottom-left")) {
				try {
					for (int i = 0; i < resultAttribute.size(); i++) {
						ans+=1;
						shapeAlignmentAns = (String) resultAttribute.get(i).get("alignment");
						fillAns = (String) resultAttribute.get(i).get("fill");
						shapeAns = (String) resultAttribute.get(i).get("shape");

						if(shapeB.equals(shapeAns) && shapeAlignmentAns.equals("top-left") && fillB.equals(fillAns)) {
							return ans;
						}
					}
				} catch(NullPointerException e) {
					System.out.println("Exception thrown  :" + e);
				}
			}
		}
		return -1;
	}
	private int differentFigReflectionDiffFill(){ //reflection along x axis but different object on A and B (B-05) different fill on A and C
		int ans = 0;
		if ((ROaa.get("alignment").equals("bottom-right") && (ROca.get("alignment").equals("top-right")))) {
			shapeAlignmentB = ROba.get("alignment");
			if (shapeAlignmentB.equals("bottom-left")) {
				try {
					for (int i = 0; i < resultAttribute.size(); i++) {
						ans+=1;
						shapeAlignmentAns = (String) resultAttribute.get(i).get("alignment");
						fillAns = (String) resultAttribute.get(i).get("fill");
						shapeAns = (String) resultAttribute.get(i).get("shape");

						if(shapeB.equals(shapeAns) && shapeAlignmentAns.equals("top-left") && !fillB.equals(fillAns)) {
							return ans;
						}
					}
				} catch(NullPointerException e) {
					System.out.println("Exception thrown  :" + e);
				}
			}
		}
		return -1;
	}

	private int figureAEqualsB(){ //if figure A equals object B, then object D is equal to object C, no angle
		int ans = 0;
		try {
			for(int i = 0; i < resultAttribute.size(); i++) {
				ans+=1;
				if(ROca.equals(resultAttribute.get(i))) {
					return ans;
				}
			}
		} catch(NullPointerException e){
			System.out.println("Exception thrown  :" + e);
		}
		return -1;
	}

	private int figureAEqualsC(){ //if object c is equal to object a, then object d is equal to object b, no angle
		int ans = 0;
		try {
			for(int i = 0; i < resultAttribute.size(); i++) {
				ans+=1;
				if(ROba.equals(resultAttribute.get(i))) {
					return ans;
				}
			}
		} catch(NullPointerException e){
			System.out.println("Exception thrown  :" + e);
		}
		return -1;
	}

	private int figureAEqualsBDiffFill() { //different fill on object a and b, no angle
		int ans = 0;

		try {
			for (int i = 0; i < resultAttribute.size(); i++) {
				ans+=1;
				shapeAns = (String) resultAttribute.get(i).get("shape");
				fillAns = (String) resultAttribute.get(i).get("fill");

				if(shapeC.equals(shapeAns) && !fillC.equals(fillAns)) {
					return ans;
				}
			}
		} catch(NullPointerException e) {
			System.out.println("Exception thrown  :" + e);
		}
		return -1;
	}

	private int figureAEqualsCDiffFill() { //different fill on a and c, no angle
		int ans = 0;

		try {
			for (int i = 0; i < resultAttribute.size(); i++) {
				ans+=1;
				shapeAns = (String) resultAttribute.get(i).get("shape");
				fillAns = (String) resultAttribute.get(i).get("fill");

				if(shapeB.equals(shapeAns) && !fillB.equals(fillAns)) {
					return ans;
				}
			}
		} catch(NullPointerException e) {
			System.out.println("Exception thrown  :" + e);
		}
		return -1;
	}

	private int figureReflection() { //A reflects to B or A reflects to C with same fill, reflection means angle
		int ans = 0;

		if (diffAB == 180) { //reflection across y axis, B-04
			try {
				for (int i = 0; i < resultAttribute.size(); i++) {
					ans+=1;

					shapeAns = (String) resultAttribute.get(i).get("shape");
					fillAns = (String) resultAttribute.get(i).get("fill");
					angleAns = (String) resultAttribute.get(i).get("angle");

					int angleC_diff_angle_Ans = sumAngle(angleC, angleAns) % 360;

					if(shapeC.equals(shapeAns) && fillC.equals(fillAns) && angleC_diff_angle_Ans == diffAB) {
						return ans;
					}
				}
			} catch(NullPointerException e) {
				System.out.println("Exception thrown  :" + e);
			}
		} else if (diffAC == 0) { //reflection across x axis, B-04
			try {
				for (int i = 0; i < resultAttribute.size(); i++) {
					ans+=1;

					shapeAns = (String) resultAttribute.get(i).get("shape");
					fillAns = (String) resultAttribute.get(i).get("fill");
					angleAns = (String) resultAttribute.get(i).get("angle");

					int angleB_diff_angle_Ans = sumAngle(angleB, angleAns) % 360;

					if(shapeC.equals(shapeAns) && fillC.equals(fillAns) && angleB_diff_angle_Ans == diffAB) {
						return ans;
					}
				}
			} catch(NullPointerException e) {
				System.out.println("Exception thrown  :" + e);
			}
		}
		return -1;
	}

	private int figureReflectionDiffFill() { //reflection with either fill or no fill, reflection means angle
		int ans = 0;

		if (!(ROaa.get("fill").equals(ROca.get("fill"))) && (diffAB == 180)) { //reflection across y axis
			try {
				for (int i = 0; i < resultAttribute.size(); i++) {
					ans+=1;

					shapeAns = (String) resultAttribute.get(i).get("shape");
					fillAns = (String) resultAttribute.get(i).get("fill");
					angleAns = (String) resultAttribute.get(i).get("angle");

					int angleC_diff_angle_Ans = sumAngle(angleC, angleAns) % 360;

					if(shapeC.equals(shapeAns) && fillC.equals(fillAns) && angleC_diff_angle_Ans == diffAB) {
						return ans;
					}
				}
			} catch(NullPointerException e) {
				System.out.println("Exception thrown  :" + e);
			}
		} else if (!(ROaa.get("fill").equals(ROba.get("fill"))) && (diffAC == 0)) { //reflection across x axis
			try {
				for (int i = 0; i < resultAttribute.size(); i++) {
					ans+=1;

					shapeAns = (String) resultAttribute.get(i).get("shape");
					fillAns = (String) resultAttribute.get(i).get("fill");
					angleAns = (String) resultAttribute.get(i).get("angle");

					int angleB_diff_angle_Ans = sumAngle(angleB, angleAns) % 360;

					if(shapeC.equals(shapeAns) && fillC.equals(fillAns) && (angleB_diff_angle_Ans == diffAB)) {
						return ans;
					}
				}
			} catch(NullPointerException e) {
				System.out.println("Exception thrown  :" + e);
			}
		}
		return -1;
	}

	private void shapeChecker() { //assign the values to various objects
		if (ROaa.containsKey("angle") && ROba.containsKey("angle") && ROca.containsKey("angle")){
			angleA = ROaa.get("angle");
			angleB = ROba.get("angle");
			angleC = ROca.get("angle");
		}
		diffAB = sumAngle(angleA, angleB) % 360;
		diffAC = sumAngle(angleA, angleC) % 360;

		if (ROba!=null){
			shapeB = ROba.get("shape"); //find the shape and look for the same shape
			fillB = ROba.get("fill"); //find if the shape is filled or not and look for the opposite fill
		}

		if (ROca!=null){
			shapeC = ROca.get("shape"); //find the shape and look for the same shape
			fillC = ROca.get("fill"); //find if the shape is filled or not and look for the opposite fill
		}
	}

	private int sumAngle(String p, String q) { //we get angle as string
		if (p!=null && p!= null) {
			angleP_int = Integer.parseInt(p);
			angleQ_int = Integer.parseInt(q);
		}
		int sum = Math.abs(angleP_int + angleQ_int);
		return sum;
	}
	
	@SuppressWarnings("rawtypes")
	private List<Map> getObjectAttributes(RavensProblem problem) { //returns the object attributes in a list
		RF = problem.getFigures();

		//these are the Ravens Figure from the problem
		RFA = RF.get("A");
		RFB = RF.get("B");
		RFC = RF.get("C");

		//these are the Ravens Figure from the solution
		RF1 = RF.get("1");
		RF2 = RF.get("2");
		RF3 = RF.get("3");
		RF4 = RF.get("4");
		RF5 = RF.get("5");
		RF6 = RF.get("6");

		//get the Ravens Objects from the problem figures
		ROA = RFA.getObjects();
		ROB = RFB.getObjects();
		ROC = RFC.getObjects();

		//get the Ravens Objects from the problem solutions
		RO1 = RF1.getObjects();
		RO2 = RF2.getObjects();
		RO3 = RF3.getObjects();
		RO4 = RF4.getObjects();
		RO5 = RF5.getObjects();
		RO6 = RF6.getObjects();


		if (ROA!=null) {
			ROa = ROA.get("a");
		}
		if (ROB!=null){
			ROb = ROB.get("b");
		}
		if (ROC!=null){
			ROc = ROC.get("c");
		}

		if (RO1!=null){
			ROd = RO1.get("d");
		}
		if (RO2!=null) {
			ROe = RO2.get("e");
		}
		if (RO3!=null){
			ROf = RO3.get("f");
		}
		if (RO4!=null){
			ROg = RO4.get("g");
		}
		if (RO5!=null){
			ROh = RO5.get("h");
		}
		if (RO6!=null){
			ROi = RO6.get("i");
		}

		//get attributes for the Ravens Objects

		if (ROa!=null) {
			ROaa = ROa.getAttributes();
		}
		if (ROb!=null) {
			ROba = ROb.getAttributes();
		}
		if (ROc!=null) {
			ROca = ROc.getAttributes();
		}
		if (ROd!=null){
			ROda = ROd.getAttributes();
		}
		if (ROe!=null) {
			ROea = ROe.getAttributes();
		}
		if (ROf!=null) {
			ROfa = ROf.getAttributes();
		}
		if (ROg!=null) {
			ROga = ROg.getAttributes();
		}
		if (ROh!=null) {
			ROha = ROh.getAttributes();
		}
		if (ROi!=null) {
			ROia = ROi.getAttributes();
		}

		resultAttribute.add(ROda);
		resultAttribute.add(ROea);
		resultAttribute.add(ROfa);
		resultAttribute.add(ROga);
		resultAttribute.add(ROha);
		resultAttribute.add(ROia);

		return resultAttribute;
	}
}