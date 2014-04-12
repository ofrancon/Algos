package com.ofrancon.algos.string;

public class StringCompressor {

	public String compress(String s) {
		char[] chars = s.toCharArray();
		char last = chars[0];
		int count = 1;
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < chars.length; i++) {
			char c = chars[i];
			if (c == last) {
				count++;
			} else {
				sb.append(last).append(count);
				last = c;
				count = 1;
			}
		}
		sb.append(last).append(count);
		String output = sb.toString();
		if (output.length() > s.length()) {
			return s;
		} else {
			return output;
		}
	}

	public static void main(String[] args) {
		// Check the input
		String in = args[0];
		System.out.println("In: " + in);
		StringCompressor sc = new StringCompressor();
		String out = sc.compress(in);
		System.out.println("Out: " + out);
	}
}
