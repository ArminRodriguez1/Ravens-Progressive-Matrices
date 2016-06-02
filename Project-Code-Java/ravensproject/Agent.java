package ravensproject;

import java.util.HashMap;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

// Uncomment these lines to access image processing.
//import java.awt.Image;
//import java.io.File;
//import javax.imageio.ImageIO;

/**
 * Your Agent for solving Raven's Progressive Matrices. You MUST modify this
 * file.
 * 
 * You may also create and submit new files in addition to modifying this file.
 * 
 * Make sure your file retains methods with the signatures:
 * public Agent()
 * public char Solve(RavensProblem problem)
 * 
 * These methods will be necessary for the project's main method to run.
 * 
 */
public class Agent {
    /**
     * The default constructor for your Agent. Make sure to execute any
     * processing necessary before your Agent starts solving problems here.
     * 
     * Do not add any variables to this signature; they will not be used by
     * main().
     * 
     */
    public Agent() {
        
    }
    /**
     * The primary method for solving incoming Raven's Progressive Matrices.
     * For each problem, your Agent's Solve() method will be called. At the
     * conclusion of Solve(), your Agent should return an int representing its
     * answer to the question: 1, 2, 3, 4, 5, or 6. Strings of these ints 
     * are also the Names of the individual RavensFigures, obtained through
     * RavensFigure.getName(). Return a negative number to skip a problem.
     * 
     * Make sure to return your answer *as an integer* at the end of Solve().
     * Returning your answer as a string may cause your program to crash.
     * @param problem the RavensProblem your agent should solve
     * @return your Agent's answer to this problem
     */
    public int Solve(RavensProblem problem) {
    	System.out.println("problem name: " + problem.getName());
    	System.out.println("************");
    	int ans = -1;
    	HashMap<String, RavensFigure> RF = problem.getFigures();
   	
    	//System.out.println("RF");
    	
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
    	
   	
//    	System.out.println(RFA.getName());
//    	System.out.println(RFB.getName());
//    	System.out.println(RFC.getName());
//    	System.out.println(RF1.getName());
//    	System.out.println(RF2.getName());
//    	System.out.println(RF3.getName());
    	
   	
    	//get the Ravens Figure from the problem figures
    	HashMap<String, RavensObject> ROA = RFA.getObjects();
    	HashMap<String, RavensObject> ROB = RFB.getObjects();
    	HashMap<String, RavensObject> ROC = RFC.getObjects();
    	
//    	System.out.println("Value of ROB hasmap: " + ROB.get("b"));
//    	RavensObject ravensObj = ROB.get("b");
//    	HashMap<String, String> hashObject = ravensObj.getAttributes();
//    	System.out.println("The value of HashMap String String: "+ hashObject.get("size"));
   
    	
    	//get the Ravens Figure from the problem solutions
    	HashMap<String, RavensObject> RO1 = RF1.getObjects();
    	HashMap<String, RavensObject> RO2 = RF2.getObjects();
    	HashMap<String, RavensObject> RO3 = RF3.getObjects();
    	HashMap<String, RavensObject> RO4 = RF4.getObjects();
    	HashMap<String, RavensObject> RO5 = RF5.getObjects();
    	HashMap<String, RavensObject> RO6 = RF6.getObjects();
    	
    	//get the Ravens Objects from Ravens Figure
    	RavensObject ROa = ROA.get("a");
    	//System.out.println(ROa.getName());
    	System.out.println(ROa.hashCode());
    	
    	RavensObject ROb = ROB.get("b");
    	System.out.println(ROb.getName());
    	
    	RavensObject ROc = ROC.get("c");
    	System.out.println(ROc.getName());
    	    	
    	//get the Ravens Objects from the Ravens Figure in solution
    	RavensObject RO1d = RO1.get("d");
    	RavensObject RO2e = RO2.get("e");
    	RavensObject RO3f = RO3.get("f");
    	RavensObject RO4g = RO4.get("g");
    	RavensObject RO5h = RO5.get("h");
    	RavensObject RO6i = RO6.get("i");
    	
    	System.out.println(ROb.getName());
    	    	
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
    	    	
//    	System.out.println(ROaa);
//    	System.out.println(ROba);
//    	System.out.println(ROca);
    	
    	if (ROaa.equals(ROba)) {
    		if (ROca.equals(RO1da)) {
    			ans = 1;
    		} else if (ROca.equals(RO2ea)) {
    			ans = 2;
    		} else if (ROca.equals(RO3fa)) {
    			ans = 3;
    		} else if (ROca.equals(RO4ga)) {
    			ans = 4;
    		} else if (ROca.equals(RO5ha)) {
    			ans = 5;
    		} else if (ROca.equals(RO6ia)) {
    			ans = 6;
    		}
    	}    	
        return ans;
    }
}
