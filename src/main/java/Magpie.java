/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *          Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
    /**
     * Get a default greeting   
     * @return a greeting
     */
    public String getGreeting()
    {
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
        boolean contains_result = false;
        for (int i = 0; i < response.length(); i++){
            if (response.charAt(i) == 32) {
                contains_result = true;
                break;
            }
        }
        if (contains_result != true) {
            response = "Your input does not contain anything.";
        }
        else if (statement.indexOf("no") >= 0) {
            response = "No, don't you talk to me like that.";
        }
        else if (statement.indexOf("sister") >= 0 || statement.indexOf("brother") >= 0) {
            response = "Tell me more about your family.";
        }
        else if (statement.indexOf("dog") >= 0) {
			response = "Tell me more about your pets.";
        }
        else if (statement.indexOf("cat") >= 0) {
			response = "Tell me more about your pets.";
        }
        else if (statement.indexOf("Nathan Lin") >= 0) {
            response = "Nathan Lin! He is a great AP comp sci teacher.";
        }
        else if (statement.indexOf("mother") >= 0) {
            response = "How old is she?";
        }
        else if (statement.indexOf("father") >= 0) {
            response = "Wait, what is up with your father?";
        }
        else if (statement.indexOf("football") >= 0) {
            response = "Football is the best sport. Tell me more about it.";
        }
        else if (statement.indexOf("gooba") >= 0) {
            response = "Goooooooooooba.";
        }
        else {
            response = getRandomResponse();
        }
        return response;
    }
    
    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    public String getRandomResponse() {
        final int NUMBER_OF_RESPONSES = 4;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
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

    // Checks to see if the String word appears as a whole word
    // in the String str (in this case, a "whole word" means that 
    // word is not just a substring of some larger word in str)

    // This method should work regardless of the capitalization 
    // of str or word

    // The method returns the index of the first character in word
    // if it is found, and returns -1 otherwise. 
    public int findWord(String str, String word) {
        String input = str.trim();
		int word_pos = input.toLowerCase().indexOf(word.toLowerCase(), 0);
		while (word_pos >= 0) {
            String not_u = " ";
            String u = " ";
			if (word_pos > 0) {
				not_u = input.substring (word_pos - 1, word_pos).toLowerCase();
			}
			if (word_pos + word.length() < input.length()) {
				u = input.substring(word_pos + word.length(), word_pos + word.length() + 1).toLowerCase();
			}
			if (((not_u.compareTo("a") < 0 ) || (not_u.compareTo("z") > 0)) && ((u.compareTo("a") < 0 ) || (u.compareTo("z") > 0))){
				return word_pos;
			}
            word_pos = input.indexOf(word.toLowerCase(), word_pos + 1);
        }
        return -1;
    }

    
    // We will work on the following methods later!

    /**
     * Take a statement with "I want <something>." and transform it into 
     * "Would you really be happy if you had <something>?"
     * @param statement the user statement, assumed to contain "I want"
     * @return the transformed statement
     */
    public String transformIWantStatement(String statement) {
        statement = statement.trim();
        String ch = statement.substring(statement.length() - 1);
        if (ch.equals(".")) {
            statement = statement.substring(0, statement.length() - 1);
        }
        int word_pos = findWord(statement, "I want");
        String remaining = statement.substring(word_pos + 7);
        return "Would you really be happy if you had " + remaining + "?";
    }

    /**
     * Take a statement with "I <something> you" and transform it into 
     * "Why do you <something> me?"
     * @param statement the user statement, assumed to contain "I" followed by "you"
     * @return the transformed statement
     */
    public String transformIYouStatement(String statement) {
        statement = statement.trim();
		String ch = statement.substring(statement.length() - 1);
		if (ch.equals(".")) {
			statement = statement.substring(0, statement.length() - 1);
		}
		int I = findWord(statement, "I");
		int You = findWord (statement, "you");
		String remaining = statement.substring(I + 1, You).trim();
		return "Why do you " + remaining + " me?";
    }

    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    public String transformIWantToStatement(String statement) {
        statement = statement.trim();
        String ch = statement.substring(statement.length() - 1);
        if (ch.equals(".")) {
            statement = statement.substring(0, statement.length() - 1);
        }
        int word_pos = findWord(statement, "I want to");
        String remaining = statement.substring(word_pos + 9).trim();
        return "What would it mean to " + remaining + "?";
    }

    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     * @param statement the user statement, assumed to contain "you" followed by "me"
     * @return the transformed statement
     */
    public String transformYouMeStatement(String statement) {
        statement = statement.trim();
        String ch = statement.substring(statement.length() - 1);
        if (ch.equals(".")) {
            statement = statement.substring(0, statement.length() - 1);
        }
        int You = findWord(statement, "you");
        int Me = findWord(statement, "me");
        String remaining = statement.substring(You + 3, Me).trim();
        return "What makes you think that I " + remaining + " you?";
    }
}
