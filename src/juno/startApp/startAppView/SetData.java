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
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SetData extends JFrame implements ActionListener{

    /** OBSERVER **/

    private JFrame frame;

    private String avatarPath;

    private JButton confirmBtn;

    private JButton goBackBtn;

    private JButton chooseFile;

    private JPanel titlePanel;

    private JPanel centralPanel;

    private JPanel inputPanel;

    private JPanel avatarPanel;

    private JLabel avatarImageLabel;

    private JPanel bottomPanel;

    private TextField usernameTextField;

    private TextField ageTextField;

    public SetData(){
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true); // this makes a fake full screen
        frame.setResizable(false);

        JLabel title = new JLabel("Inserisci i tuoi dati");
        title.setForeground(new Color(203, 90, 90));
        title.setFont(new Font("Sans Serif", Font.BOLD, 70));
        title.setVerticalAlignment(JLabel.CENTER);

        titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setPreferredSize(new Dimension(100, 200));
        titlePanel.add(title);

        centralPanel = new JPanel();
        centralPanel.setPreferredSize(new Dimension(400,400));
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));

        avatarPanel = new JPanel(new GridBagLayout());

        ImageIcon avatarIcon = new ImageIcon("src/Images/MenuIcons/default_avatar.png");
        Image image = avatarIcon.getImage();
        Image newImage = image.getScaledInstance(150,150,Image.SCALE_SMOOTH);
        avatarIcon = new ImageIcon(newImage);
        avatarImageLabel = new JLabel(avatarIcon);
        avatarImageLabel.setIcon(avatarIcon);

        avatarPanel.add(avatarImageLabel);


        inputPanel = new JPanel(new SpringLayout());

        JLabel usernameLabel = new JLabel("Username: ", JLabel.TRAILING);
        usernameLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        usernameTextField = new TextField(10);
        usernameTextField.setMaximumSize(new Dimension(250, 40));
        usernameLabel.setLabelFor(usernameTextField);

        JLabel ageLabel = new JLabel("Età: ", JLabel.TRAILING);
        ageLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        ageTextField = new TextField(10);
        ageTextField.setMaximumSize(new Dimension(250, 40));
        ageLabel.setLabelFor(ageTextField);

        JLabel avatarLabel = new JLabel("Avatar: ", JLabel.TRAILING);
        avatarLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        chooseFile = new JButton("Scegli Avatar");
        chooseFile.setMaximumSize(new Dimension(250, 40));
        chooseFile.addActionListener(this);
        avatarLabel.setLabelFor(chooseFile);

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameTextField);
        inputPanel.add(ageLabel);
        inputPanel.add(ageTextField);
        inputPanel.add(avatarLabel);
        inputPanel.add(chooseFile);


        SpringUtilities.makeCompactGrid(inputPanel,
                3, 2,       //rows, cols
                6, 6,           //initX, initY
                6, 6);           //xPad, yPad

        centralPanel.add(inputPanel);
        centralPanel.add(avatarPanel);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        ImageIcon goBackIcon = new ImageIcon("src/Images/MenuIcons/gobackIcon.png");
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


        bottomPanel.add(goBackBtn, BorderLayout.WEST);
        bottomPanel.add(confirmBtn, BorderLayout.EAST);

        /*********TO MAIN FRAME*******/
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(centralPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SetData sd = new SetData();
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

    public void addUserToJson(User user){
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
    }

    public void setErrorPanel(String text){
        JPanel errorNotificationPanel = new JPanel(new GridBagLayout());
        JLabel alreadyExistLabel = new JLabel(text);
        alreadyExistLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        errorNotificationPanel.add(alreadyExistLabel);

        bottomPanel.add(errorNotificationPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirmBtn){
            boolean isUsername = usernameTextField.getText().length() > 0;
            boolean isAge = ageTextField.getText().length() > 0;
            boolean isAvatar = avatarImageLabel != null;

            if(isUsername && isAge && isAvatar) {
                /**Add control: is Integer for the age**/
                User user = new User(usernameTextField.getText(), Integer.parseInt(ageTextField.getText()), this.avatarPath);

                boolean newUser = isNewUser(user);
                if (newUser) {
                    addUserToJson(user);
                    MenuGUI menuGUI = new MenuGUI(user);
                    frame.dispose();
                }else{
                    setErrorPanel("L'username \"" + usernameTextField.getText() +"\" è stato già utilizzato" );

                    frame.setVisible(true);
                }
            }else if(!isUsername) {
                setErrorPanel("Inserire un username" );

                frame.setVisible(true);
            }
            else if(!isAge){
                setErrorPanel("Inserire l'età" );

                frame.setVisible(true);
            }
            else if(!isAvatar){
                setErrorPanel("Selezionare un avatar" );

                frame.setVisible(true);
            }
        }
        else if(e.getSource() == goBackBtn){
            StartAppView saw = new StartAppView();
            frame.dispose();
        }
        else if(e.getSource() == chooseFile){
            ShowAvatars sa = new ShowAvatars();
            sa.addSubscriber(this);
        }
    }

    public void update(String avatarPath){
        ImageIcon icon = new ImageIcon(avatarPath);
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(150,150, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        this.avatarPath = avatarPath;
        this.avatarImageLabel.setIcon(icon);
        }

}
