package datastructure;
/**
 * @author Bengü Demireğen
 *  */
import java.util.Arrays;

public class StrassenAlgorithm {
	 public static int[][] strassen(int[][] matrix1, int[][] matrix2) {
		 
	        int n = matrix1.length;
	        int[][] result = new int[n][n];

	        if (n == 1) 
	        {
	            result[0][0] = matrix1[0][0] * matrix2[0][0];
	            return result;
	        }

	        int[][] a11 = new int[n/2][n/2];
	        int[][] a12 = new int[n/2][n/2];
	        int[][] a21 = new int[n/2][n/2];
	        int[][] a22 = new int[n/2][n/2];
	        int[][] b11 = new int[n/2][n/2];
	        int[][] b12 = new int[n/2][n/2];
	        int[][] b21 = new int[n/2][n/2];
	        int[][] b22 = new int[n/2][n/2];

	        split(matrix1, a11, 0, 0);
	        split(matrix1, a12, 0, n/2);
	        split(matrix1, a21, n/2, 0);
	        split(matrix1, a22, n/2, n/2);
	        split(matrix2, b11, 0, 0);
	        split(matrix2, b12, 0, n/2);
	        split(matrix2, b21, n/2, 0);
	        split(matrix2, b22, n/2, n/2);

	        int[][] m1 = strassen(add(a11, a22), add(b11, b22));
	        int[][] m2 = strassen(add(a21, a22), b11);
	        int[][] m3 = strassen(a11, subtract(b12, b22));
	        int[][] m4 = strassen(a22, subtract(b21, b11));
	        int[][] m5 = strassen(add(a11, a12), b22);
	        int[][] m6 = strassen(subtract(a21, a11), add(b11, b12));
	        int[][] m7 = strassen(subtract(a12, a22), add(b21, b22));

	        int[][] p1 = add(subtract(add(m1, m4), m5), m7);
	        int[][] p2 = add(m3, m5);
	        int[][] p3 = add(m2, m4);
	        int[][] p4 = add(subtract(add(m1, m3), m2), m6);

	        join(p1, result, 0, 0);
	        join(p2, result, 0, n/2);
	        join(p3, result, n/2, 0);
	        join(p4, result, n/2, n/2);

	        return result;
	    }

	    private static void split(int[][] parent, int[][] child, int startRow, int startCol) 
	    {
	        for (int i = 0; i < child.length; i++) 
	        {
	            System.arraycopy(parent[startRow + i], startCol, child[i], 0, child.length);
	        }
	    }

	    private static void join(int[][] child, int[][] parent, int startRow, int startCol) 
	    {
	        for (int i = 0; i < child.length; i++)
	        {
	            System.arraycopy(child[i], 0, parent[startRow + i], startCol, child.length);
	        }
	    }

	    private static int[][] add(int[][] matrix1, int[][] matrix2) 
	    {
	        int n = matrix1.length;
	        int[][] result = new int[n][n];
	        
	        for (int i = 0; i < n; i++) 
	        {
	            for (int j = 0; j < n; j++) 
	            {
	                result[i][j] = matrix1[i][j] + matrix2[i][j];
	            }
	        }
	        return result;
	    }

	    private static int[][] subtract(int[][] matrix1, int[][] matrix2)
	    {
	        int n = matrix1.length;
	        int[][] result = new int[n][n];
	        for (int i = 0; i < n; i++) 
	        {
	            for (int j = 0; j < n; j++) 
	            {
	                result[i][j] = matrix1[i][j] - matrix2[i][j];
	            }
	        }
	        return result;
	    }

	    public static void main(String[] args) 
	    {
	        int[][] A = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
	        int[][] B = {{17, 18, 19, 20}, {21, 22, 23, 24}, {25, 26, 27, 28}, {29, 30, 31, 32}};

	        int[][] result = strassen(A, B);
	        System.out.println("Sonuç:");
	        for (int[] row : result) 
	        {
	            System.out.println(Arrays.toString(row));
	        }
	    }
}
