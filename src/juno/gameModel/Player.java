package juno.gameModel;

import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> hand;
    private boolean hasUno; // true if hand.length == 1, false otherwise
    private boolean isBot; // true if the player is a BOT
    private int preparationsRandomCardValue;

    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public ArrayList<Card> getHand(){
        return hand;
    }

    public void setHand(ArrayList<Card> hand){
        this.hand = hand;
    }

    public boolean isBot() {
        return isBot;
    }

    public boolean isHasUno() {
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
