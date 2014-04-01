package com.ofrancon.algos;

import java.util.Arrays;

/**
 * The maximum-subarray problem
 * Introduction to algorithms, T. Cormen et al., p. 68
 * http://en.wikipedia.org/wiki/Maximum_subarray_problem
 */
public class FindMaxSubArray {

	// O(n^2)
	public MaxSubArray findMaxSubArrayBruteForce(int[] array) {
		int buyIndex = 0;
		int sellIndex = 0;
		int maxProfit = 0;
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i; j < array.length; j++) {
				int profit = array[j] - array[i];
				if (profit > maxProfit) {
					buyIndex = i + 1;
					sellIndex = j;
					maxProfit = profit;
				}
			}
		}
		return new MaxSubArray(buyIndex, sellIndex, maxProfit);
	}

	// O(n lg n)
	public MaxSubArray findMaxSubArrayDivideAndConquer(int[] array) {
		int[] maxSubArray = createMaxSubArray(array);
		return findMaxSubArray(maxSubArray, 1, maxSubArray.length - 1);
	}

	private MaxSubArray findMaxSubArray(int[] array, int low, int high) {
		MaxSubArray msa;
		if (high == low) {
			// Base case: only 1 element
			msa = new MaxSubArray(low, high, array[low]);
		} else {
			int mid = (low + high) / 2;
			MaxSubArray left = findMaxSubArray(array, low, mid);
			MaxSubArray right = findMaxSubArray(array, mid + 1, high);
			MaxSubArray cross = findMaxCrossingSubArray(array, low, mid, high);
			if (left.getMaxSum() >= right.getMaxSum() && left.getMaxSum() >= cross.getMaxSum()) {
				return left;
			} else if (right.getMaxSum() >= left.getMaxSum() && right.getMaxSum() >= cross.getMaxSum()) {
				return right;
			} else {
				return cross;
			}
		}
		return msa;
	}

	private int[] createMaxSubArray(int[] array) {
		int[] maxSubArray = new int[array.length];
		for (int i = 1; i < array.length; i++) {
			maxSubArray[i] = array[i] - array[i - 1];
		}
		System.out.println("MaxSubArray=" + Arrays.toString(maxSubArray));
		return maxSubArray;
	}

	private MaxSubArray findMaxCrossingSubArray(int[] array, int low, int mid, int high) {
		int leftSum = Integer.MIN_VALUE;
		int maxLeft = mid;
		int sum = 0;
		for (int i = mid; i >= low; i--) {
			sum = sum + array[i];
			if (sum > leftSum) {
				leftSum = sum;
				maxLeft = i;
			}
		}

		int rightSum = Integer.MIN_VALUE;
		int maxRight = mid;
		sum = 0;
		for (int i = mid + 1; i <= high; i++) {
			sum = sum + array[i];
			if (sum > rightSum) {
				rightSum = sum;
				maxRight = i;
			}
		}
		return new MaxSubArray(maxLeft, maxRight, leftSum + rightSum);
	}

	// O(n)
	public MaxSubArray findMaxSubArrayLinear(int[] array) {
		int[] maxSubArray = createMaxSubArray(array);
		MaxSubArray msa = new MaxSubArray(1, 1, maxSubArray[1]);
		MaxSubArray maxEndingHere = new MaxSubArray(1, 1, maxSubArray[1]);
		for (int i = 2; i < maxSubArray.length; i++) {
			if (maxEndingHere.getMaxSum() < 0) {
				maxEndingHere = new MaxSubArray(i, i, maxSubArray[i]);
			} else {
				maxEndingHere = new MaxSubArray(maxEndingHere.getStartIndex(), i, maxEndingHere.getMaxSum() + maxSubArray[i]);
			}
			if (maxEndingHere.isGreaterThan(msa)) {
				msa = maxEndingHere;
			}
		}
		return msa;
	}

	class MaxSubArray {
		private final int startIndex;
		private final int endIndex;
		private final int maxSum;

		public MaxSubArray(int start, int end, int maxSum) {
			this.startIndex = start;
			this.endIndex = end;
			this.maxSum = maxSum;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("MaxSubArray: ");
			sb.append("startIndex: ").append(startIndex);
			sb.append(", endIndex: ").append(endIndex);
			sb.append(", maxSum: ").append(maxSum);
			return sb.toString();
		}

		public int getStartIndex() {
			return startIndex;
		}

		public int getEndIndex() {
			return endIndex;
		}

		public int getMaxSum() {
			return maxSum;
		}

		public boolean isGreaterThan(MaxSubArray other) {
			if (this.getMaxSum() >= other.getMaxSum()) {
				return true;
			}
			return false;
		}
	}

}
