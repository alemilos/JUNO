package juno;

import java.util.ArrayList;

public class Table {

    private DrawDeck drawDeck;
    private ArrayList<Player> players;
    private Card groundCard;
    private Player dealer;
    private Player playingPlayer;
    private boolean reverseFlow; // if true, next will traverse the list in the opposite direction
    private boolean isBlocked; // if true, next will jump a player.

    public Table(ArrayList<Player> players){
        this.drawDeck = new DrawDeck();
        this.players = players;
    }

    public DrawDeck getDrawDeck(){
        return drawDeck;
    }


    public int getPlayersNumber(){
        return players.size();
    }

    public void setGroundCard(Card groundCard){
        this.groundCard = groundCard;
    }

    public Card getGroundCard(){
        return groundCard;
    }

    public void setPlayers(ArrayList<Player> players){
        // This method gets called in preparation phase to sort based on random card drawn
        this.players = players;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public void setDealer(Player dealer){
        this.dealer = dealer;
    }

    public Player getDealer(){
        return dealer;
    }

    public void setPlayingPlayer(Player playingPlayer){
        this.playingPlayer = playingPlayer;
    }

    public Player getPlayingPlayer() {
        return playingPlayer;
    }

    public void setReverseFlow(){
        // this method just changes the flow from the previous one
        this.reverseFlow = !reverseFlow;
    }

    public boolean isReverseFlow() {
        return reverseFlow;
    }


    public void nextPlayer(){
        /* This function considers the cases in which the flow of game is reversed and also when the blocked card is active
        * Since the Skip card only works for a single player hand, it gets turned off (false) at the end of this method
        * In the case in which the block was already off, nothing changes*/
        int playerIndex = this.players.indexOf(this.playingPlayer);
        int shift = isBlocked ? 2 : 1;
        int playerArraySize = this.players.size();
        if(reverseFlow){
            int position = playerIndex - shift;
            if (position < 0){
                this.playingPlayer = this.players.get(position + playerArraySize);
            }
            else{
                this.playingPlayer = this.players.get(position);
            }
        }
        else{
            int position = playerIndex + shift;
            if (position > playerArraySize-1){
                this.playingPlayer = this.players.get(position - playerArraySize);
            }
            else{
                this.playingPlayer = this.players.get(position);
            }
        }

        this.isBlocked = false;
    }
}
