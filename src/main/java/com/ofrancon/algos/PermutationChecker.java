package com.ofrancon.algos;

import java.util.HashMap;

public class PermutationChecker {

	public boolean isPermutation(String s1, String s2) {
		if (s1.equals(s2) || s1.length() != s2.length()) {
			return false;
		}
		HashMap<Character, Integer> map1 = toMap(s1, null);
		HashMap<Character, Integer> map2 = toMap(s2, map1);
		return map1.equals(map2);
	}

	private HashMap<Character, Integer> toMap(String s, HashMap<Character, Integer> ref) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			Integer refCount = null;
			Character c = Character.valueOf(chars[i]);
			// If we have a reference, make sure we have this char
			if (ref != null) {
				refCount = ref.get(c);
				if (refCount.intValue() == 0) {
					// The ref doesn't have this char, it's not a permutation
					return null;
				}
			}
			Integer count = map.get(c);
			if (count == null) {
				map.put(c, Integer.valueOf(1));
			} else {
				int newCount = count.intValue() + 1;
				// Make sure we don't have more chars than the ref
				if (refCount != null) {
					if (newCount > refCount.intValue()) {
						return null;
					}
				}
				map.put(c, Integer.valueOf(newCount));
			}
		}
		return map;
	}

	public static void main(String[] args) {
		// Check the input
		PermutationChecker pc = new PermutationChecker();
		System.out.println(pc.isPermutation(args[0], args[1]));
	}
}
