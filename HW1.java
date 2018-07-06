import java.util.*;
public class HW1{
  public static void main(String[] args) {

    String matrixFile = "matrix.txt";
    int[][] matrix = buildMatrix(matrixFile);
    displayMatrix(matrix);
    int[][] maxMatrix = maxMatrix(matrix, matrix.length -1, matrix[0].length -1);
    ArrayList<Point> path = new ArrayList<Point>();
    path.add(new Point(maxMatrix.length -1, maxMatrix[0].length-1));
    ArrayList<Point> pathToPrint = findPath(maxMatrix, maxMatrix.length -1, maxMatrix[0].length-1, path, matrix);
  }

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
      //path.add(new Point(0, 0));
      // for(int i = path.size()-1; i >= 0; i--){
      //   System.out.println("X: " + path.get(i).getX() + " Y: " + path.get(i).getY());
      // }
      System.out.println("got here");
      printPath(path, matrix);
    }
    return new ArrayList<Point>();
  }

  private static void pathMax(int x, int y, int z, int maxMatrix[][], int l, int h, ArrayList<Point> path, int[][] matrix)
  {
    if(x == y && y ==z){
      System.out.println("in all");
      path.add(new Point(l-1,h));
      findPath(maxMatrix, l-1, h, path, matrix);
      path.remove(path.size() -1);
      path.add(new Point(l-1,h-1));
      findPath(maxMatrix, l-1, h-1, path, matrix);
      path.remove(path.size() -1);
      path.add(new Point(l,h-1));
      findPath(maxMatrix, l, h-1, path, matrix);

    }
    else if(x==y){
      System.out.println("in left diag");
      path.add(new Point(l-1,h));
      findPath(maxMatrix, l-1, h, path, matrix);
      path.remove(path.size() -1);
      path.add(new Point(l-1,h-1));
      findPath(maxMatrix, l-1, h-1, path, matrix);
    }
    else if(x==z){
      System.out.println("in left up");
      ArrayList<Point> newPath = new ArrayList<Point>();
      for(int i = 0; i < path.size(); i++){
        newPath.add(path.get(i));
      }
      path.add(new Point(l-1,h));
      findPath(maxMatrix, l-1, h, path, matrix);
      //path.remove(path.size() -1);
      newPath.add(new Point(l,h-1));
      findPath(maxMatrix, l, h-1, newPath, matrix);
    }
    else if(y==z){
      System.out.println("in diag up");
      path.add(new Point(l-1,h-1));
      findPath(maxMatrix, l-1, h-1, path, matrix);
      path.remove(path.size() -1);
      path.add(new Point(l,h-1));
      findPath(maxMatrix, l, h-1, path, matrix);
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
  public static void printPath(ArrayList<Point> path, int[][] matrix){
    int h = 0;
    int spaceCount = 0;
    int increment = 0;
    System.out.println("Solution:");
    for(int i = path.size()-1; i >= 0; i--){
      Point point = path.get(i);
      if (point.getY() != h){
        System.out.println();
        for(int j = 0; j < point.getX() + spaceCount; j++){
          System.out.print(" ");
        }
        System.out.print(matrix[point.getY()][point.getX()] + " " );
        h = point.getY();
      }
      else{
        System.out.print(matrix[point.getY()][point.getX()] + " " );
        increment = (int) (Math.log10(matrix[point.getY()][point.getX()] + 1));
        spaceCount += increment;
        //System.out.print("SC: " + spaceCount);

      }
    }
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
