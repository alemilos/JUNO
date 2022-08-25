package juno.startApp.startAppView;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import juno.menu.menuModel.User;
import juno.menu.menuView.ProfileGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StartAppView {

    public StartAppView(){
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // title Label
        JLabel title = new JLabel();
        title.setText("JUNO");
        title.setForeground(new Color(203, 90, 90));
        title.setFont(new Font("Sans Serif", Font.BOLD, 80));
        title.setVerticalAlignment(JLabel.CENTER);

        // NewGame Button
        JButton newGameBtn = new JButton();
        newGameBtn.setText("Nuova Partita");
        newGameBtn.setForeground(Color.WHITE);
        newGameBtn.setOpaque(true);
        newGameBtn.setBorderPainted(false);
        newGameBtn.setBackground(new Color(221, 121, 115));
        newGameBtn.setFont(new Font("Sans Serif", Font.BOLD, 70));
        newGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameBtn.setPreferredSize(new Dimension(100,50));

        newGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetData setData = new SetData();
                frame.setVisible(false);
                frame.dispose();
            }
        });


        User[] users = readFile("src/profili.json");

        // ContinueGame Button
        JButton continueBtn = new JButton();
        continueBtn.setText("   Continua   ");
        continueBtn.setForeground(Color.WHITE);
        continueBtn.setOpaque(true);
        continueBtn.setBorderPainted(false);
        continueBtn.setBackground(new Color(221, 121, 115));
        continueBtn.setFont(new Font("Sans Serif", Font.BOLD, 70));
        continueBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueBtn.setPreferredSize(new Dimension(100,50));
        continueBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**TODO:
                 * This should open a list of users.
                 * When clicked and confirmed, we can
                 * use it to play the game
                 * ***/
                ChooseAccount ca = new ChooseAccount(users);
                frame.setVisible(false);
                frame.dispose();

            }
        });

        // IF JSON USERS IS EMPTY

        if(users.length < 1) {
            continueBtn.setEnabled(false);
        }

        // exit Button
        ImageIcon exitIcon = new ImageIcon("src/menuIcons/exitIcon.png");
        Image exitImage = exitIcon.getImage();
        Image newExitImage = exitImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        exitIcon = new ImageIcon(newExitImage);
        JButton exitBtn = new JButton(exitIcon);
        //ExitButton Listener
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // SubPanelTop
        JPanel topSubPanel = new JPanel();
        topSubPanel.setPreferredSize(new Dimension(100,160));
        topSubPanel.add(title);
        // SubPanelCentral
        JPanel centralSubPanel = new JPanel();
        centralSubPanel.setLayout(new BoxLayout(centralSubPanel, BoxLayout.Y_AXIS));
        centralSubPanel.setPreferredSize(new Dimension(100,100));
        centralSubPanel.add(newGameBtn);
        centralSubPanel.add(Box.createVerticalStrut(20));
        centralSubPanel.add(continueBtn);

        // SubPanelBottom
        JPanel bottomSubPanel = new JPanel();
        bottomSubPanel.setLayout(new BorderLayout());
        bottomSubPanel.setPreferredSize(new Dimension(100,60));
        bottomSubPanel.add(exitBtn, BorderLayout.EAST);

        mainPanel.add(topSubPanel, BorderLayout.NORTH);
        mainPanel.add(centralSubPanel, BorderLayout.CENTER);
        mainPanel.add(bottomSubPanel, BorderLayout.SOUTH);

        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        StartAppView saw = new StartAppView();
        //readFile("src/profili.json");
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


}
