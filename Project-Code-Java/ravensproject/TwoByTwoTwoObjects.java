package ravensproject;

import java.util.*;

public class TwoByTwoTwoObjects {

	private RavensProblem problem;
	private int tempAns;

	HashMap<String, String> ROaa, ROba, ROca, ROda, ROea, ROfa, ROga, ROha, ROia, ROja, ROka, ROla, ROma,
	ROna, ROoa, ROpa, ROqa, ROra;

	public TwoByTwoTwoObjects(RavensProblem problem){
		this.problem = problem;
	}

	public int TwoObjectRPM() {
		List<Map> resultAttribute = getObjectAttributes(problem);
		//		System.out.println("Print ROaa form the two object method: " + ROaa);
		//		System.out.println("Print ROca from the two object method: " + ROca);
		//		System.out.println("Compare ROaa to ROca: " + ROaa.equals(ROca));
		//		System.out.println("Compare ROba to ROda: " + ROba.equals(ROda));
		//		System.out.println("Compare the if entry: " + ((ROaa.equals(ROca)) && (ROba.equals(ROda))));
		if ((ROaa.equals(ROca))) {
			tempAns = FigureAEqualsB();
			//System.out.println("temp ans: " + tempAns);
		}
		return tempAns;
	}

	private int FigureAEqualsB() {//if figure A equals object B, then object D is equal to object C
		List<Map> resultAttribute = getObjectAttributes(problem);
		int ans = 0;
		//System.out.println("ROea: " + ROea);
		//System.out.println("ROfa: " + ROfa);
		for (int i = 0; i < resultAttribute.size(); i=i+2){
			//int b = i +1;
			ans+=1;
			//System.out.println("print the if statement criteria: " + ((ROea.equals(resultAttribute.get(i))) && (ROfa.equals(resultAttribute.get(i+1)))));
			System.out.println("value of i " + i + ": " + resultAttribute.get(i));
			//System.out.println("value of i " + b + ": " + resultAttribute.get(i+1));

			if ((ROea.equals(resultAttribute.get(i)))) {
				String shapeObjectf = ROfa.get("shape");
				String angleObjectf = ROfa.get("angle");

				String shapeObjectAns ="";
				String angleObjectAns = "";

				if (resultAttribute.get(i+1) != null){
					shapeObjectAns = (String) (resultAttribute.get(i+1)).get("shape");
				}
				if (resultAttribute.get(i+1) != null) {
					angleObjectAns = (String) (resultAttribute.get(i+1)).get("angle");
				}

				if ((shapeObjectf.equals(shapeObjectAns)) && (angleObjectf.equals(angleObjectAns))) {
					System.out.println(ans);
					break;
				}
			}
		}
		return ans;
	}

	public List<Map> getObjectAttributes(RavensProblem problem) { //returns the object attributes in a list
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
		RavensObject ROb = ROA.get("b");  
		RavensObject ROc = ROB.get("c");
		RavensObject ROd = ROB.get("d");    	
		RavensObject ROe = ROC.get("e");    	
		RavensObject ROf = ROC.get("f");
		RavensObject ROg = RO1.get("g");    	
		RavensObject ROh = RO1.get("h");    	
		RavensObject ROi = RO2.get("i");
		RavensObject ROj = RO2.get("j");    	
		RavensObject ROk = RO3.get("k");    	
		RavensObject ROl = RO3.get("l");
		RavensObject ROm = RO4.get("m");    	
		RavensObject ROn = RO4.get("n");    	
		RavensObject ROo = RO5.get("o");
		RavensObject ROp = RO5.get("p");    	
		RavensObject ROq = RO6.get("q");    
		RavensObject ROr = RO6.get("r");    

		//		System.out.println("Print ROa: " + ROa);
		//		System.out.println("Print ROb: " + ROb);
		//		System.out.println("Print ROc: " + ROc);
		//		System.out.println("Print ROd: " + ROd);
		//		System.out.println("Print ROe: " + ROe);
		//		System.out.println("Print ROf: " + ROf);
		//		System.out.println("Print ROg: " + ROg);
		//		System.out.println("Print ROh: " + ROh);
		//		System.out.println("Print ROi: " + ROi);
		//		System.out.println("Print ROj: " + ROj);
		//		System.out.println("Print ROk: " + ROk);
		//		System.out.println("Print ROl: " + ROl);
		//		System.out.println("Print ROm: " + ROm);
		//		System.out.println("Print ROn: " + ROn);
		//		System.out.println("Print ROo: " + ROo);
		//		System.out.println("Print ROp: " + ROp);
		//		System.out.println("Print ROq: " + ROq);
		//		System.out.println("Print ROr: " + ROr);

		//get attributes for the Ravens Objects

		if (ROa!=null) {
			ROaa = ROa.getAttributes();
			//System.out.println("ROaa: " + ROaa);
		}
		if (ROb!=null) {
			ROba = ROb.getAttributes();
			//System.out.println("ROba: " + ROba);

		} 
		if (ROc!=null) {
			ROca = ROc.getAttributes();
			//System.out.println("ROca: " + ROca);

		} 
		if (ROd!=null){
			ROda = ROd.getAttributes();
			//System.out.println("ROda: " + ROda);

		} 
		if (ROe!=null) {
			ROea = ROe.getAttributes();
			//System.out.println("ROea: " + ROea);
		} 
		if (ROf!=null) {
			ROfa = ROf.getAttributes();
			//System.out.println("ROfa: " + ROfa);
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
		if (ROj!=null) {
			ROja = ROj.getAttributes();
		} 
		if (ROk!=null) {
			ROka = ROk.getAttributes();
		} 
		if (ROl!=null) {
			ROla = ROl.getAttributes();
		} 
		if (ROm!=null) {
			ROma = ROm.getAttributes();
		}
		if (ROn!=null) {
			ROna = ROn.getAttributes();
		}
		if (ROo!=null) {
			ROoa = ROo.getAttributes();
		}
		if (ROp!=null) {
			ROpa = ROp.getAttributes();
		}
		if (ROq!=null) {
			//System.out.println("ROq " + ROq.hashCode());
			ROqa = ROq.getAttributes();
		}
		if (ROr!=null) {
			//System.out.println("ROr " + ROr.hashCode());
			ROra = ROr.getAttributes();
		}

		List<Map> resultAttribute = new ArrayList(); //array list for the result object attributes
		resultAttribute.add(ROga);
		resultAttribute.add(ROha);
		resultAttribute.add(ROia);
		resultAttribute.add(ROja);
		resultAttribute.add(ROka);
		resultAttribute.add(ROla);
		resultAttribute.add(ROma);
		resultAttribute.add(ROna);
		resultAttribute.add(ROoa);
		resultAttribute.add(ROpa);
		resultAttribute.add(ROqa);
		resultAttribute.add(ROra);

		//		System.out.println("Printing the test array: " + resultAttribute);
		//		System.out.println("Print first containt for test " + resultAttribute.get(0) + "Print last for test " + resultAttribute.get(5) +
		//				"Print the size: " + resultAttribute.size());

		return resultAttribute;
	}
}
