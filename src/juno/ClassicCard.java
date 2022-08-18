package juno;

public class ClassicCard extends ColoredCard{

    private int number;
    private CardColors color;
    private CardTypes type;

    public ClassicCard(CardColors color, int number, CardTypes type){
        super(color, type);
        this.number = number;
        this.color = color;
        this.type = type;
    }

    public int getNumber(){
        return number;
    }

    public String toString(){
        return "Color: " + this.color.toString() +  " Number: " + this.number +  " Type: " + this.type.toString();
    }

}
