package io.windfree.util.array;

public class ArrayLotateUtil {
	public  static void printArray(int[][] arr) {
		int rowCount = arr.length;
		int colCount = arr[0].length;
		for(int i = 0; i < rowCount; i++) {
			System.out.println();
			for(int j = 0; j < colCount; j++) {
				System.out.print(arr[i][j] + "   ");
			}
		}
	}
	
	public static int[][] rotate90Clockwise(int[][] orgArr) {
		//orgArray(row,col) --> rotatedArray(col, (rowCount -1 )- row) 
		int rowCount = orgArr.length;
		int colCount = orgArr[0].length;
		
		int[][] targetArr = new int[colCount][rowCount];
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0 ; j < colCount; j++) {
				targetArr[j][rowCount-1-i] =  orgArr[i][j];
			}
		}
		
		return targetArr;
	}
	
	public static void main(String[] args) {
		int arr[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
		printArray(arr);
		System.out.println();
		arr = rotate90Clockwise(arr);
		printArray(arr);
		
		System.out.println();
		arr = rotate90Clockwise(arr);
		printArray(arr);
		
		System.out.println();
		arr = rotate90Clockwise(arr);
		printArray(arr);
		
		System.out.println();
		arr = rotate90Clockwise(arr);
		printArray(arr);
	}
}
