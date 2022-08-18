package juno;

public class WildDrawFourCard extends Card{

    private final int CARDS_TO_DRAW = 4;
    private CardColors stateColor;

    public WildDrawFourCard(CardTypes type){
        super(type);

    }

    public void setStateColor(CardColors color){
        stateColor = color;
    }

    public CardColors getStateColor(){
        return stateColor;
    }

    public CardTypes getType(){
        return this.type;
    }

    public String toString(){
        return "Type: " + this.type.toString();
    }
}
