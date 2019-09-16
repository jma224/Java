//Name: Jingyan Ma
//McGill ID: 260783581

import java.io.*;
import java.util.ArrayList;

public class FileIO{

  //write a method to load a spaceship
  public static Spaceship loadSpaceship(String filename){
    try{
      //use a FileReader and BufferedReader to read a file
      FileReader fr = new FileReader(filename);
      BufferedReader br = new BufferedReader(fr);
      String name = br.readLine();
      String maxHealth = br.readLine();
      String numWins = br.readLine();
      double maxHealthD = Double.parseDouble(maxHealth);
      int numWinsI = Integer.parseInt(numWins);
      //use the info from the file to create a new spaceship
      Spaceship ss = new Spaceship(name, maxHealthD, numWinsI);
      //close the BufferedReader
      br.close();
      return ss;
      //catch FileNotFoundException and IOException
    }catch (FileNotFoundException e){
      throw new IllegalArgumentException("Could not find file: "+filename);
    }catch (IOException e){
      throw new IllegalArgumentException("Problem with file: "+filename);
    }
  }

  //write a method to load planets from a file
  public static ArrayList<Planet> loadPlanets(String filename){
    try{
      //use FileReader and BufferedReader to read a file
      FileReader fr = new FileReader(filename);
      BufferedReader br = new BufferedReader(fr);
      //create a new ArrayList of Planets to put the planets in
      ArrayList<Planet> planets = new ArrayList<Planet>();
      String currentLine = br.readLine();
      while (currentLine != null){
        //get each line of the file and use the info to create a new planet
        String tokens[] = currentLine.split(" ");
        Planet p = new Planet(tokens[0], Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
        //add the new planet to the ArrayList
        planets.add(p);
        //read the next line
        currentLine = br.readLine();
      }
      br.close();
      return planets;
      //catch FileNotFoundException and IOException
    }catch (FileNotFoundException e){
      throw new IllegalArgumentException("Could not find file: "+filename);
    }catch (IOException e){
      throw new IllegalArgumentException("Problem with file: "+filename);
    }
  }

  //a method to save the spaceship and write the information to a file
  public static void saveSpaceship (Spaceship ship, String filename) throws IOException {
      //use FileWriter and BufferedWriter to write to a file
      FileWriter fw = new FileWriter(filename);
      BufferedWriter bw = new BufferedWriter(fw);
      //convert the type to String and write them
      bw.write(ship.getName());
      String maxHealthW = String.format("%1$.2f", ship.getMaxHealth());
      bw.write(maxHealthW);
      bw.write("" + ship.getNumWins());
      bw.close();
  }


}