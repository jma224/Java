//* Jingyan Ma *//
public class SlotMachine{
   public static void main(String[] args){
     System.out.println("Welcome to Fun Casino!");
     double money = Double.parseDouble(args[0]);
     double roundBet = Double.parseDouble(args[1]);
     double goal = Double.parseDouble(args[2]);
     playMachine(money, roundBet,goal);
  }
   
  //write diceRoll method that returns a random integer between 1 and 6
  public static int diceRoll(){
    //get a random double between 1.0 to 7.0,not including 7.0
    double randomDouble=1+Math.random()*6;
    //cast the random double to get a random int between 1 and 6
    int randomInt=(int) randomDouble;
    return randomInt;
  }
  
  //write getSymbol method to get the corresponding symbol of any integer between 1 and 6
  public static String getSymbol(int a){
    if (a==1){
      return("Cherries");
    }else if (a==2){
      return ("Oranges");
    }else if (a==3){
      return ("Plums");
    }else if (a==4){
      return ("Bells");
    }else if (a==5){
      return ("Melons");
    }else if (a==6){
      return ("Bars");
    }else{
      //prints ERROR when receives any number that is not 1,2,3,4,5 or 6
      return ("ERROR");
    }
  }
  
  //write getMultiplier method that returns the corresponding multiplier that the three strings represent
  public static int getMultiplier(String a, String b, String c){
    //3 Bells indicate getting the multiplier 5
    if (a.equals("Bells")&&b.equals("Bells")&&c.equals("Bells")){
      return 5;
     //if three strings are the same and are not Bells, return multiplier 3
    }else if(a.equals(b)&&b.equals(c)){
      return 3;
     //if any 2 of the 3 strings are the same, return multiplier 2
    }else if (a.equals(b)||a.equals(c)||b.equals(c)){
      return 2;
     //if none of the above conditions apply, the player gets no money back
    }else {
      return 0;
    }
  }
  
  //write a canPlay method to determine whether the player has enough money to play one more round
  public static boolean canPlay(double money, double roundBet){
    //the player can continue playing if he has more than the amount of a round bet and the round bet is greater than o
    if (money>=roundBet&&roundBet>0){
      return true;
    }else{
      return false;
    }  
  }
  
  //write goalReached method to test if the player has reached his goal for the day
  public static boolean goalReached(double money, double goal){
    //the player reachs his goal if he has money not less than his goal number
    if (money>=goal){
      return true;
    }else{
      return false;
    }
  }
  
  //write displaySymbols method to print which three strings the player gets
  public static void displaySymbols(String a, String b, String c){
    System.out.println("* |"+ a +"| |"+ b +"| |"+ c +"| *"); 
  }
  
  //write formatMoney method to add $ in front of every amonut of money
  public static String formatMoney(double money){
  return String.format("$%.2f", money);
  }
 
  //write playMachine method to simulate playing the machine
  //takes in money, roundbet amount and the goal amount
  public static void playMachine(double money, double roundBet, double goal){
    //if the player does not have enough money to play one round
    if (!canPlay(money, roundBet)){
      //prints the money he has and the amount needed for one round
      //shows that he cannot play anymore
      System.out.println("You have "+formatMoney(money)+" and the round bet is "+formatMoney(roundBet)+".");
      System.out.println(" Sorry, you don't have enough money to play this slot machine! Come back later!");
    //if the player has reached his goal
    }else if (goalReached(money,goal)){
      //prints out the money he has and his goal amount
      //indicates that he reached the goal
      System.out.println("You have "+formatMoney(money)+" and your goal is: "+formatMoney(goal)+".");
      System.out.println("You have reached your goal for today! You cannot play anymore!");
     //if neither one of the above is true, then the player can play the machine
    }else{
     //set j equal to 1
     //the loop executes while the player has enough money and has not reached the goal
     //at the end of each iteration, increase j by 1
    for (int j=1;canPlay(money, roundBet)&&!goalReached(money,goal);j++){
      System.out.println();
      //shows the number of round
      System.out.println("You are at Round "+j+".");
      //sustract the round bet amount from total amount
      money = money - roundBet;
      //assign three random symbols to strings a,b,c
      String a = getSymbol(diceRoll());
      String b = getSymbol(diceRoll());
      String c = getSymbol(diceRoll()); 
      //display the symbols
      displaySymbols(a,b,c);
      //get the multiplier that the three symbols indicate
      int multiplier = getMultiplier(a,b,c);
      //the according amount of money is added to total amount
      money = money + roundBet * multiplier;
      System.out.println("Now you have: " + formatMoney(money) + ".");
      System.out.println();
    }
     //perform a test to see if the player ran out of money or reached his goal
     //print the money left at the end
      if (!canPlay(money, roundBet)){
      System.out.println("Sorry, you ran out of money! Better luck next time! You end up with "+formatMoney(money)+".");
    }else if (goalReached(money,goal)){
      System.out.println("Congratualations, you have"+formatMoney(money)+"! You reached your goal!");
    }
   }
  }
}
