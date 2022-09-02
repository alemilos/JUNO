package juno.game.gameModel;

public abstract class SpecialCard extends Card{

    protected CardColors color;
    protected boolean isActive;

    public SpecialCard(CardTypes type){
        super(type);
    }

    public void setColor(CardColors color){
        this.color = color;
    }

    public CardColors getColor(){
        return color;
    }

    public void setActive(boolean isActive){
        this.isActive = isActive;
    }

    public boolean isActive(){
        return isActive;
    }

}
