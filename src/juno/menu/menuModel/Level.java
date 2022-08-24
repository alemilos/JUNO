package juno.menu.menuModel;

public class Level {
    /*
    * Level up mechanism:
    * Each game grants an X number of xp
    * An established set of conditions will enable different (at least 3) way of getting
    * other xps.
    * 1) Win
    * 2) Place (each place will have an XP )
    * 3) Fast game || something smarter
    *
    * Fist level has Y xp.
    * For each new Level we'll have Y + parseInt(sqrt(Y)) xp to gain
    * */

    private int xp;
    private int xpToNextLevel;
    private int level;

    public Level(){
        xp = 0;
        xpToNextLevel = 100;
        level = 0;
    }


    public int getXp() {
        return xp;
    }

    public int getXpToNextLevel() {
        return xpToNextLevel;
    }

    public int getLevel() {
        return level;
    }
}
