import java.util.*;
public class HW1{
  public static void main(String[] args) {
    int height = -1;
    int length = -1;
    //height and length (counting 0 index)


    String matrixFile = "matrix.txt";
    int[][] matrix = buildMatrix(matrixFile);
    displayMatrix(matrix);

    longestPath(matrix);

  }
  public static void longestPath(int[][] matrix){

  }
  public static int[][] buildMatrix(String matrixFile){
    java.util.Scanner input = null;
    java.util.Scanner hl = null;

    try {
        input = new java.util.Scanner(new java.io.File(matrixFile));
        hl = new java.util.Scanner(new java.io.File(matrixFile));

    } catch( java.io.FileNotFoundException e ) {
        System.err.println("Error: Unable to open file " + matrixFile);
        System.exit(1);
    }
    int height = 0;
    int length = 0;
    //find height/length
    while(hl.hasNextLine()){
      String[] line = hl.nextLine().split("\\s");
      length = line.length;
      height++;
    }
    int[][] matrix = new int[height][length];
    //puts values into matrix
    int i = -1;
    while(input.hasNextLine()){
      i++;
      int j = 0;
      String[] line = input.nextLine().split("\\s");
      for(String num :line){
         matrix[i][j] =  Integer.parseInt(num);
         j++;
      }
    }
    input.close();
    return matrix;
  }
  public static void displayMatrix(int[][] matrix){
    for(int i = 0; i < matrix.length; i++){
      for(int j = 0; j < matrix[i].length; j++){
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }
}
