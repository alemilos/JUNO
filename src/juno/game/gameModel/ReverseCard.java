package juno.game.gameModel;

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

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
