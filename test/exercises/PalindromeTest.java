package exercises;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PalindromeTest {

	Palindrome testPalindrome = new Palindrome("resource/words.txt");

	@Test
	void testLoadWords() {
		assertTrue(testPalindrome.loadWords("resource/words.txt")
				.contains("conundrum"));
	}

	// 2. Test that a word exists in the dictionary
	@Test
	void testIsInDictionary() {
		assertFalse(testPalindrome.isInDictionary("edgar"));	// As a proper name…
		assertTrue(testPalindrome.isInDictionary("Edgar"));		// the first letter would be capitalized. 
	}

	// 3. Test that a word is a palindrome
	@Test
	void testIsAPalindrome () {
		assertTrue(testPalindrome.isAPalindrome("Was it a cat I saw?"));
		assertTrue(testPalindrome.isAPalindrome("Red rum, sir, is murder!"));
		assertFalse(testPalindrome.isAPalindrome("Edgar"));
	}

}
