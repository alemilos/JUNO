package juno;

public class DrawTwoCard extends ColoredCard{

    private final int CARDS_TO_DRAW = 2;
    private CardColors color;
    private CardTypes type;

    public DrawTwoCard(CardColors color, CardTypes type){
        super(color, type);
        this.color = color;
        this.type = type;
    }

    public String toString(){
        return "Color: " + this.color.toString() + " Type: " + this.type.toString();
    }
}
