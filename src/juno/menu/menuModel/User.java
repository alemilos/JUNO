package juno.menu.menuModel;

import java.util.Iterator;

public class User{

    private String username;
    private int age;
    private Stats stats;

    private Avatar avatar;

    public User(String username, int age, String avatarPath){
        this.username = username;
        this.age = age;
        this.stats = new Stats();
        this.avatar = new Avatar(avatarPath);
    }

    public String getUsername(){
        return username;
    }

    public int getAge(){
        return age;
    }
    public Stats getStats(){
        return stats;
    }
    public Avatar getAvatar(){
        return avatar;
    }

}