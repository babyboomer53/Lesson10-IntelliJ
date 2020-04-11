package examples;

import java.util.function.BooleanSupplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	
	public static void main(String[] args) {
		// Pattern
		Pattern emailPattern = Pattern.compile("[.]*");
		Pattern phonePattern = Pattern.compile("[^1-9,0]{,3}[.-]*[0-9]{,3}[.-]*[0-9]{,4}");
		
		// Matcher
		Matcher matcher = pattern.matcher("joonspoon1");
		
		System.out.println(matcher.find());
		
		// Regex reference guide: https://cs.lmu.edu/~ray/notes/regex/
		
		// Exercise: validate email addresses and phone numbers
	}

	public boolean theEmailIsValid(String string) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean thePhoneNumberIsValid(String string) {
		// TODO Auto-generated method stub
		return true;
	}
	

}
