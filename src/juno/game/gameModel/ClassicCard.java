package juno.game.gameModel;

public class ClassicCard extends ColoredCard{

    private int number;

    public ClassicCard(CardColors color, int number, CardTypes type){
        super(color, type);
        this.number = number;

    }

    public int getNumber(){
        return number;
    }

    public CardTypes getType(){
        return this.type;
    }

    public String toString(){
        return "Color: " + this.color.toString() +  " Number: " + this.number +  " Type: " + this.type.toString();
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
