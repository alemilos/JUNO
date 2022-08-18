package juno;

public class Player {

    private String name;
    private Card[] hand;
    private boolean hasUno; // true if hand.length == 1, false otherwise
    private boolean isBot; // true if the player is a BOT
    private int preparationsRandomCardValue;

    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public Card[] getHand(){
        return hand;
    }

    public boolean getIsBOT() {
        return isBot;
    }

    public boolean getHasUNO() {
        return hasUno;
    }

    public int getPreparationsRandomCardValue() {
        return preparationsRandomCardValue;
    }

    public void setPreparationsRandomCardValue(Card preparationsRandomCard) {
        if (preparationsRandomCard.getClass() != ClassicCard.class){
            this.preparationsRandomCardValue = 0;
        }
        else{
            ClassicCard downCastClassicCard = (ClassicCard)preparationsRandomCard;
            this.preparationsRandomCardValue = downCastClassicCard.getNumber();
        }
    }
}
