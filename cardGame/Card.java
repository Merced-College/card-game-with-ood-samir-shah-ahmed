/**
 * Author: Samir Ahmed
    Date: June 18th 2025
 * Card Class
 * This class represents a playing card with suit, name, value, and display properties.
 * It handles both the logical representation of cards (game mechanics) and
 * visual representation (ASCII display in terminal).
 */
public class Card {
    /** Properties that define a card */
    private String suit;    // The card's suit (Hearts, Diamonds, Clubs, Spades)
    private String name;    // The card's name (Ace, King, Queen, etc.)
    private int value;     // The card's numerical value
    private String picture; // The card's picture representation (unused in ASCII)

    /**
     * Constructor to create a new card
     * @param cardSuit   
     * @param cardName   The name of the card (Ace, King, etc.)
     * @param cardValue  The numerical value of the card
     * @param cardPicture The picture representation (unused in ASCII version)
     */
    public Card(String cardSuit, String cardName, int cardValue, String cardPicture) {
        suit=cardSuit;
        name=cardName;
        value=cardValue;
        picture=cardPicture;
    }
    /** Accessor methods - provide read access to card properties */
    public String getSuit(){
        return suit;
    }
    public String getName(){
        return name;
    }
    public String getPicture(){
        return picture;
    }
    /** Mutator methods - allow modification of card properties */
    public void setSuit(String suit){
        this.suit=suit;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setValue(int value){
        this.value=value;
    }
    public void setPicture(String picture){
        this.picture=picture;
    }
    
    public int getValue(){
        return value;
    }
    /**
     * Converts card information to a readable string
     * @return String representation of the card
     */
    public String toString(){
        return name+" of "+suit +" (Value: " + value + ")";
    }

    /**
     * Compares two cards for equality based on their names only (for pair checking)
     * Example: Two Kings are equal regardless of suit
     * @param obj The object to compare with
     * @return true if the cards have the same name
     */
    public boolean equals(Object obj){
        if (this==obj){
         return true;
        }
        if (obj==null || getClass() != obj.getClass()) {
            return false;
        }
        Card card =(Card) obj;
        // Only compare names for pairs, not suits
        return name.equals(card.name);
    }

    /**
     * Generates ASCII art representation of the card
     * Creates a 7-line ASCII drawing of the card showing:
     * - Card borders
     * - Card value in top-left and bottom-right
     * - Suit symbol in the center
     * @return Multi-line string containing the ASCII art
     */
    public String getAsciiArt() {
        String cardTop = "┌─────────┐";
        String cardBottom = "└─────────┘";
        String[] cardBody = new String[5];
        
        String suitSymbol = getSuitSymbol();
        String displayValue = getDisplayValue();
        
        // Handle alignment for 10
        String leftPad = displayValue.equals("10") ? "" : " ";
        String rightPad = displayValue.equals("10") ? "" : " ";
        
        cardBody[0] = String.format("│%s%s       │", leftPad, displayValue);
        cardBody[1] = "│         │";
        cardBody[2] = String.format("│    %s    │", suitSymbol);
        cardBody[3] = "│         │";
        cardBody[4] = String.format("│       %s%s│", displayValue, rightPad);
        
        StringBuilder result = new StringBuilder();
        result.append(cardTop).append("\n")
              .append(cardBody[0]).append("\n")
              .append(cardBody[1]).append("\n")
              .append(cardBody[2]).append("\n")
              .append(cardBody[3]).append("\n")
              .append(cardBody[4]).append("\n")
              .append(cardBottom);
        return result.toString();
    }

    /**
     * Converts suit name to a symbol for display
     * @return Unicode symbol for the card's suit
     */
    private String getSuitSymbol() {
        String suitLower = suit.toLowerCase();
        switch (suitLower) {
            case "hearts": return "♥";
            case "diamonds": return "♦";
            case "clubs": return "♣";
            case "spades": return "♠";
            default: return "?";
        }
    }

    /**
     * Converts card name to a display value
     * @return Single character or number representing the card's value
     */
    private String getDisplayValue() {
        String nameUpper = name.toUpperCase();
        switch (nameUpper) {
            case "ACE": return "A";
            case "KING": return "K";
            case "QUEEN": return "Q";
            case "JACK": return "J";
            default: return String.valueOf(value);
        }
    }

    /**
     * Gets the card's comparison value for game logic
     * Handles special cases like Ace high and face cards
     * @return Numerical value for card comparison (Ace=14, King=13, Queen=12, Jack=11)
     */
    public int getComparisonValue() {
        switch (name.toUpperCase()) {
            case "ACE": return 14;  // Ace high
            case "KING": return 13;
            case "QUEEN": return 12;
            case "JACK": return 11;
            default: return value;
        }
    }
}

