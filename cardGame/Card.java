
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
    public void getPicture(String picture){
        this.picture=picture;
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
}