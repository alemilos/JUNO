package juno.startApp.startAppView;

import com.google.gson.JsonElement;
import juno.menu.menuModel.User;
import juno.menu.menuView.MenuGUI;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.FileSystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class SetData {

    private static ImageIcon avatarChoosedIcon;
    private static JFrame frame;

    private static JPanel rightPanel;

    private static JLabel avatarImageLabel;

    private static String avatarPathStatic;
    public SetData(){
        this.frame = new JFrame();
        frame.setSize(600,500);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        // Title Label
        JLabel title = new JLabel("<html> Inserisci <br> i tuoi dati </html>");
        title.setForeground(new Color(203, 90, 90));
        title.setFont(new Font("Sans Serif", Font.BOLD, 70));
        title.setVerticalAlignment(JLabel.CENTER);
        // top panel
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(100, 200));
        topPanel.add(title);

        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));


        /******** REIMPLEMENT USERNAME AND AGE WITH SPRING LAYOUT *****/
        JPanel inputPanel = new JPanel(new SpringLayout());

        JLabel usernameLabel = new JLabel("Username: ", JLabel.TRAILING);
        inputPanel.add(usernameLabel);
        TextField usernameTextField = new TextField(10);
        usernameTextField.setMaximumSize(new Dimension(250, 40));
        usernameLabel.setLabelFor(usernameTextField);
        inputPanel.add(usernameTextField);

        JLabel ageLabel = new JLabel("Età: ", JLabel.TRAILING);
        inputPanel.add(ageLabel);
        TextField ageTextField = new TextField(10);
        ageTextField.setMaximumSize(new Dimension(250, 40));
        ageLabel.setLabelFor(ageTextField);
        inputPanel.add(ageTextField);

        JLabel avatarLabel = new JLabel("Avatar: ", JLabel.TRAILING);
        inputPanel.add(avatarLabel);
        JButton chooseFile = new JButton("Scegli Avatar");
        chooseFile.setMaximumSize(new Dimension(250, 40));
        avatarLabel.setLabelFor(chooseFile);
        inputPanel.add(chooseFile);

        chooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowAvatars sa = new ShowAvatars();
            }
        });

        SpringUtilities.makeCompactGrid(inputPanel,
                3, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        /********ADDING TO CENTRAL PANEL*********/
        centralPanel.add(inputPanel);



        // BOTTOM PANEL
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        ImageIcon goBackIcon = new ImageIcon("src/menuIcons/gobackIcon.png");
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

        ImageIcon confirmIcon = new ImageIcon("src/menuIcons/confirmIcon.png");
        Image confirmImage = confirmIcon.getImage();
        Image newConfirmImage = confirmImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        confirmIcon = new ImageIcon(newConfirmImage);
        JButton confirmBtn = new JButton(confirmIcon);

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean isUsername = usernameTextField.getText().length() > 0;
                boolean isAge = ageTextField.getText().length() > 0;
                boolean isAvatar = avatarImageLabel != null;

                User user = new User(usernameTextField.getText(),
                        Integer.parseInt(ageTextField.getText()),
                        avatarPathStatic
                );

                boolean newUser = isNewUser(user);


                if(isUsername && isAge && isAvatar && newUser) {
                    // Write to json
                    User[] oldUsers = StartAppView.readFile("src/profili.json");
                    // add user to json user's list
                    User[] newUsers = new User[oldUsers.length +1];
                    for(int i = 0; i < oldUsers.length; i++){
                        newUsers[i] = oldUsers[i];
                    }
                    newUsers[oldUsers.length] = user;
                    try(Writer writer = new FileWriter("src/profili.json")){
                        Gson gsonBuilder = new GsonBuilder().create();
                        gsonBuilder.toJson(newUsers, writer);
                    }catch(IOException ex){
                        ex.printStackTrace();
                    }

                    MenuGUI menuGUI = new MenuGUI(user);
                    frame.setVisible(false);
                    frame.dispose();
                }else{
                    System.out.println("already exist");
                }

            }
        });

        bottomPanel.add(goBackBtn, BorderLayout.WEST);
        bottomPanel.add(confirmBtn, BorderLayout.EAST);




        // Sides void panels
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(100,100));
        rightPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(100,100));


        /*********ADDING TO MAIN FRAME*******/
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(centralPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.WEST);
        frame.add(bottomPanel, BorderLayout.SOUTH);


        frame.setVisible(true);
    }

    /*
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==submitUserNameBtn){
            this.userName = userNameInput.getText();
            System.out.println(userName);
        }
    }
    */

    public static void main(String[] args) {
        SetData sd = new SetData();
    }

    public static void addAvatarImage(ImageIcon icon, String avatarPath){
        if (avatarChoosedIcon != null){
            rightPanel.remove(avatarImageLabel);
            frame.resize(601,601);
            frame.resize(600,600);
        }
        avatarChoosedIcon = icon;
        avatarImageLabel = new JLabel();
        avatarImageLabel.setIcon(avatarChoosedIcon);
        rightPanel.add(avatarImageLabel);
        avatarPathStatic = avatarPath;
        frame.resize(600,600);
    }

    public boolean isNewUser(User user){
        User[] oldUsers = StartAppView.readFile("src/profili.json");
        for (User oldUser : oldUsers){
            if(oldUser.getUsername().equals(user.getUsername())){
                return false;
            }
        }
        return true;
    }

}
