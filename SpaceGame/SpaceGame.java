//* Jingyan Ma *//

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class SpaceGame{
  
  private Scanner scan;
  private Spaceship player;
  private Spaceship enemy;
  private static final int NUM_ARTIFACTS_WIN = 5;
  
  public static void main (String[] args){

    SpaceGame game = new SpaceGame("sol.txt");
    game.playGame();
  }

  //write a constructor to create a space game
  public SpaceGame(String filename){
    System.out.println("Welcome to SpaceGame!");
    //create a scanner
    this.scan = new Scanner(System.in);
    //initialize the player and the enemy spaceships using provided files
    this.player = FileIO.loadSpaceship("player.txt");
    this.enemy = FileIO.loadSpaceship("enemy.txt");
    //load the planets from the provided file
    ArrayList<Planet> planets = FileIO.loadPlanets(filename);
    System.out.println("Loaded solar system from " + filename);
    //set the planets for all Spaceships
    this.player.setPlanets(planets);
    //move the player spaceship to the first planet in the list
    Planet moveTo1 = planets.get(0);
    this.player.moveTo(moveTo1.getName());
    //move the enemy spaceship to the last planet in the list
    Planet moveTo2 = planets.get(planets.size()-1);
    this.enemy.moveTo(moveTo2.getName());
    System.out.println();
    System.out.println("You must find "+NUM_ARTIFACTS_WIN+" artifacts to win!");
  }

  //write a method to check for the health of the player and the enemy
  private int checkForDestroyed(){
    //if the player's health is below zero, return 1
    if (this.player.getCurrentHealth() <= 0) {
        return 1;
     //if the player's health is below zero, return 2
    }else if(this.enemy.getCurrentHealth() <= 0){
        return 2;
    }else{
      return 0;
    }
  }

  //write a method to check the number of artifacts
  private int checkForWin(){
    //get the number of artifacts for the player and the enemy
    int num = this.player.getNumArtifacts();
    int num2 = this.enemy.getNumArtifacts();
    if (num >= NUM_ARTIFACTS_WIN) {
        return 1;
    }else if(num2 >= NUM_ARTIFACTS_WIN){
        return 2;
    }else{
      return 0;
    }
  }

  //write a method to play the game
  public void playGame(){
    //check the spaceships' health and the number of artifacts
    while (checkForDestroyed()!=1 && checkForWin()!=1 && checkForDestroyed()!=2 && checkForWin()!=2){
      System.out.println();
      System.out.println("Enter a command:");
      //use the scanner to scan user's input
      String line = scan.nextLine();
      //react according to the input from the user
      if (line.equalsIgnoreCase("moveIn")){
        this.player.moveIn();
      }else if (line.equalsIgnoreCase("moveOut")){
        this.player.moveOut();
      }else if (line.equalsIgnoreCase("moveTo")) {
          System.out.println("Enter the destination:");
          //scan the destination of the spaceship
          String line2 = scan.nextLine();
          this.player.moveTo(line2);
      }else if (line.equalsIgnoreCase("search")){
          this.player.doSearch();
      }else{
        System.out.println("Command not recognized: " + line);
      }
      //create the random ai to control the enemy spaceship
      Random rand = new Random();
      //generate a random int from 0, 1, and 2
      int x = rand.nextInt(3);
      //react according to the random int
      if(x == 0){
          this.enemy.doSearch();
      }else if(x == 1){
          this.enemy.moveIn();
      }else if(x == 2){
          this.enemy.moveOut();
      }
    }
    //if player spaceship is destroyed
    if (this.checkForDestroyed() == 1){
      System.out.println("Your spaceship is destroyed. You lose!");
      //increase the number of wins of the enemy
      this.enemy.increaseWins();
      System.out.println(this.enemy.getName() + " has won " + this.enemy.getNumWins() + " times.");
      //save the spaceship information to the file and catch any IOException
      //print messages according to the conditions
      try{
          FileIO.saveSpaceship(this.enemy, "enemy.txt");
          System.out.println("Successfully wrote to file: enemy.txt");
      }catch(IOException e){
          System.out.println("Invalid input to enemy.txt");
      }
    }
    //if the player collects enough artifacts, the player wins
    //save the information and catch IOException
    if (this.checkForWin() == 1){
      System.out.println("Your spaceship collects enough artifacts. You win!");
      this.player.increaseWins();
      System.out.println(this.player.getName() + " has won " + this.player.getNumWins() + " times.");
      try{
          FileIO.saveSpaceship(this.player, "player.txt");
          System.out.println("Successfully wrote to file: player.txt");
      }catch(IOException e){
          System.out.println("Invalid input to player.txt");
      }
    }
    //if the enemy spaceship is destroyed, the player wins
    //save the information and catch IOException
    if (this.checkForDestroyed() == 2){
        System.out.println("Your enemy spaceship is destroyed. You win!");
        this.player.increaseWins();
        System.out.println(this.player.getName() + " has won " + this.player.getNumWins() + " times.");
        try{
            FileIO.saveSpaceship(this.player, "player.txt");
            System.out.println("Successfully wrote to file: player.txt");
        }catch(IOException e){
            System.out.println("Invalid input to player.txt");
        }
    }
    //if the enemy collects enough artifacts, the player loses
    //save the information and catch IOException
    if (this.checkForWin() == 2){
        System.out.println("Your enemy collects enough artifacts. You lose!");
        this.enemy.increaseWins();
        System.out.println(this.enemy.getName() + " has won " + this.enemy.getNumWins() + " times.");
        try{
            FileIO.saveSpaceship(this.enemy, "enemy.txt");
            System.out.println("Successfully wrote to file: enemy.txt");
        }catch(IOException e){
            System.out.println("Invalid input to enemy.txt");
        }
    }
  }
 
}
