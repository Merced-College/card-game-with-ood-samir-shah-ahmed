import java.util.List;

/**
 * Author: Samir Ahmed
 * Date: June 18th 2025
 * CardDisplay Class
 * Handles the visual representation of cards in the terminal.
 */
public class CardDisplay {
    /**
     * Displays a list of cards side by side in the terminal using ASCII art.
     * Each card is represented as a 7-line ASCII art drawing.
     * 
     * @param cards List of Card objects to display
     */
    public static void displayCards(List<Card> cards) {
        if (cards.isEmpty()) return;
        
        String[] cardLines = new String[7];
        for (int i = 0; i < 7; i++) cardLines[i] = "";
        
        for (Card card : cards) {
            String[] currentCard = card.getAsciiArt().split("\n");
            for (int i = 0; i < currentCard.length; i++) {
                cardLines[i] += currentCard[i] + " ";
            }
        }
        
        for (String line : cardLines) {
            System.out.println(line);
        }
    }
    
    /**
     * Clears the terminal screen for a clean display.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}