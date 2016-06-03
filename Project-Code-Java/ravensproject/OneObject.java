package ravensproject;

import java.util.HashMap;


/**
 * This class solves for the RPM that have only one object 
 * in a figure. 
 */


public class OneObject {

	private RavensProblem problem;
	private int tempAns;

	public OneObject(RavensProblem problem) {
		this.problem = problem;
	}

	public int test() {
		HashMap<String, RavensFigure> RF = problem.getFigures();

		//these are the Ravens Figure from the problem
		RavensFigure RFA = RF.get("A");
		RavensFigure RFB = RF.get("B");
		RavensFigure RFC = RF.get("C");

		//these are the Ravens Figure from the solution
		RavensFigure RF1 = RF.get("1");
		RavensFigure RF2 = RF.get("2");
		RavensFigure RF3 = RF.get("3");
		RavensFigure RF4 = RF.get("4");
		RavensFigure RF5 = RF.get("5");
		RavensFigure RF6 = RF.get("6");

		//get the Ravens Figure from the problem figures
		HashMap<String, RavensObject> ROA = RFA.getObjects();
		HashMap<String, RavensObject> ROB = RFB.getObjects();
		HashMap<String, RavensObject> ROC = RFC.getObjects();   

		//get the Ravens Figure from the problem solutions
		HashMap<String, RavensObject> RO1 = RF1.getObjects();
		HashMap<String, RavensObject> RO2 = RF2.getObjects();
		HashMap<String, RavensObject> RO3 = RF3.getObjects();
		HashMap<String, RavensObject> RO4 = RF4.getObjects();
		HashMap<String, RavensObject> RO5 = RF5.getObjects();
		HashMap<String, RavensObject> RO6 = RF6.getObjects();

		//get the Ravens Objects from Ravens Figure
		RavensObject ROa = ROA.get("a");    	
		RavensObject ROb = ROB.get("b");    	
		RavensObject ROc = ROC.get("c");

		//get the Ravens Objects from the Ravens Figure in solution
		RavensObject RO1d = RO1.get("d");
		RavensObject RO2e = RO2.get("e");
		RavensObject RO3f = RO3.get("f");
		RavensObject RO4g = RO4.get("g");
		RavensObject RO5h = RO5.get("h");
		RavensObject RO6i = RO6.get("i");

		//get attributes for the Ravens Objects
		HashMap<String, String> ROaa = ROa.getAttributes();
		HashMap<String, String> ROba = ROb.getAttributes();
		HashMap<String, String> ROca = ROc.getAttributes();

		//get attributes for the solutions
		HashMap<String, String> RO1da = RO1d.getAttributes();
		HashMap<String, String> RO2ea = RO2e.getAttributes();
		HashMap<String, String> RO3fa = RO3f.getAttributes();
		HashMap<String, String> RO4ga = RO4g.getAttributes();
		HashMap<String, String> RO5ha = RO5h.getAttributes();
		HashMap<String, String> RO6ia = RO6i.getAttributes();

		if (ROaa.equals(ROba)) {
			tempAns = -1;
			if (ROca.equals(RO1da)) {
				tempAns = 1;
			} else if (ROca.equals(RO2ea)) {
				tempAns = 2;
			} else if (ROca.equals(RO3fa)) {
				tempAns = 3;
			} else if (ROca.equals(RO4ga)) {
				tempAns = 4;
			} else if (ROca.equals(RO5ha)) {
				tempAns = 5;
			} else if (ROca.equals(RO6ia)) {
				tempAns = 6;
			} else {
				tempAns = -1;
			}
		} else if (ROaa.equals(ROca)) {
			tempAns = -1;
			if (ROba.equals(RO1da)) {
				tempAns = 1;
			} else if (ROba.equals(RO2ea)) {
				tempAns = 2;
			} else if (ROba.equals(RO3fa)) {
				tempAns = 3;
			} else if (ROba.equals(RO4ga)) {
				tempAns = 4;
			} else if (ROba.equals(RO5ha)) {
				tempAns = 5;
			} else if (ROba.equals(RO6ia)) {
				tempAns = 6;
			}  else {
				tempAns = -1;
			}
		}
		return tempAns;
	}

}

