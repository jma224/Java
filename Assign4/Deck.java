//Name: Jingyan Ma
//McGill ID: 260783581
import java.util.Random;

public class Deck {

    private Card[] deck;

    //write a constructor that initializes the attribute deck with all 52 possible cards in a deck
    public Deck() {
        //initialize the attribute with a Card[] of size 52
        this.deck = new Card[52];
        String[] suitArr = {"Hearts", "Spades", "Clubs", "Diamonds"};
        //for every suit, there are 13 possible values
        for (int i = 0; i < 4; i++) {
            String suit = suitArr[i];
            //for every value of a suit
            for (int j = 0; j < 13; j++) {
                //set the attributes of a Card using the constructor in the Card class
                //and store the Cards in the Card array in order
                this.deck[13 * i + j] = new Card(j+1, suit);
            }
        }
    }

    //write a private helper method to swap two elements in a Card array
    private static void swap(Card[] cardArr, int a, int b){
        Card temp = cardArr[a];
        cardArr[a] = cardArr[b];
        cardArr[b] = temp;
    }

    //write a public method to shuffle the array of Cards of this deck
    public void shuffle(){
        //create an object of type Random and seed it with 123
        Random rand = new Random(123);
        //create a loop that iterate 1000 times
        for (int i=0; i<1000; i++) {
            //generate 2 random ints between 0 and 51
            int x = rand.nextInt(52);
            int y = rand.nextInt(52);
            //swap the values at those positions in the array using helper method
            swap(this.deck, x, y);
        }
    }

    //write a public method to deal the cards to players
    public Card[] dealHand(int n, int player){
        //create a Card array indicating the Cards that the player gets
        Card[] arr = new Card[n];
        //if there are not enough cards to deal
        if (52-player*n<n){
            //throw an IllegalArgumentException showing that there are not enough cards in the deck
            String err = "There aren't enough cards in the deck!";
            throw new IllegalArgumentException (err);
        }else{
            //if there are enough cards, assign the according cards in the deck to the new card array
            for (int i=0; i<n; i++){
                arr[i] = this.deck[player*n+i];
            }
        }
        return arr;
    }


}
