import java.util.ArrayList;
import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 * 		    Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie 
{
	List<String> inputs = new ArrayList<String>();
	List<String> outputs = new ArrayList<String>();
	File keyFile = new File("Keywords.txt");
	File repFile = new File("Responses.txt");
	Scanner keyScanner = new Scanner(keyFile);
	Scanner repScanner = new Scanner(repFile);
	boolean whyLike = false;
	
	public Magpie() throws FileNotFoundException {}
	
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */
	public String getGreeting()
	{
		return "Hello, let's talk.";
	}
	
	public String getResponse2(String statement)
	{
		String tempKey = "";
		String tempRep = "";
		while (keyScanner.hasNextLine())
		{
			tempKey = keyScanner.nextLine();
			tempRep = repScanner.nextLine();
			if (statement.equals(tempKey))
			{
				return tempRep;
			}
		}
		return tempRep;
	}
	
	public String getResponseArray(String statement)
	{
		String response = "";
		
		for (int i = 0; i < inputs.size(); i++)
		{
			if (statement.equals(inputs.get(i)))
			{
				response = outputs.get(i);
			}
		}
		return response;
	}
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
			String response = "";
			if (statement.trim().length() > 0) {
				if (whyLike) {
					response = "I couldn't agree more!";
					whyLike = false;
				} else if (statement.indexOf("no") >= 0) {
					response = "Why so negative?";
				} else if (findKeyword(statement, "mother") >= 0 
						|| findKeyword(statement, "father") >= 0
						|| findKeyword(statement, "sister") >= 0 
						|| findKeyword(statement, "brother") >= 0) {
					response = "Tell me more about your family.";
				} else if (findKeyword(statement, "dog") >= 0 
						|| findKeyword(statement, "cat") >= 0 
						|| findKeyword(statement, "pet") >= 0
						|| findKeyword(statement, "horse") >= 0) {
					response = "Tell me more about your pets.";
				} else if (findKeyword(statement, "Seth") >= 0
						|| findKeyword(statement, "watgen") >= 0) {

					response = "Oh Seth loves prison!";
				} else if (findKeyword(statement, "Mrs. O") >= 0 
						|| findKeyword(statement, "Mrs. O'Laughlin") >= 0
						|| findKeyword(statement, "teacher") >= 0) {

					response = "Your teacher sounds very nice!";
				} else if (findKeyword(statement, "Fortnite") >= 0 
						|| findKeyword(statement, "fortnite") >= 0
						|| findKeyword(statement, "ninja") >= 0) {

					response = "Fortnite is so EPIC!! Let's get some vicroys!";
				} else if (findKeyword(statement, "Mrs. O") >= 0 
						|| findKeyword(statement, "Mrs. O'Laughlin") >= 0
						|| findKeyword(statement, "teacher") >= 0) {

					response = "Your teacher sounds very fun!";
				} else if (findKeyword(statement, "Panera") >= 0 
						|| findKeyword(statement, "work") >= 0
						|| findKeyword(statement, "job") >= 0) {

					response = "What do you do for work?";
				} else if (findKeyword(statement, "I like") >= 0) {
					String temp = " ";
					int i = statement.length() - 1;
					while (!(statement.substring(i, i+1).equals(" ")))
					{
						i--;
					}
					temp = statement.substring(i+1);
					response = "Why do you like " + temp + "?";
					whyLike = true;
				} else {
				response = "Please enter something!";
			}
		}
			return response;
	}

		/**
		 * Search for one word in phrase. The search is not case
		 * sensitive. This method will check that the given goal
		 * is not a substring of a longer string (so, for
		 * example, "I know" does not contain "no").
		 *
		 * @param statement
		 *            the string to search
		 * @param goal
		 *            the string to search for
		 * @param startPos
		 *            the character of the string to begin the
		 *            search at
		 * @return the index of the first occurrence of goal in
		 *         statement or -1 if it's not found
		 */
		private int findKeyword(String statement, String goal,
				int startPos)
		{
			String phrase = statement.trim().toLowerCase();
			goal = goal.toLowerCase();

			// The only change to incorporate the startPos is in
			// the line below
			int psn = phrase.indexOf(goal, startPos);

			// Refinement--make sure the goal isn't part of a
			// word
			while (psn >= 0)
			{
				// Find the string of length 1 before and after
				// the word
				String before = " ", after = " ";
				if (psn > 0)
				{
					before = phrase.substring(psn - 1, psn);
				}
				if (psn + goal.length() < phrase.length())
				{
					after = phrase.substring(
							psn + goal.length(),
							psn + goal.length() + 1);
				}

				// If before and after aren't letters, we've
				// found the word
				if (((before.compareTo("a") < 0) || (before
						.compareTo("z") > 0)) // before is not a
												// letter
						&& ((after.compareTo("a") < 0) || (after
								.compareTo("z") > 0)))
				{
					return psn;
				}

				// The last position didn't work, so let's find
				// the next, if there is one.
				psn = phrase.indexOf(goal, psn + 1);

			}

			return -1;
		}

		/**
		 * Search for one word in phrase. The search is not case
		 * sensitive. This method will check that the given goal
		 * is not a substring of a longer string (so, for
		 * example, "I know" does not contain "no"). The search
		 * begins at the beginning of the string.
		 * 
		 * @param statement
		 *            the string to search
		 * @param goal
		 *            the string to search for
		 * @return the index of the first occurrence of goal in
		 *         statement or -1 if it's not found
		 */
		private int findKeyword(String statement, String goal)
		{
			return findKeyword(statement, goal, 0);
		}

		/**
		 * Pick a default response to use if nothing else fits.
		 * 
		 * @return a non-committal string
		 */
		private String getRandomResponse()
		{
			final int NUMBER_OF_RESPONSES = 4;
			double r = Math.random();
			int whichResponse = (int) (r * NUMBER_OF_RESPONSES);
			String response = "";

			if (whichResponse == 0)
			{
				response = "Interesting, tell me more.";
			}
			else if (whichResponse == 1)
			{
				response = "Hmmm.";
			}
			else if (whichResponse == 2)
			{
				response = "Do you really think so?";
			}
			else if (whichResponse == 3)
			{
				response = "You don't say.";
			}

			return response;
		}
		
		/**
		 * Take a statement with "I want to <something>." and transform it into 
		 * "What would it mean to <something>?"
		 * @param statement the user statement, assumed to contain "I want to"
		 * @return the transformed statement
		 */
		private String transformIWantToStatement(String statement)
		{
			//  Remove the final period, if there is one
			statement = statement.trim();
			String lastChar = statement.substring(statement
					.length() - 1);
			if (lastChar.equals("."))
			{
				statement = statement.substring(0, statement
						.length() - 1);
			}
			int psn = findKeyword (statement, "I want to", 0);
			String restOfStatement = statement.substring(psn + 9).trim();
			return "What would it mean to " + restOfStatement + "?";
		}

		
		/**
		 * Take a statement with "I want <something>." and transform it into 
		 * "Would you really be happy if you had <something>?"
		 * @param statement the user statement, assumed to contain "I want"
		 * @return the transformed statement
		 */
		private String transformIWantStatement(String statement)
		{
			//  Remove the final period, if there is one
			statement = statement.trim();
			String lastChar = statement.substring(statement
					.length() - 1);
			if (lastChar.equals("."))
			{
				statement = statement.substring(0, statement
						.length() - 1);
			}
			int psn = findKeyword (statement, "I want", 0);
			String restOfStatement = statement.substring(psn + 6).trim();
			return "Would you really be happy if you had " + restOfStatement + "?";
		}
		
		/**
		 * Take a statement with "you <something> me" and transform it into 
		 * "What makes you think that I <something> you?"
		 * @param statement the user statement, assumed to contain "you" followed by "me"
		 * @return the transformed statement
		 */
		private String transformYouMeStatement(String statement)
		{
			//  Remove the final period, if there is one
			statement = statement.trim();
			String lastChar = statement.substring(statement
					.length() - 1);
			if (lastChar.equals("."))
			{
				statement = statement.substring(0, statement
						.length() - 1);
			}
			
			int psnOfYou = findKeyword(statement, "you", 0);
			int psnOfMe = findKeyword(statement, "me", psnOfYou + 3);
			
			String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
			return "What makes you think that I " + restOfStatement + " you?";
		}
		
		/**
		 * Take a statement with "I <something> you" and transform it into 
		 * "Why do you <something> me?"
		 * @param statement the user statement, assumed to contain "I" followed by "you"
		 * @return the transformed statement
		 */
		private String transformIYouStatement(String statement)
		{
			//  Remove the final period, if there is one
			statement = statement.trim();
			String lastChar = statement.substring(statement
					.length() - 1);
			if (lastChar.equals("."))
			{
				statement = statement.substring(0, statement
						.length() - 1);
			}
			
			int psnOfI = findKeyword(statement, "I", 0);
			int psnOfYou = findKeyword(statement, "you", psnOfI);
			
			String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
			return "Why do you " + restOfStatement + " me?";
		}

	}

