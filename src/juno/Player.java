package juno;

public class Player {

    private String name;
    private Card[] hand;
    private boolean hasUno; // true if hand.length == 1, false otherwise
    private boolean isBot; // true if the player is a BOT

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
}
