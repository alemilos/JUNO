package juno;

public abstract class Card {

    private CardTypes cardType;

    public Card(CardTypes type) {
        cardType = type;
    }

    public abstract String toString();

}

