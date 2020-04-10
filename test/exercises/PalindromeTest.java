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
		assertTrue(testPalindrome.isInDictionary("edgar"));
		assertTrue(testPalindrome.isInDictionary("Edgar"));
	}

	// 3. Test that a word is a palindrome
	@Test
	void testIsAPalindrome () {
		assertTrue(testPalindrome.isAPalindrome("nurses run"));
		//assertTrue(testPalindrome.isAPalindrome("Edgar"));
	}

}
