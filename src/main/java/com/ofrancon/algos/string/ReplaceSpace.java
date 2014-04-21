package com.ofrancon.algos.string;

public class ReplaceSpace {

	public String replaceSpace(String s) {
		char[] chars = s.toCharArray();
		int last = chars.length - 1;
		boolean isInBuffer = true;
		for (int i = last; i >= 0; i--) {
			char c = chars[i];
			if (isInBuffer) {
				if (c != ' ') {
					isInBuffer = false;
					chars[last] = c;
					last--;
				}
			} else {
				if (c == ' ') {
					// replace
					chars[last] = '0';
					last--;
					chars[last] = '2';
					last--;
					chars[last] = '%';
					last--;
				} else {
					chars[last] = c;
					last--;
				}
			}
		}
		return new String(chars);
	}

	public static void main(String[] args) {
		// Check the input
		String in = args[0];
		System.out.println("In: " + in);
		ReplaceSpace rs = new ReplaceSpace();
		String out = rs.replaceSpace(in);
		System.out.println("Out: " + out);
	}
}
