/* Chris White
* 405 HW 1
* finds max sum through matrix
* takes in a .txt file
* requires Point.java
*
*/
import java.util.*;
public class HW1{
  public static int solutionNum = 1;
  public static void main(String[] args) {
    if(args.length != 0){
      String matrixFile = args[0];
      int[][] matrix = buildMatrix(matrixFile);
      //displayMatrix(matrix);
      int[][] maxMatrix = maxMatrix(matrix, matrix.length -1, matrix[0].length -1);
      ArrayList<Point> path = new ArrayList<Point>();
      path.add(new Point(maxMatrix[0].length -1, maxMatrix.length-1));
      ArrayList<Point> pathToPrint = findPath(maxMatrix, maxMatrix[0].length -1, maxMatrix.length-1, path, matrix);

    }
    else{
      System.out.println("error:  no text file specified");
      System.exit(0);
    }
  }
  /* findPath
  * takes in a maxMatrix, current position in matrix (l & w)
  * arryalist of the current path its on
  * original matrix of values
  *
  * finds all optimal paths from the top left corner to the bottom right corner of the matrix summing up all numbers along the way
  * after it has found an optimal path it calls print path
  *uses path max as a helper function
  */
  public static ArrayList<Point> findPath(int maxMatrix[][], int l, int h, ArrayList<Point> path, int[][] matrix){
    if(h != 0 && l != 0){
      pathMax(maxMatrix[h][l-1], maxMatrix[h-1][l-1], maxMatrix[h-1][l], maxMatrix, l, h, path, matrix);
    }
    else if(h == 0 && l != 0){
      path.add(new Point(l-1, 0));
      findPath(maxMatrix, l-1, h, path, matrix);

    }
    else if(h != 0 && l == 0){
      path.add(new Point(l, h-1));
      findPath(maxMatrix, l, h-1, path, matrix);
    }
    else{
      printPath(path, matrix, maxMatrix);
    }
    return new ArrayList<Point>();
  }

  /* pathMax
  * takes in 3 values (x, y, z) and compares them to find the biggest of the three
  * x represents the spot to the left of the current
  * y represents the spot to the upper-left of the current
  * z represents the spot above the current
  * takes in maxMatrix current position in the matrix (l, h)
  * takes in current path that we have traversed
  * takes in the original matrix
  *
  * pathMax coompares the left, topleft diagonal and up positions from your current point in the maxMatrix and moves in the direction of the biggest number.
  * once found it recursivly calls back to findPath
  */
  private static void pathMax(int x, int y, int z, int maxMatrix[][], int l, int h, ArrayList<Point> path, int[][] matrix)
  {
    if(x == y && y ==z){
      ArrayList<Point> newPath = new ArrayList<Point>();
      ArrayList<Point> newPath2 = new ArrayList<Point>();

      for(int i = 0; i < path.size(); i++){
        newPath.add(path.get(i));
        newPath2.add(path.get(i));

      }
      path.add(new Point(l-1,h));
      findPath(maxMatrix, l-1, h, path, matrix);
      newPath.add(new Point(l-1,h-1));
      findPath(maxMatrix, l-1, h-1, newPath, matrix);
      newPath2.add(new Point(l,h-1));
      findPath(maxMatrix, l, h-1, newPath2, matrix);

    }
    else if(x==y && x > z){
      ArrayList<Point> newPath = new ArrayList<Point>();
      for(int i = 0; i < path.size(); i++){
        newPath.add(path.get(i));
      }
      path.add(new Point(l-1,h));
      findPath(maxMatrix, l-1, h, path, matrix);
      newPath.add(new Point(l-1,h-1));
      findPath(maxMatrix, l-1, h-1, newPath, matrix);
    }
    else if(x==z && x > y){
      ArrayList<Point> newPath = new ArrayList<Point>();
      for(int i = 0; i < path.size(); i++){
        newPath.add(path.get(i));
      }
      path.add(new Point(l-1,h));
      findPath(maxMatrix, l-1, h, path, matrix);
      newPath.add(new Point(l,h-1));
      findPath(maxMatrix, l, h-1, newPath, matrix);
    }
    else if(y==z && y > x){
      ArrayList<Point> newPath = new ArrayList<Point>();
      for(int i = 0; i < path.size(); i++){
        newPath.add(path.get(i));
      }
      path.add(new Point(l-1,h-1));
      findPath(maxMatrix, l-1, h-1, path, matrix);
      newPath.add(new Point(l,h-1));
      findPath(maxMatrix, l, h-1, newPath, matrix);
    }
    else if (x > y){
      if(x > z){
        path.add(new Point(l-1,h));
        findPath(maxMatrix, l-1, h, path, matrix);
      }
      else{
        path.add(new Point(l,h-1));
        findPath(maxMatrix, l, h-1, path, matrix);
      }
    }
    else{
      if(y > z){
        path.add(new Point(l-1,h-1));
        findPath(maxMatrix, l-1, h-1, path, matrix);
      }
      else{
        path.add(new Point(l,h-1));
        findPath(maxMatrix, l, h-1, path, matrix);
      }
    }
  }
  // Print the optimal path from the top left corner to the bottom right corner of the matrix, along with the optimal sum.
  public static void printPath(ArrayList<Point> path, int[][] matrix, int[][]maxMatrix){
    int h = 0;
    int l = 0;
    int spaceCount = 0;
    String prevNum = "";
    System.out.println("Solution #" + solutionNum);
    solutionNum++;
    Point point = path.get(path.size()-1);
    String toPrint = "";
    for(int i = path.size() - 1; i >= 0; i--){
      Point pp = path.get(i);
    }
    for(int i = path.size()-1; i >= 0; i--){
      point = path.get(i);
      //path went down
      if (point.getY() != h){
        //path went diagonal
        if(point.getX() != l){
          spaceCount = toPrint.length();

        }else{
          spaceCount = (toPrint.length() - prevNum.length());
        }
        System.out.println(toPrint);
        toPrint = "";
        for (int j = 0 ; j < spaceCount; j++) {
          toPrint += " ";
        }

        toPrint += String.valueOf(matrix[point.getY()][point.getX()] + " ");
      }
      //path went right
      else{
        toPrint += (matrix[point.getY()][point.getX()]+ " ");
      }
      prevNum = matrix[point.getY()][point.getX()] + " ";
      h = point.getY();
      l = point.getX();

    }
    System.out.println(toPrint);

    System.out.println("Max Sum = " + maxMatrix[maxMatrix.length-1][maxMatrix[0].length-1]);
    System.out.println();


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
  /* maxMatrix
  * takes in any matrix and returns a matrix with the max possible sum at each coordinate point
  *
  */
  public static int[][] maxMatrix(int matrix[][], int m, int n)
  {
      int i;
      int j;
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

      // Builds the rest of the totalSum array
      for (i = 1; i <= m; i++){
        for (j = 1; j <= n; j++){
          totalSum[i][j] = max(totalSum[i-1][j-1],
          totalSum[i-1][j],
          totalSum[i][j-1]) + matrix[i][j];
        }
      }

      System.out.println();
      //displayMatrix(totalSum);
      return totalSum;
  }
  /* buildMatrix
  * takes in a text file then, builds a matrix from the file and returns it
  * (ignores all extra white space)
  */
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
      String trimer = hl.nextLine().trim().replaceAll(" +", " ");
      String[] line = trimer.split("\\s");
      length = line.length;
      height++;
    }
    int[][] matrix = new int[height][length];
    //puts values into matrix
    int i = -1;
    while(input.hasNextLine()){
      i++;
      int j = 0;
      String trimer = input.nextLine().trim().replaceAll(" +", " ");
      String[] line = trimer.split("\\s");
      for(String num :line){
         matrix[i][j] =  Integer.parseInt(num);
         j++;
      }
    }
    input.close();
    return matrix;
  }

  /* displayMatrix
  * tester function that prints a matrix
  */
  public static void displayMatrix(int[][] matrix){
    for(int i = 0; i < matrix.length; i++){
      for(int j = 0; j < matrix[i].length; j++){
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }
}
