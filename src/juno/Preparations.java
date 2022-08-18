package juno;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Preparations {

    private Table table;
    private ArrayList<ArrayList<Card>> playerHands;

    public Preparations(Table table){
        this.table = table;

        // Everyone draw a card
        DrawDeck deck = table.getDrawDeck();
        for(Player player : table.getPlayers()){
            Card randomCard = this.table.getDrawDeck().getRandomCard();
            player.setPreparationsRandomCardValue(randomCard);
        }
        // sort players based on: randomCardValue
        ArrayList<Player> players = table.getPlayers().stream()
                .sorted(Comparator.comparing(Player::getPreparationsRandomCardValue))
                .collect(Collectors.toList());
    }

}
