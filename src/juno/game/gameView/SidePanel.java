package juno.game.gameView;

import juno.menu.menuModel.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidePanel extends JPanel implements ActionListener {

    private JPanel sidePanel;

    private JPanel innerNorthPanel;

    private JButton leaveDoorBtn;

    private JButton settingsBtn;

    private JFrame gameFrame;

    private User user;

    public SidePanel(User user){
        this.user = user;

        sidePanel = new JPanel(new BorderLayout());
        sidePanel.setSize(100,800);

        innerNorthPanel = new JPanel();
        innerNorthPanel.setLayout(new BoxLayout(innerNorthPanel,BoxLayout.Y_AXIS));
        innerNorthPanel.setPreferredSize(new Dimension(100,400));

        ImageIcon leaveDoorIcon = new ImageIcon("src/Images/MenuIcons/leave_door.png");
        Image leaveDoorImage = leaveDoorIcon.getImage();
        Image newLeaveDoorImage = leaveDoorImage.getScaledInstance(40,40, Image.SCALE_SMOOTH);
        leaveDoorIcon = new ImageIcon(newLeaveDoorImage);
        leaveDoorBtn = new JButton(leaveDoorIcon);
        leaveDoorBtn.setFocusPainted(false);
        leaveDoorBtn.addActionListener(this);

        ImageIcon settingsIcon = new ImageIcon("src/Images/MenuIcons/settings.png");
        Image settingsImage = settingsIcon.getImage();
        Image newSettingsImage = settingsImage.getScaledInstance(40,40, Image.SCALE_SMOOTH);
        settingsIcon = new ImageIcon(newSettingsImage);
        settingsBtn = new JButton(settingsIcon);
        settingsBtn.setFocusPainted(false);
        settingsBtn.addActionListener(this);

        innerNorthPanel.add(Box.createVerticalStrut(20));
        innerNorthPanel.add(leaveDoorBtn);
        innerNorthPanel.add(settingsBtn);

        sidePanel.add(innerNorthPanel, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == leaveDoorBtn ){
            System.out.println("IMPLEMENT A CONFIRM FRAME AND THEN ADD A LOSE IN USER STATS");
            updateJSON();
            gameFrame.dispose();
        }else if(e.getSource() == settingsBtn){
            System.out.println("settings");
        }
    }

    public JPanel getSidePanel(){
        return sidePanel;
    }

    public void setSidePanel(JPanel sidePanel) {
        this.sidePanel = sidePanel;
    }

    public JPanel getInnerNorthPanel() {
        return innerNorthPanel;
    }

    public void setInnerNorthPanel(JPanel innerNorthPanel) {
        this.innerNorthPanel = innerNorthPanel;
    }

    public void updateJSON(){
        System.out.println("Implement a function that changes JSON element adding 1 lose to him");
    }

    public void setGameFrame(JFrame gameFrame){
        this.gameFrame = gameFrame;
    }

}
