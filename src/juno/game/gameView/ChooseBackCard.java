package juno.game.gameView;

import juno.startApp.startAppView.SetData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ChooseBackCard extends JFrame implements ActionListener{

    private JFrame frame;

    private JPanel cardsPanel;
    private JPanel bottomPanel;

    private ImageIcon selectedCardBack;
    private String cardBackPath;

    private JButton cardButton;

    private JButton goBackBtn;
    private JButton confirmBtn;


    public ChooseBackCard(){
        frame = new JFrame();
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);

        cardsPanel = new JPanel(new FlowLayout());
        bottomPanel = new JPanel(new BorderLayout());

        JScrollPane avatarsScrollPane = new JScrollPane(cardsPanel,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        File dir = new File("src/Images/CardsBack");
        int numberOfCardsBack = 0;
        for (File file: dir.listFiles()){
            if(!file.isDirectory() && !file.getPath().equals("src/Images/Avatars/.DS_Store")){
                numberOfCardsBack +=1;
                // Create Images
                ImageIcon icon = new ImageIcon(file.getPath());
                Image image = icon.getImage();
                Image newImage = image.getScaledInstance(75,120, Image.SCALE_SMOOTH);
                icon = new ImageIcon(newImage);
                cardButton = new JButton(icon);
                cardButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedCardBack = (ImageIcon) cardButton.getIcon();
                        cardBackPath = file.getPath();
                    }
                });

                cardsPanel.add(cardButton);
            }
        }

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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == goBackBtn){
            frame.dispose();
        }
        else if(e.getSource() == confirmBtn){
            if(cardBackPath != null){
                GameSettingsGUI.setCardBackPath(cardBackPath);
                frame.dispose();
            }
        }
    }
}
