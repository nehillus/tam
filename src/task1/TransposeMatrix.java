package task1;

public class TransposeMatrix {

	public static void main(String[] args) {
		int[][] matrixToTranspose = { { 1, 3, 5 }, { 7, 9, 11 }, { 13, 15, 17 } };
		int[][] transposedMatrix = new int[3][3];
		for (int i = 0; i < matrixToTranspose.length; i++) {
			for (int j = 0; j < matrixToTranspose.length; j++) {
				transposedMatrix[i][j] = matrixToTranspose[j][i];
				System.out.print(transposedMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
