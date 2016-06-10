package ravensproject;

import java.util.*;

public class TwoByTwoTwoObjects {

	private RavensProblem problem;
	private int tempAns;

	//hash map for Ravens Figure
	private HashMap<String, RavensFigure> RF;
	//Ravens figure
	private RavensFigure RFA, RFB, RFC, RF1, RF2, RF3, RF4, RF5, RF6;
	//Hash map of Ravens object
	private HashMap<String, RavensObject> ROA, ROB, ROC, RO1, RO2, RO3, RO4, RO5, RO6;
	//Ravens object
	private RavensObject ROa, ROb,ROc, ROd, ROe, ROf, ROg, ROh, ROi, ROj, ROk, ROl, ROm, ROn, ROo, ROp, ROq, ROr;
	//hash map of object name and attributes
	@SuppressWarnings("unused")
	private HashMap<String, String> ROaa, ROba, ROca, ROda, ROea, ROfa, ROga, ROha, ROia, ROja, ROka, ROla, ROma, ROna, ROoa, ROpa, ROqa, ROra;
	//map of the Ravens object and its attributes in a list
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> resultAttribute = new ArrayList(); //array list for the result object attributes

	private String shapeObjectf, angleObjectf, shapeObjectd, angleObjectd; //attributes of object f
	private String shapeObjectAns, angleObjectAns; //attributes of object from the potential answer

	public TwoByTwoTwoObjects(RavensProblem problem){
		this.problem = problem;
	}

	public int twoObjectRPM() {
		resultAttribute = getObjectAttributes(problem);
		if (ROaa!=null && ROca!=null && (ROaa.equals(ROca)) && ROda!=null) { //horizontal equals A==C
			tempAns = figureAEqualsB();
			//System.out.println("temp ans " + tempAns);
		} else if (ROaa!=null && ROea!=null && (ROaa.equals(ROea)) && ROfa!=null) { //vertical equals A==E
			tempAns = figureAEqualsC();
			//System.out.println("temp ans " + tempAns);
		} else if (ROaa!=null && ROca!=null && (ROaa.equals(ROca)) && ROda==null) { //A has two objects, B has inside object deleted
			tempAns = figureBDeletesA();
			//System.out.println("temp ans " + tempAns);
		} else if (ROaa!=null && ROea!=null && (ROaa.equals(ROea)) && ROfa==null) { //A has two object, C has inside object deleted
			tempAns = figureCDeletesA();
			//System.out.println("temp ans " + tempAns);
		} else {
			tempAns = -1;
			//System.out.println("temp ans " + tempAns);
		}
		return tempAns;
	}

	private int figureAEqualsB() {//if figure A equals object B, then object D is equal to object C
		//resultAttribute = getObjectAttributes(problem);
		int ans = 0;

		try{
			for (int i = 0; i < resultAttribute.size(); i=i+2){
				ans+=1;

				if (ROea!=null && resultAttribute.get(i)!= null && (ROea.equals(resultAttribute.get(i)))) { //can only compare on the object e to make it more generic
					if (ROfa!=null){
						shapeObjectf = ROfa.get("shape");
						angleObjectf = ROfa.get("angle");
					}
					if (resultAttribute.get(i+1) != null){
						shapeObjectAns = (String) (resultAttribute.get(i+1)).get("shape");
						angleObjectAns = (String) (resultAttribute.get(i+1)).get("angle");
					}
					if (shapeObjectf!=null && angleObjectAns!= null && (shapeObjectf.equals(shapeObjectAns)) && (angleObjectf.equals(angleObjectAns))) {
						return ans;
					}
				} 
			}
		}catch(NullPointerException e){
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}

	private int figureAEqualsC() {//if figure A equals figure C, then Figure D is equal to Figure C
		//resultAttribute = getObjectAttributes(problem);
		int ans = 0;

		try{
			for (int i = 0; i < resultAttribute.size(); i=i+2){
				ans+=1;

				if (ROca!=null && resultAttribute.get(i)!= null && (ROca.equals(resultAttribute.get(i)))) { //can only compare on the object c to make it more generic
					if (ROda!=null){
						shapeObjectd = ROda.get("shape");
						angleObjectd = ROda.get("angle");
					}
					if (resultAttribute.get(i+1) != null){
						shapeObjectAns = (String) (resultAttribute.get(i+1)).get("shape");
						angleObjectAns = (String) (resultAttribute.get(i+1)).get("angle");
					}
					if (shapeObjectd!=null && angleObjectAns!= null && (shapeObjectd.equals(shapeObjectAns)) && (angleObjectd.equals(angleObjectAns))) {
						break;
					}
				} 
			}
		} catch(NullPointerException e){
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}

	private int figureCDeletesA() {//if figure C has inside object deleted
		//resultAttribute = getObjectAttributes(problem);
		int ans = 0;

		try{
			for (int i = 0; i < resultAttribute.size(); i=i+2){
				ans+=1;
				if (ROca!=null && resultAttribute.get(i)!= null && (ROca.equals(resultAttribute.get(i)))) { // compare on the object e to make it more generic
					if (resultAttribute.get(i+1)==null){ //checks the inside of the figure
						break;
					}
				} 
			} 
		} catch(NullPointerException e){
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}

	private int figureBDeletesA() {//if figure B has inside object deleted
		//resultAttribute = getObjectAttributes(problem);
		int ans = 0;

		try{
			for (int i = 0; i < resultAttribute.size(); i=i+2){
				ans+=1;
				if (ROea!=null && resultAttribute.get(i)!= null && (ROea.equals(resultAttribute.get(i)))) { // compare on the object e to make it more generic
					if (resultAttribute.get(i+1)==null){ //checks the inside of the figure
						break;
					}
				}  
			}
		} catch(NullPointerException e){
			System.out.println("Exception thrown  :" + e);
		}
		return ans;
	}

	//TO DO: decompose this
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

		//get attributes for the Ravens Objects

		if (ROa!=null) {
			ROaa = ROa.getAttributes();		}
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
			ROqa = ROq.getAttributes();
		}
		if (ROr!=null) {
			ROra = ROr.getAttributes();
		}

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

		return resultAttribute;
	}
}