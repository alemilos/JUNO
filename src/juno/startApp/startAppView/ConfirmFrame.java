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

public class ConfirmFrame extends JFrame implements ActionListener{

    private String result = null;

    private JFrame frame;

    private JButton undoBtn;

    private JButton confirmBtn;

    private ProfileGUI profileGUI;

    private GameSettingsGUI gameSettingsGUI;

    public ConfirmFrame(JFrame parentFrame, String parentFrameName, User user, int playersNumber, Difficulty difficulty, String backCardPath){
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(200,120));

        // Confirm Label
        JLabel confirmLabel = new JLabel("Confermi?");
        confirmLabel.setForeground(new Color(203, 90, 90));
        confirmLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
        confirmLabel.setHorizontalAlignment(JLabel.CENTER);

        // Undo BTN
        ImageIcon undoIcon = new ImageIcon("src/Images/MenuIcons/undoIcon.jpeg");
        Image undoImage = undoIcon.getImage();
        Image newUndoImage = undoImage.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        undoIcon = new ImageIcon(newUndoImage);
        undoBtn = new JButton(undoIcon);
        undoBtn.setFocusPainted(false);
        undoBtn.addActionListener(this);

        // Confirm BTN
        ImageIcon confirmIcon = new ImageIcon("src/Images/MenuIcons/confirmIcon.png");
        Image confirmImage = confirmIcon.getImage();
        Image newConfirmImage = confirmImage.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        confirmIcon = new ImageIcon(newConfirmImage);
        confirmBtn = new JButton(confirmIcon);
        confirmBtn.setFocusPainted(false);
        confirmBtn.addActionListener(this);

        // adding to frame
        frame.add(confirmLabel, BorderLayout.NORTH);
        frame.add(undoBtn, BorderLayout.WEST);
        frame.add(confirmBtn, BorderLayout.EAST);

        frame.setVisible(true);

    }

    public String getResult(){
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


   /* @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == undoBtn){
            frame.dispose();
        }
        else if(e.getSource() == confirmBtn){
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
    }

    */
}
