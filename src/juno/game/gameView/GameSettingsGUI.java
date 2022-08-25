package juno.game.gameView;

import juno.game.gameModel.Difficulty;
import juno.menu.menuModel.User;
import juno.menu.menuView.MenuGUI;
import juno.startApp.startAppView.ConfirmFrame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameSettingsGUI extends JFrame{

    private int playersNumber = 2;


    private JButton easyBtn;
    private JButton hardBtn;

    private JButton randomBtn;

    private Difficulty difficulty;

    public GameSettingsGUI(User user){

        /**
         * TO REMEMBER
         * up left panel inner bottom panel has BOX LAYOUT
         * up right panel inner bottom panel has BorderLayout
         *
         * To center a button in a container
         * make the container a gridbaglayout and
         * add(button, new gridbagConstraints)
         * **/

        // FRAME
        JFrame mainFrame = new JFrame();
        mainFrame.setLayout(new GridLayout(2, 3));
        mainFrame.setSize(1000, 800);

        /***PANELS***/
        // up-left
        JPanel upLeftPanel = new JPanel(new BorderLayout());

        JPanel upLeftTOPPanel = new JPanel();
        upLeftTOPPanel.setLayout(new BoxLayout(upLeftTOPPanel, BoxLayout.Y_AXIS));

        JLabel stylesLabel = new JLabel("Stili");
        stylesLabel = addStylesToLabel(stylesLabel);
        stylesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel stylesPanel = new JPanel();
        stylesPanel.setLayout(new BoxLayout(stylesPanel, BoxLayout.Y_AXIS));
        JButton changeCardBtn = new JButton(" Cambia Carte ");
        changeCardBtn = addStyleToButton(changeCardBtn);

        JButton changeTableBtn = new JButton("Cambia Tavolo");
        changeTableBtn = addStyleToButton(changeTableBtn);

        stylesPanel.add(Box.createVerticalStrut(40));
        stylesPanel.add(changeCardBtn);
        stylesPanel.add(Box.createVerticalStrut(20));
        stylesPanel.add(changeTableBtn);

        upLeftTOPPanel.add(Box.createVerticalStrut(40));
        upLeftTOPPanel.add(stylesLabel);

        upLeftPanel.add(upLeftTOPPanel,BorderLayout.NORTH);
        upLeftPanel.add(stylesPanel,BorderLayout.CENTER);

        // up-center
        JPanel upCenterPanel = new JPanel(new BorderLayout());

        JPanel upCenterTOPPanel = new JPanel();
        upCenterTOPPanel.setLayout(new BoxLayout(upCenterTOPPanel,BoxLayout.Y_AXIS));

        JLabel playersLabel = new JLabel("Giocatori");
        playersLabel = addStylesToLabel(playersLabel);
        playersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        upCenterTOPPanel.add(Box.createVerticalStrut(40));
        upCenterTOPPanel.add(playersLabel);

        JPanel playersPanel = new JPanel(new BorderLayout());

        String[] numbers = {"2", "3", "4", "5", "6", "7", "8", "9", "10"};
        JComboBox choosePlayerNumberComboBox = new JComboBox(numbers);
        ((JLabel)choosePlayerNumberComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER); // this centers the input inside the combobox

        randomBtn = new JButton("Casuale");
        randomBtn = addStyleToButton(randomBtn);
        randomBtn.setFont(new Font("Sans Serif", Font.BOLD, 20));

        JPanel randomBtnContainer = new JPanel();
        randomBtnContainer.add(randomBtn);

        playersPanel.add(choosePlayerNumberComboBox, BorderLayout.NORTH);
        playersPanel.add(randomBtnContainer, BorderLayout.CENTER);

        upCenterPanel.add(upCenterTOPPanel, BorderLayout.NORTH);
        upCenterPanel.add(playersPanel, BorderLayout.CENTER);


        // up-right
        JPanel upRightPanel = new JPanel(new BorderLayout());

        JPanel upRightTOPPanel = new JPanel();
        upRightTOPPanel.setLayout(new BoxLayout(upRightTOPPanel, BoxLayout.Y_AXIS));

        JLabel difficultyLabel = new JLabel("Difficoltà");
        difficultyLabel = addStylesToLabel(difficultyLabel);
        difficultyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        upRightTOPPanel.add(Box.createVerticalStrut(40));
        upRightTOPPanel.add(difficultyLabel);

        JPanel difficultyPanel = new JPanel();
        difficultyPanel.setLayout(new BoxLayout(difficultyPanel, BoxLayout.Y_AXIS));

        easyBtn = new JButton("  Facile  ");
        easyBtn = addStyleToButton(easyBtn);

        hardBtn = new JButton("Difficile");
        hardBtn = addStyleToButton(hardBtn);

        difficultyPanel.add(Box.createVerticalStrut(40));
        difficultyPanel.add(easyBtn);
        difficultyPanel.add(Box.createVerticalStrut(20));
        difficultyPanel.add(hardBtn);

        upRightPanel.add(upRightTOPPanel, BorderLayout.NORTH);
        upRightPanel.add(difficultyPanel, BorderLayout.CENTER);

        // down-left
        ImageIcon goBackIcon = new ImageIcon("src/menuIcons/gobackIcon.png");
        Image goBackImage = goBackIcon.getImage();
        Image newGoBackImage = goBackImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        goBackIcon = new ImageIcon(newGoBackImage);
        JButton goBackBtn = new JButton(goBackIcon);

        JPanel downLeftPanel = new JPanel(new BorderLayout());
        goBackBtn.setVerticalAlignment(JButton.BOTTOM);
        goBackBtn.setHorizontalAlignment(JButton.LEFT);
        goBackBtn.setSize(50,50);

        JPanel downLeftInnerBottomPanel = new JPanel(new BorderLayout());
        downLeftInnerBottomPanel.setSize(100, 60);

        downLeftInnerBottomPanel.add(goBackBtn, BorderLayout.WEST);
        downLeftPanel.add(downLeftInnerBottomPanel, BorderLayout.SOUTH);


        // down-center
        JPanel downCenterPanel = new JPanel(new BorderLayout());

        JLabel hereGoesTheImage = new JLabel("image goes here");
        hereGoesTheImage = addStylesToLabel(hereGoesTheImage);

        JButton startBtn = new JButton("Inizia");
        startBtn = addStyleToButton(startBtn);

        downCenterPanel.add(hereGoesTheImage, BorderLayout.NORTH);
        downCenterPanel.add(startBtn, BorderLayout.SOUTH);

        // down-right

        ImageIcon settingsIcon = new ImageIcon("src/menuIcons/settingsIcon.png");
        Image settingsImage = settingsIcon.getImage();
        Image newSettingsImage = settingsImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        settingsIcon = new ImageIcon(newSettingsImage);
        JButton settingsBtn = new JButton(settingsIcon);

        JPanel downRightInnerBottomPanel = new JPanel(new BorderLayout());
        downRightInnerBottomPanel.setSize(100, 60);

        downRightInnerBottomPanel.add(settingsBtn, BorderLayout.EAST);
        JPanel downRightPanel = new JPanel(new BorderLayout());
        downRightPanel.add(downRightInnerBottomPanel, BorderLayout.SOUTH);

        /**ACTION LISTENERS FOR BUTTONS**/
        // changeCardBtn listener
        changeCardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        // changeTableBtn listener
        changeTableBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // randomBtn listener
        randomBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                int randIndex = rand.nextInt(9);
                choosePlayerNumberComboBox.setSelectedIndex(randIndex);
                playersNumber = randIndex+2;
                randomBtn.setFocusPainted(true);
            }
        });
        // easyBtn & hardBtn
        easyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easyBtn.setBackground(new Color(119,198,110));
                difficulty = Difficulty.EASY;

                hardBtn.setBackground(new Color(221, 121, 115));

            }
        });

        hardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hardBtn.setBackground(new Color(119,198,110));
                difficulty = Difficulty.HARD;

                easyBtn.setBackground(new Color(221, 121, 115));
            }
        });


        // goBackBtn listener
        goBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuGUI menu = new MenuGUI(user);
                mainFrame.dispose();
            }
        });
        // settingsBtn listener
        settingsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        // startBtn listener
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playersNumber = choosePlayerNumberComboBox.getSelectedIndex() + 2;
                // +2 perché la combobox è indicizzata da 0 e io ho valori [2,10]
                boolean isDifficulty = difficulty != null ? true : false;
                if (isDifficulty) {
                    ConfirmFrame cf = new ConfirmFrame(mainFrame,
                                        "GameSettingsGUI",
                                                        user,
                                                        playersNumber,
                                                        difficulty);
                }
            }
        });



        // adding to main frame

        mainFrame.add(upLeftPanel);
        mainFrame.add(upCenterPanel);
        mainFrame.add(upRightPanel);
        mainFrame.add(downLeftPanel);
        mainFrame.add(downCenterPanel);
        mainFrame.add(downRightPanel);

        mainFrame.setVisible(true);

    }

    public JLabel addStylesToLabel(JLabel label){
        label.setForeground(new Color(203, 90, 90));
        label.setFont(new Font("Sans Serif", Font.BOLD, 20));
        label.setHorizontalAlignment(JLabel.CENTER);

        return label;
    }

    public JButton addStyleToButton(JButton button){
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setBackground(new Color(221, 121, 115));
        button.setFont(new Font("Sans Serif", Font.BOLD, 30));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setSize(new Dimension(100,50));
        button.setFocusPainted(false);
        return button;
    }
}
