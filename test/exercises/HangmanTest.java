package exercises;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HangmanTest {

	Hangman hangman = new Hangman();

	@Test
	void testTheStringParser() {
		assertTrue(hangman.thePuzzleContainsSpecialCharacters("June Clarke"));
		assertFalse(hangman.thePuzzleContainsSpecialCharacters("June"));
	}
}
