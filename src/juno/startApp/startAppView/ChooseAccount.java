package juno.startApp.startAppView;

import juno.menu.menuModel.User;
import juno.menu.menuView.MenuGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChooseAccount extends JFrame implements ActionListener{

    private JFrame frame;

    private JButton confirmBtn;

    private JButton goBackBtn;

    private JComboBox accountsComboBox;

    private JScrollPane scrollPane;
    private User[] users;

    public ChooseAccount(User[] users){
        this.users = users;

        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true); // this makes a fake full screen
        frame.setResizable(false);

        JLabel title = new JLabel("Scegli Profilo");
        title.setForeground(new Color(203, 90, 90));
        title.setFont(new Font("Sans Serif", Font.BOLD, 80));
        title.setVerticalAlignment(JLabel.CENTER);

        // get usernames
        String[] usernames = new String[users.length];
        for(int i = 0; i < users.length; i++){
            usernames[i] = users[i].getUsername();
        }

        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setPreferredSize(new Dimension(1000,200));
        titlePanel.add(title);

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setPreferredSize(new Dimension(1000,200));
        comboBoxPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        accountsComboBox = new JComboBox(usernames);
        accountsComboBox.setPreferredSize(new Dimension(300,50));
        comboBoxPanel.add(accountsComboBox);


        ImageIcon goBackIcon = new ImageIcon("src/Images/MenuIcons/gobackIcon.jpeg");
        Image goBackImage = goBackIcon.getImage();
        Image newGoBackImage = goBackImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        goBackIcon = new ImageIcon(newGoBackImage);
        goBackBtn = new JButton(goBackIcon);
        goBackBtn.addActionListener(this);


        ImageIcon confirmIcon = new ImageIcon("src/Images/MenuIcons/confirmIcon.png");
        Image confirmImage = confirmIcon.getImage();
        Image newConfirmImage = confirmImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        confirmIcon = new ImageIcon(newConfirmImage);
        confirmBtn = new JButton(confirmIcon);
        confirmBtn.addActionListener(this);

        // create bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(100, 60));

        bottomPanel.add(goBackBtn, BorderLayout.WEST);
        bottomPanel.add(confirmBtn,BorderLayout.EAST);

        // adding components to frame
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(comboBoxPanel, BorderLayout.CENTER);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmBtn){
            User user = findUserByUsername(accountsComboBox.getSelectedItem().toString(), users);
            MenuGUI menuGUI = new MenuGUI(user);
            frame.setVisible(false);
            frame.dispose();
        }
        else if(e.getSource() == goBackBtn){
            StartAppView saw = new StartAppView();
            frame.setVisible(false);
            frame.dispose();
        }
    }
}
