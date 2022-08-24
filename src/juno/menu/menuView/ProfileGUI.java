package juno.menu.menuView;

import juno.menu.menuModel.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileGUI {

    public ProfileGUI(User user){
        JFrame frame = new JFrame("Profilo");
        frame.setSize(600,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Title label
        JLabel title = new JLabel("Profilo");
        title.setForeground(new Color(203, 90, 90));
        title.setFont(new Font("Sans Serif", Font.BOLD, 80));
        title.setVerticalAlignment(JLabel.CENTER);
        frame.add(title);

        /*
        * Here goes the code to program
        *  a rectangle in which an Avatar image is displayed
        */

        // username label
        JLabel username = new JLabel();
        username.setText("Username: " + user.getUsername() );


        // GoBack Button
        ImageIcon goBackIcon = new ImageIcon("src/menuIcons/gobackIcon.png");
        Image goBackImage = goBackIcon.getImage();
        Image newGoBackImage = goBackImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        goBackIcon = new ImageIcon(newGoBackImage);
        JButton goBackBtn = new JButton(goBackIcon);

        // goback action
        goBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuGUI menuGUI = new MenuGUI(user);
                frame.setVisible(false);
                frame.dispose();

            }
        });

        // Panels
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(100,120));
        topPanel.add(title);

        JPanel centralPanel = new JPanel();
        centralPanel.add(username);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(100,100));
        bottomPanel.add(goBackBtn, BorderLayout.WEST);

        // Adding panels to frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centralPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);



        frame.setVisible(true);
    }
}
