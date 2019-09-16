//Name: Jingyan Ma
//McGill ID: 260783581
public class Game {

    public static void main(String[] args){
        //create a Deck
        Deck deck = new Deck();
        //shuffle the Deck
        deck.shuffle();
        //deal four hands of Cards with each hand containing 13 cards
        //for each player, count the points of their hands and display the cards inside their hands
        Card[] arr1 = deck.dealHand(13,0);
        int point1 = BridgeUtilities.countPoints(arr1);
        displayCards(1, point1, arr1);
        Card[] arr2 = deck.dealHand(13,1);
        int point2 = BridgeUtilities.countPoints(arr2);
        displayCards(2, point2, arr2);
        Card[] arr3 = deck.dealHand(13,2);
        int point3 = BridgeUtilities.countPoints(arr3);
        displayCards(3, point3, arr3);
        Card[] arr4 = deck.dealHand(13,3);
        int point4 = BridgeUtilities.countPoints(arr4);
        displayCards(4, point4, arr4);
    }

    //write a private helper method to display cards
    private static void displayCards(int n, int point, Card[] arr){
        System.out.println("Player "+n+" was dealt the following hand of cards:");
        int index = 0;
        //for the first 12 cards
        while (index<12){
            Card card = arr[index];
            //print the card out using the print() method
            card.print();
            //print a comma to separate the cards
            System.out.print(" , ");
            index++;
        }
        //print the last Card of the array, without a comma following
        Card card = arr[index];
        card.print();
        System.out.println();
        //print out the total points of the hand
        System.out.println("Their hand is worth "+point+" points.");
    }
}
