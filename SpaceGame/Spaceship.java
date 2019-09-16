//Name: Jingyan Ma
//McGill ID: 260783581

import java.util.ArrayList;
public class Spaceship{
  
  private String name;
  private double currentHealth;
  private double maxHealth;
  private int numArtifacts;
  private int numWins;
  private Planet currPlanet;
  private static ArrayList<Planet> planets;
  
  //write getters and setters for the attributes
  public String getName(){
      return this.name;
  }

  public double getMaxHealth(){
      return this.maxHealth;
  }

  public int getNumWins(){
      return this.numWins;
  }
  private Planet getCurrPlanet(){
      return this.currPlanet;
  }
   
  public double getCurrentHealth(){
      return this.currentHealth;
   }

  public int getNumArtifacts(){
      return this.numArtifacts;
  }
   
  public ArrayList<Planet> getPlanets(){
      return planets;
   }

  private void setCurrPlanet(Planet currPlanet){
      this.currPlanet = currPlanet;
   }

   //write a constructor for a spaceship
  public Spaceship(String name, double maxHealth, int numWins){
    this.name = name;
    this.currentHealth = maxHealth;
    this.maxHealth = maxHealth;
    this.numWins = numWins;
  }

  //write a toString method to print the attributes of a spaceship
  public String toString(){
    return "Name: " + this.name + " Health: " + String.format("%1$.2f", this.currentHealth) + " Num. of artifacts: " +numArtifacts;
  }

  //a method to set the planets attribute
  public void setPlanets(ArrayList<Planet> planets){
    //set the attribute to be a new ArrayList
    this.planets = new ArrayList<Planet>();
    //for every element of the parameter, copy it, and add it to the attribute
    for (int i=0; i<planets.size(); i++){
      Planet p = planets.get(i);
        this.planets.add(p);
    }
    //print out the details of every planet using toString method of the Planet class
    for (int i=0; i<this.planets.size(); i++){
      Planet p = this.planets.get(i);
      System.out.println(p.toString());
    }
  }

  //write a method to move the spaceship to a different planet
  public void moveTo(String moveTo){
    //search for the planet using findPlanet method
    int index = Planet.findPlanet(moveTo, this.planets);
    //if the planet is not found, print a message
    if (index == -1){
      System.out.println("The "+this.getName()+" tried to move to "+moveTo+", but that planet is not in this solar system!");
    }else{
      //move the spaceship to the new planet
      this.setCurrPlanet(this.planets.get(index));
      System.out.println("The "+this.getName()+" moved to "+moveTo+".");
    }
  }

  //write a method to move the spaceship in
  public void moveIn(){
    //get the current planet of the spaceship
    Planet p = this.getCurrPlanet();
    //get the index of the current planet in the solar system
    int index = Planet.findPlanet(p.getName(), this.planets);
    //if the current planet is already at the first of the list, print a message
    if (index == 0){
      System.out.println(this.getName()+" couldn't move in. No planet is closer in.");
    }else{
      //decrease the index by one
      index--;
      //get the new planet in the list
      Planet currPlanet = this.planets.get(index);
      //call the moveTo method to move the spaceship
      this.moveTo(currPlanet.getName());
    }
  }

  //a moveOut method to move the spaceship out
  public void moveOut(){
    //get the current planet and get its index
    Planet p = this.getCurrPlanet();
     int index = Planet.findPlanet(p.getName(), this.planets);
     //if it already is at the last of the list, print a message
    if (index == this.planets.size()-1){
      System.out.println(this.getName()+" couldn't move out. No planet is farther out.");
    }else{
      //otherwise, increase the index and get the new planet
      index++;
      Planet currPlanet = this.planets.get(index);
      //move to the new planet
      this.moveTo(currPlanet.getName());
    }
  }

  //a method to increase the number of wins for the spaceship
  public void increaseWins(){
    this.numWins++;
  }

  //a method to search for an artifact
  public void doSearch() {
    //search the current planet and store the boolean value
    boolean search = this.currPlanet.searchForArtifact();
    //if the search is successful, print a message and increase the attribute
    if (search){
        System.out.println(this.getName() + " found an artifact!");
        this.numArtifacts++;
    }else {
        System.out.println(this.getName() + " didn't find an artifact.");
    }
    //find out the number of damage and print a message
    double damageTaken = this.currPlanet.getDamageTaken();
    System.out.println(name + " took " + String.format("%1$.2f", damageTaken) +
            " damage while searching for an artifact on " + currPlanet.getName()+".");
    //calculate the current health
    this.currentHealth = this.currentHealth - damageTaken;
    System.out.println(this.toString());
    //if the health is less than zero, the spaceship explodes
    if (this.currentHealth<0){
        System.out.println(this.getName() + " exploded!");
    }
  }


}