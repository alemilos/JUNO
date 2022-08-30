package juno.startApp.startAppView;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import juno.menu.menuModel.User;
import juno.menu.menuView.ProfileGUI;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StartAppView extends JFrame implements ActionListener{

    private JFrame frame;

    private JPanel titlePanel;

    private JPanel centralPanel;

    private JPanel bottomPanel;

    private JButton newGameBtn;

    private JButton exitBtn;

    private JButton continueBtn;

    private User[] users;

    public StartAppView(){
        frame = new JFrame("JUNO");
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true); // this makes a fake full screen
        frame.setResizable(false);

        JLabel title = new JLabel("JUNO");
        title.setForeground(new Color(203, 90, 90));
        title.setFont(new Font("Sans Serif", Font.BOLD, 140));
        title.setVerticalAlignment(JLabel.CENTER);

        newGameBtn = new JButton("Nuova Partita");
        newGameBtn.setForeground(Color.WHITE);
        newGameBtn.setOpaque(true);
        newGameBtn.setBackground(new Color(221, 121, 115));
        newGameBtn.setFont(new Font("Sans Serif", Font.BOLD, 70));
        newGameBtn.setPreferredSize(new Dimension(600,100));
        newGameBtn.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        newGameBtn.addActionListener(this);

        users = readFile("src/profili.json");

        continueBtn = new JButton("Continua");
        continueBtn.setForeground(Color.WHITE);
        continueBtn.setOpaque(true);
        continueBtn.setBackground(new Color(221, 121, 115));
        continueBtn.setFont(new Font("Sans Serif", Font.BOLD, 70));
        continueBtn.setPreferredSize(new Dimension(600,100));
        continueBtn.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        continueBtn.addActionListener(this);

        if(users.length < 1) {
            continueBtn.setEnabled(false);
        }

        ImageIcon exitIcon = new ImageIcon("src/Images/MenuIcons/leave_door.png");
        Image exitImage = exitIcon.getImage();
        Image newExitImage = exitImage.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        exitIcon = new ImageIcon(newExitImage);
        exitBtn = new JButton(exitIcon);
        exitBtn.addActionListener(this);
        exitBtn.setFocusPainted(false);

        titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(100,170));
        titlePanel.add(title);

        JPanel button1Panel = new JPanel(new GridBagLayout());
        button1Panel.setPreferredSize(new Dimension(200,100));
        button1Panel.add(newGameBtn);

        JPanel button2Panel = new JPanel();
        button2Panel.setPreferredSize(new Dimension(200,500));
        button2Panel.setAlignmentY(Component.TOP_ALIGNMENT);
        button2Panel.add(continueBtn);

        centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        centralPanel.setPreferredSize(new Dimension(100,100));
        centralPanel.add(button1Panel);
        centralPanel.add(button2Panel);

        bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setPreferredSize(new Dimension(100,150));
        bottomPanel.add(exitBtn);

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(centralPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        StartAppView saw = new StartAppView();
    }

    public static User[] readFile(String fileAddress){
        Gson gson = new Gson();

        try{
            User[] users = gson.fromJson(new FileReader(fileAddress), User[].class);
            return users;
        }catch (JsonSyntaxException e) {
            e.printStackTrace();
        }catch(JsonIOException e){
            e.printStackTrace();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameBtn){
            SetData setData = new SetData();
            frame.setVisible(false);
            frame.dispose();
        }
        else if(e.getSource() == exitBtn){
            frame.dispose();
        }
        else if(e.getSource() == continueBtn){
            ChooseAccount ca = new ChooseAccount(users);
            frame.setVisible(false);
            frame.dispose();
        }
    }
}
