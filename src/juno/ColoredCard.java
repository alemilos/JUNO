package juno;

public abstract class ColoredCard extends Card{

    private CardColors color;

    public ColoredCard(CardColors color, CardTypes type){
        super(type);
        this.color = color;
    }

    public CardColors getColor(){
        return color;
    }


}
