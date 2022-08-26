package juno.game.gameModel;

public abstract class Card {

    protected CardTypes type;

    protected String imagePath;


    public Card(CardTypes type) {
        this.type = type;
    }

    public abstract CardTypes getType();

    public abstract CardColors getColor();

    public abstract String toString();

    public abstract String getImagePath();

    public abstract void setImagePath(String imagePath);





}

