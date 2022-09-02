package juno.game.gameModel;

import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> hand;
    private boolean hasUno; // true if hand.length == 1, false otherwise
    private boolean isBot = true; // true if the player is a BOT

    private boolean isDealer;
    private int preparationsRandomCardValue;

    public Player(String name){
        this.name = name;
    }
    public Player(String name, boolean isBot){
        this.name = name;
        this.isBot = isBot;
    }

    public void setPreparationsRandomCardValue(Card preparationsRandomCard) {
        /**For preparation phase**/
        if (preparationsRandomCard.getClass() != ClassicCard.class){
            this.preparationsRandomCardValue = 0;
        }
        else{
            ClassicCard downCastClassicCard = (ClassicCard)preparationsRandomCard;
            this.preparationsRandomCardValue = downCastClassicCard.getNumber();
        }
    }

    public void drawCard(Card card){
        this.hand.add(card);
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

    public boolean hasUno() {
        return hasUno;
    }

    public int getPreparationsRandomCardValue() {
        return preparationsRandomCardValue;
    }

    public String toString(){
        return name;
    }

    public boolean isDealer() {
        return isDealer;
    }

    public void setDealer(boolean dealer) {
        isDealer = dealer;
    }
}
