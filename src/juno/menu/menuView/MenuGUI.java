package juno.menu.menuView;

import juno.game.gameView.GameSettingsGUI;
import juno.menu.menuModel.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI {

    public MenuGUI(User user) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(600,500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        // instances of JPanel
        JPanel topPanel = new JPanel();

        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        // TITLE label
        JLabel title = new JLabel();
        title.setText("JUNO");
        title.setForeground(new Color(203, 90, 90));
        title.setFont(new Font("Sans Serif", Font.BOLD, 80));
        title.setVerticalAlignment(JLabel.CENTER);

        // Settings Button
        ImageIcon settingsIcon = new ImageIcon("src/menuIcons/settingsIcon.png");
        Image settingsImage = settingsIcon.getImage();
        Image newSettingsImage = settingsImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        settingsIcon = new ImageIcon(newSettingsImage);
        JButton settingsBtn = new JButton(settingsIcon);

        // Profile Button
        ImageIcon profileIcon = new ImageIcon("src/menuIcons/profileIcon.jpeg");
        Image profileImage = profileIcon.getImage();
        Image newProfileImage = profileImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        profileIcon = new ImageIcon(newProfileImage);
        JButton profileBtn = new JButton(profileIcon);

        // open ProfileFrame if clicked
        profileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProfileGUI profilePage = new ProfileGUI(user);
                frame.setVisible(false);
                frame.dispose();
            }
        });

        // Button GIOCA
        JButton playButton = new JButton("Gioca");
        playButton.setForeground(Color.WHITE);
        playButton.setOpaque(true);
        playButton.setBorderPainted(false);
        playButton.setBackground(new Color(221, 121, 115));
        playButton.setFont(new Font("Sans Serif", Font.BOLD, 70));
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setSize(new Dimension(100,50));

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameSettingsGUI gsg = new GameSettingsGUI(user);
                frame.dispose();
            }
        });

        // Button ESCI
        JButton exitButton = new JButton();
        exitButton.setText(" Esci ");
        exitButton.setForeground(Color.WHITE);
        exitButton.setOpaque(true);
        exitButton.setBorderPainted(false);
        exitButton.setBackground(new Color(221, 121, 115));
        exitButton.setFont(new Font("Sans Serif", Font.BOLD, 70));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setSize(new Dimension(100,50));

        // close game if clicked
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Another Window should appear telling: are you sure?
                frame.dispose();
            }
        });



        // Panels settings
        topPanel.setPreferredSize(new Dimension(100,120));
        topPanel.add(title);

        centralPanel.setPreferredSize(new Dimension(100,100));
        centralPanel.add(playButton);
        centralPanel.add(Box.createVerticalStrut(20));
        centralPanel.add(exitButton);

        bottomPanel.setPreferredSize(new Dimension(100,60));
        bottomPanel.add(settingsBtn, BorderLayout.WEST);
        bottomPanel.add(profileBtn, BorderLayout.EAST);

        // Adding panels to frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centralPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);




        frame.setVisible(true);

    }
}
