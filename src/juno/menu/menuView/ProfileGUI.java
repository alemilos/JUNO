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

public class ProfileGUI extends JFrame implements ActionListener{

    private boolean openedConfirmFrame;

    private User user;

    private JFrame frame;

    private JPanel titlePanel;

    private JPanel centralPanel;

    private JButton goBackBtn;

    private JButton deleteProfile;

    private JButton changeProfileBtn;

    private Font ssPlain16 = new Font("Sans Serif",Font.PLAIN, 16);

    /**
     * OBSERVER TO CONFIRM FRAME
      **/

    public ProfileGUI(User user){
        this.user = user;

        frame = new JFrame("Profilo");
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setResizable(false);

        // Title label
        JLabel title = new JLabel("Profilo");
        title.setForeground(new Color(203, 90, 90));
        title.setFont(new Font("Sans Serif", Font.BOLD, 80));

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
        username.setFont(ssPlain16);
        infoPanel.add(username);
        JLabel actualUsername = new JLabel(user.getUsername());
        actualUsername.setFont(ssPlain16);

        username.setLabelFor(actualUsername);
        infoPanel.add(actualUsername);
        /***AGE LABEL***/
        JLabel age = new JLabel("Età: ", JLabel.TRAILING);
        age.setFont(ssPlain16);
        infoPanel.add(age);
        JLabel actualAge = new JLabel(""+user.getAge());
        actualAge.setFont(ssPlain16);

        age.setLabelFor(actualAge);
        infoPanel.add(actualAge);
        /***STATS LABEL***/
        String[] statsLabels = {"Partite Giocate: ","Partite Vinte: ","Partite Perse: "};


        for(int i = 0; i < statsLabels.length; i++){
            JLabel label = new JLabel(statsLabels[i], JLabel.TRAILING);
            label.setFont(ssPlain16);
            infoPanel.add(label);
            JLabel actualLabel = new JLabel();
            actualLabel.setFont(ssPlain16);
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
        ImageIcon goBackIcon = new ImageIcon("src/Images/MenuIcons/gobackIcon.png");
        Image goBackImage = goBackIcon.getImage();
        Image newGoBackImage = goBackImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        goBackIcon = new ImageIcon(newGoBackImage);
        goBackBtn = new JButton(goBackIcon);
        goBackBtn.setFocusPainted(false);
        goBackBtn.addActionListener(this);

        // deleteButton
        deleteProfile = new JButton("Cancella Profilo");
        deleteProfile.setForeground(Color.WHITE);
        deleteProfile.setOpaque(true);
        deleteProfile.setBorderPainted(false);
        deleteProfile.setBackground(new Color(221, 121, 115));
        deleteProfile.setFont(new Font("Sans Serif", Font.BOLD, 10));
        deleteProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteProfile.setSize(new Dimension(100,50));
        deleteProfile.setFocusPainted(false);
        deleteProfile.addActionListener(this);

        // logOutButton
        changeProfileBtn = new JButton("Cambia Profilo");
        changeProfileBtn.setForeground(Color.WHITE);
        changeProfileBtn.setOpaque(true);
        changeProfileBtn.setBorderPainted(false);
        changeProfileBtn.setBackground(new Color(221, 121, 115));
        changeProfileBtn.setFont(new Font("Sans Serif", Font.BOLD, 10));
        changeProfileBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        changeProfileBtn.setSize(new Dimension(100,50));
        changeProfileBtn.setFocusPainted(false);
        changeProfileBtn.addActionListener(this);

        // Panels
        titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setPreferredSize(new Dimension(100,160));
        titlePanel.add(title);


        // PANEL for delete and logout
        JPanel deleteAndLogOut = new JPanel(new FlowLayout());
        deleteAndLogOut.add(deleteProfile);
        deleteAndLogOut.add(changeProfileBtn);

        centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        centralPanel.setPreferredSize(new Dimension(100,100));
        centralPanel.add(avatarLabel);
        centralPanel.add(Box.createVerticalStrut(20));
        centralPanel.add(infoPanel);
        centralPanel.add(Box.createVerticalStrut(20));
        centralPanel.add(deleteAndLogOut);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(100,60));
        bottomPanel.add(goBackBtn, BorderLayout.WEST);

        // Adding panels to frame
        frame.add(titlePanel, BorderLayout.NORTH);

        frame.add(centralPanel, BorderLayout.CENTER);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void removeUserFromJSON() {
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

        StartAppView saw = new StartAppView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == goBackBtn){
            MenuGUI menuGUI = new MenuGUI(this.user);
            this.frame.dispose();
        }
        else if(e.getSource() == deleteProfile){
            if (!openedConfirmFrame){
                setOpenedConfirmFrame(true);
                ConfirmFrame confirmFrame = new ConfirmFrame();
                confirmFrame.addProfileGUI(this);
            }
        }
        else if(e.getSource() == changeProfileBtn){
            StartAppView saw = new StartAppView();
            this.frame.dispose();
        }
    }

    public void setOpenedConfirmFrame(boolean isOpened){
        this.openedConfirmFrame = isOpened;
    }

    public void closeFrame(){
        this.frame.dispose();
    }

}
