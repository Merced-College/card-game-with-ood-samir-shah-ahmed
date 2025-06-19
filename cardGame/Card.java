//constructors 
public class Card{
    private String suit;
    private String name;
    private int value;
    private String picture;
//accessors
    public Card(String cardSuit, String cardName, int cardValue, String cardPicture){
        suit=cardSuit;
        name=cardName;
        value=cardValue;
        picture=cardPicture;
    }
    public String getSuit(){
        return suit;
    }
    public String getName(){
        return name;
    }
    public String getPicture(){
        return picture;
    }
    //Mutators
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
    //toString to print card information
     
    public String toString(){
        return name+" of "+suit +" (Value: " + value + ")";
    }
    //used for pair checking
    public boolean equals(Object obj){
        if (this==obj){
         return true;
        }
        if (obj==null || getClass() != obj.getClass()) {
            return false;
        }
        Card card =(Card) obj;
        return name.equals(card.name)&& suit.equals(card.suit);
    }

    // Add these new methods for terminal display
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
}

