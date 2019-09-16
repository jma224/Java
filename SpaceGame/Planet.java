//Name: Jingyan Ma
//McGill ID: 260783581

import java.util.ArrayList;
import java.util.Random;
public class Planet{
  
  private String name;
  private double chance;
  private double damage;

  //write a constructor for a planet
  public Planet(String name, double chance, double damage){
     //throw an exception if the input is invalid
     if (chance<0||chance>1||damage<0){
        String err = "Error: Invalid value!";
        throw new IllegalArgumentException (err);
     }else{
      this.name = name;
      this.chance = chance;
      this.damage = damage;
     }
  }

  //a getter method for name attribute
  public String getName(){
      return this.name;
  }

  //a toString method to print the attributes
  public String toString(){
    return "Name: " + this.name + " Artifact Chance: " + this.chance*100 + "% Possible Damage: " + this.damage;
  }

  //write a method to search for a planet in an ArrayList
  public static int findPlanet(String str, ArrayList<Planet> planets){
    //if the input is a null ArrayList, print a warning
    if (planets == null){
      System.out.println ("You entered a null ArrayList!");
      return -1;
    }else{
      //set the index to be -1
      int index = -1;
      //iterate through the ArrayList
      for (int i=0; i<planets.size(); i++){
        //get each planet
        Planet p = planets.get(i);
        //get the name of that planet
        String name = p.getName();
        //if the name of the planet is the same as the parameter, return its index
        if (str.equalsIgnoreCase(name)){
          index = i;
          break;
        }
      }
      return index;
    }
  }

  //write a method to search for alien artifact on the planet
  public boolean searchForArtifact() {
      //get a random double between 0.0 and 1.0
      Random rand = new Random();
      double randInt = rand.nextDouble();
      //if the random double is less than chance, return true
      if(randInt<this.chance){
          return true;
      }else {
          //otherwise, return false
          return false;
      }
  }

  //write a method to find out how much damage the spaceship takes when searching
  public double getDamageTaken() {
      Random rand = new Random();
      double randInt = rand.nextDouble();
      double num = randInt * this.damage;
      return num;
  }


}