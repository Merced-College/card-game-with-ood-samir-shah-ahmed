import java.util.List;

public class CardDisplay {
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
    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}