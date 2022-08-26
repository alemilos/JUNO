package juno.startApp.startAppView;

import juno.game.gameModel.Difficulty;
import juno.game.gameView.GameGUI;
import juno.game.gameView.GameSettingsGUI;
import juno.menu.menuModel.User;
import juno.menu.menuView.ProfileGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmFrame extends JFrame{

    String result = null;

    public ConfirmFrame(JFrame parentFrame, String parentFrameName, User user, int playersNumber, Difficulty difficulty, String backCardPath){
        // here go 2 buttons : UNDO and CONFIRM
        // if confirm --> delete the user
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(200,120));

        // Confirm Label
        JLabel confirmLabel = new JLabel("Confermi?");
        confirmLabel.setForeground(new Color(203, 90, 90));
        confirmLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
        confirmLabel.setHorizontalAlignment(JLabel.CENTER);

        // Undo BTN
        ImageIcon undoIcon = new ImageIcon("src/menuIcons/undoIcon.jpeg");
        Image undoImage = undoIcon.getImage();
        Image newUndoImage = undoImage.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        undoIcon = new ImageIcon(newUndoImage);
        JButton undoBtn = new JButton(undoIcon);
        undoBtn.setFocusPainted(false);

        undoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // Confirm BTN
        ImageIcon confirmIcon = new ImageIcon("src/menuIcons/confirmIcon.png");
        Image confirmImage = confirmIcon.getImage();
        Image newConfirmImage = confirmImage.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        confirmIcon = new ImageIcon(newConfirmImage);
        JButton confirmBtn = new JButton(confirmIcon);
        confirmBtn.setFocusPainted(false);

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                if (parentFrameName.equals("ProfileGUI")) {
                    ProfileGUI.removeUserFromJSON(user);
                    StartAppView saw = new StartAppView();
                    parentFrame.dispose();
                } else if(parentFrameName.equals("GameSettingsGUI")){
                    GameGUI gg = new GameGUI(user, playersNumber, difficulty, backCardPath );
                    parentFrame.dispose();
                }
            }
        });

        // adding to frame
        frame.add(confirmLabel, BorderLayout.NORTH);
        frame.add(undoBtn, BorderLayout.WEST);
        frame.add(confirmBtn, BorderLayout.EAST);

        frame.setVisible(true);


    }

    public String getResult(){
        return result;
    }
}
