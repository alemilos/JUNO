package juno.game.gameModel;

public class WildCard extends SpecialCard {

    public WildCard(CardTypes type){
        super(type);
    }

    public CardTypes getType(){
        return this.type;
    }


    public String toString(){
        return "Type: " + this.type.toString();
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
