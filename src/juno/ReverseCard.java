package juno;

public class ReverseCard extends ColoredCard{

    public ReverseCard(CardColors color, CardTypes type){
        super(color, type);
    }

    public CardTypes getType(){
        return this.type;
    }

    public String toString(){
        return "Color: " + this.color.toString() + " Type: " + this.type.toString();
    }
}
