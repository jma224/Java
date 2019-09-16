//* Jingyan Ma *//
public class Card {

    private String suit;
    private int value;

    //write a constructor to initialize the attributes of a Card instance
    public Card(int value, String suit){
        boolean validValue = value>0&&value<14;
        boolean validSuit = suit.equals("hearts")||suit.equals("spades")||suit.equals("clubs")||suit.equals("diamonds");
        //if the inputs received are not valid
        if (!validValue||!validSuit){
            //throw an IllegalArgumentException
            String err = "No card of such type can be created!";
            throw new IllegalArgumentException(err);
        }else{
            //otherwise, initialize the attributes accordingly
            this.value = value;
            this.suit = suit;
        }
    }

    public String getSuit(){
        return suit;
    }

    public int getValue(){
        return value;
    }

    //write print() to print out the content of the card
    public void print(){
        System.out.print(this.getValue() + " of " + this.getSuit());
    }
}
