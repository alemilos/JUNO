package juno;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        Player alessandro = new Player("Alessandro");
        Player marcello = new Player("Marcello");
        ArrayList<Player> giocatori = new ArrayList<>();
        giocatori.add(alessandro);
        giocatori.add(marcello);
        Table tavolo = new Table(giocatori);


        // 1) Start thinking about interactions between stuff


    }
}
