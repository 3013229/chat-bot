import java.lang.String;

/**
 * A program to carry on conversations with a human user. This is the initial
 * version that:
 * <ul>
 * <li>Uses indexOf to find strings</li>
 * <li>Handles responding to simple words and phrases</li>
 * </ul>
 * This version uses a nested if to handle default responses.
 * 
 * @author Laurie White
 * @version April 2012
 */
public class Magpie2 {
	/**
	 * Get a default greeting
	 * 
	 * @return a greeting
	 */
	public String getGreeting() {
		return "Hello, let's talk.";
	}

	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement) {
		String response = "";
		if (statement.trim().length() > 0) {
			if (statement.indexOf("no") >= 0) {
				response = "Why so negative?";
			} else if (statement.indexOf("mother") >= 0 
					|| statement.indexOf("father") >= 0
					|| statement.indexOf("sister") >= 0 
					|| statement.indexOf("brother") >= 0) {
				response = "Tell me more about your family.";
			} else if (statement.indexOf("dog") >= 0 
					|| statement.indexOf("cat") >= 0 
					|| statement.indexOf("pet") >= 0
					|| statement.indexOf("horse") >= 0) {
				response = "Tell me more about your pets.";
			} else if (statement.indexOf("meth") >= 0 
					|| statement.indexOf("Seth") >= 0
					|| statement.indexOf("watgen") >= 0) {

				response = "Oh Seth loves prison!";
			} else if (statement.indexOf("Mrs. O") >= 0 
					|| statement.indexOf("Mrs. O'Laughlin") >= 0
					|| statement.indexOf("teacher") >= 0) {

				response = "Your teacher sounds very nice!";
			} else if (statement.indexOf("Fortnite") >= 0 
					|| statement.indexOf("fortnite") >= 0
					|| statement.indexOf("ninja") >= 0) {

				response = "Fortnite is so EPIC!! Let's get some vicroys!";
			} else if (statement.indexOf("Mrs. O") >= 0 
					|| statement.indexOf("Mrs. O'Laughlin") >= 0
					|| statement.indexOf("teacher") >= 0) {

				response = "Your teacher sounds very fun!";
			} else if (statement.indexOf("Panera") >= 0 
					|| statement.indexOf("work") >= 0
					|| statement.indexOf("job") >= 0) {

				response = "What do you do for work?";
			} else {
				response = getRandomResponse();
			}
		} else {
			response = "Please enter something!";
		}
		return response;
	}

	/**
	 * Pick a default response to use if nothing else fits.
	 * 
	 * @return a non-committal string
	 */
	private String getRandomResponse() {
		final int NUMBER_OF_RESPONSES = 4;
		double r = Math.random();
		int whichResponse = (int) (r * NUMBER_OF_RESPONSES);
		String response = "";

		if (whichResponse == 0) {
			response = "Interesting, tell me more.";
		} else if (whichResponse == 1) {
			response = "Hmmm.";
		} else if (whichResponse == 2) {
			response = "Do you really think so?";
		} else if (whichResponse == 3) {
			response = "You don't say.";
		}

		return response;
	}
}
