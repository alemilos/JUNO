package juno;

public class WildDrawFourCard extends Card{

    private final int CARDS_TO_DRAW = 4;
    private CardColors stateColor;
    private CardTypes type;

    public WildDrawFourCard(CardTypes type){
        super(type);
        this.type = type;
    }

    public void setStateColor(CardColors color){
        stateColor = color;
    }

    public String toString(){
        return "Type: " + this.type.toString();
    }
}
