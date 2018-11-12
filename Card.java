/* Kevin Chang - War Card Game Program in Java 
 * This is my War Card game designed for 2 players. 
 * Implemented Rules: 
 * 1) Each player is dealt an evenly divided and shuffled deck.
 * 2) The top card of each player's deck is compared by rank. The player with the higher card takes both cards.
 * 3) If the cards have the same rank, it is "war". 
 * 4) In a "war", each player plays 2 cards each. The 2nd card each player plays is compared by rank. 
 * 		The player with the higher ranking card takes all the cards in the war.
 * 		if the cards have the same rank, "war" is repeated until one player wins.
 * 5) The game ends when one player has 0 cards left to play or if one player does not have enough cards for "war".	
 * 6) If both players do not have enough cards left to play at the same time, it is a tie.  	
 */
public class Card 
{
    private int rank; 
    private int suit; 
    
    public Card(int suit, int rank)
    {
        this.suit = suit;
        this.rank = rank;
    }

    public int getCard()
    {
        return rank; 
    }
    
    @Override
    public String toString()
    {
    	//combine rank and suit together into a string ex: Ace of Diamonds
        StringBuilder displayCard = new StringBuilder();
        
        //synchronize Jack, Queen, King, and Ace to their numerical values
        switch(rank)
        {          
            case 11:
                displayCard.append("Jack");
                break;
            case 12:
                displayCard.append("Queen");
                break;
            case 13:
                displayCard.append("King");
                break;
            case 14:
                displayCard.append("Ace");
                break;    
            default:
                displayCard.append(rank); 
                break;
        }       
        displayCard.append(" of "); 
        
        switch(suit)
        {
            case 0:
                displayCard.append("Spades");
                break;
            case 1:
                displayCard.append("Hearts");
                break;
            case 2:
                displayCard.append("Clubs");
                break;
            case 3:
                displayCard.append("Diamonds");
                break;
            default: 
                break;
        }
        //return the result of an entire combined string
        return displayCard.toString();
    }
}//end Card Class