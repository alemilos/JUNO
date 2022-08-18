package juno;

import java.util.ArrayList;

public class Game {

    private Table table;
    private int roundNumber;
    private int playerNumber;
    private boolean isOver; // implicitly false
    private ArrayList<ArrayList<Card>> playerHands;

    public Game(Table table){
        this.table = table;
        this.roundNumber = 0;
        this.playerNumber = table.getPlayersNumber();

    }








}
