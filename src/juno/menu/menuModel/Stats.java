package juno.menu.menuModel;

public class Stats {

    private Level level;
    private int win;
    private int lose;
    private int played;

    public Stats(){
        win = 0;
        lose = 0;
        played = 0;
        level = new Level();
    }

    public int getWin(){
        return win;
    }
    public int getLose(){
       return lose;
    }
    public int getPlayed(){
        return played;
    }

    public Level getLevel(){
        return level;
    }
}