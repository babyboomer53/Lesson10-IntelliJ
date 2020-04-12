package examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	
	private String emailRegex = "";
	
	public static void main(String[] args) {

		// Regex reference guide: https://cs.lmu.edu/~ray/notes/regex/
		
		// Exercise: validate email addresses and phone numbers
	}

	public boolean theEmailIsValid(String emailAddress) {
		Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher matcher = emailPattern.matcher(emailAddress);
		return matcher.find();
	}

	public boolean thePhoneNumberIsValid(String phoneNumber) {
		Pattern phonePattern = Pattern.compile("^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$");
		Matcher matcher = phonePattern.matcher(phoneNumber);
		return matcher.find();
	}
	
}
