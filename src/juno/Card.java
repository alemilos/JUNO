package juno;

public abstract class Card {

    protected CardTypes type;

    public Card(CardTypes type) {
        this.type = type;
    }

    public abstract CardTypes getType();

    public abstract String toString();

}

