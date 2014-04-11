package com.ofrancon.algos;

public class WordCount {

	/*
	 * java wordcount "hello world"
	 * 2
	 */

	public int count(String s) {
		char[] chars = s.toCharArray();
		int count = 0;
		char previousChar = ' ';
		for (int i = 0; i < chars.length; i++) {
			// "    hello   world   "
			// "hello world"
			// "hello   "
			char c = chars[i];
			if (c != ' ') {
				if (previousChar == ' ') {
					count++;
				}
			}
			previousChar = c;
		}
		return count;
	}

	private boolean isValidInput(String[] args) {
		if (args != null && args.length == 1) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		WordCount wc = new WordCount();
		if (wc.isValidInput(args)) {
			int count = wc.count(args[0]);
			System.out.println(count);
		} else {
			System.out.println("Invalid input: please provide 1 and only 1 parameter.");
		}
	}

}
