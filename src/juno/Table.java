package juno;

import java.util.ArrayList;

public class Table {

    private DrawDeck drawDeck;
    private ArrayList<Player> players;
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

    public int getPlayersNumber(){
        return players.size();
    }

    public Card getGroundCard(){
        return groundCard;
    }

    public void setPlayers(ArrayList<Player> players){
        // This method gets called in preparation phase to sort based on random card drawn
        this.players = players;
    }


}
