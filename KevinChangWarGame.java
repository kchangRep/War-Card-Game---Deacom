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
import java.util.ArrayList;     
import java.util.Random;               
import java.util.Collections;   
import java.util.LinkedList;    

public class KevinChangWarGame 
{
    static boolean player2Win = false;
    static boolean player1Win = false;
    static boolean noWin = false;
    
    public static void main(String[] args) 
    {
        boolean gameOn = true;

        //create an ArrayList for the deck of cards 
        ArrayList<Card> cardDeck = new ArrayList<Card>(); 
        
        //0-3 for suit (4 suits)
        for (int x = 0; x < 4; x++) 
        {   
        	//2-14 for rank (13 ranks)       
            for(int y = 2; y < 15; y++) 
            {   
            	//create new card and add it into the deck
                cardDeck.add(new Card(x,y)); 
            } 
        }
        
        //shuffle the deck 
        Collections.shuffle(cardDeck, new Random()); 
        
        //create 2 decks, 1 for each player
        LinkedList<Card> p1deck = new LinkedList<Card>();
        LinkedList<Card> p2deck = new LinkedList<Card>();
                
        p1deck.addAll(cardDeck.subList(0, 25));                   
        p2deck.addAll(cardDeck.subList(26, cardDeck.size()));
            
        while(gameOn)
        {
        	//each round, each player plays 1 card from the top of their deck
            Card p1Card = p1deck.pop();  
            Card p2Card = p2deck.pop();
            
            System.out.println("Player 1's card is " + p1Card.toString());
            System.out.println("Player 2's card is " + p2Card.toString());
            /*comparison of the ranks
             *the higher rank wins both cards and they are placed at the bottom of the winner's deck             
             */
            //player 1 winning scenario 
            if (p1Card.getCard() > p2Card.getCard())
            {
                p1deck.addLast(p1Card);   
                p1deck.addLast(p2Card);  
                System.out.println("Player 1 wins the round");
                System.out.println("");
            }
            //player 2 winning scenario 
            else if (p1Card.getCard() < p2Card.getCard())
            {
                p2deck.addLast(p1Card);   
                p2deck.addLast(p2Card);  
                System.out.println("Player 2 wins the round");
                System.out.println("");
            }                     
            //tie scenario - "war" occurs             
            else 
            {
            	System.out.println("The cards are equal");
            	war(p1deck, p2deck, true, p1Card, p2Card);            	
            }//end else
            
            //the game is over if either player is out of cards
            if (noWin == true)
            {
            	System.out.println("Both players are out of cards. It's a tie!");
            	gameOn = false;
            }
            else if (p1deck.size() == 0 || player2Win == true)
            {
            	gameOn = false;
                System.out.println("Game over, Player 1 is out of cards. Player 2 is the winner of the game!");                
            }
            else if (p2deck.size() == 0 || player1Win == true)
            {
            	gameOn = false;
                System.out.println("Game over, Player 2 is out of cards. Player 1 is the winner of the game!");
            }            
        }//end while loop  
    }//end main

    public static void war(LinkedList<Card> deck1, LinkedList<Card> deck2, boolean continueWar, 
    		Card player1Card, Card player2Card)
    {
        ArrayList<Card> p1war = new ArrayList<Card>(); 
        ArrayList<Card> p2war = new ArrayList<Card>();
        
        //add the previously popped cards at the beginning of the round
    	p1war.add(player1Card);
    	p2war.add(player2Card);
    	
        while (continueWar == true)
    	{
        	System.out.println("---------------------");
            System.out.println("~Commence War~");           
            
            //no war occurs if either player has 0 or 1 card left
        	if (deck1.size() < 2 || deck2.size() < 2) 
            {          
        		continueWar = false;
        		if (deck1.size() < 2 && deck2.size() < 2)
        		{
        			noWin = true;
        		}
        		else if (deck1.size() < 2)
        		{
        			player2Win = true;
        		}
        		else
        		{
        			player1Win = true;
        		}
            }
        	else
            {
                for (int x = 0; x < 2; x++)
                { 
                    p1war.add(deck1.pop());  
                    p2war.add(deck2.pop());		                     
                    
                    System.out.println("War card for player1 is " + p1war.get(p1war.size()-1).toString());
                    System.out.println("War card for player2 is " + p2war.get(p2war.size()-1).toString());
                    System.out.println();
                } 
                if(p1war.get(p1war.size()-1).getCard() > p2war.get(p1war.size()-1).getCard())
                {
                	deck1.addAll(p1war); 
                	deck1.addAll(p2war);
                    System.out.println("Player 1 wins the war round");
                    System.out.println("---------------------");
                    continueWar = false;
                }
                
                //if both cards are the same, war is repeated 
                else if(p1war.get(p1war.size()-1).getCard() == p2war.get(p1war.size()-1).getCard())
                {
                	System.out.println("The cards are equal. War will now commence again.");
                	continueWar = true;
                }
                
                //player 2 wins the war round and gets all the cards
                else
                {
                	deck2.addAll(p1war); 
                	deck2.addAll(p2war);
                    System.out.println("Player 2 wins the war round");
                    System.out.println("---------------------");
                    continueWar = false;
                }                
            }// end else	                
    	}//end while loop	
    }
}//end WarCardGame class