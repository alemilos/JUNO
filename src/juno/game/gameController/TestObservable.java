package juno.game.gameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

public class TestObservable implements ActionListener {

    private ArrayList<TestObserver> subscribers = new ArrayList<>();

    private JFrame frame;

    private JButton btn;

    public TestObservable(){
        frame = new JFrame();
        frame.setSize(600,600);

        btn = new JButton("BOTTONE");
        btn.setPreferredSize(new Dimension(500,200));
        btn.addActionListener(this);

        frame.add(btn);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        TestObserver to1 = new TestObserver("ale");
        TestObserver to2 = new TestObserver("vale");
        TestObserver to3 = new TestObserver("fede");

        TestObservable tObservable= new TestObservable();

        tObservable.addSub(to1);
        tObservable.addSub(to2);
        tObservable.addSub(to3);

        System.out.println(tObservable.getSubscribers());

    }

    public void addSub(TestObserver to){
        subscribers.add(to);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn){
            for(TestObserver to : subscribers){
                to.update();
            }
        }
    }

    public ArrayList<TestObserver> getSubscribers(){
        return subscribers;
    }

}
