package juno;

public class SkipCard extends ColoredCard{

    private CardColors color;
    private CardTypes type;

    public SkipCard(CardColors color, CardTypes type){
        super(color, type);
        this.color = color;
        this.type = type;
    }

    public String toString(){
        return "Color: " + this.color.toString() + " Type: " + this.type.toString();
    }


}
