package juno.startApp.startAppView;

import juno.menu.menuModel.User;
import juno.menu.menuView.MenuGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChooseAccount {

    public ChooseAccount(User[] users){
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // TITLE LABEL
        JLabel title = new JLabel();
        title.setText("Scegli Profilo");
        title.setForeground(new Color(203, 90, 90));
        title.setFont(new Font("Sans Serif", Font.BOLD, 80));
        title.setVerticalAlignment(JLabel.CENTER);

        // get usernames
        String[] usernames = new String[users.length];
        for(int i = 0; i < users.length; i++){
            usernames[i] = users[i].getUsername();
        }
        // create a comboBOX
        JComboBox accounts = new JComboBox(usernames);

        ImageIcon goBackIcon = new ImageIcon("src/Images/MenuIcons/gobackIcon.png");
        Image goBackImage = goBackIcon.getImage();
        Image newGoBackImage = goBackImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        goBackIcon = new ImageIcon(newGoBackImage);
        JButton goBackBtn = new JButton(goBackIcon);

        goBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartAppView saw = new StartAppView();
                frame.setVisible(false);
                frame.dispose();

            }
        });

        ImageIcon confirmIcon = new ImageIcon("src/Images/MenuIcons/confirmIcon.png");
        Image confirmImage = confirmIcon.getImage();
        Image newConfirmImage = confirmImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        confirmIcon = new ImageIcon(newConfirmImage);
        JButton confirmBtn = new JButton(confirmIcon);

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = findUserByUsername(accounts.getSelectedItem().toString(), users);

                MenuGUI menuGUI = new MenuGUI(user);
                frame.setVisible(false);
                frame.dispose();

            }
        });

        // create bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(100, 60));

        bottomPanel.add(goBackBtn, BorderLayout.WEST);
        bottomPanel.add(confirmBtn,BorderLayout.EAST);

        // adding components to frame
        frame.add(title, BorderLayout.NORTH);
        frame.add(accounts, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

    private User findUserByUsername(String username, User[] users){
        for(User user: users){
            if (username.equals(user.getUsername())){
                return user;
            }
        }
        return null; // never going to happen
    }
}
