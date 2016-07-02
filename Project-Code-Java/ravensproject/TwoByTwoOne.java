package ravensproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class solves for the RPM that have only one object
 * in a figure. This class only deals with 2 by 2 RPMs.
 */


public class TwoByTwoOne {

	private RavensProblem problem;

	private RavensFigure RFA, RFB, RFC, RF1, RF2, RF3, RF4, RF5, RF6; 	//Ravens figure

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Map> ravensObjects = new ArrayList(); //array to list the objects from a Ravens object

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<RavensObject> objectsA = new ArrayList();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<RavensObject> objectsB = new ArrayList();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<RavensObject> objectsC = new ArrayList();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<RavensObject> objects1 = new ArrayList();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<RavensObject> objects2 = new ArrayList();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<RavensObject> objects3 = new ArrayList();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<RavensObject> objects4 = new ArrayList();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<RavensObject> objects5 = new ArrayList();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<RavensObject> objects6 = new ArrayList();

	//Hash map of Ravens object
	@SuppressWarnings("unused")
	private HashMap<String, RavensObject> ROA, ROB, ROC, RO1, RO2, RO3, RO4, RO5, RO6;

	private int sizeA, sizeB, sizeC; //number of objects in figure E, F and H
	private int tempAns;
	private String shapeA, shapeB, shapeC, fillA, fillB, fillC;

	private String getShape = "shape";
	private String getFill = "fill";
	private String getAngle = "angle";
	private String getAlignment = "alignment";

	private String angleA, angleB, angleC, angle1, angle2, angle3, angle4, angle5, angle6;
	private int angleP_int, angleQ_int, diffAB, diffAC; //angle of C for rotation cases

	public TwoByTwoOne(RavensProblem problem) {
		this.problem = problem;
	}

	public int oneObjectRPM() {
		ravensFigures(problem);
		ravensObjects();
		figureSize();
		objectMethod();
		shapeAndFillChecker();
		getAngles();
		tempAns = -1;

		try {
			if (objectsA.get(0).getAttributes().equals(objectsB.get(0).getAttributes())) { //Figure A equals to B, B-01
				System.out.println("enters here 1");
				tempAns = figureAEqualsB();
			} else if (objectsA.get(0).getAttributes().equals(objectsC.get(0).getAttributes())) { //Figure A equals to C, B-03, B-08
				System.out.println("enters here 2");
				tempAns = figureAEqualsC();;
			} else if (shapeA.equals(shapeB) && !fillA.equals(fillB) && !objectsA.get(0).getAttributes().containsKey("angle")) {// Figure A and B are same shape and differ on fill, B-09
				System.out.println("enters here 3");
				tempAns = figureAEqualsBDiffFill();
			} else if (shapeA.equals(shapeC) && !fillA.equals(fillC) && !objectsA.get(0).getAttributes().containsKey("angle")) {// Figure A and C are same shape and differ on fill, B-09 analogous ones
				System.out.println("enters here 4");
				tempAns = figureAEqualsCDiffFill();
			} else if (diffAB == 180) { //B-04, angle diff between A and B is reflection in y axis
				if (fillA.equals(fillB)) { //for same fill between A and B, C and answer need to have the same fill
					System.out.println("enters here");
					tempAns = AB_YaxisSameFill();
				} else if (!fillA.equals(fillB)){ //for different fill between A and B
					tempAns = AB_YaxisDiffFill();
				}
			} else if (diffAC == 0 || diffAC == 90) { //reflection in x axis
				if (objectsC.get(0).getAttributes().get(getAlignment).equals("top-right")) { //B-05
					System.out.println("enter here 5");
					tempAns = differentFigReflection();
				} else if (fillA.equals(fillC)) {
					tempAns = AC_XaxisSameFill();
				} else if (!fillA.equals(fillC)) {
					tempAns = AC_XaxisDiffFill();
				}
			}
		} catch (NullPointerException e){
			System.out.println("Exception thrown  :" + e);
		}
		return tempAns;
	}

	private int AC_XaxisDiffFill(){ //reflection across x axis, B-07, shape, size same and fill different
		int ans = -1;
		try {
			if (shapeB.equals(objects1.get(0).getAttributes().get(getShape)) &&
					!fillB.equals(objects1.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleB, angle1) == diffAC) {
				ans = 1;
			} else if (shapeB.equals(objects2.get(0).getAttributes().get(getShape)) &&
					!fillB.equals(objects2.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleB, angle2) == diffAC) {
				ans = 2;
			} else if (shapeB.equals(objects3.get(0).getAttributes().get(getShape)) &&
					!fillB.equals(objects3.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleB, angle3) == diffAC) {
				ans = 3;
			} else if (shapeB.equals(objects4.get(0).getAttributes().get(getShape)) &&
					!fillB.equals(objects4.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleB, angle4) == diffAC) {
				ans = 4;
			} else if (shapeB.equals(objects5.get(0).getAttributes().get(getShape)) &&
					!fillB.equals(objects5.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleB, angle5) == diffAC) {
				ans = 5;
			} else if (shapeB.equals(objects6.get(0).getAttributes().get(getShape)) &&
					!fillB.equals(objects6.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleB, angle6) == diffAB) {
				ans = 6;
			}
		} catch (NullPointerException e) {
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}

	private int AC_XaxisSameFill(){ //reflection across x axis, B-04, shape, size and fill are the same
		int ans = -1;
		try {
			if (shapeB.equals(objects1.get(0).getAttributes().get(getShape)) &&
					fillB.equals(objects1.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleB, angle1) == diffAC) {
				ans = 1;
			} else if (shapeB.equals(objects2.get(0).getAttributes().get(getShape)) &&
					fillB.equals(objects2.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleB, angle2) == diffAC) {
				ans = 2;
			} else if (shapeB.equals(objects3.get(0).getAttributes().get(getShape)) &&
					fillB.equals(objects3.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleB, angle3) == diffAC) {
				ans = 3;
			} else if (shapeB.equals(objects4.get(0).getAttributes().get(getShape)) &&
					fillB.equals(objects4.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleB, angle4) == diffAC) {
				ans = 4;
			} else if (shapeB.equals(objects5.get(0).getAttributes().get(getShape)) &&
					fillB.equals(objects5.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleB, angle5) == diffAC) {
				ans = 5;
			} else if (shapeB.equals(objects6.get(0).getAttributes().get(getShape)) &&
					fillB.equals(objects6.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleB, angle6) == diffAC) {
				ans = 6;
			}
		} catch (NullPointerException e) {
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}

	private int AB_YaxisDiffFill(){ //reflection across y axis, B-04, B-07 shape, size and fill are the same
		int ans = -1;
		try {
			if (shapeC.equals(objects1.get(0).getAttributes().get(getShape)) &&
					!fillC.equals(objects1.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleC, angle1) == diffAB) {
				ans = 1;
			} else if (shapeC.equals(objects2.get(0).getAttributes().get(getShape)) &&
					!fillC.equals(objects2.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleC, angle2) == diffAB) {
				ans = 2;
			} else if (shapeC.equals(objects3.get(0).getAttributes().get(getShape)) &&
					!fillC.equals(objects3.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleC, angle3) == diffAB) {
				ans = 3;
			} else if (shapeC.equals(objects4.get(0).getAttributes().get(getShape)) &&
					!fillC.equals(objects4.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleC, angle4) == diffAB) {
				ans = 4;
			} else if (shapeC.equals(objects5.get(0).getAttributes().get(getShape)) &&
					!fillC.equals(objects5.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleC, angle5) == diffAB) {
				ans = 5;
			} else if (shapeC.equals(objects6.get(0).getAttributes().get(getShape)) &&
					!fillC.equals(objects6.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleC, angle6) == diffAB) {
				ans = 6;
			}
		} catch (NullPointerException e) {
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}

	private int AB_YaxisSameFill(){ //reflection across y axis, B-04, B-07 shape, size and fill are the same
		int ans = -1;
		try {
			if (shapeC.equals(objects1.get(0).getAttributes().get(getShape)) &&
					fillC.equals(objects1.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleC, angle1) == diffAB) {
				System.out.println("enter 1");
				ans = 1;
			} else if (shapeC.equals(objects2.get(0).getAttributes().get(getShape)) &&
					fillC.equals(objects2.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleC, angle2) == diffAB) {
				System.out.println("enter 2");
				ans = 2;
			} else if (shapeC.equals(objects3.get(0).getAttributes().get(getShape)) &&
					fillC.equals(objects3.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleC, angle3) == diffAB) {
				System.out.println("enter 3");
				ans = 3;
			} else if (shapeC.equals(objects4.get(0).getAttributes().get(getShape)) &&
					fillC.equals(objects4.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleC, angle4) == diffAB) {
				System.out.println("enter 4");
				ans = 4;
			} else if (shapeC.equals(objects5.get(0).getAttributes().get(getShape)) &&
					fillC.equals(objects5.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleC, angle5) == diffAB) {  
				System.out.println("enter 5");
				ans = 5;
			} else if (shapeC.equals(objects6.get(0).getAttributes().get(getShape)) &&
					fillC.equals(objects6.get(0).getAttributes().get(getFill)) &&
					sumAngle(angleC, angle6) == diffAB) {
				System.out.println("enter 6");
				ans = 6;
			}
		} catch (NullPointerException e) {
			System.out.println("Exception caught: " + e);
		}
		return ans;
	}

	private void getAngles() { //get angles from objects
		if (objectsA!=null){
			try {
				angleA = (String) objectsA.get(0).getAttributes().get(getAngle);
			} catch (NullPointerException e){
				System.out.println("Exception caught: " + e);
			}
		}
		if (objectsB!=null){
			try {
				angleB = (String) objectsB.get(0).getAttributes().get(getAngle);
			} catch (NullPointerException e){
				System.out.println("Exception caught: " + e);
			}
		}
		if (objectsC!=null){
			try {
				angleC = (String) objectsC.get(0).getAttributes().get(getAngle);
			} catch (NullPointerException e){
				System.out.println("Exception caught: " + e);
			}
		}

		if (angleA!=null && angleB!=null){
			try {
				diffAB = sumAngle(angleA, angleB); //diff between A and B, 180 equals reflection across y axis
			} catch(NullPointerException e){
				System.out.println("Exception thrown  :" + e);
			}
		}

		if (angleA!=null && angleC!=null) {
			try {
				diffAC = sumAngle(angleA, angleC); //diff between A and C, 0 equals reflection across x axis
			} catch(NullPointerException e){
				System.out.println("Exception thrown  :" + e);
			}
		}
		//angles for ans
		if (objects1!=null){
			try {
				angle1 = (String) objects1.get(0).getAttributes().get(getAngle);
			} catch (NullPointerException e){
				System.out.println("Exception caught: " + e);
			}
		}
		if (objects2!=null){
			try {
				angle2 = (String) objects2.get(0).getAttributes().get(getAngle);
			} catch (NullPointerException e){
				System.out.println("Exception caught: " + e);
			}
		}
		if (objects3!=null){
			try {
				angle3 = (String) objects3.get(0).getAttributes().get(getAngle);
			} catch (NullPointerException e){
				System.out.println("Exception caught: " + e);
			}
		}
		if (objects4!=null){
			try {
				angle4 = (String) objects4.get(0).getAttributes().get(getAngle);
			} catch (NullPointerException e){
				System.out.println("Exception caught: " + e);
			}
		}
		if (objects5!=null){
			try {
				angle5 = (String) objects5.get(0).getAttributes().get(getAngle);
			} catch (NullPointerException e){
				System.out.println("Exception caught: " + e);
			}
		}
		if (objects6!=null){
			try {
				angle6 = (String) objects6.get(0).getAttributes().get(getAngle);
			} catch (NullPointerException e){
				System.out.println("Exception caught: " + e);
			}
		}
	}

	private int sumAngle(String p, String q) { //we get angle as string
		if (p!=null && p!= null) {
			angleP_int = Integer.parseInt(p);
			angleQ_int = Integer.parseInt(q);
		}
		return Math.abs(angleP_int + angleQ_int)%360;
	}

	private int differentFigReflection() { //B-05
		int ans = -1;
		try {
			if (fillA.equals(fillC)) {
				ans = differentFigReflectionSameFill();
			} else if (!fillA.equals(fillC)) {
				ans = differentFigReflectionDiffFill();
			}
		} catch (NullPointerException e){
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}

	private int differentFigReflectionSameFill(){ //reflection along x axis but different object on A and B (B-05) same fill on A and C
		int ans = -1;
		try {
			if (shapeB.equals(objects1.get(0).getAttributes().get(getShape)) && 
					fillB.equals(objects1.get(0).getAttributes().get(getFill)) && 
					objectsB.get(0).getAttributes().get(getAlignment).equals("bottom-left") &&
					objects1.get(0).getAttributes().get(getAlignment).equals("top-left")) {
				ans = 1;
			} else if (shapeB.equals(objects2.get(0).getAttributes().get(getShape)) && 
					fillB.equals(objects2.get(0).getAttributes().get(getFill)) && 
					objectsB.get(0).getAttributes().get(getAlignment).equals("bottom-left") &&
					objects2.get(0).getAttributes().get(getAlignment).equals("top-left")) {
				ans = 2;
			} else if (shapeB.equals(objects3.get(0).getAttributes().get(getShape)) && 
					fillB.equals(objects3.get(0).getAttributes().get(getFill)) && 
					objectsB.get(0).getAttributes().get(getAlignment).equals("bottom-left") &&
					objects3.get(0).getAttributes().get(getAlignment).equals("top-left")) {
				ans = 3;
			} else if (shapeB.equals(objects4.get(0).getAttributes().get(getShape)) && 
					fillB.equals(objects4.get(0).getAttributes().get(getFill)) && 
					objectsB.get(0).getAttributes().get(getAlignment).equals("bottom-left") &&
					objects4.get(0).getAttributes().get(getAlignment).equals("top-left")) {
				ans = 4;
			} else if (shapeB.equals(objects5.get(0).getAttributes().get(getShape)) && 
					fillB.equals(objects5.get(0).getAttributes().get(getFill)) && 
					objectsB.get(0).getAttributes().get(getAlignment).equals("bottom-left") &&
					objects5.get(0).getAttributes().get(getAlignment).equals("top-left")) {
				ans = 5;
			} else if (shapeB.equals(objects6.get(0).getAttributes().get(getShape)) && 
					fillB.equals(objects6.get(0).getAttributes().get(getFill)) && 
					objectsB.get(0).getAttributes().get(getAlignment).equals("bottom-left") &&
					objects6.get(0).getAttributes().get(getAlignment).equals("top-left")) {
				ans = 6;
			}
		} catch (NullPointerException e) {
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}

	private int differentFigReflectionDiffFill(){ //reflection along x axis but different object on A and B (B-05) diff fill on A and C
		int ans = -1;
		try {
			if (shapeB.equals(objects1.get(0).getAttributes().get(getShape)) && 
					!fillB.equals(objects1.get(0).getAttributes().get(getFill)) && 
					objectsB.get(0).getAttributes().get(getAlignment).equals("bottom-left") &&
					objects1.get(0).getAttributes().get(getAlignment).equals("top-left")) {
				ans = 1;
			} else if (shapeB.equals(objects2.get(0).getAttributes().get(getShape)) && 
					!fillB.equals(objects2.get(0).getAttributes().get(getFill)) && 
					objectsB.get(0).getAttributes().get(getAlignment).equals("bottom-left") &&
					objects2.get(0).getAttributes().get(getAlignment).equals("top-left")) {
				ans = 2;
			} else if (shapeB.equals(objects3.get(0).getAttributes().get(getShape)) && 
					!fillB.equals(objects3.get(0).getAttributes().get(getFill)) && 
					objectsB.get(0).getAttributes().get(getAlignment).equals("bottom-left") &&
					objects3.get(0).getAttributes().get(getAlignment).equals("top-left")) {
				ans = 3;
			} else if (shapeB.equals(objects4.get(0).getAttributes().get(getShape)) && 
					!fillB.equals(objects4.get(0).getAttributes().get(getFill)) && 
					objectsB.get(0).getAttributes().get(getAlignment).equals("bottom-left") &&
					objects4.get(0).getAttributes().get(getAlignment).equals("top-left")) {
				ans = 4;
			} else if (shapeB.equals(objects5.get(0).getAttributes().get(getShape)) && 
					!fillB.equals(objects5.get(0).getAttributes().get(getFill)) && 
					objectsB.get(0).getAttributes().get(getAlignment).equals("bottom-left") &&
					objects5.get(0).getAttributes().get(getAlignment).equals("top-left")) {
				ans = 5;
			} else if (shapeB.equals(objects6.get(0).getAttributes().get(getShape)) && 
					!fillB.equals(objects6.get(0).getAttributes().get(getFill)) && 
					objectsB.get(0).getAttributes().get(getAlignment).equals("bottom-left") &&
					objects6.get(0).getAttributes().get(getAlignment).equals("top-left")) {
				ans = 6;
			}
		} catch (NullPointerException e) {
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}

	private int figureAEqualsCDiffFill() { // Figure A and C are same shape and differ on fill, analogous to B-09
		int ans = -1;
		try {
			if (shapeB.equals(objects1.get(0).getAttributes().get(getShape)) && 
					!fillB.equals(objects1.get(0).getAttributes().get(getFill))) {
				ans = 1;
			} else if (shapeB.equals(objects2.get(0).getAttributes().get(getShape)) && 
					!fillB.equals(objects2.get(0).getAttributes().get(getFill))) {
				ans = 2;
			} else if (shapeB.equals(objects3.get(0).getAttributes().get(getShape)) && 
					!fillB.equals(objects3.get(0).getAttributes().get(getFill))) {
				ans = 3;
			} else if (shapeB.equals(objects4.get(0).getAttributes().get(getShape)) && 
					!fillB.equals(objects4.get(0).getAttributes().get(getFill))) {
				ans = 4;
			} else if (shapeB.equals(objects5.get(0).getAttributes().get(getShape)) && 
					!fillB.equals(objects5.get(0).getAttributes().get(getFill))) {
				ans = 5;
			} else if (shapeB.equals(objects6.get(0).getAttributes().get(getShape)) && 
					!fillB.equals(objects6.get(0).getAttributes().get(getFill))) {
				ans = 6;
			}
		} catch (NullPointerException e) {
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}

	private int figureAEqualsBDiffFill() { // Figure A and B are same shape and differ on fll, B-09
		int ans = -1;
		try {
			if (shapeC.equals(objects1.get(0).getAttributes().get(getShape)) && 
					!fillC.equals(objects1.get(0).getAttributes().get(getFill))) {
				ans = 1;
			} else if (shapeC.equals(objects2.get(0).getAttributes().get(getShape)) && 
					!fillC.equals(objects2.get(0).getAttributes().get(getFill))) {
				ans = 2;
			} else if (shapeC.equals(objects3.get(0).getAttributes().get(getShape)) && 
					!fillC.equals(objects3.get(0).getAttributes().get(getFill))) {
				ans = 3;
			} else if (shapeC.equals(objects4.get(0).getAttributes().get(getShape)) && 
					!fillC.equals(objects4.get(0).getAttributes().get(getFill))) {
				ans = 4;
			} else if (shapeC.equals(objects5.get(0).getAttributes().get(getShape)) && 
					!fillC.equals(objects5.get(0).getAttributes().get(getFill))) {
				ans = 5;
			} else if (shapeC.equals(objects6.get(0).getAttributes().get(getShape)) && 
					!fillC.equals(objects6.get(0).getAttributes().get(getFill))) {
				ans = 6;
			}
		} catch (NullPointerException e) {
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}

	private void shapeAndFillChecker() { //get shape and fill from objects
		if (objectsA!=null){
			shapeA = (String) objectsA.get(0).getAttributes().get(getShape);
			fillA = (String) objectsA.get(0).getAttributes().get(getFill);
		}
		if (objectsB!=null){
			shapeB = (String) objectsB.get(0).getAttributes().get(getShape);
			fillB = (String) objectsB.get(0).getAttributes().get(getFill);
		}
		if (objectsC!=null){
			shapeC = (String) objectsC.get(0).getAttributes().get(getShape);
			fillC = (String) objectsC.get(0).getAttributes().get(getFill);
		}
	}



	private int figureAEqualsB() { //if figure A is equal to figure B, B-01
		int ans = -1;
		try {
			if (objectsC.get(0).getAttributes().equals(objects1.get(0).getAttributes())) {
				ans = 1;
			} else if (objectsC.get(0).getAttributes().equals(objects2.get(0).getAttributes())) {
				ans = 2;
			} else if (objectsC.get(0).getAttributes().equals(objects3.get(0).getAttributes())) {
				ans = 3;
			} else if (objectsC.get(0).getAttributes().equals(objects4.get(0).getAttributes())) {
				ans = 4;
			} else if (objectsC.get(0).getAttributes().equals(objects5.get(0).getAttributes())) {
				ans = 5;
			} else if (objectsC.get(0).getAttributes().equals(objects6.get(0).getAttributes())) {
				ans = 6;
			}
		} catch(NullPointerException e){
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}

	private int figureAEqualsC() { //if figure A is equal to figure C, B-03
		int ans = -1;
		try {
			if (objectsB.get(0).getAttributes().equals(objects1.get(0).getAttributes())) {
				ans = 1;
			} else if (objectsB.get(0).getAttributes().equals(objects2.get(0).getAttributes())) {
				ans = 2;
			} else if (objectsB.get(0).getAttributes().equals(objects3.get(0).getAttributes())) {
				ans = 3;
			} else if (objectsB.get(0).getAttributes().equals(objects4.get(0).getAttributes())) {
				ans = 4;
			} else if (objectsB.get(0).getAttributes().equals(objects5.get(0).getAttributes())) {
				ans = 5;
			} else if (objectsB.get(0).getAttributes().equals(objects6.get(0).getAttributes())) {
				ans = 6;
			}
		} catch(NullPointerException e){
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}



	//create objects for equal method
	private void objectMethod() {
		RavensObject thisObjectA = null;
		RavensObject thisObjectB = null;
		RavensObject thisObjectC = null;
		RavensObject thisObject1 = null;
		RavensObject thisObject2 = null;
		RavensObject thisObject3 = null;
		RavensObject thisObject4 = null;
		RavensObject thisObject5 = null;
		RavensObject thisObject6 = null;

		try {
			for (String objectNameA : RFA.getObjects().keySet()){
				thisObjectA = RFA.getObjects().get(objectNameA);
				objectsA.add(thisObjectA);
			}
			for (String objectNameB : RFB.getObjects().keySet()){
				thisObjectB = RFB.getObjects().get(objectNameB);
				objectsB.add(thisObjectB);
			}
			for (String objectNameC : RFC.getObjects().keySet()){
				thisObjectC = RFC.getObjects().get(objectNameC);
				objectsC.add(thisObjectC);
			}
			for (String objectName1 : RF1.getObjects().keySet()) {
				thisObject1 = RF1.getObjects().get(objectName1);
				objects1.add(thisObject1);
			}
			for (String objectName2 : RF2.getObjects().keySet()) {
				thisObject2 = RF2.getObjects().get(objectName2);
				objects2.add(thisObject2);
			}
			for (String objectName3 : RF3.getObjects().keySet()) {
				thisObject3 = RF3.getObjects().get(objectName3);
				objects3.add(thisObject3);
			}
			for (String objectName4 : RF4.getObjects().keySet()) {
				thisObject4 = RF4.getObjects().get(objectName4);
				objects4.add(thisObject4);
			}
			for (String objectName5 : RF5.getObjects().keySet()) {
				thisObject5 = RF5.getObjects().get(objectName5);
				objects5.add(thisObject5);
			}
			for (String objectName6 : RF6.getObjects().keySet()) {
				thisObject6 = RF6.getObjects().get(objectName6);
				objects6.add(thisObject6);
			}
		} catch(NullPointerException e){
			System.out.println("Exception thrown  :" + e);
		}
	}

	//total number of objects in A, B and C
	private void figureSize(){
		sizeA = RFA.getObjects().size();
		sizeB = RFB.getObjects().size();
		sizeC = RFC.getObjects().size();
	}

	//create ravens figure for question figure and answer choices
	private void ravensFigures(RavensProblem problem) { //returns the object attributes in a list
		//these are the Ravens Figure from the problem
		RFA = problem.getFigures().get("A");
		RFB = problem.getFigures().get("B");
		RFC = problem.getFigures().get("C");

		//these are the Ravens Figure from the solution
		RF1 = problem.getFigures().get("1");
		RF2 = problem.getFigures().get("2");
		RF3 = problem.getFigures().get("3");
		RF4 = problem.getFigures().get("4");
		RF5 = problem.getFigures().get("5");
		RF6 = problem.getFigures().get("6");
	}

	//create ravens objects for each figure
	private void ravensObjects() {
		//get the Ravens Objects from the problem figures
		ROA = RFA.getObjects();
		ROB = RFB.getObjects();
		ROB = RFC.getObjects();

		//get the Ravens Objects from the problem solutions
		RO1 = RF1.getObjects();
		RO2 = RF2.getObjects();
		RO3 = RF3.getObjects();
		RO4 = RF4.getObjects();
		RO5 = RF5.getObjects();
		RO6 = RF6.getObjects();
		//
		//		//add answer ravens objects to a list
		//		ravensObjects.add(RO1);
		//		ravensObjects.add(RO2);
		//		ravensObjects.add(RO3);
		//		ravensObjects.add(RO4);
		//		ravensObjects.add(RO5);
		//		ravensObjects.add(RO6);
	}
}