package com.ofrancon.algos.structure;

/**
 * A Matrix implementation backed by a 2D array
 * Introduction to Algorithms, T. Cormen et al., p. 1217
 */
public class Matrix {

	private final int[][] _matrix;

	public Matrix(int rows, int columns) {
		this(new int[rows][columns]);
	}

	public Matrix(int[][] array) {
		check(array);
		_matrix = array;
	}

	private static final void check(int[][] array) {
		if (array == null || array.length <= 0) {
			throw new IllegalArgumentException("The number of rows must be greater than 0");
		}
		if (array[0].length <= 0) {
			throw new IllegalArgumentException("The number of columns must be greater than 0");
		}
	}

	public int[][] getArray() {
		return _matrix;
	}

	public int getNumberOfRows() {
		return _matrix.length;
	}

	public int getNumberOfColumns() {
		return _matrix[0].length;
	}

	/**
	 * Matrix multiplication
	 * 
	 * @param matrixB
	 *            The matrix to multiply by
	 * @return a new Matrix
	 * @throws IllegalArgumentException
	 *             if the matrices don't have the right sizes
	 */
	public Matrix multiply(Matrix matrixB) {
		int[][] output = multiply(this.getArray(), matrixB.getArray());
		return new Matrix(output);
	}

	/**
	 * Matrix multiplication
	 * The algorithm design manual, S. Skiena, p. 45, 401
	 * O(xyz) = O(n^3) when all 3 dimensions are equal, i.e. a cubic algorithm
	 * 
	 * @param a
	 *            An x by y matrix
	 * @param b
	 *            A y by z matrix
	 * @return A x by z matrix
	 * @throws IllegalArgumentException
	 *             if a and b don't have any element or
	 *             if a.y is different from b.y
	 */
	public static final int[][] multiply(int[][] a, int[][] b) {
		check(a);
		check(b);

		int aX = a.length;
		int aY = a[0].length;
		int bY = b.length;
		int bZ = b[0].length;
		if (aY != bY) {
			throw new IllegalArgumentException("a.y and b.y don't match");
		}

		int[][] c = new int[aX][bZ];
		// For each row in C
		for (int i = 0; i < aX; i++) {
			// For each column in C
			for (int j = 0; j < bZ; j++) {
				// No need to intialize in Java - 0 is already the default value
				// c[i][j] = 0;
				for (int k = 0; k < bY; k++) {
					c[i][j] += a[i][k] * b[k][j];
				}
			}
		}

		return c;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		// For each row
		for (int i = 0; i < _matrix.length; i++) {
			StringBuilder row = new StringBuilder();
			// It's not the first row, so it's a new row, i.e. a new line
			if (i != 0) {
				row.append("]\n[");
			}
			// For each column
			for (int j = 0; j < _matrix[0].length; j++) {
				// It's not the first column so separate it from the previous
				// one by a space
				if (j > 0) {
					row.append(" ");
				}
				row.append(_matrix[i][j]);
			}
			sb.append(row.toString());
		}
		// Close the last row
		sb.append("]");
		return sb.toString();
	}

}
