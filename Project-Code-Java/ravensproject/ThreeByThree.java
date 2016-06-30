package ravensproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class solves for the RPM that have only one object
 * in a figure. This class only deals with 2 by 2 RPMs.
 */


public class ThreeByThree {

	private RavensProblem problem;

	//Ravens figure
	private RavensFigure RFE, RFF, RFH, RF1, RF2, RF3, RF4, RF5, RF6, RF7, RF8;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Map> ravensObjects = new ArrayList(); //array to list the objects from a Ravens object

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<RavensObject> objectsE = new ArrayList();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<RavensObject> objectsF = new ArrayList();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<RavensObject> objectsH = new ArrayList();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<RavensObject> objects7 = new ArrayList();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<RavensObject> objects8 = new ArrayList();

	//Hash map of Ravens object
	@SuppressWarnings("unused")
	private HashMap<String, RavensObject> ROE, ROF, ROH, RO1, RO2, RO3, RO4, RO5, RO6, RO7, RO8;

	private int sizeE, sizeF, sizeH; //number of objects in figure E, F and H
	
	private String getSize = "size";
	private String getShape = "shape";
	private String getAngle = "angle";
	private String getHeight = "height";
	private String getAbove = "above";
	private String keyLeft = "left-of";
	private String valueVeryLarge = "very large";
	private String valueHuge = "huge";
	

	public ThreeByThree (RavensProblem problem) {
		this.problem = problem;
	}

	public int ThreeByThreeRPM() {
		RFigures(problem);
		//System.out.println("Size of RFE/no of objects: " + RFE.getObjects().size());
		int tempAns = -1;
		RObjects();
		figureSize();
		objectMethod();
		try {
			if (sizeE == 2 && sizeF == 2) { //when the total number of objects are equal
				tempAns = equalObjectsSize();
			} else if (sizeF > sizeE) {//when the total number of objects are not equal
				tempAns = increaseObject();
			} else if (sizeF < sizeE) {
				tempAns = decreaseObject();
			} if (sizeE == 1){
				tempAns = oneObject();
			}
		} catch(Exception e){
			System.out.println("Exception thrown  :" + e);
		}
		return tempAns;
	}


	private int oneObject(){ //when E has only one object
		int ans = -1;

		try {
			if (objectsE.get(0).getAttributes().get(getSize).equals(objectsF.get(0).getAttributes().get(getHeight))){ //C-06
				ans = oneObject1();																						//C-06
			} else if (objectsE.get(0).getAttributes().get(getSize).equals(objectsF.get(0).getAttributes().get(getSize))) { //C-07
				ans = oneObject2(); 																							  //C-07			
			} else {
				ans = -1;
			}
		} catch(Exception e){
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}

	private int oneObject1() { //C-06
		int ans = -1;
		try {
			if (objectsH.get(0).getAttributes().get(getHeight).equals(objects1.get(0).getAttributes().get(getSize))){
				ans = 1;
			} else if (objectsH.get(0).getAttributes().get(getHeight).equals(objects2.get(0).getAttributes().get(getSize))) {
				ans = 2;
			} else if (objectsH.get(0).getAttributes().get(getHeight).equals(objects3.get(0).getAttributes().get(getSize))) {
				ans = 3;
			} else if (objectsH.get(0).getAttributes().get(getHeight).equals(objects4.get(0).getAttributes().get(getSize))) {
				ans = 4;
			} else if (objectsH.get(0).getAttributes().get(getHeight).equals(objects5.get(0).getAttributes().get(getSize))) {
				ans = 5;
			} else if (objectsH.get(0).getAttributes().get(getHeight).equals(objects6.get(0).getAttributes().get(getSize))) {
				ans = 6;
			} else if (objectsH.get(0).getAttributes().get(getHeight).equals(objects7.get(0).getAttributes().get(getSize))) {
				ans = 7;
			} else if (objectsH.get(0).getAttributes().get(getHeight).equals(objects8.get(0).getAttributes().get(getSize))) {
				ans = 8;
			}
		} catch (Exception e){
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}

	private int oneObject2() { //C-07
		int ans = -1;
		try {
			if (objects1.size() == 2 && objectsH.get(0).getAttributes().get(getShape).equals(objects1.get(0).getAttributes().get(getShape)) && 
					objectsH.get(0).getAttributes().containsKey(getAbove) && objects1.get(0).getAttributes().containsKey(getAbove)) {
				ans = 1;
			} else if (objects2.size() == 2 && objectsH.get(0).getAttributes().get(getShape).equals(objects2.get(0).getAttributes().get(getShape)) && 
					objectsH.get(0).getAttributes().containsKey(getAbove) && objects2.get(0).getAttributes().containsKey(getAbove)) {
				ans = 2;
			} else if (objects3.size() == 2 && objectsH.get(0).getAttributes().get(getShape).equals(objects3.get(0).getAttributes().get(getShape)) && 
					objectsH.get(0).getAttributes().containsKey(getAbove) && objects3.get(0).getAttributes().containsKey(getAbove)) {
				ans = 3;
			} else if (objects4.size() == 2 && objectsH.get(0).getAttributes().get(getShape).equals(objects4.get(0).getAttributes().get(getShape)) && 
					objectsH.get(0).getAttributes().containsKey(getAbove) && objects4.get(0).getAttributes().containsKey(getAbove)) {
				ans = 4;
			} else if (objects5.size() == 2 && objectsH.get(0).getAttributes().get(getShape).equals(objects5.get(0).getAttributes().get(getShape)) && 
					objectsH.get(0).getAttributes().containsKey(getAbove) && objects5.get(0).getAttributes().containsKey(getAbove)) {
				ans = 5;
			} else if (objects6.size() == 2 && objectsH.get(0).getAttributes().get(getShape).equals(objects6.get(0).getAttributes().get(getShape)) && 
					objectsH.get(0).getAttributes().containsKey(getAbove) && objects6.get(0).getAttributes().containsKey(getAbove)) {
			} else if (objects7.size() == 2 && objectsH.get(0).getAttributes().get(getShape).equals(objects7.get(0).getAttributes().get(getShape)) && 
					objectsH.get(0).getAttributes().containsKey(getAbove) && objects7.get(0).getAttributes().containsKey(getAbove)) {
				ans = 7;
			} else if (objects8.size() == 2 && objectsH.get(0).getAttributes().get(getShape).equals(objects8.get(0).getAttributes().get(getShape)) && 
					objectsH.get(0).getAttributes().containsKey(getAbove) && objects8.get(0).getAttributes().containsKey(getAbove)) {
				ans = 8;
			}
		} catch (Exception e){
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}

	private int increaseObject(){ //when objects are increased, doesn't account for size or fill, C-03 C-04, C-05
		int ans = -1;
		int increment = sizeF - sizeE;
		int anssize = 0; //potential answer size

		if (increment == 1 && sizeE == 3) { //C-11
			ans = increaseObject2();
			return ans;
		}
		else if (increment == 1) {
			anssize = sizeH + increment; //increase of 1
			ans = increaseObject1(anssize);
		} else if (increment == 2) {
			anssize = sizeH + increment + 1; //increase of 2
			ans = increaseObject1(anssize);
		}
		return ans;
	}
	
	private int increaseObject1(int a) { //C-03 C-04, C-05
		int ans = -1;
		try {
			for (int i = 0; i < ravensObjects.size(); i++) {
				if (ravensObjects.get(i).size() == a) {
					ans = i+1;
					return ans;
				}
			}
		} catch(Exception e){
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}
	
	private int increaseObject2(){ //C-11
		int ans = -1;
		
		try {
			if (sizeH == 2 && objectsH.get(0).getAttributes().containsKey(keyLeft )) {
				if (objects1.size() == sizeE && objects1.get(0).getAttributes().containsKey(keyLeft ) && objects1.get(0).getAttributes().containsKey(keyLeft )) {
					ans = 1;
				} else if (objects2.size() == sizeE && objects2.get(0).getAttributes().containsKey(keyLeft ) && objects2.get(0).getAttributes().containsKey(keyLeft )) {
					ans = 2;
				} else if (objects3.size() == sizeE && objects3.get(0).getAttributes().containsKey(keyLeft ) && objects3.get(0).getAttributes().containsKey(keyLeft )) {
					ans = 3;
				} else if (objects4.size() == sizeE && objects4.get(0).getAttributes().containsKey(keyLeft ) && objects4.get(0).getAttributes().containsKey(keyLeft )) {
					ans = 4;
				} else if (objects5.size() == sizeE && objects5.get(0).getAttributes().containsKey(keyLeft ) && objects5.get(0).getAttributes().containsKey(keyLeft )) {
					ans = 5;
				} else if (objects6.size() == sizeE && objects6.get(0).getAttributes().containsKey(keyLeft ) && objects6.get(0).getAttributes().containsKey(keyLeft )) {
					ans = 6;
				} else if (objects7.size() == sizeE && objects7.get(0).getAttributes().containsKey(keyLeft ) && objects7.get(0).getAttributes().containsKey(keyLeft )) {
					ans = 7;
				} else if (objects8.size() == sizeE && objects8.get(0).getAttributes().containsKey(keyLeft ) && objects8.get(0).getAttributes().containsKey(keyLeft )) {
					ans = 8;
				} else {
					ans = -1;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception thrown: " + e);
		}

		return ans;
	}

	private int decreaseObject(){ //when objects are decreased, doesn't account for size or fill
		int ans = -1;
		int decrease = sizeE - sizeF;
		int anssize = 0; //potential answer size
		
		if (decrease == 1) {
			anssize = sizeH - decrease; //decrease by 1
		} else if (decrease == 2) {
			anssize = sizeH - decrease - 1; //decrease by 3
		}
		try {
			for (int i = 0; i < ravensObjects.size(); i++) {
				if (ravensObjects.get(i).size() == anssize){
					ans = i+1;
					return ans;
				}
			}
		} catch(Exception e){
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}

	private int equalObjectsSize() { //equal number of objects from left to right but account for the change in the size
		int ans = -1;

		try {
			if (equalEF()){
				ans = EandFequal();
			} else {
				ans = FisLargerE(); //Case C-02
			}
			return ans;
		} catch(Exception e){
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}

	//to check if E and F are same like in problem C-01

	private boolean equalEF(){ //C-01
		
		if (sizeE == 2 && sizeF == 2) {
			if ((objectsE.get(0).getAttributes().get(getSize).equals(objectsF.get(0).getAttributes().get(getSize))) && 
					(objectsE.get(0).getAttributes().get(getShape).equals(objectsF.get(0).getAttributes().get(getShape))) &&
					((objectsE.get(1).getAttributes().get(getSize).equals(objectsF.get(1).getAttributes().get(getSize))) && 
							(objectsE.get(1).getAttributes().get(getShape).equals(objectsF.get(1).getAttributes().get(getShape))))) {
				return true;
			}
		}
		return false;
	}

	private int FisLargerE() { //for case C-02
		int ans = -1;
		
		if (sizeH == 2) {
			try {				
				//object count starts from inside for problem and the object count starts from outside for answer choices
				ans = FisLargerE1();  //C-02
			} catch(Exception e){
				System.out.println("Exception thrown: " + e);
			}
		}
		return ans;
	}

	private int FisLargerE1() { //C-02
		int ans = -1;
		
		
		
		try {
			if (objects1.size() == sizeH) { 
				if (objectsH.get(1).getAttributes().containsValue(valueVeryLarge) && objects1.get(0).getAttributes().containsValue(valueHuge) && 
						(objectsH.get(1).getAttributes().get(getShape).equals(objects1.get(0).getAttributes().get(getShape))) &&
						((objectsH.get(0).getAttributes().get(getSize).equals(objects1.get(1).getAttributes().get(getSize))) && 
								(objectsH.get(0).getAttributes().get(getShape).equals(objects1.get(1).getAttributes().get(getShape))))) {
					ans = 1;
				}
			} if (objects2.size() == sizeH) {
				if (objectsH.get(1).getAttributes().containsValue(valueVeryLarge) && objects2.get(0).getAttributes().containsValue(valueHuge) &&  
						(objectsH.get(1).getAttributes().get(getShape).equals(objects2.get(0).getAttributes().get(getShape))) &&
						((objectsH.get(0).getAttributes().get(getSize).equals(objects2.get(1).getAttributes().get(getSize))) && 
								(objectsH.get(0).getAttributes().get(getShape).equals(objects2.get(1).getAttributes().get(getShape))))) {
					ans = 2;
				}
			} if (objects3.size() == sizeH) {
				if (objectsH.get(1).getAttributes().containsValue(valueVeryLarge) && objects3.get(0).getAttributes().containsValue(valueHuge) && 
						(objectsH.get(1).getAttributes().get(getShape).equals(objects3.get(0).getAttributes().get(getShape))) &&
						((objectsH.get(0).getAttributes().get(getSize).equals(objects3.get(1).getAttributes().get(getSize))) && 
								(objectsH.get(0).getAttributes().get(getShape).equals(objects3.get(1).getAttributes().get(getShape))))) {
					ans = 3;
				}
			} if (objects4.size() == sizeH) {
				if (objectsH.get(1).getAttributes().containsValue(valueVeryLarge) && objects4.get(0).getAttributes().containsValue(valueHuge) && 
						(objectsH.get(1).getAttributes().get(getShape).equals(objects4.get(0).getAttributes().get(getShape))) &&
						((objectsH.get(0).getAttributes().get(getSize).equals(objects4.get(1).getAttributes().get(getSize))) && 
								(objectsH.get(0).getAttributes().get(getShape).equals(objects4.get(1).getAttributes().get(getShape))))) {
					ans = 4;
				}
			} if (objects5.size() == sizeH) {
				if (objectsH.get(1).getAttributes().containsValue(valueVeryLarge) && objects5.get(0).getAttributes().containsValue(valueHuge) && 
						(objectsH.get(1).getAttributes().get(getShape).equals(objects5.get(0).getAttributes().get(getShape))) &&
						((objectsH.get(0).getAttributes().get(getSize).equals(objects5.get(1).getAttributes().get(getSize))) && 
								(objectsH.get(0).getAttributes().get(getShape).equals(objects5.get(1).getAttributes().get(getShape))))) {
					ans = 5;
				}
			} if (objects6.size() == sizeH) {
				if (objectsH.get(1).getAttributes().containsValue(valueVeryLarge) && objects6.get(0).getAttributes().containsValue(valueHuge) && 
						(objectsH.get(1).getAttributes().get(getShape).equals(objects6.get(0).getAttributes().get(getShape))) &&
						((objectsH.get(0).getAttributes().get(getSize).equals(objects6.get(1).getAttributes().get(getSize))) && 
								(objectsH.get(0).getAttributes().get(getShape).equals(objects6.get(1).getAttributes().get(getShape))))) {
					ans = 6;
				}
			} if (objects7.size() == sizeH) {
				if (objectsH.get(1).getAttributes().containsValue(valueVeryLarge) && objects7.get(0).getAttributes().containsValue(valueHuge) &&  
						(objectsH.get(1).getAttributes().get(getShape).equals(objects7.get(0).getAttributes().get(getShape))) &&
						((objectsH.get(0).getAttributes().get(getSize).equals(objects7.get(1).getAttributes().get(getSize))) && 
								(objectsH.get(0).getAttributes().get(getShape).equals(objects7.get(1).getAttributes().get(getShape))))) {
					ans = 7;
				}
			} if (objects8.size() == sizeH) {
				if (objectsH.get(1).getAttributes().containsValue(valueVeryLarge) && objects8.get(0).getAttributes().containsValue(valueHuge) && 
						(objectsH.get(1).getAttributes().get(getShape).equals(objects8.get(0).getAttributes().get(getShape))) &&
						((objectsH.get(0).getAttributes().get(getSize).equals(objects8.get(1).getAttributes().get(getSize))) && 
								(objectsH.get(0).getAttributes().get(getShape).equals(objects8.get(1).getAttributes().get(getShape))))) {
					ans = 8;
				}
			} else {
				ans = -1;
			}
		} catch (Exception e){
			System.out.println("Exception thrown: " + e);
		}
		return ans;
	}
	
	
	private int EandFequal(){ 
		int ans = -1;
		
		try {
			if (sizeH == 3) { //for case C-01
				ans = EandFequal1();
			} else if (sizeH == 2) { //for C-09
				ans = EandFEqual2(); 
			}
		} catch (Exception e){
			System.out.println("Exception thrown: " + e);
		}
		return ans;
	}

	private int EandFEqual2() { //for case C-09
		int ans = -1;
		
		String objectE1angle = null; 
		String objectE2angle = null; 
		String objectF1angle = null;
		String objectF2angle = null;
		
		String objectH1angle = null;
		String objectH2angle = null;
		
		//get angles from objects in E
		try {
			objectE1angle = objectsE.get(0).getAttributes().get(getAngle);
			objectE2angle = objectsE.get(1).getAttributes().get(getAngle);
		} catch (Exception e){
			System.out.println("Exception thrown: " + e);
		}
		
		//get angles from objects in F
		try {
			objectF1angle = objectsF.get(0).getAttributes().get(getAngle);
			objectF2angle = objectsF.get(1).getAttributes().get(getAngle);
		} catch (Exception e){
			System.out.println("Exception thrown: " + e);
		}
				
		//get angles from objects in H
		try {
			objectH1angle = objectsH.get(0).getAttributes().get(getAngle);
			objectH2angle = objectsH.get(1).getAttributes().get(getAngle);
		} catch (Exception e){
			System.out.println("Exception thrown: " + e);
		}
		
		try {
			if (objectE1angle.equals(objectF2angle) && (objectE2angle.equals(objectF1angle))){ //when the remainder is the same and equal to 0
				if (objectH1angle.equals(objects1.get(0).getAttributes().get(getAngle)) && objectH2angle.equals(objects1.get(1).getAttributes().get(getAngle))){
					ans = 1;
				} else if (objectH1angle.equals(objects2.get(0).getAttributes().get(getAngle)) && objectH2angle.equals(objects2.get(1).getAttributes().get(getAngle))){
					ans = 2;
				} else if (objectH1angle.equals(objects3.get(0).getAttributes().get(getAngle)) && objectH2angle.equals(objects3.get(1).getAttributes().get(getAngle))) {
					ans = 3;
				} else if (objectH1angle.equals(objects4.get(0).getAttributes().get(getAngle)) && objectH2angle.equals(objects4.get(1).getAttributes().get(getAngle))) {
					ans = 4;
				} else if (objectH1angle.equals(objects5.get(0).getAttributes().get(getAngle)) && objectH2angle.equals(objects5.get(1).getAttributes().get(getAngle))) {
					ans = 5;
				} else if (objectH1angle.equals(objects6.get(0).getAttributes().get(getAngle)) && objectH2angle.equals(objects6.get(1).getAttributes().get(getAngle))) {
					ans = 6;
				} else if (objectH1angle.equals(objects7.get(0).getAttributes().get(getAngle)) && objectH2angle.equals(objects7.get(1).getAttributes().get(getAngle))) {
					ans = 7;
				} else if (objectH1angle.equals(objects8.get(0).getAttributes().get(getAngle)) && objectH2angle.equals(objects8.get(1).getAttributes().get(getAngle))) {
					ans = 8;
				} else {
					ans = -1;
				}
			}
		} catch (Exception e){
			System.out.println("Exception thrown: " + e);
		}
		return ans;
	}
	
	private int EandFequal1() { //for case C-01
		int ans = -1;
		
		try {
			if ((objectsH.get(0).getAttributes().get(getSize).equals(objects1.get(0).getAttributes().get(getSize))) && 
					(objectsH.get(0).getAttributes().get(getShape).equals(objects1.get(0).getAttributes().get(getShape))) &&
					((objectsH.get(1).getAttributes().get(getSize).equals(objects1.get(1).getAttributes().get(getSize))) && 
							(objectsH.get(1).getAttributes().get(getShape).equals(objects1.get(1).getAttributes().get(getShape)))) && 
					((objectsH.get(2).getAttributes().get(getSize).equals(objects1.get(2).getAttributes().get(getSize))) && 
							(objectsH.get(2).getAttributes().get(getShape).equals(objects1.get(2).getAttributes().get(getShape))))) {
				ans = 1;
			} else if ((objectsH.get(0).getAttributes().get(getSize).equals(objects2.get(0).getAttributes().get(getSize))) && 
					(objectsH.get(0).getAttributes().get(getShape).equals(objects2.get(0).getAttributes().get(getShape))) &&
					((objectsH.get(1).getAttributes().get(getSize).equals(objects2.get(1).getAttributes().get(getSize))) && 
							(objectsH.get(1).getAttributes().get(getShape).equals(objects2.get(1).getAttributes().get(getShape)))) && 
					((objectsH.get(2).getAttributes().get(getSize).equals(objects2.get(2).getAttributes().get(getSize))) && 
							(objectsH.get(2).getAttributes().get(getShape).equals(objects2.get(2).getAttributes().get(getShape))))) {
				ans = 2;
			} else if ((objectsH.get(0).getAttributes().get(getSize).equals(objects3.get(0).getAttributes().get(getSize))) && 
					(objectsH.get(0).getAttributes().get(getShape).equals(objects3.get(0).getAttributes().get(getShape))) &&
					((objectsH.get(1).getAttributes().get(getSize).equals(objects3.get(1).getAttributes().get(getSize))) && 
							(objectsH.get(1).getAttributes().get(getShape).equals(objects3.get(1).getAttributes().get(getShape)))) && 
					((objectsH.get(2).getAttributes().get(getSize).equals(objects3.get(2).getAttributes().get(getSize))) && 
							(objectsH.get(2).getAttributes().get(getShape).equals(objects3.get(2).getAttributes().get(getShape))))) {
				ans = 3;
			} else if ((objectsH.get(0).getAttributes().get(getSize).equals(objects4.get(0).getAttributes().get(getSize))) && 
					(objectsH.get(0).getAttributes().get(getShape).equals(objects4.get(0).getAttributes().get(getShape))) &&
					((objectsH.get(1).getAttributes().get(getSize).equals(objects4.get(1).getAttributes().get(getSize))) && 
							(objectsH.get(1).getAttributes().get(getShape).equals(objects4.get(1).getAttributes().get(getShape)))) && 
					((objectsH.get(2).getAttributes().get(getSize).equals(objects4.get(2).getAttributes().get(getSize))) && 
							(objectsH.get(2).getAttributes().get(getShape).equals(objects4.get(2).getAttributes().get(getShape))))) {
				ans = 4;
			} else if ((objectsH.get(0).getAttributes().get(getSize).equals(objects5.get(0).getAttributes().get(getSize))) && 
					(objectsH.get(0).getAttributes().get(getShape).equals(objects5.get(0).getAttributes().get(getShape))) &&
					((objectsH.get(1).getAttributes().get(getSize).equals(objects5.get(1).getAttributes().get(getSize))) && 
							(objectsH.get(1).getAttributes().get(getShape).equals(objects5.get(1).getAttributes().get(getShape)))) && 
					((objectsH.get(2).getAttributes().get(getSize).equals(objects5.get(2).getAttributes().get(getSize))) && 
							(objectsH.get(2).getAttributes().get(getShape).equals(objects5.get(2).getAttributes().get(getShape))))) {
				ans = 5;
			} else if ((objectsH.get(0).getAttributes().get(getSize).equals(objects6.get(0).getAttributes().get(getSize))) && 
					(objectsH.get(0).getAttributes().get(getShape).equals(objects6.get(0).getAttributes().get(getShape))) &&
					((objectsH.get(1).getAttributes().get(getSize).equals(objects6.get(1).getAttributes().get(getSize))) && 
							(objectsH.get(1).getAttributes().get(getShape).equals(objects6.get(1).getAttributes().get(getShape)))) && 
					((objectsH.get(2).getAttributes().get(getSize).equals(objects6.get(2).getAttributes().get(getSize))) && 
							(objectsH.get(2).getAttributes().get(getShape).equals(objects6.get(2).getAttributes().get(getShape))))) {
				ans = 6;
			} else if ((objectsH.get(0).getAttributes().get(getSize).equals(objects7.get(0).getAttributes().get(getSize))) && 
					(objectsH.get(0).getAttributes().get(getShape).equals(objects7.get(0).getAttributes().get(getShape))) &&
					((objectsH.get(1).getAttributes().get(getSize).equals(objects7.get(1).getAttributes().get(getSize))) && 
							(objectsH.get(1).getAttributes().get(getShape).equals(objects7.get(1).getAttributes().get(getShape)))) && 
					((objectsH.get(2).getAttributes().get(getSize).equals(objects7.get(2).getAttributes().get(getSize))) && 
							(objectsH.get(2).getAttributes().get(getShape).equals(objects7.get(2).getAttributes().get(getShape))))) {
				ans = 7;
			} else if ((objectsH.get(0).getAttributes().get(getSize).equals(objects8.get(0).getAttributes().get(getSize))) && 
					(objectsH.get(0).getAttributes().get(getShape).equals(objects8.get(0).getAttributes().get(getShape))) &&
					((objectsH.get(1).getAttributes().get(getSize).equals(objects8.get(1).getAttributes().get(getSize))) && 
							(objectsH.get(1).getAttributes().get(getShape).equals(objects8.get(1).getAttributes().get(getShape)))) && 
					((objectsH.get(2).getAttributes().get(getSize).equals(objects8.get(2).getAttributes().get(getSize))) && 
							(objectsH.get(2).getAttributes().get(getShape).equals(objects8.get(2).getAttributes().get(getShape))))) {
				ans = 8;
			} else {
				ans = -1;
			}
		} catch (Exception e){
			System.out.println("Exception thrown: " +e);
		}
		return ans;
	}

	//create objects for equal method
	private void objectMethod() {
		//figureSize();
		RavensObject thisObjectE = null;
		RavensObject thisObjectF = null;
		RavensObject thisObjectH = null;
		RavensObject thisObject1 = null;
		RavensObject thisObject2 = null;
		RavensObject thisObject3 = null;
		RavensObject thisObject4 = null;
		RavensObject thisObject5 = null;
		RavensObject thisObject6 = null;
		RavensObject thisObject7 = null;
		RavensObject thisObject8 = null;

		try {
			for (String objectNameE : RFE.getObjects().keySet()) { //objectName1 is for E
				thisObjectE = RFE.getObjects().get(objectNameE);
				objectsE.add(thisObjectE);
			}
			for (String objectNameF : RFF.getObjects().keySet()) { //objectName2 is for F
				thisObjectF = RFF.getObjects().get(objectNameF);
				objectsF.add(thisObjectF);
			}
			for (String objectNameH : RFH.getObjects().keySet()) {//for objectName3 is for H
				thisObjectH = RFH.getObjects().get(objectNameH);
				objectsH.add(thisObjectH);
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
			for (String objectName7 : RF7.getObjects().keySet()) {
				thisObject7 = RF7.getObjects().get(objectName7);
				objects7.add(thisObject7);
			}
			for (String objectName8 : RF8.getObjects().keySet()) {
				thisObject8 = RF8.getObjects().get(objectName8);
				objects8.add(thisObject8);
			}
		} catch(Exception e){
			System.out.println("Exception thrown  :" + e);
		}
	}

	//total number of objects in E, F and H
	private void figureSize(){
		sizeE = RFE.getObjects().size();
		sizeF = RFF.getObjects().size();
		sizeH = RFH.getObjects().size();
	}

	//create ravens figure for question figure and answer choices
	private void RFigures(RavensProblem problem) { //returns the object attributes in a list

		//these are the Ravens Figure from the problem
		RFE = problem.getFigures().get("E");
		RFF = problem.getFigures().get("F");
		RFH = problem.getFigures().get("H");

		//these are the Ravens Figure from the solution
		RF1 = problem.getFigures().get("1");
		RF2 = problem.getFigures().get("2");
		RF3 = problem.getFigures().get("3");
		RF4 = problem.getFigures().get("4");
		RF5 = problem.getFigures().get("5");
		RF6 = problem.getFigures().get("6");
		RF7 = problem.getFigures().get("7");
		RF8 = problem.getFigures().get("8");		
	}

	//create ravens objects for each figure
	private void RObjects() {

		//get the Ravens Objects from the problem figures
		ROE = RFE.getObjects();
		ROF = RFF.getObjects();
		ROH = RFH.getObjects();

		//get the Ravens Objects from the problem solutions
		RO1 = RF1.getObjects();
		RO2 = RF2.getObjects();
		RO3 = RF3.getObjects();
		RO4 = RF4.getObjects();
		RO5 = RF5.getObjects();
		RO6 = RF6.getObjects();
		RO7 = RF7.getObjects();
		RO8 = RF8.getObjects();

		//add answer ravens objects to a list
		ravensObjects.add(RO1);
		ravensObjects.add(RO2);
		ravensObjects.add(RO3);
		ravensObjects.add(RO4);
		ravensObjects.add(RO5);
		ravensObjects.add(RO6);
		ravensObjects.add(RO7);
		ravensObjects.add(RO8);
	}
}
