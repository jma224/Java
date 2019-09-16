//Student Name: Jingyan Ma
//McGill ID: 260783581
import java.util.Random;
import java.util.Arrays;

public class SortAndFind{
  
  public static void main(String[] args){
    int[][] arr = generateRandomMatrix(6,8);
    displayMatrix(arr);
    System.out.println();
    sortMatrix(arr);
    displayMatrix(arr);
    System.out.println("The element 14 is in position " + Arrays.toString(findElement(arr,14)));
    System.out.println("The element 26 is in position " + Arrays.toString(findElement(arr,26)));
    System.out.println("The element 5 is in position " + Arrays.toString(findElement(arr,5)));
  }
  
  //write a method to generate an array of random integers
  public static int[][] generateRandomMatrix (int m, int n){
    //create a new two-dimensional int array of size m by n 
    int[][] arr = new int[m][n];
    //create an object of type Random and give a seed of 123
    Random rand = new Random(123);
    //for every position of the 2D int array
    for (int i=0; i<arr.length; i++){
      for (int j=0; j<arr[i].length; j++){
        //create a random int between 0 and 50
        int x = rand.nextInt(50);
        //and assign it to the corresponding element in the array
        arr[i][j] = x;
      }
    }
    return arr;
  }
  
  //write a method to diaplay all elements of the 2D array
  public static void displayMatrix (int[][] arr){
    //for every element in the 2D array
    for (int i=0; i<arr.length; i++){
      for (int j=0; j<arr[i].length; j++){
        //print it out and separate it with the tab character
        System.out.print(arr[i][j]+"\t");
      }
      System.out.println();
    }
  }
  
  //write a helper method to find the smallest element in an int array
  public static int findSmallestElement (int[] arr){
    //create a variable to store the smallest int and initialize it to be equal to the first element of the array
    int small = arr[0];
    //create index to indicate the index of the smallest element
    int index = 0;
    //for every element of the array starting from the second one
    for (int i=1; i<arr.length; i++){
      //take the element out and compare it with the samllest element
      int element = arr[i];
      //if this element is smaller
      if (element < small){
        //set the smallest element to be this element
        small = element;
        //and change the index to record the position of the current element
        index = i;
      }
    }
    return index;
  }
  
  //write a helper method to swap two elements in an array
  public static void swapElements (int[] arr, int i1, int i2){
    //create a variable to store the value of int with index i1
    int store = arr[i1];
    //set the int at index i1 to be equal to the element at i2
    arr[i1] = arr[i2];
    //set the int at i2 to be eqal to the original value of the element at i1
    arr[i2] = store;
  } 
  
  //write a helper method to generate the unsorted array
  public static int[] generateUnsortedArray(int begIndex, int[] arr){
    //create an unsorted array and set the length to be equal to the unsorted part of the original array
    int[] unsortedArr = new int[arr.length - begIndex];
    //for every element in the unsorted array
    for (int i=0; i<arr.length-begIndex; i++){
      //assign the unsorted elements of the original array to the unsorted array
      unsortedArr[i] = arr[begIndex+i];
    }
    return unsortedArr;
  }

  //write a method to sort an int array to an increasing order
  public static void sortOneRow (int[] arr){
    //for every element of the array
    for (int i = 0; i<arr.length; i++){
      //generate the unsorted array starting from the first element of the unsorted elements
      int[] unsortedArr = generateUnsortedArray(i, arr);
      //find where the smallest element in the unsorted part of the array is positioned
      int smallIndexUnsorted = findSmallestElement(unsortedArr);
      //swap that element with the element in the initial position of the unsorted part of the array
      swapElements (arr,i, smallIndexUnsorted+i);
    }
  }
  
  //write a method to sort one column of a 2D array
  public static void sortOneColumn (int[][] arr, int a){
    //create an array to record the elements of the column needed to be sorted
    //the size is equal to the number of rows in the 2D array
    int[] col = new int[arr.length];
    //assign the elements of the column to the new array
    for (int i=0; i<arr.length; i++){
      col[i]=arr[i][a];
    }
    //call the sortOneRow method to sort the new array
    sortOneRow(col);
    //after all the elements are sorted, assign them back to the columns of the 2D array
    for (int j=0; j<arr.length; j++){
      arr[j][a] = col[j];
    }
  }
  
  //write a method to sort a 2D array
  public static void sortMatrix(int[][] arr){
    //first sort all its rows by using the sortOneRow method
    for (int i=0; i<arr.length; i++){
      sortOneRow(arr[i]);
    }
    //then sort all its columns by calling the sortOneColumn method
    for (int j=0; j<arr[0].length; j++){
      sortOneColumn(arr,j);
    }
  }
  
  //write a method to find an element in a 2D array
  public static int[] findElement(int[][] arr, int n){
    //get the number of rows and the the number of columns of the 2D array
    int numOfRows = arr.length;
    int numOfCols = arr[0].length;
    //create 2 variables to record the position of the element we are looking at in the array
    //we start by looking at the bottom left element of the matrix
    int posRow = numOfRows-1;
    int posCol = 0;
    boolean isEqual = false;
    //for every move of the algorithm
    //the number of moves must be smaller than the largest number of moves possible, i.e. from the bottom left to the upper right 
    for (int moves=0; moves<numOfRows-1+numOfCols-1; moves++){
      //if we get out of bounds, which indicates that the element is not in the matrix
      if (posRow<0||posCol>=numOfCols){
        //then the method returns the array {-1, -1}
        posRow = -1;
        posCol = -1;
        break;
        //if the element equals the input, then return the array with the correct position
      }else if (arr[posRow][posCol]==n){
        break;
        //if n is smaller than the element we are looking at, then move up by one position
      }else if(n<arr[posRow][posCol]){
        posRow--;
        //if n is greater than the element we are looking at, then move to the right by one position
      }else if(n>arr[posRow][posCol]){
       posCol++; 
      }
    }
    //create an array to record the positions, and return it
    int[] position = new int[2];
    position[0] = posRow;
    position[1] = posCol;
    return position;
    }

}