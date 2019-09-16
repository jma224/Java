//* Jingyan Ma *//
public class BridgeUtilities {

    //write a private constructor that does nothing
    //to avoid any object of type BridgeUtilities being created outside this class
    private BridgeUtilities(){
    }

    //write a private method to count how many Cards in an array have the specified value
    private static int countValue(Card[] arr, int value){
        //initialize the count number to be 0
        int count = 0;
        //for every Card of the array
        for (int i=0; i<arr.length; i++){
            Card card = arr[i];
            //if the value of the Card equals the input value
            if (card.getValue()==value){
                //count it
                count++;
            }
        }
        return count;
    }

    //write a private method to count how many Cards in an array have the specified suit
    private static int countSuit(Card[] arr, String suit){
        //initialize the count number to be 0
        int count = 0;
        //for every Card of the array
        for (int i=0; i<arr.length; i++) {
            Card card = arr[i];
            //if the suit of the Card is the input suit
            if (card.getSuit().equalsIgnoreCase(suit)) {
                //count it
                count++;
            }
        }
        return count;
    }

    //write a public method to generate the points that the hand os worth
    public static int countPoints(Card[] arr){
        //count the number of aces, kings, queens, and jacks in the hand of cards
        int numAce = countValue(arr,1);
        int numKing = countValue(arr,13);
        int numQueen = countValue(arr, 12);
        int numJack = countValue(arr,11);
        //calculate HCP
        int hcp = numAce*4+numKing*3+numQueen*2+numJack;
        String[] arrStr= {"clubs", "hearts", "diamonds", "spades"};
        //initialize distributional points to be zero
        int disPoints = 0;
        //for every suit of the four suits
        for (int i=0; i<arrStr.length; i++) {
            String str = arrStr[i];
            //count the number of each suit in the hand
            int count = countSuit(arr, str);
            //if the number of any suit exceeds 4
            if (count >= 4){
                //add the additional points to distributional points
                disPoints = disPoints + count - 4;
            }
        }
        //calculate total point by adding up HCP and distributional points
        int total = hcp + disPoints;
        return total;
    }


}
