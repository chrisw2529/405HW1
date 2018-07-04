import java.util.*;
public class HW1{
  public static void main(String[] args) {

    String matrixFile = "matrix.txt";
    int[][] matrix = buildMatrix(matrixFile);
    displayMatrix(matrix);
    maxMatrix[][] = maxMatrix(matrix, matrix.length -1, matrix[0].length -1));
    printPath(maxMatrix, matrix);
  }

  public static void printPath(maxMatrix[][], actualMatrix[][]){
    ArrayList pathX = new int ArrayList();
    ArrayList pathY = new int ArrayList();

  }



  //returns maximum of 3 integers x, y, or z
  private static int max(int x, int y, int z)
  {
      if (x > y){
        if(x > z){
          return x;
        }
        else{
          return z;
        }
      }
      else{
        if(y > z){
          return y;
        }
        else{
          return z;
        }
      }
  }

  private static int[][] maxMatrix(int matrix[][], int m, int n)
  {
      int i, j;
      int totalSum[][] = new int[m+1][n+1];
      totalSum[0][0] = matrix[0][0];

      //builds first column of the totalSum matrix
      for (i = 1; i <= m; i++){
        totalSum[i][0] = totalSum[i-1][0] + matrix[i][0];
      }

      //builds first row of the totalSum matrix
      for (j = 1; j <= n; j++){
        totalSum[0][j] = totalSum[0][j-1] + matrix[0][j];
      }
      System.out.println();

      /* Construct rest of the tc array */
      for (i = 1; i <= m; i++){
        for (j = 1; j <= n; j++){
          totalSum[i][j] = max(totalSum[i-1][j-1],
          totalSum[i-1][j],
          totalSum[i][j-1]) + matrix[i][j];
        }
      }

      System.out.println();
      displayMatrix(totalSum);
      return totalSum[][];
  }

















  public static void longestPath(int[][] adjMatrix){
    //========================
    //Shortest path algorithum
    //========================
    int[] sum = new int[adjMatrix.length];
    int[] path = new int[adjMatrix.length];
    for(int i = 0;  i < adjMatrix.length; i++){
       sum[i] = (int) Double.NEGATIVE_INFINITY;
       path[i] = -1;
    }
    sum[0] = adjMatrix[0][0];
    //============================================
    //checks to see if node is pointing to another
    //============================================
    for(int i = 0; i < adjMatrix.length; i++){
       for(int j = 0; j < adjMatrix.length; j++){
         //CHANGE TO null later
          if(adjMatrix[i][j] != 0){
            //soething here for all longestPaths
             if(sum[i] + adjMatrix[i][j] > sum[j]){
                sum[j] = sum[i] + adjMatrix[i][j];
                path[j] = i;
             }
          }
       }
    }
    System.out.println(Arrays.toString(path));
    System.out.println(sum[sum.length-1]);
    // //=======================================
    // //Backtracking to add words to stack
    // //=======================================
    // Stack<String> words = new Stack<String>();
    // Stack<Integer> score = new Stack<Integer>();
    // int x = getNumNodes() - 1;
    // while(path[x] != -1){
    //    words.push(adjMatrix[path[x]][x].getLabel());
    //    score.push(adjMatrix[path[x]][x].getCombinedScore(lmScale));
    //    x = path[x];
    // }
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
