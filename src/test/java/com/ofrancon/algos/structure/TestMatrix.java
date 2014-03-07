package com.ofrancon.algos.structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestMatrix {

	@Test
	public void testToString00Matrix() {
		int[][] a = new int[0][0];
		try {
			Matrix matrixA = new Matrix(a);
			fail("The array is not valid: an IllegalArgumentException should have been thrown");
		} catch (Throwable t) {
			assertTrue("Should have an IllegalArgumentException", t instanceof IllegalArgumentException);
			assertTrue("Should complain about the number of rows", t.getMessage().contains("number of rows"));
		}
	}

	@Test
	public void test10Matrix() {
		int[][] a = new int[1][0];
		try {
			Matrix matrixA = new Matrix(a);
			fail("The array is not valid: an IllegalArgumentException should have been thrown");
		} catch (Throwable t) {
			assertTrue("Should have an IllegalArgumentException", t instanceof IllegalArgumentException);
			assertTrue("Should complain about the number of rows", t.getMessage().contains("number of columns"));
		}
	}

	@Test
	public void test11Matrix() {
		int[][] a = { { 1 } };
		Matrix matrixA = new Matrix(a);
		testMatrix("1x1 matrix", matrixA, 1, 1);
	}

	@Test
	public void test31Matrix() {
		int[][] a = { { 1 }, { 2 }, { 3 } };
		Matrix matrixA = new Matrix(a);
		testMatrix("3x1 matrix", matrixA, 3, 1);
	}

	@Test
	public void test32Matrix() {
		int[][] a = { { 11, 12 }, { 21, 22 }, { 31, 32 } };
		Matrix matrixA = new Matrix(a);
		testMatrix("3x2 matrix", matrixA, 3, 2);
	}

	@Test
	public void testEmpty33Matrix() {
		int[][] a = new int[3][3];
		Matrix matrixA = new Matrix(a);
		testMatrix("Empty 3x3 matrix", matrixA, 3, 3);
	}

	private void testMatrix(String matrixName, Matrix matrixA, int nbRows, int nbColumns) {
		System.out.println(matrixName + ":\n" + matrixA.toString());
		assertEquals("Matrix does not contain the expected number of rows", matrixA.getNumberOfRows(), nbRows);
		assertEquals("Matrix does not contain the expected number of columns", matrixA.getNumberOfColumns(), nbColumns);
	}

	@Test
	public void testMultiplyMatrix() {
		int[][] a = { { 2, 3 }, { 3, 4 }, { 4, 5 } };
		int[][] b = { { 2, 3, 4 }, { 3, 4, 5 } };
		int[][] c = { { 13, 18, 23 }, { 18, 25, 32 }, { 23, 32, 41 } };

		Matrix matrixA = new Matrix(a);
		Matrix matrixB = new Matrix(b);
		Matrix matrixC = matrixA.multiply(matrixB);

		int[][] output = matrixC.getArray();
		checkMatrix(c, output);
	}

	@Test
	public void testMultiplyArray() {
		// Input
		int[][] a = { { 2, 3 }, { 3, 4 }, { 4, 5 } };
		int[][] b = { { 2, 3, 4 }, { 3, 4, 5 } };
		System.out.println("Input A:\n" + new Matrix(a));
		System.out.println("Input B:\n" + new Matrix(b));
		// Expected output
		int[][] c = { { 13, 18, 23 }, { 18, 25, 32 }, { 23, 32, 41 } };

		int[][] output = Matrix.multiply(a, b);
		System.out.println("Output C:\n" + new Matrix(output));
		checkMatrix(c, output);
	}

	private void checkMatrix(int[][] expected, int[][] actual) {
		assertEquals("Didn't get the expected number of rows", expected.length, actual.length);
		assertEquals("Didn't get the expected number of columns", expected[0].length, actual.length);

		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[0].length; j++) {
				assertEquals("c(" + i + "," + j + ") is not correct", expected[i][j], actual[i][j]);
			}
		}
	}
}
