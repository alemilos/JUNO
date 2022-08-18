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

        // 1) sort players based on their random value with stream -- if equal do again?
        // 2) distr of cards by the dealer, which is the Player with the highest value in the sorted LIST (7 each)
        // the deck is now ready to be used
        // 3) groundCard is the firstDeck card
        // 4) Game starts 



    }
}
