package juno.game.gameModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Preparations {

    private Table table;

    public Preparations(Table table){
        this.table = table;

        // Everyone draw a card
        DrawDeck deck = table.getDrawDeck();
        for(Player player : table.getPlayers()){
            Card randomCard = this.table.getDrawDeck().getRandomCard();
            player.setPreparationsRandomCardValue(randomCard);
        }
        // sort players based on: randomCardValue (big to small)
        ArrayList<Player> players = table.getPlayers().stream()
                                    .sorted(Comparator.comparing(Player::getPreparationsRandomCardValue).reversed())
                                    .collect(Collectors.toCollection(ArrayList::new));
        // Setting the order in wich the players will play.
        this.table.setPlayers(players);
        // Setting the dealer (first one of the players list)
        this.table.setDealer(players.get(0));
        // Setting the first playing Player to the player next to the dealer
        this.table.setPlayingPlayer(players.get(1));

        // set cards for each player
        for (Player player: players){
            ArrayList<Card> hand = new ArrayList<>();
            // Add 7 cards to hand for each player
            for (int distributeCard = 0; distributeCard < 7; distributeCard++) {
                hand.add(this.table.getDrawDeck().getFirstCard());
            }
            player.setHand(hand);
        }

        // set the ground card as the first card from the deck
        this.table.setGroundCard(this.table.getDrawDeck().getFirstCard());
        // check if the ground card is WildDrawFour
        boolean cardProblemIsFixed = false;
        // while the groundCard equals a WildDrawFourCard...
        while(!cardProblemIsFixed){
            // check if ground card equals WildDrawFourCard
            if (this.table.getGroundCard().getClass() == WildDrawFourCard.class){
                // If YES... put card in the deck randomly
                this.table.getDrawDeck().putCardInTheMiddle(this.table.getGroundCard());
                // set GroundCard to first card of the deck
                this.table.setGroundCard(this.table.getDrawDeck().getFirstCard());
            }else{
                // If NO... end cycle
                cardProblemIsFixed = true;
            }
        }

    }

    public Table getTable(){
        return table;
    }

}
