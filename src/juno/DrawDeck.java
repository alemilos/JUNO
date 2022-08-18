package juno;

import java.util.ArrayList;

public class DrawDeck {

    private ArrayList<Card> deck;

    public DrawDeck(){
        CardColors colors[] = CardColors.values(); // list of cards colors
        ArrayList<Card> deck = new ArrayList<>(0); // empty deck to fill
        // Add coloredCards to the deck
        for(CardColors color: colors){
            // Add a ZERO classic card
            deck.add(new ClassicCard(color, 0, CardTypes.CLASSIC));
            // Add 18 classic cards ->  two for each other number in range [1-9]
            for(int number = 1; number < 10; number++){
                deck.add(new ClassicCard(color, number, CardTypes.CLASSIC));
                deck.add(new ClassicCard(color, number, CardTypes.CLASSIC));
            }
            for(int i = 0; i < 2; i++){
                // Add 2 drawTwo cards per color
                deck.add(new DrawTwoCard(color, CardTypes.DRAW_TWO));
                // Add 2 reverse cards per color
                deck.add(new ReverseCard(color, CardTypes.REVERSE));
                // Add 2 skip  cards per color
                deck.add(new SkipCard(color, CardTypes.SKIP));
            }
        }
        for(int i = 0; i < 4; i++){
            // Add 4 wild cards to deck
            deck.add(new WildCard(CardTypes.WILD));
            // Add 4 wildDrawFour cards to deck
            deck.add(new WildDrawFourCard(CardTypes.WILD_DRAW_FOUR));
        }

        shuffleCards(deck);
        this.deck = deck;

    }

    public void shuffleCards(ArrayList<Card> deck){
        // This uses the Fisher-Yates Algorithm to shuffle
        int m = deck.size();
        Card t;
        int i;
        while(m > 0){
            i = (int)(Math.floor(Math.random() * m--));
            t = deck.get(m); // keep the m-th card temporary
            deck.set(m, deck.get(i)); // set m-th card with i-th (random) card
            deck.set(i, t); // set i-th card to temporary card, which was m-th
            // switch is done
        }
    }

    public int getCardsLeft(){
        return this.deck.size();
    }

    public Card getFirstCard(){
        return deck.remove(0);
    }

    public String toString(){
        String cards = "";
        for(Card card: deck){
            cards += card.toString() + "\n";
        }
        return cards;
    }


}
