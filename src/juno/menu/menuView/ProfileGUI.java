package juno.menu.menuView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import juno.game.gameModel.Difficulty;
import juno.menu.menuModel.User;
import juno.startApp.startAppView.ConfirmFrame;
import juno.startApp.startAppView.SpringUtilities;
import juno.startApp.startAppView.StartAppView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ProfileGUI extends JFrame{

    public ConfirmFrame confirmOrUndoChildFrame;
    public ProfileGUI(User user){
        JFrame frame = new JFrame("Profilo");
        frame.setLayout(new BorderLayout());
        frame.setSize(600,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Title label
        JLabel title = new JLabel("Profilo");
        title.setForeground(new Color(203, 90, 90));
        title.setFont(new Font("Sans Serif", Font.BOLD, 80));
        title.setVerticalAlignment(JLabel.CENTER);
        frame.add(title);

        // Avatar IMAGE
        ImageIcon avatarIcon = new ImageIcon(user.getAvatar().getAvatarPath());
        Image avatarImage = avatarIcon.getImage();
        Image newAvatarImage = avatarImage.getScaledInstance(150,150, Image.SCALE_SMOOTH);
        avatarIcon = new ImageIcon(newAvatarImage);
        JLabel avatarLabel = new JLabel();
        avatarLabel.setIcon(avatarIcon);
        avatarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        // INFO Panel
        JPanel infoPanel = new JPanel(new SpringLayout());

        /***USERNAME LABEL***/
        JLabel username = new JLabel("Username: ", JLabel.TRAILING);
        infoPanel.add(username);
        JLabel actualUsername = new JLabel(user.getUsername());

        username.setLabelFor(actualUsername);
        infoPanel.add(actualUsername);
        /***AGE LABEL***/
        JLabel age = new JLabel("Età: ", JLabel.TRAILING);
        infoPanel.add(age);
        JLabel actualAge = new JLabel(""+user.getAge());

        age.setLabelFor(actualAge);
        infoPanel.add(actualAge);
        /***STATS LABEL***/
        String[] statsLabels = {"Partite Giocate: ","Partite Vinte: ","Partite Perse: "};


        for(int i = 0; i < statsLabels.length; i++){
            JLabel label = new JLabel(statsLabels[i], JLabel.TRAILING);
            infoPanel.add(label);
            JLabel actualLabel = new JLabel();
            switch (i){
                case 0:
                    actualLabel.setText(""+user.getStats().getPlayed());
                    break;
                case 1:
                    actualLabel.setText(""+user.getStats().getWin());
                    break;
                case 2:
                    actualLabel.setText(""+user.getStats().getLose());
                    break;
            }
            label.setLabelFor(actualLabel);
            infoPanel.add(actualLabel);
        }

        // SPRING
        SpringUtilities.makeCompactGrid(infoPanel,
                5, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        // GoBack Button
        ImageIcon goBackIcon = new ImageIcon("src/menuIcons/gobackIcon.png");
        Image goBackImage = goBackIcon.getImage();
        Image newGoBackImage = goBackImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        goBackIcon = new ImageIcon(newGoBackImage);
        JButton goBackBtn = new JButton(goBackIcon);
        goBackBtn.setFocusPainted(false);

        // goback action
        goBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuGUI menuGUI = new MenuGUI(user);
                frame.setVisible(false);
                frame.dispose();

            }
        });

        // deleteButton
        JButton deleteProfile = new JButton("Cancella Profilo");
        deleteProfile.setForeground(Color.WHITE);
        deleteProfile.setOpaque(true);
        deleteProfile.setBorderPainted(false);
        deleteProfile.setBackground(new Color(221, 121, 115));
        deleteProfile.setFont(new Font("Sans Serif", Font.BOLD, 10));
        deleteProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteProfile.setSize(new Dimension(100,50));
        deleteProfile.setFocusPainted(false);

        deleteProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (confirmOrUndoChildFrame == null) {
                    confirmOrUndoChildFrame = new ConfirmFrame(frame, "ProfileGUI", user, 666, Difficulty.EASY, "");
                }
            }
        });

        // logOutButton
        JButton logOut = new JButton("Cambia Profilo");
        logOut.setForeground(Color.WHITE);
        logOut.setOpaque(true);
        logOut.setBorderPainted(false);
        logOut.setBackground(new Color(221, 121, 115));
        logOut.setFont(new Font("Sans Serif", Font.BOLD, 10));
        logOut.setAlignmentX(Component.CENTER_ALIGNMENT);
        logOut.setSize(new Dimension(100,50));
        logOut.setFocusPainted(false);
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logOut.setFocusPainted(false);
                StartAppView saw = new StartAppView();
                frame.dispose();
            }
        });


        // Panels
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(100,120));
        topPanel.add(title);

        JScrollPane infoScrollPane = new JScrollPane(infoPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        infoScrollPane.setForeground(Color.red);

        // PANEL for delete and logout
        JPanel deleteAndLogOut = new JPanel(new FlowLayout());
        deleteAndLogOut.add(deleteProfile);
        deleteAndLogOut.add(logOut);

        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        centralPanel.setPreferredSize(new Dimension(100,100));
        centralPanel.add(avatarLabel);
        centralPanel.add(Box.createVerticalStrut(20));
        centralPanel.add(infoScrollPane);
        centralPanel.add(Box.createVerticalStrut(20));
        centralPanel.add(deleteAndLogOut);




        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(100,60));
        bottomPanel.add(goBackBtn, BorderLayout.WEST);

        // Adding panels to frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(new JPanel(), BorderLayout.WEST);
        frame.add(centralPanel, BorderLayout.CENTER);
        frame.add(new JPanel(), BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void removeUserFromJSON(User user) {
        /*
        * Creates a new array that removes the user if the json string of user equals
        * one of the old users in the json file.
        * This cannot fail because if we are in that state of the program
        * there needs to be an element equals to user.
        * */
        User[] oldUsers = StartAppView.readFile("src/profili.json");
        User[] newUsers = new User[oldUsers.length - 1];
        Gson gson = new Gson();
        int j = 0;
        for (int i = 0; i < oldUsers.length; i++) {
            if (!gson.toJson(user).equals(gson.toJson(oldUsers[i]))) {
                newUsers[j++] = oldUsers[i];
            }
        }
        try(Writer writer = new FileWriter("src/profili.json")){
            Gson gsonBuilder = new GsonBuilder().create();
            gsonBuilder.toJson(newUsers, writer);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
