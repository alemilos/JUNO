package juno;

import java.util.ArrayList;

public class Table {

    private DrawDeck drawDeck;
    private ArrayList<Player> players;
    private ArrayList<Card> userPlayerHand;
    private Card groundCard;



    public Table(ArrayList<Player> players){
        this.drawDeck = new DrawDeck();
        this.players = players;
    }

    public DrawDeck getDrawDeck(){
        return drawDeck;
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }
    public ArrayList<Card> getUserPlayerHand(){
        return userPlayerHand;
    }
    public int getPlayersNumber(){
        return players.size();
    }
    public Card getGroundCard(){
        return groundCard;
    }


}
