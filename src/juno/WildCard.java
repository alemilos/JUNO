package juno;

public class WildCard extends Card {

    private CardColors stateColor;
    private CardTypes type;

    public WildCard(CardTypes type){
        super(type);
        this.type = type;
    }

    public void setStateColor(CardColors color){
        this.stateColor = color;
    }

    public String toString(){
        return "Type: " + this.type.toString();
    }

}
