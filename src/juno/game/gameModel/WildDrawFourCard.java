package juno.game.gameModel;

public class WildDrawFourCard extends SpecialCard{

    private final int CARDS_TO_DRAW = 4;

    public WildDrawFourCard(CardTypes type){
        super(type);

    }

    public CardTypes getType(){
        return this.type;
    }


    public String toString(){
        return "Type: " + this.type.toString();
    }
}
