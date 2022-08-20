package juno;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        Player alessandro = new Player("Alessandro");
        Player marcello = new Player("Marcello");
        Player federico = new Player("Federico");
        Player denis = new Player("Denis");
        Player luca = new Player("Luca");
        Player piera = new Player("Piera");
        ArrayList<Player> giocatori = new ArrayList<>();
        giocatori.add(alessandro);
        giocatori.add(marcello);
        giocatori.add(federico);
        giocatori.add(denis);
        Table tavolo = new Table(giocatori);
        Preparations prep = new Preparations(tavolo);


        System.out.println("Ground Card: " + tavolo.getGroundCard());

        // 4) Game
        // A next function has to direct the flow of the players. When a card reverse appears it has to reverse this flow




    }
}
