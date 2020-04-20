package exercises;

import java.util.ArrayList;
import java.util.List;

import examples.FileHelper;

public class Palindrome {

	List<String> wordList = new ArrayList<>();

	Palindrome(String pathname) {
		this.wordList = this.loadWords("resource/words.txt");
	}

	public List<String> loadWords(String pathname) {
		return FileHelper.loadFileContentsIntoArrayList(pathname);
	}

	public boolean isInDictionary(String word) {
		return wordList.contains(word);
	}

	public boolean isAPalindrome(String string) {
		String candidate = new String(string)
				.replaceAll("[ \t,.:;?!][ \t,.:;?!]*", "").toLowerCase();
		for (int front = 0, back = candidate.length()
				- 1; front <= back; front++, back--) {
			if (candidate.charAt(front) != candidate.charAt(back)) {
				return false;
			}
		}
		return true;
	}
}
