package juno.game.gameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class TestObserver {


    private boolean state;

    private String name;

    public TestObserver(String name){
        this.name = name;
    }

    public void update(){
        state = !state;
        System.out.println("new state" + state);
    }

    @Override
    public String toString() {
        return name;
    }
}
