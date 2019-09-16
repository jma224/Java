//* Jingyan Ma *//
import java.util.Arrays;
public class Phrases{
  
  public static void main(String[] args){
  
    System.out.println(combineStrings("Not all those who wander are lost.", 
                                      "Make new friends, but keep the old; The first are silver, the rest are gold."));
    System.out.println(combineStrings("Don't burn a bridge until it's behind you.", 
                                      "Do not worry about crossing the bridge until you're ready to cross it."));
  }
  
  //write a method to turn a String array into a String
  public static String join (String[] arr){
    //declare and initialize an empty String s
    String s = "";
    //for every element of the array
    for (int i=0; i<arr.length; i++){
      //access the element in arr with index i and assign it to a String
      String add = arr[i];
      //concatenate the String add to s
      s = s + add;
      //add a blank after each word of the array except for the last one to form a proper sentence
      if (i<arr.length-1){
        s=s+" ";
      }
    }
    return s; 
  }
  
  //write a method to search the String array for the String s
  //and return an int indicating the position of the first String found
  public static int findInStringArray (String s, String[] arr){
    //declare and initialize an int a to be -1,
    //indicating the case where the String cannot be found
    int a = -1;
    //for every element of the array
    for (int i=0; i<arr.length; i++){
      //access the element of arr with index i and assign it to a String
      String string = arr[i];
      //if, ignoring case, an element in arr is equal to the given s
      if (string.equalsIgnoreCase(s)){
        //set a to be equal to the index of this element 
        a = i;
        //and stop the loop immediately
        break;
      }
    }
    return a; 
  }
  
  //write a method to count the number of words in a String
  public static int countWords (String s){
    //if the String s is an empty String, return 0
    if (s.length()==0){
      return 0;
    }else{
    //set int count equal to 1, indicating the first word in the String
    int count = 1;
    //for every character in the String
    for (int i=0; i<s.length(); i++){
      //set character c to be the character of String s at index i
      char c = s.charAt(i);
      //if c is blank, since there is always a word following a blank, add count by 1
      if (c==' '){
        count = count + 1;
    }
   }
    return count;
  }
  }
  //write a method to divide a String s into its component words, and put them into an array
  public static String[] tokenize (String s){
    //create a String array arr of the size equal to the number of words in s
    String[] arr = new String[countWords(s)];
    //set the index of arr to be 0
    int index = 0;
    //create an empty String str to temporarily store words
    String str = "";
    //for every character of s
    for (int i=0; i<s.length(); i++){
      //create character c and assign it to be equal to the character of s at index i
      char c = s.charAt(i);
      //if the character is blank, which means that the chars in front forms a word
      if (c==' '){
        //put the word into the array
        arr[index] = str;
        //increase index by 1 to store the next word into the array
        index = index + 1;
        //reset str to be empty to store the next word
        str = "";
      }else{
        //if the character is not blank, put it in str
        str = str + c;
      }
    }
    //when iterating to the last character and the last word, since there is no blank after, 
    //we put str into the last position of arr after the loop ends
    arr[index] = str;
   return arr; 
  }
  
  //write a method to combine two String arrays at the positions of chars given
  public static String[] combineArray (String[] a, String[] b, int x, int y){
    //create a new String array to hold Strings from both arrays
    String[] combine = new String[x+1+b.length-y-1];
    //for every String of array a from the first position to and including the index specified by int x
    for (int i=0; i<=x; i++){
      //store them in the new array
      combine[i] = a[i];
    }
    //after the first loop ends, for the new array, the next position to fill has the index index
    int index = x+1;
    //for every String of array b from the second int parameter b until the end 
    for (int j=y+1; j<b.length;j++){
      //store them in the new array into positions starting from index
      combine[index+j-y-1] = b[j];
    }
    return combine;
  }
  
  //write a method to create a new String from the combination of two String
  public static String combineStrings (String s1, String s2){
    //use tokenize method to split the Strings into String arrays
    String[] a = tokenize(s1);
    String[] b = tokenize(s2);
    boolean equal = false;
    //create 2 ints to record the positions of where the same word is in the two arrays
    int aInt = 0;
    int bInt = 0;
    //for each String in the first String array, search for that word in the second String array
    for (int i=0; i<a.length; i++){
      //creat int x to record the position of word a[i] in String array b
      int x = findInStringArray(a[i], b);
      //if x is not equal to -1, which indicates a match
      if (x != -1){
        equal = true;
        //change ints to record the positions
        aInt = i;
        bInt = x;
        //end the loop
        break;
      }else{
        continue;
      }
    }    
    //if boolean equal is false, which means that no word in the first array can be found in the second array
    if (equal==false){
      //throw IllegalArgumentException with an informative error message
      throw new IllegalArgumentException("There aren't any same words in the two Strings!!" );
    }else{
      //if there is a match, use the combineArray method to obtain a combined String array containing the combined phrase
      String[] arr = combineArray(a, b, aInt, bInt);
      //turn the String array into a String and return it
      String str = join(arr);
      return str;
    }
  }
  
  
  
  
}
