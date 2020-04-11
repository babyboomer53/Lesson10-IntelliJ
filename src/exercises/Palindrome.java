package exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

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
		for (int forward = 0, backward = candidate.length()
				- 1; forward <= backward; forward++, backward--) {
			if (candidate.charAt(forward) != candidate.charAt(backward)) {
				return false;
			}
		}
		return true;
	}
}
