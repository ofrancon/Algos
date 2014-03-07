package com.ofrancon.algos;

public class FindMatch {

	public FindMatch() {
	}

	/**
	 * Find the subString into the string
	 * 
	 * @param string
	 *            The big string
	 * @param subString
	 *            The substring to find in the big string.
	 * @return
	 */
	public int findMatch(String string, String subString) {
		// Check the arguments are not null or empty
		if (isEmpty(string) || isEmpty(subString)) {
			return -1;
		}

		for (int i = 0; i <= (string.length() - subString.length()); i++) {
			int j = 0;
			while (j < subString.length() && (string.charAt(i + j) == subString.charAt(j))) {
				j++;
			}
			if (j == subString.length()) {
				return i;
			}
		}
		return -1;
	}

	private boolean isEmpty(String s) {
		if (s == null || s.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public int findMatch(char[] string, char[] subString) {
		if (isEmpty(string) || isEmpty(subString)) {
			return -1;
		}

		for (int i = 0; i <= string.length - subString.length; i++) {
			int j = 0;
			while (j < subString.length && string[i + j] == subString[j]) {
				j++;
			}
			if (j == subString.length) {
				return i;
			}
		}

		return -1;
	}

	private boolean isEmpty(char[] s) {
		if (s == null || s.length == 0) {
			return true;
		} else {
			return false;
		}
	}
}
