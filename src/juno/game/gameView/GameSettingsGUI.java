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

public class GameSettingsGUI extends JFrame implements ActionListener{

    private User user;

    private int playersNumber = 2;

    private Difficulty difficulty;

    private String cardBackPath = "src/Images/CardsBack/uno_version_1.png";

    private JLabel cardBackImageLabel;

    private JFrame frame;

    private JComboBox choosePlayerNumberComboBox;

    private JButton changeCardBtn;

    private JButton goBackBtn;

    private JButton startBtn;

    private JButton easyBtn;

    private JButton hardBtn;

    private JButton randomBtn;

    private boolean openedConfirmFrame;

    public GameSettingsGUI(User user){
        this.user = user;

        frame = new JFrame();
        frame.setLayout(new GridLayout(2, 3));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        /***PANELS***/
        // up-left
        JPanel upLeftPanel = new JPanel(new BorderLayout());

        JPanel upLeftTOPPanel = new JPanel();
        upLeftTOPPanel.setLayout(new BoxLayout(upLeftTOPPanel, BoxLayout.Y_AXIS));

        JLabel stylesLabel = new JLabel("Stile");
        stylesLabel = addStylesToLabel(stylesLabel);
        stylesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel stylesPanel = new JPanel();
        stylesPanel.setLayout(new BoxLayout(stylesPanel, BoxLayout.Y_AXIS));
        changeCardBtn = new JButton(" Retro Carte ");
        changeCardBtn = addStyleToButton(changeCardBtn);
        changeCardBtn.addActionListener(this);

        stylesPanel.add(Box.createVerticalStrut(40));
        stylesPanel.add(changeCardBtn);
        stylesPanel.add(Box.createVerticalStrut(20));

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
        choosePlayerNumberComboBox = new JComboBox(numbers);
        ((JLabel)choosePlayerNumberComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER); // this centers the input inside the combobox

        randomBtn = new JButton("Casuale");
        randomBtn = addStyleToButton(randomBtn);
        randomBtn.setFont(new Font("Sans Serif", Font.BOLD, 20));
        randomBtn.addActionListener(this);

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
        easyBtn.addActionListener(this);

        hardBtn = new JButton("Difficile");
        hardBtn = addStyleToButton(hardBtn);
        hardBtn.addActionListener(this);

        difficultyPanel.add(Box.createVerticalStrut(40));
        difficultyPanel.add(easyBtn);
        difficultyPanel.add(Box.createVerticalStrut(20));
        difficultyPanel.add(hardBtn);

        upRightPanel.add(upRightTOPPanel, BorderLayout.NORTH);
        upRightPanel.add(difficultyPanel, BorderLayout.CENTER);

        // down-left
        ImageIcon goBackIcon = new ImageIcon("src/Images/MenuIcons/gobackIcon.jpeg");
        Image goBackImage = goBackIcon.getImage();
        Image newGoBackImage = goBackImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        goBackIcon = new ImageIcon(newGoBackImage);
        goBackBtn = new JButton(goBackIcon);

        JPanel downLeftPanel = new JPanel(new BorderLayout());
        goBackBtn.setVerticalAlignment(JButton.BOTTOM);
        goBackBtn.setHorizontalAlignment(JButton.LEFT);
        goBackBtn.setSize(50,50);
        goBackBtn.addActionListener(this);

        JPanel downLeftInnerBottomPanel = new JPanel(new BorderLayout());
        downLeftInnerBottomPanel.setSize(100, 60);

        downLeftInnerBottomPanel.add(goBackBtn, BorderLayout.WEST);
        downLeftPanel.add(downLeftInnerBottomPanel, BorderLayout.SOUTH);


        // down-center
        JPanel downCenterPanel = new JPanel(new BorderLayout());

        ImageIcon defaultCardIcon = new ImageIcon(cardBackPath);
        Image cardImage = defaultCardIcon.getImage();
        Image newCardImage = cardImage.getScaledInstance(200,400, Image.SCALE_SMOOTH);
        defaultCardIcon = new ImageIcon(newCardImage);
        cardBackImageLabel = new JLabel(defaultCardIcon);

        startBtn = new JButton("Inizia");
        startBtn = addStyleToButton(startBtn);
        startBtn.addActionListener(this);

        JPanel cardTitlePanel = new JPanel(new GridBagLayout());
        JLabel choosedStyleLabel = new JLabel("Stile Scelto");
        choosedStyleLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
        choosedStyleLabel.setForeground(new Color(221, 121, 115));
        cardTitlePanel.add(choosedStyleLabel);

        downCenterPanel.add(cardTitlePanel, BorderLayout.NORTH);
        downCenterPanel.add(cardBackImageLabel, BorderLayout.CENTER);
        downCenterPanel.add(startBtn, BorderLayout.SOUTH);

        // down-right

        ImageIcon settingsIcon = new ImageIcon("src/Images/MenuIcons/settingsIcon.png");
        Image settingsImage = settingsIcon.getImage();
        Image newSettingsImage = settingsImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        settingsIcon = new ImageIcon(newSettingsImage);
        JButton settingsBtn = new JButton(settingsIcon);

        JPanel downRightInnerBottomPanel = new JPanel(new BorderLayout());
        downRightInnerBottomPanel.setSize(100, 60);

        downRightInnerBottomPanel.add(settingsBtn, BorderLayout.EAST);
        JPanel downRightPanel = new JPanel(new BorderLayout());
        downRightPanel.add(downRightInnerBottomPanel, BorderLayout.SOUTH);

        // adding to main frame

        frame.add(upLeftPanel);
        frame.add(upCenterPanel);
        frame.add(upRightPanel);
        frame.add(downLeftPanel);
        frame.add(downCenterPanel);
        frame.add(downRightPanel);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == randomBtn) {
            Random rand = new Random();
            int randIndex = rand.nextInt(9);
            choosePlayerNumberComboBox.setSelectedIndex(randIndex);
            playersNumber = randIndex + 2;
            randomBtn.setFocusPainted(true);
        } else if (source == changeCardBtn) {
            ChooseBackCard chooseBackCard = new ChooseBackCard();
            chooseBackCard.addSubscriber(this);
        } else if (source == easyBtn) {
            easyBtn.setBackground(new Color(119, 198, 110));
            difficulty = Difficulty.EASY;
            hardBtn.setBackground(new Color(221, 121, 115));
        } else if (source == hardBtn) {
            hardBtn.setBackground(new Color(119, 198, 110));
            difficulty = Difficulty.HARD;
            easyBtn.setBackground(new Color(221, 121, 115));
        } else if (source == goBackBtn) {
            MenuGUI menu = new MenuGUI(user);
            frame.dispose();
        } else if (source == startBtn) {
            playersNumber = choosePlayerNumberComboBox.getSelectedIndex() + 2; // +2 perché la combobox è indicizzata da 0 e io ho valori [2,10]
            boolean isDifficulty = difficulty != null ? true : false;
            if (!openedConfirmFrame) {
                if(isDifficulty){
                    setOpenedConfirmFrame(true);
                    ConfirmFrame confirmFrame = new ConfirmFrame();
                    confirmFrame.addGameSettingsGUI(this);
                }
                else{
                    System.out.println("add a difficulty");
                }
            }
        }
    }

    public void setOpenedConfirmFrame(boolean isOpened){
        this.openedConfirmFrame = isOpened;
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

    public static String setCardBackPath(String cardBackPath){
        return cardBackPath;
    }

    public void update(String cardBackPath){
        ImageIcon icon = new ImageIcon(cardBackPath);
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(200,400, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        this.cardBackPath = cardBackPath;
        this.cardBackImageLabel.setIcon(icon);
    }

    public User getUser(){
        return user;
    }

    public int getPlayersNumber(){
        return playersNumber;
    }

    public Difficulty getDifficulty(){
        return difficulty;
    }

    public String getCardBackPath(){
        return cardBackPath;
    }

    public void closeFrame(){
        this.frame.dispose();
    }

}
