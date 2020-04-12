package exercises;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BooleanSupplier;

import org.junit.jupiter.api.Test;

import examples.Regex;

class RegexTest {
	
	Regex regularExpressionParser = new Regex ();

	@Test
	void testForValidEmailAddress () {
		assertTrue(regularExpressionParser.theEmailIsValid ("JohnDoe@gmail.com"));
	}
	
	@Test
	void testForValidPhoneNumber () {
		assertTrue(regularExpressionParser.thePhoneNumberIsValid ("(555) 123-4567"));
	}

}
