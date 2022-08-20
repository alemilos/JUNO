package juno;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        Player alessandro = new Player("Alessandro");
        Player marcello = new Player("Marcello");
        Player federico = new Player("Federico");
        Player denis = new Player("Denis");
        ArrayList<Player> giocatori = new ArrayList<>();
        giocatori.add(alessandro);
        giocatori.add(marcello);
        giocatori.add(federico);
        giocatori.add(denis);
        Game gioco = new Game(new Preparations(new Table(giocatori)));

        System.out.println();




    }
}
