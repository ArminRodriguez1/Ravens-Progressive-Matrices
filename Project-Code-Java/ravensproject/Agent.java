package ravensproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

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
	//private RavensProblem problem;
	HashMap<String, RavensFigure> RF;
	private int ans;
	private RavensFigure RFA;
	private HashMap<String, RavensObject> ROA;
	Random rand;

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
		System.out.println("************");
		System.out.println("Problem name: " + problem.getName());
		printDateTime();

		//check if the problem has verbal or not
		if (problem.hasVerbal()) {
			//these are the Ravens Figure from the problem
			RF = problem.getFigures();
			System.out.println("Total number of RF: "+ RF.size());

			RFA = RF.get("A");
			//get the Ravens Figure from the problem figures
			ROA = RFA.getObjects();
			int numberOfObjects = ROA.size(); //number of objects in Figure A
			System.out.println("The size of the hash map/number of objects is: " + numberOfObjects);

			/*
			 * 2 by 2 RPM problem have 9 Ravens Figure and 3 by 3 RPM have 16 Ravens Figure.
			 * Find the total number of Ravens Figure and put check accordingly.
			 */

			if (RF.size() == 9) {
				if (numberOfObjects == 1) {
					ans = OneObject(problem); //2 by 2 RPM with one objects per figure
					if (ans == 0) { //if ans = 0 from the method set it to -1 to skip the problem
						ans = -1;
					}
				} else if (numberOfObjects == 2) {
					ans = TwoObjects(problem);
					if (ans == 0) { //if ans = 0 from the method set it to -1 to skip the problem
						ans = -1;
					}
				} 
			} else {
				ans = -1;
			}
		}
		return ans;
	}


	/*
	 * This method solves for 2 by 2 RPM that has one object in a figure
	 */

	private int OneObject(RavensProblem problem) {
		TwoByTwoOneObject oneObject = new TwoByTwoOneObject(problem);
		return oneObject.oneObjectRPM();
	}

	/*
	 * This method solves for 2 by 2 RPM that has two objects in a figure
	 */

	private int TwoObjects(RavensProblem problem) {
		TwoByTwoTwoObjects twoObjects = new TwoByTwoTwoObjects(problem);
		return twoObjects.twoObjectRPM();
	}

	//prints current date and time for the execution
	//http://www.mkyong.com/java/java-how-to-get-current-date-time-date-and-calender/
	private void printDateTime(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("Executed at: " + dateFormat.format(date));
	}
}
