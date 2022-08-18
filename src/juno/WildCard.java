package juno;

public class WildCard extends Card {

    private CardColors stateColor;

    public WildCard(CardTypes type){
        super(type);

    }



    public void setStateColor(CardColors color){
        this.stateColor = color;
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
