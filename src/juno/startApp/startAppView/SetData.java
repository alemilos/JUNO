package juno.startApp.startAppView;

import juno.menu.menuModel.User;
import juno.menu.menuView.MenuGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class SetData {
    public SetData(){
        JFrame frame = new JFrame();
        frame.setSize(600,500);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        // frame BORDER PANEL
            // top panel
            // sides panel left and right to make space
            // central panel
                // username BORDER PANEL
                    // title panel
                    // textfield, submit FLOWPANEL
            //bottom panel


        // Title Label
        JLabel title = new JLabel("<html> Inserisci <br> i tuoi dati </html>");
        title.setForeground(new Color(203, 90, 90));
        title.setFont(new Font("Sans Serif", Font.BOLD, 70));
        title.setVerticalAlignment(JLabel.CENTER);
        // top panel
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(100, 200));
        topPanel.add(title);

        /***********USERNAME PANEL****************/
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new FlowLayout());

        JLabel usernameText = new JLabel("Username:");

        JTextField usernameTextField = new JTextField();
        usernameTextField.setPreferredSize(new Dimension(250, 40));

        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));

        usernamePanel.add(usernameText);
        usernamePanel.add(usernameTextField);

        /***********AGE PANEL****************/
        JPanel agePanel = new JPanel();
        agePanel.setLayout(new FlowLayout());

        JLabel ageText = new JLabel("Età:");

        JTextField ageTextField = new JTextField();
        ageTextField.setPreferredSize(new Dimension(50, 40));

        agePanel.add(ageText);
        agePanel.add(ageTextField);

        /***********AVATAR PANEL****************/

        // IMPLEMENT THIS



        /********ADDING TO CENTRAL PANEL*********/
        centralPanel.add(usernamePanel);
        centralPanel.add(agePanel);


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
                /**TODO
                 ** Put data in JSON FILE
                 *
                 ** TODO
                 * Allow this iFF both textfields are not EMPTY
                 **/

                // the argument passed to new MENUGUI is for testing purpose
                MenuGUI menuGUI = new MenuGUI(new User());
                frame.setVisible(false);
                frame.dispose();

            }
        });

        bottomPanel.add(goBackBtn, BorderLayout.WEST);
        bottomPanel.add(confirmBtn, BorderLayout.EAST);




        // Sides void panels
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(100,100));
        JPanel rightPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(100,100));


        /*********ADDING TO MAIN FRAME*******/
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(centralPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.WEST);
        frame.add(bottomPanel, BorderLayout.SOUTH);





        // submit Button
        /*
        this.submitUserNameBtn = new JButton("Conferma");
        submitUserNameBtn.addActionListener(this::actionPerformed);

        // Name text input
        this.userNameInput = new JTextField("Name");
        userNameInput.setPreferredSize(new Dimension(250,40));

        frame.add(userNameInput);
        frame.add(submitUserNameBtn);
    */
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



}
