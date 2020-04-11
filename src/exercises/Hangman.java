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
		puzzleList = loadPuzzles("resource/words.txt");
		for (String string : puzzleList) {
			puzzles.add(string);
		}
	}

	JPanel panel = new JPanel();
	private String puzzle;

	private void createUI() {
		playDeathKnell();
		JFrame frame = new JFrame("June's Hangman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(livesLabel);
		loadNextPuzzle();
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		frame.addKeyListener(this);
	}

	private void loadNextPuzzle() {
		removeBoxes();
		lives = 9;
		livesLabel.setText("" + lives);
		randomNumber = randomNumberGenerator.nextInt(puzzles.size() - 1) + 1;
		puzzle = puzzles.get(randomNumber);
		System.out.println("puzzle is now " + puzzle);
		createBoxes();
		characterCount = 0;
	}

	public void keyTyped(KeyEvent arg0) {
		System.out.println(arg0.getKeyChar());
		updateBoxesWithUserInput(arg0.getKeyChar());
		if (lives == 0 || characterCount == puzzle.length()) {
			playDeathKnell();
			loadNextPuzzle();
		}
	}

	private void updateBoxesWithUserInput(char keyChar) {
		boolean gotOne = false;
		for (int i = 0; i < puzzle.length(); i++) {
			// In the following conditional, I modified the original
			// statement so that the case (upper or lower) would not
			// be a factor.
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

}
