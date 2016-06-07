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
	HashMap<String, RavensFigure> RF;
	//Ravens figure
	RavensFigure RFA, RFB, RFC, RF1, RF2, RF3, RF4, RF5, RF6;
	//Hash map of Ravens object
	HashMap<String, RavensObject> ROA, ROB, ROC, RO1, RO2, RO3, RO4, RO5, RO6;
	//Ravens object
	RavensObject ROa, ROb,ROc, RO1d, RO2e, RO3f, RO4g, RO5h, RO6i;
	//attributes for Ravens object
	HashMap<String, String> ROaa, ROba,ROca, RO1da, RO2ea, RO3fa, RO4ga, RO5ha, RO6ia;

	public TwoByTwoOneObject(RavensProblem problem) {
		this.problem = problem;
	}

	public int OneObjectRPM() {

		//check if the problem has verbal or not
		if (problem.hasVerbal()) {
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

			//get the Ravens Figure from the problem figures
			ROA = RFA.getObjects();
			ROB = RFB.getObjects();
			HashMap<String, RavensObject> ROC = RFC.getObjects();   

			//get the Ravens Figure from the problem solutions
			RO1 = RF1.getObjects();
			RO2 = RF2.getObjects();
			RO3 = RF3.getObjects();
			RO4 = RF4.getObjects();
			RO5 = RF5.getObjects();
			RO6 = RF6.getObjects();

			//get the Ravens Objects from Ravens Figure
			if (ROA!=null){
			ROa = ROA.get("a");
			}
			if (ROB!=null){
				ROb = ROB.get("b");    	
			}
			if (ROC!=null){
			ROc = ROC.get("c");
			}
			//System.out.println("name of the ravens object ROa: " + ROa.getAttributes().containsValue("square"));

			//get the Ravens Objects from the Ravens Figure in solution
			if (RO1!=null){
			RO1d = RO1.get("d");
			}
			if (RO2!=null){
				RO2e = RO2.get("e");
			}
			if (RO3!=null){
				RO3f = RO3.get("f");
			}
			if (RO4!=null){
				RO4g = RO4.get("g");
			}
			if (RO5!=null){
				RO5h = RO5.get("h");
			}
			if (RO6!=null){
				RO6i = RO6.get("i");
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

			//System.out.println("ROaa: " + ROaa);
			//System.out.println("one object, ROca: " + ROca);

			//get attributes for the solution objects
			if (RO1d!=null) {
				RO1da = RO1d.getAttributes();
			}
			if (RO2e!=null) {
				RO2ea = RO2e.getAttributes();
			}
			if (RO3f!=null) {
				RO3fa = RO3f.getAttributes();
			}
			if (RO4g!=null) {
				RO4ga = RO4g.getAttributes();
			}
			if (RO5h!=null) {
				RO5ha = RO5h.getAttributes();
			}
			if (RO6i!=null) {
				RO6ia = RO6i.getAttributes();
			}
			
			//System.out.println("Compare ROca to RO1da in first class: " + ROca.equals(RO1da) );

			
			
			//test
			List<Map> test = new ArrayList();
			test.add(RO1da);
			test.add(RO2ea);
			test.add(RO3fa);
			test.add(RO4ga);
			test.add(RO5ha);
			test.add(RO6ia);
			
//			System.out.println("Printing the test array: " + test);
//			System.out.println("Print first containt for test " + test.get(0) + "Print last for test " + test.get(5) +
//					"Print the size: " + test.size()); 
			
			//return test;
			
			
			if (ROaa.equals(ROba)) { //if figure A equals object B, then object D is equal to object C
				if (ROca.equals(RO1da)) {
					System.out.println("Compare ROca to RO1da in first class: " + ROca.equals(RO1da) );
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
			} else if (ROaa.equals(ROca)) { //if object c is equal to object a, then object d is equal to object b
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
								
								//System.out.println("Diff between A and B " + angleDiffAandB);
								
								String angleC = ROca.get("angle"); //String angle 
								int angleCint = Integer.parseInt(angleC); //integer angle
								
								//compare the difference between C and results to angleDiffAandB. They should be the same
								if (ROca.containsKey("angle") && (RO1da.containsKey("angle"))) {
									String angle1d = RO1da.get("angle"); //String angle
									int angle1dint = Integer.parseInt(angle1d); //integer angle
									
									int angleDiff = Math.abs(angleCint - angle1dint);
									System.out.println("Diff 1 and C " + angleDiff);
									
									if ((angleDiffAandB == angleDiff) && (RO1da.get("fill").equals(fillinC)))  { //decide whether to add fill component or not
										tempAns = 1;
									}
								} if (ROca.containsKey("angle") && (RO2ea.containsKey("angle"))) {
									String angle2e = RO2ea.get("angle"); //String angle 
									int angle2eint = Integer.parseInt(angle2e); //integer angle

									int angleDiff = Math.abs(angleCint - angle2eint);
									System.out.println("Diff 2 and C " + angleDiff);

									if ((angleDiffAandB == angleDiff) && (RO2ea.get("fill").equals(fillinC))) {
										tempAns = 2;
									}
								} if (ROca.containsKey("angle") && (RO3fa.containsKey("angle"))) {
									String angle3f = RO3fa.get("angle"); //String angle
									int angle3fint = Integer.parseInt(angle3f); //integer angle

									int angleDiff = Math.abs(angleCint - angle3fint);
									System.out.println("Diff 3 and C " + angleDiff);

									if ((angleDiffAandB == angleDiff) && (RO3fa.get("fill").equals(fillinC))) {
										tempAns = 3;
									}
								} if (ROca.containsKey("angle") && (RO4ga.containsKey("angle"))) {
									String angle4g = RO4ga.get("angle"); //String angle 
									int angle4gint = Integer.parseInt(angle4g); //integer angle

									int angleDiff = Math.abs(angleCint - angle4gint);
									System.out.println("Diff 4 and C " + angleDiff);

									if ((angleDiffAandB == angleDiff) && (RO4ga.get("fill").equals(fillinC))) {
										tempAns = 4;
									}
								} if (ROca.containsKey("angle") && (RO5ha.containsKey("angle"))) {
									String angle5h = RO5ha.get("angle"); //String angle
									int angle5hint = Integer.parseInt(angle5h); //integer angle

									int angleDiff = Math.abs(angleCint - angle5hint);
									System.out.println("Diff 5 and C " + angleDiff);

									if ((angleDiffAandB == angleDiff) && (RO5ha.get("fill").equals(fillinC))) {
										tempAns = 5;
									}
								} if (ROca.containsKey("angle") && (RO6ia.containsKey("angle"))){
									String angle6i = RO6ia.get("angle"); //String angle 
									int angle6iint = Integer.parseInt(angle6i); //integer angle

									int angleDiff = Math.abs(angleCint - angle6iint);
									System.out.println("Diff 6 and C " + angleDiff);

									if ((angleDiffAandB == angleDiff) && (RO6ia.get("fill").equals(fillinC))) {
										tempAns = 6;
									}
								} else {
									tempAns = -1;
								}
							} else if (!ROba.containsKey("angle")) { //case where the figure B doesn't have angle
								String shapeA = ROaa.get("shape");
								String shapeC = ROca.get("shape");
								String shapeB = ROba.get("shape");
								
								String shapeAFill = ROaa.get("fill");
								String shapeCFill = ROca.get("fill");
								String shapeBFill = ROba.get("fill");
								
								String shapeAAlign = ROaa.get("alignment");
								String shapeBAlign = ROba.get("alignment");
								String shapeCAlign = ROca.get("alignment");


								if ((shapeA.equals(shapeC)) && !(shapeAAlign.equals(shapeCAlign)) && (shapeAFill.equals(shapeCFill))) { //check if the shape of  A and C are reflection of each other
									String shape1 = RO1da.get("shape");
									String shape1Fill = RO1da.get("fill");
									String shape1Align = RO1da.get("alignment");
									
									String shape2 = RO2ea.get("shape");
									String shape2Fill = RO2ea.get("fill");
									String shape2Align = RO2ea.get("alignment");
									
									String shape3 = RO3fa.get("shape");
									String shape3Fill = RO3fa.get("fill");
									String shape3Align = RO3fa.get("alignment");
									
									String shape4 = RO4ga.get("shape");
									String shape4Fill = RO4ga.get("fill");
									String shape4Align = RO4ga.get("alignment");
									
									String shape5 = RO5ha.get("shape");
									String shape5Fill = RO5ha.get("fill");
									String shape5Align = RO5ha.get("alignment");
									
									String shape6 = RO6ia.get("shape");
									String shape6Fill = RO6ia.get("fill");
									String shape6Align = RO6ia.get("alignment");
									
									if((shape1.equals(shapeB)) && !(shape1Align.equals(shapeBAlign)) && (shape1Fill.equals(shapeBFill))){
										tempAns = 1;
									} else if((shape2.equals(shapeB)) && !(shape2Align.equals(shapeBAlign)) && (shape2Fill.equals(shapeBFill))){
										tempAns = 2;
									} else if((shape3.equals(shapeB)) && !(shape3Align.equals(shapeBAlign)) && (shape3Fill.equals(shapeBFill))){
										tempAns = 3;
									} else if((shape4.equals(shapeB)) && !(shape4Align.equals(shapeBAlign)) && (shape4Fill.equals(shapeBFill))){
										tempAns = 4;
									} else if((shape5.equals(shapeB)) && !(shape5Align.equals(shapeBAlign)) && (shape5Fill.equals(shapeBFill))){
										tempAns = 5;
									} else if((shape6.equals(shapeB)) && !(shape6Align.equals(shapeBAlign)) && (shape6Fill.equals(shapeBFill))){
										tempAns = 6;
									} else {
										tempAns = -1;
									}
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


