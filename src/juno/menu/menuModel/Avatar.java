package juno.menu.menuModel;

import javax.swing.*;
import java.awt.*;

public class Avatar {

    private String avatarPath;

    public Avatar(String avatarPath){
        this.avatarPath = avatarPath;
    }

    public void setAvatarPath(String avatarPath){
        this.avatarPath = avatarPath;
    }

    public String getAvatarPath(){
        return avatarPath;
    }
}
