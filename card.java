package cardGame;

public class Card{
    private String suit;
    private String name;
    private int value;
    private String picture;

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
}