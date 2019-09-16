//* Jingyan Ma *//
import java.util.Arrays;
public class Mountains{
  
  public static void main(String[] args){
    
    double[] arr = generateMountains(100, 8, 20);
    drawMountains(arr,"*");
  }
  
   //write a method to generate the next point in the range of steepness
   public static double getNextPoint (double heightPre, double steepness, double heightMax){
     //generate a random number of increase or decrease to the previous point based on steepness
     double random = Math.random() * 2 * steepness - steepness;
     //the new height equals the previous height plus the change within the steepness
     double heightNew = heightPre + random;
     //if the new point is above the maximum height, then the new height is changed to be equal to the maximum height
     if (heightNew > heightMax){
       heightNew = heightMax;
     //if the new point is below zero, the new height is set to zero
     }else if (heightNew < 0){
       heightNew = 0;
     }
     return heightNew;
   }
  
  //write a method to generate the heights for all points in the mountain range
  public static double[] generateMountains (int numPoints, double steepness, double heightMax){
    //create a new double array with size of the number of points to store all the heights 
    double[] heights = new double[numPoints];
    //the first point of the array has the value of half of the maximum height of the mountain range
    heights[0] = heightMax / 2;
    //for every point in the mountain method
    for (int i=1; i<numPoints; i++){
      //call the heightNew method to generate the new height for the next point 
      double heightNew = getNextPoint(heights[i-1], steepness, heightMax);
      //store the newly generated height in the double array
      heights[i] = heightNew;
    }
    return heights;
  }
  
  //write a method to find the largest height in the array
  public static double findMaxHeight(double[] arr){
    //if the array is empty, return 0
    if (arr.length == 0){
      return 0;
    }else{
      //create a double max to store the maximum value and initialize it to be equal to the first double in the array
      double max = arr[0];
      //for each following double in the array
      for (int i=1; i<arr.length; i++){
        //create a double num to store the double in the double array with an index i
        double num = arr[i];
        //if num is larger than the recorded maximum number
        if (num > max){
          //set the maximum number to be equal to num
          max = num;
        }
      }
     return max;
   }
  }
  
  //write a method to draw the mountain with a double array containing the heights in the mountain range and a String to draw the mountain with
  public static void drawMountains(double[] arr, String s){
    //create a double max to store the maximum height in the mountain range
    double max = findMaxHeight(arr);
    //take max, plus one, and cast it to an integer
    int maxInt = (int)(max+1);
    //the outer for-loop loops from the maximum height to zero
    for (int i=maxInt; i>=0; i--){
      //create y to be the vertical component of the grid 
      int y = i;
      //the inner-loop loops from the beginning of the points array to the end
      for (int j=0; j<arr.length; j++){
        //create double height to store the double in arr with index j
        double height = arr[j];
        //if y=0, print the ground -
        if (y == 0){
          System.out.print("-");
          //if y>height, print the space character
        }else if (y > height){
          System.out.print(" ");
          //if y-height<1 and y-height>-1, then print the mountaintop ^
        }else if (y-height>-1&&y-height<1){
          System.out.print("^");
          //else, y<height, and print the mountain symbol
        }else{
          System.out.print(s);
        }
      }
      System.out.println();
    }
  }
  
}
