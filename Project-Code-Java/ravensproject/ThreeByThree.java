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
	private int tempAns;

	//hash map for Ravens Figure
	private HashMap<String, RavensFigure> RF;
	//Ravens figure
	private RavensFigure RFE, RFF, RFH, RF1, RF2, RF3, RF4, RF5, RF6, RF7, RF8;

	//	@SuppressWarnings("rawtypes")
	//	private List ravensObject = new ArrayList();
	//	
	//	@SuppressWarnings("rawtypes")
	//	private List objectAttribute = new ArrayList();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Map> ravensObjects = new ArrayList(); //array to list the objects from a Ravens object

	//Hash map of Ravens object
	@SuppressWarnings("unused")
	private HashMap<String, RavensObject> ROE, ROF, ROH, RO1, RO2, RO3, RO4, RO5, RO6, RO7, RO8;
	//Ravens object in each figure
	@SuppressWarnings("unused")
	private RavensObject ROf, ROg, ROh, ROi, ROm, ROn, ROo;
	//attributes for Ravens object
	@SuppressWarnings("unused")
	private HashMap<String, String> ROfa, ROga, ROha, ROia, ROma, ROna, ROoa;
	
	@SuppressWarnings("unused")
	private String shapeC, fillC, shapeB, fillB; //for the object c
	@SuppressWarnings("unused")
	private String shapeAns, fillAns, shapeAlignmentAns; //potential result

	@SuppressWarnings("unused")
	private String angleA, angleB, angleC, angleAns, shapeAlignmentA, shapeAlignmentC, shapeAlignmentB; //angle of C for rotation cases

	public ThreeByThree (RavensProblem problem) {
		this.problem = problem;
	}

	public int ThreeByThreeRPM() {
		RFigures(problem);
		//System.out.println("Size of RFE/no of objects: " + RFE.getObjects().size());

		RObjects();

		try {
			if (RFE.getObjects().size() == RFF.getObjects().size()) {
				tempAns = equalObjects();
			} else if (RFE.getObjects().size() != RFF.getObjects().size()) {
				tempAns = increaseObject();
			}
		} catch(NullPointerException e){
			System.out.println("Exception thrown  :" + e);
		}

		return tempAns;


	}

	private int equalObjects() {
		int ans = 0;
		try {
			for (int i = 0; i < ravensObjects.size(); i++) {
				if (RFH.getObjects().size() == ravensObjects.get(i).size()) {
					ans = i+1;
					return ans;
				}
			}
		} catch(NullPointerException e){
			System.out.println("Exception thrown  :" + e);
		}
		return -1;
	}
	
	private int increaseObject(){
		int ans = 0;
		int sizeE = RFE.getObjects().size();
		int sizeF = RFF.getObjects().size();
		int sizeH = RFH.getObjects().size();
		int increment = sizeF - sizeE;
		int anssize = 0;
		if (increment == 1) {
			anssize = sizeH + increment;
		} else if (increment == 2) {
			anssize = sizeH + increment +1;
		}
		try {
			for (int i = 0; i < ravensObjects.size(); i++) {
				if (ravensObjects.get(i).size() == anssize){
					ans = i+1;
					return ans;
				}
			}
		} catch(NullPointerException e){
			System.out.println("Exception thrown  :" + e);
		}
		return -1;
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
