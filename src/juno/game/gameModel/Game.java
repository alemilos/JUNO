package juno.game.gameModel;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private Table table;
    private int roundNumber;
    private boolean isOver;


    public Game(Preparations preparation){
        this.table = preparation.getTable();
        this.roundNumber = 0;
    }

    public Table getTable(){
        return table;
    }

    public void setRoundNumber(){
        this.roundNumber+=1;
    }

    public int getRoundNumber(){
        return roundNumber;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public boolean isOver() {
        return isOver;

    }

    // re-make with streams
    public ArrayList<Card> usableCards(ArrayList<Card> playerHand){
        ArrayList<Card> usableCards = new ArrayList<>();
        Card groundCard = table.getGroundCard();
        CardColors colorOnGround = groundCard.getColor();
        // only classic cards has number. If the card is not classic the number on ground will be -1
        int numberOnGround = groundCard instanceof ClassicCard
                            ? ((ClassicCard)groundCard).getNumber()
                            : -1;
        for (Card card : playerHand){
            // evaluate color, number, special cards
            if (card.getColor() == colorOnGround){
            // same color
                usableCards.add(card);
            }
            else if(card instanceof ClassicCard){
                int cardNumber = ((ClassicCard)card).getNumber();
                if (cardNumber == numberOnGround){
                    usableCards.add(card);
                }
            }else if(card instanceof SpecialCard){
                usableCards.add(card);
            }
        }
        return usableCards;
    }


    public void playRound(Player player){
        // here goes the check for the WILD DRAW FOUR CARD -> playable iff no classical card in hand matches the color with the ground card.
        if (player.isBot()){
            playRoundAI(player);
        }
        else{
            playRoundUser(player);
        }
    }

    public void playRoundAI(Player player){
        if (table.getGroundCard() instanceof WildCard){
            // If the wild card is not active, AI will choose randomly a color
            WildCard groundCard = (WildCard)table.getGroundCard();
            if (!groundCard.isActive()){
                // get a random value [0,3]
                Random rand = new Random();
                int randomValue = rand.nextInt(4);

                CardColors color = CardColors.values()[randomValue];
                groundCard.setColor(color);
                groundCard.setActive(true);
            }
        }
        if (usableCards(player.getHand()).size() > 0){

        }
    }

    public void playRoundUser(Player player){

    }
}
