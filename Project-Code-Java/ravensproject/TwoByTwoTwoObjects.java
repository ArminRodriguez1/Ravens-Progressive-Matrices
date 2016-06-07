package ravensproject;

import java.util.*;

public class TwoByTwoTwoObjects {

	private RavensProblem problem;
	private int tempAns;

	//hash map for Ravens Figure
	HashMap<String, RavensFigure> RF;
	//Ravens figure
	RavensFigure RFA, RFB, RFC, RF1, RF2, RF3, RF4, RF5, RF6;
	//Hash map of Ravens object
	HashMap<String, RavensObject> ROA, ROB, ROC, RO1, RO2, RO3, RO4, RO5, RO6;
	//Ravens object
	RavensObject ROa, ROb,ROc, ROd, ROe, ROf, ROg, ROh, ROi, ROj, ROk, ROl, ROm, ROn, ROo, ROp, ROq, ROr;

	//hash map of object name and attributes
	HashMap<String, String> ROaa, ROba, ROca, ROda, ROea, ROfa, ROga, ROha, ROia, ROja, ROka, ROla, ROma, ROna, ROoa, ROpa, ROqa, ROra;
	//map of the Ravens object and its attributes in a list
	List<Map> resultAttribute;
	
	private String shapeObjectf, angleObjectf;
	private String shapeObjectAns, angleObjectAns;

	public TwoByTwoTwoObjects(RavensProblem problem){
		this.problem = problem;
	}

	public int TwoObjectRPM() {
		resultAttribute = getObjectAttributes(problem);
		//		System.out.println("Print ROaa form the two object method: " + ROaa);
		//		System.out.println("Print ROca from the two object method: " + ROca);
		//		System.out.println("Compare ROaa to ROca: " + ROaa.equals(ROca));
		//		System.out.println("Compare ROba to ROda: " + ROba.equals(ROda));
		//		System.out.println("Compare the if entry: " + ((ROaa.equals(ROca)) && (ROba.equals(ROda))));
		if (ROaa!=null && ROca!=null && (ROaa.equals(ROca))) {
			tempAns = FigureAEqualsB();
			//System.out.println("temp ans: " + tempAns);
		} else {
			tempAns = -1;
		}
		return tempAns;
	}

	private int FigureAEqualsB() {//if figure A equals object B, then object D is equal to object C
		resultAttribute = getObjectAttributes(problem);
		int ans = 0;
		//		System.out.println("ROaa: " + ROaa);
		//		System.out.println("ROca: " + ROca);
		
	      try{
	  		if(ROaa!=null && ROca!=null && (ROaa.get("shape").equals(ROca.get("shape"))) && ((ROda!=null))) {
				for (int i = 0; i < resultAttribute.size(); i=i+2){
					//int b = i +1;
					ans+=1;
					//System.out.println("print the if statement criteria: " + ((ROea.equals(resultAttribute.get(i))) && (ROfa.equals(resultAttribute.get(i+1)))));
					//System.out.println("value of i " + i + ": " + resultAttribute.get(i));
					//System.out.println("value of i " + b + ": " + resultAttribute.get(i+1));

					if (ROea!=null && resultAttribute.get(i)!= null && (ROea.equals(resultAttribute.get(i)))) { //can only compare on the object e to make it more generic

						if (ROfa!=null){
							shapeObjectf = ROfa.get("shape");
						} 
						if (ROfa!=null){
							angleObjectf = ROfa.get("angle");
						}

					
						if (resultAttribute.get(i+1) != null){
							shapeObjectAns = (String) (resultAttribute.get(i+1)).get("shape");
						}
						if (resultAttribute.get(i+1) != null) {
							angleObjectAns = (String) (resultAttribute.get(i+1)).get("angle");
						}

						if (shapeObjectf!=null && angleObjectAns!= null && (shapeObjectf.equals(shapeObjectAns)) && (angleObjectf.equals(angleObjectAns))) {
							System.out.println(ans);
							break;
						}
					}
				}
			} else {
				ans = -1;
			}
	       }catch(ArrayIndexOutOfBoundsException e){
	          System.out.println("Exception thrown  :" + e);
	       }
		return ans;
	}

	public List<Map> getObjectAttributes(RavensProblem problem) { //returns the object attributes in a list
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
		ROC = RFC.getObjects();   

		//get the Ravens Figure from the problem solutions
		RO1 = RF1.getObjects();
		RO2 = RF2.getObjects();
		RO3 = RF3.getObjects();
		RO4 = RF4.getObjects();
		RO5 = RF5.getObjects();
		RO6 = RF6.getObjects();


		if (ROA!=null) {
			ROa = ROA.get("a");    	
			ROb = ROA.get("b");
		} 
		if (ROB!=null){
			ROc = ROB.get("c");
			ROd = ROB.get("d");
		}
		if (ROC!=null){
			ROe = ROC.get("e");    	
			ROf = ROC.get("f");
		}
		if (RO1!=null){
			ROg = RO1.get("g");    	
			ROh = RO1.get("h");
		}
		if (RO2!=null){
			ROi = RO2.get("i");
			ROj = RO2.get("j");
		}
		if (RO3!=null) {
			ROk = RO3.get("k");    	
			ROl = RO3.get("l");
		}
		if (RO4!=null){
			ROm = RO4.get("m");    	
			ROn = RO4.get("n");
		} 
		if (RO5!=null){
			ROo = RO5.get("o");
			ROp = RO5.get("p");
		}
		if (RO6!=null){
			ROq = RO6.get("q");    
			ROr = RO6.get("r");
		}

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
		//		System.out.println("Print first contait for test " + resultAttribute.get(0) + "Print last for test " + resultAttribute.get(5) +
		//				"Print the size: " + resultAttribute.size());

		return resultAttribute;
	}
}
