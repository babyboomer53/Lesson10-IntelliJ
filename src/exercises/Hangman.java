package exercises;

import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import examples.FileHelper;

import java.security.SecureRandom;

import java.util.regex.*;

/*
*
* This program implements a rendition of the Hangman game in which participants
* try  to guess  a  word or  phrase  for which  they know  only  the number  of
* characters  in that  phrase. It is the premise of the popular television game
* show, "Wheel of Fortune."
*
* In  this version  of Hangman, users are  presented with a window in which the
* characters  in the  phrase are represented by underscores. Each time the user
* enters  a character  contained in the phrase, the positions of that character
* in the phrase are revealed by replacing the corresponding underscore with the
* character.  If the  user enters a character that occurs multiple times in the
* phrase, all positions will be revealed.
*
* If  the  user guesses incorrectly  more than nine  times before the puzzle is
* solved,  they lose the game. A number is displayed in the window representing
* the  number  of failures left. The  number starts at nine, and is decremented
* each time the user guesses incorrectly.
*
* As  originally submitted,  the  program implemented  the basic  functionality
* described above. However, the original author recommended a few enhancements.
* Specifically,  the program would not load a new puzzle unless the user failed
* to  solve  the previous  one. Ideally  the program should  load a new puzzle,
* whether the user wins or loses.
*
* The data used to populate the program's inventory of puzzles was hardcoded in
* the  class, resulting  in an implementation that was inflexible and difficult
* to scale.
*
* I  modified the program so that a new puzzle would be loaded whether the user
* won  or lost. Also,  I modified the program so that the data used to populate
* the  puzzle stack  is obtained dynamically (i.e., read at runtime from a flat
* file on disk).
*
* While testing the new process for managing the puzzle data, it occurred to me
* that  the data structure (i.e., a stack) in which the puzzles were stored was
* too  restrictive. The  records are sorted in inverse-alphabetical order. With
* tens of thousands of records, one would have to play hundreds of games before
* encountering  a phrase beginning with anything other than the letter Z. Being
* able  to access  the  records randomly  would alleviate  the  problem. So,  I
* switched  the  data structure containing the  puzzles from a Stack to a List,
* and switched the access mode from sequential to random.
*
* I  noticed that when  comparing user input to the puzzle, the program was not
* considering upper and lowercase versions of the same character as equivalent.
* For  example,  if the  user entered  the letter  's' while  trying to solve a
* puzzle  containing  the  word  "Stork", the  program  wouldn't  consider  the
* lowercase  's' and the uppercase 'S' as equivalent. I modified the program so
* that  this  comparison is  now case-insensitive. In  other words, a lowercase
* letter and its uppercase counterpart are considered the same.
*
*/

public class Hangman extends KeyAdapter {

	ArrayList<String> puzzles = new ArrayList<String>();
	ArrayList<JLabel> boxes = new ArrayList<JLabel>();
	int lives = 9;
	JLabel livesLabel = new JLabel("" + lives);
	private int characterCount;
	List<String> puzzleList = new ArrayList<>();
	SecureRandom randomNumberGenerator = new SecureRandom();
	int randomNumber;

	public static void main(String[] args) {
		Hangman hangman = new Hangman();
		hangman.addPuzzles();
		hangman.createUI();
	}

	public List<String> loadPuzzles(String pathname) {
		return FileHelper.loadFileContentsIntoArrayList(pathname);
	}

	private void addPuzzles() {
		puzzleList = loadPuzzles("resource/specialcharacters.txt");
		puzzles.addAll(puzzleList);
	}

	JPanel panel = new JPanel();
	private String puzzle;

	private void createUI() {
		playDeathKnell();
		JFrame frame = new JFrame("June's Hangman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(livesLabel);
		while (true) {
			try {
				loadNextPuzzle();
			} catch (InvalidCharactersInStringException exception) {
				System.err.println(exception.getMessage());
				continue;
			}
			break;
		}
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		frame.addKeyListener(this);
	}

	public boolean thePuzzleContainsSpecialCharacters(String string) {
		Pattern specialCharacterPattern = Pattern.compile("[^a-zA-Z0-9]");
		Matcher matcher = specialCharacterPattern.matcher(string);
		return matcher.find();
	}

	private void loadNextPuzzle() throws InvalidCharactersInStringException {
		removeBoxes();
		lives = 9;
		livesLabel.setText("" + lives);
		randomNumber = randomNumberGenerator.nextInt(puzzles.size() - 1) + 1;
		puzzle = puzzles.get(randomNumber);
		if (thePuzzleContainsSpecialCharacters(puzzle))
			throw new InvalidCharactersInStringException(String.format(
					"The puzzle (%s) contains special characters.", puzzle));
		System.out.println("puzzle is now " + puzzle);
		createBoxes();
		characterCount = 0;
	}

	public void keyTyped(KeyEvent arg0) {
		System.out.println(arg0.getKeyChar());
		updateBoxesWithUserInput(arg0.getKeyChar());
		if (lives == 0 || characterCount == puzzle.length()) {
			playDeathKnell();
			while (true) {
				try {
					loadNextPuzzle();
				} catch (InvalidCharactersInStringException exception) {
					System.err.println(exception.getMessage());
					continue;
				}
				break;
			}
		}
	}

	private void updateBoxesWithUserInput(char keyChar) {
		boolean gotOne = false;
		for (int i = 0; i < puzzle.length(); i++) {
			// In the following conditional, I modified the original
			// statement so that the comparison would not be case-
			// sensitive.
			if (Character.toLowerCase(puzzle.charAt(i)) == Character
					.toLowerCase(keyChar)) {
				boxes.get(i).setText("" + puzzle.charAt(i));
				characterCount++;
				gotOne = true;
			}
		}
		if (!gotOne)
			livesLabel.setText("" + --lives);
	}

	void createBoxes() {
		for (int i = 0; i < puzzle.length(); i++) {
			JLabel textField = new JLabel("_");
			boxes.add(textField);
			panel.add(textField);
		}
	}

	void removeBoxes() {
		for (JLabel box : boxes) {
			panel.remove(box);
		}
		boxes.clear();
	}

	public void playDeathKnell() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
					new File("resource/funeral-march.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			Thread.sleep(8400);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

} // End of class Hangman

class InvalidCharactersInStringException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	InvalidCharactersInStringException(String message) {
		super(message);
	}
}
