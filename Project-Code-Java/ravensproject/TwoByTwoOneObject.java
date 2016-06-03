package ravensproject;

import java.util.HashMap;


/**
 * This class solves for the RPM that have only one object 
 * in a figure. This class only deals with 2 by 2 RPMs.
 */


public class TwoByTwoOneObject {

	private RavensProblem problem;
	private int tempAns;

	public TwoByTwoOneObject(RavensProblem problem) {
		this.problem = problem;
	}

	public int test() {

		//check if the problem has verbal or not
		if (problem.hasVerbal()) {
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

			//System.out.println("name of the ravens object ROa: " + ROa.getAttributes().containsValue("square"));

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

			//System.out.println("ROaa: " + ROaa);

			//get attributes for the solution objects
			HashMap<String, String> RO1da = RO1d.getAttributes();
			HashMap<String, String> RO2ea = RO2e.getAttributes();
			HashMap<String, String> RO3fa = RO3f.getAttributes();
			HashMap<String, String> RO4ga = RO4g.getAttributes();
			HashMap<String, String> RO5ha = RO5h.getAttributes();
			HashMap<String, String> RO6ia = RO6i.getAttributes();

			if (ROaa.equals(ROba)) {
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
			} else if ((ROaa.containsValue("octagon") || ROaa.containsValue("right triangle") 
					|| ROaa.containsValue("pac-man"))) { //check for the three odd shapes

				String shapeofC = ROca.get("shape"); //find the shape and look for the same shape
				String fillinC = ROca.get("fill"); //find if the shape is filled or not and look for the opposite fill

				//			    System.out.println("Value of octagon, triangle, pac-man and angle: " + ((ROaa.containsValue("octagon") || ROaa.containsValue("right triangle") 
				//				|| ROaa.containsValue("pac-man")) && !ROaa.containsKey("angle")));

				//System.out.println("value of shape of C: " + shapeofC + " value of fill in C: " + fillinC);
				//System.out.println("print the value of the bool for B-09: " + ((((ROaa.containsValue("no") && ROba.containsValue("yes"))) || ((ROaa.containsValue("yes") && ROba.containsValue("no"))))));


				if (!ROaa.containsKey("angle")) { //cases without any rotations
					if (((ROaa.containsValue("no") && ROba.containsValue("yes"))) || ((ROaa.containsValue("yes") && ROba.containsValue("no")))) { //check for fill in A and no fill in B or vice versa
						if ((RO1da.get("shape").equals(shapeofC)) && (!(RO1da.get("fill").equals(fillinC)))) {
							System.out.println("shape of RO1da: " + RO1da.get("shape"));
							System.out.println("fill of RO1da: " + RO1da.get("fill"));
							tempAns = 1;
						} else if ((RO2ea.get("shape").equals(shapeofC)) && (!(RO2ea.get("fill").equals(fillinC)))) {
							tempAns = 2;
						} else if ((RO3fa.get("shape").equals(shapeofC)) && (!(RO3fa.get("fill").equals(fillinC)))) {
							tempAns = 3;
						} else if ((RO4ga.get("shape").equals(shapeofC)) && (!(RO4ga.get("fill").equals(fillinC)))) {
							tempAns = 4;
						} else if ((RO5ha.get("shape").equals(shapeofC)) && (!(RO5ha.get("fill").equals(fillinC)))) {
							tempAns = 5;
						} else if ((RO6ia.get("shape").equals(shapeofC)) && (!(RO6ia.get("fill").equals(fillinC)))) {
							tempAns = 6;
						} else {
							tempAns = -1;
						}
					}
				} else if (ROaa.containsKey("angle")) { //cases with rotations, angles
					String angleA = ROaa.get("angle"); //this angle is String
					int angleAint = Integer.parseInt(angleA); //integer of the angle

					if (((ROaa.containsValue("yes") && ROba.containsValue("yes")))) {//case where A and B are filled
						if ((RO1da.get("shape").equals(shapeofC))) { //TO DO: incorporate angle logic
							if (ROba.containsKey("angle")) { //check if B has been rotated or not or has angle or not
								String angleB = ROba.get("angle"); //String angle
								int angleBint = Integer.parseInt(angleB); //integer angle
								int angleDiffAandB = Math.abs(angleAint - angleBint); 
								
								System.out.println("Diff between A and B " + angleDiffAandB);
								
								String angleC = ROca.get("angle"); //String angle 
								int angleCint = Integer.parseInt(angleC); //integer angle
								
								//compare the difference between C and results to angleDiffAandB. They should be the same
								if (ROca.containsKey("angle") && (RO1da.containsKey("angle"))) {
									String angle1d = RO1da.get("angle"); //String angle
									int angle1dint = Integer.parseInt(angle1d); //integer angle
									
									int angleDiff = Math.abs(angleCint - angle1dint);
									System.out.println("Diff 1 and C " + angleDiff);
									
									if (angleDiffAandB == angleDiff)  { //decide whether to add fill component or not
										tempAns = 1;
									}
								} if (ROca.containsKey("angle") && (RO2ea.containsKey("angle"))) {
									String angle2e = RO2ea.get("angle"); //String angle 
									int angle2eint = Integer.parseInt(angle2e); //integer angle

									int angleDiff = Math.abs(angleCint - angle2eint);
									System.out.println("Diff 2 and C " + angleDiff);

									if (angleDiffAandB == angleDiff) {
										tempAns = 2;
									}
								} if (ROca.containsKey("angle") && (RO3fa.containsKey("angle"))) {
									String angle3f = RO3fa.get("angle"); //String angle
									int angle3fint = Integer.parseInt(angle3f); //integer angle

									int angleDiff = Math.abs(angleCint - angle3fint);
									System.out.println("Diff 3 and C " + angleDiff);

									if (angleDiffAandB == angleDiff) {
										tempAns = 3;
									}
								} if (ROca.containsKey("angle") && (RO4ga.containsKey("angle"))) {
									String angle4g = RO4ga.get("angle"); //String angle 
									int angle4gint = Integer.parseInt(angle4g); //integer angle

									int angleDiff = Math.abs(angleCint - angle4gint);
									System.out.println("Diff 4 and C " + angleDiff);

									if (angleDiffAandB == angleDiff) {
										tempAns = 4;
									}
								} if (ROca.containsKey("angle") && (RO5ha.containsKey("angle"))) {
									String angle5h = RO5ha.get("angle"); //String angle
									int angle5hint = Integer.parseInt(angle5h); //integer angle

									int angleDiff = Math.abs(angleCint - angle5hint);
									System.out.println("Diff 5 and C " + angleDiff);

									if (angleDiffAandB == angleDiff) {
										tempAns = 5;
									}
								} if (ROca.containsKey("angle") && (RO6ia.containsKey("angle"))){
									String angle6i = RO6ia.get("angle"); //String angle 
									int angle6iint = Integer.parseInt(angle6i); //integer angle

									int angleDiff = Math.abs(angleCint - angle6iint);
									System.out.println("Diff 6 and C " + angleDiff);

									if (angleDiffAandB == angleDiff) {
										tempAns = 6;
									}
								} else {
									tempAns = -1;
								}
							}
						}
					}
				}

			}
		}
		return tempAns;
	}
}

