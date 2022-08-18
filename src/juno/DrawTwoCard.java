package juno;

public class DrawTwoCard extends ColoredCard{

    private final int CARDS_TO_DRAW = 2;

    public DrawTwoCard(CardColors color, CardTypes type){
        super(color, type);
    }

    public CardTypes getType(){
        return this.type;
    }

    public String toString(){
        return "Color: " + this.color.toString() + " Type: " + this.type.toString();
    }
}
