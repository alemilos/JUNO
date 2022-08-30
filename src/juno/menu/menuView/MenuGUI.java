package juno.menu.menuView;

import juno.game.gameView.GameSettingsGUI;
import juno.menu.menuModel.User;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI extends JFrame implements ActionListener{

    private JFrame frame;

    private JPanel titlePanel;

    private JPanel centralPanel;

    private JPanel bottomPanel;

    private JButton settingsBtn;

    private JButton profileBtn;

    private JButton playBtn;

    private JButton exitBtn;

    private User user;

    public MenuGUI(User user) {

        this.user = user;

        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setResizable(true);

        /** TITLE PANEL**/
        titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setPreferredSize(new Dimension(100,120));

        JLabel title = new JLabel("JUNO");
        title.setForeground(new Color(203, 90, 90));
        title.setFont(new Font("Sans Serif", Font.BOLD, 80));

        titlePanel.add(title);

        /** CENTRAL PANEL**/
        centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        centralPanel.setPreferredSize(new Dimension(100,100));

        playBtn = new JButton("Gioca");
        playBtn.setForeground(Color.WHITE);
        playBtn.setOpaque(true);
        playBtn.setBackground(new Color(221, 121, 115));
        playBtn.setFont(new Font("Sans Serif", Font.BOLD, 70));
        playBtn.setPreferredSize(new Dimension(500,100));
        playBtn.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        playBtn.addActionListener(this);

        exitBtn = new JButton("Esci");
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setOpaque(true);
        exitBtn.setBackground(new Color(221, 121, 115));
        exitBtn.setFont(new Font("Sans Serif", Font.BOLD, 70));
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.setPreferredSize(new Dimension(500,100));
        exitBtn.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        exitBtn.addActionListener(this);

        JPanel button1Panel = new JPanel(new GridBagLayout());
        button1Panel.setPreferredSize(new Dimension(200,200));
        button1Panel.add(playBtn);

        JPanel button2Panel = new JPanel();
        button2Panel.setPreferredSize(new Dimension(200,500));
        button2Panel.setAlignmentY(Component.TOP_ALIGNMENT);
        button2Panel.add(exitBtn);

        centralPanel.add(button1Panel);
        centralPanel.add(button2Panel);

        /** BOTTOM PANEL**/
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        // Settings Button
        ImageIcon settingsIcon = new ImageIcon("src/Images/MenuIcons/settingsIcon.png");
        Image settingsImage = settingsIcon.getImage();
        Image newSettingsImage = settingsImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        settingsIcon = new ImageIcon(newSettingsImage);
        settingsBtn = new JButton(settingsIcon);
        settingsBtn.addActionListener(this);

        // Profile Button
        ImageIcon profileIcon = new ImageIcon("src/Images/MenuIcons/profileIcon.jpeg");
        Image profileImage = profileIcon.getImage();
        Image newProfileImage = profileImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        profileIcon = new ImageIcon(newProfileImage);
        profileBtn = new JButton(profileIcon);
        profileBtn.addActionListener(this);

        bottomPanel.setPreferredSize(new Dimension(100,60));
        bottomPanel.add(settingsBtn, BorderLayout.WEST);
        bottomPanel.add(profileBtn, BorderLayout.EAST);

        // Adding panels to frame
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(centralPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == profileBtn){
            ProfileGUI profilePage = new ProfileGUI(user);
            this.frame.setVisible(false);
            this.frame.dispose();
        }
        else if(e.getSource() == settingsBtn){

        }
        else if(e.getSource() == playBtn){
            GameSettingsGUI gsg = new GameSettingsGUI(user);
            frame.dispose();
        }
        else if(e.getSource() == exitBtn){
            this.frame.dispose();
        }
    }
}
