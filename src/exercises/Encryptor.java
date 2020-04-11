package exercises;

public class Encryptor {

	public static String encrypt(String encryptMe) {
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < encryptMe.length(); i++) {
			int numericalValue = encryptMe.charAt(i);
			numericalValue+=2;
			builder.append((char) numericalValue);
		}
		return builder.toString();
	}

	public static String decrypt(String string) {
		// 
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < string.length(); i++) {
			int numericalValue = string.charAt(i);
			numericalValue-=2;
			builder.append((char) numericalValue);
		}
		return builder.toString();

	}

}
