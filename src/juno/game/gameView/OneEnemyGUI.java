package juno.game.gameView;

import juno.game.gameModel.Card;
import juno.game.gameModel.Player;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

public class OneEnemyGUI extends JPanel {

    /**
     * WIDTH: 850
     * HEIGHT: 280
     * **/

    private JPanel enemyInfoPanel;
    private JPanel enemyAvatarPanel;
    private JLabel enemyAvatarLabel;
    private JPanel enemyNamePanel;
    private JLabel enemyNameLabel;
    private JPanel enemyCardsContainerPanel;

    private JPanel enemyCardsPanel;


    public OneEnemyGUI(String cardBackPath, Player enemy, String enemyAvatarPath, int playersNumber) {
        enemyInfoPanel = new JPanel(new BorderLayout());
        enemyInfoPanel.setSize(150, 280);

        enemyAvatarPanel = new JPanel(new GridBagLayout());
        enemyAvatarPanel.setPreferredSize(new Dimension(150, 140));


        enemyNamePanel = new JPanel(new BorderLayout());
        enemyNamePanel.setPreferredSize(new Dimension(150, 140));
        if(playersNumber == 2){
            enemyAvatarPanel.setPreferredSize(new Dimension(150, 200));
            enemyNamePanel.setPreferredSize(new Dimension(150, 80));

        }

        enemyNameLabel = new JLabel(enemy.getName());
        enemyNameLabel.setFont(new Font("Sans Serif", Font.BOLD, 12));
        enemyNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        enemyNameLabel.setVerticalAlignment(SwingConstants.TOP);
        enemyNamePanel.add(enemyNameLabel);

        ImageIcon enemyAvatarIcon = new ImageIcon(enemyAvatarPath);
        Image enemyAvatarImage = enemyAvatarIcon.getImage();
        Image newEnemyAvatarImage = enemyAvatarImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        enemyAvatarIcon = new ImageIcon(newEnemyAvatarImage);
        enemyAvatarLabel = new JLabel();
        enemyAvatarLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        enemyAvatarLabel.setIcon(enemyAvatarIcon);

        enemyAvatarPanel.add(enemyAvatarLabel);

        enemyInfoPanel.add(enemyAvatarPanel, BorderLayout.CENTER);
        enemyInfoPanel.add(enemyNamePanel, BorderLayout.SOUTH);

        enemyCardsContainerPanel = new JPanel(new BorderLayout());
        enemyCardsContainerPanel.setSize(700, 280);

        enemyCardsPanel = new JPanel(new FlowLayout());
        enemyCardsPanel.setPreferredSize(new Dimension(700, 140));

        ArrayList<Card> enemyHand = enemy.getHand();

        /**
         * TODO create a blank panel to align things
        **/
        for (Card card : enemyHand) {
            ImageIcon cardIcon = new ImageIcon(cardBackPath);
            Image cardImage = cardIcon.getImage();
            Image newCardImage = cardImage.getScaledInstance(75, 120, Image.SCALE_SMOOTH);
            cardIcon = new ImageIcon(newCardImage);
            JLabel cardLabel = new JLabel();
            cardLabel.setIcon(cardIcon);
            enemyCardsPanel.add(cardLabel);
        }

        enemyCardsContainerPanel.add(enemyCardsPanel);
    }

    public JPanel getEnemyInfoPanel(){
        return enemyInfoPanel;
    }

    public JPanel getEnemyAvatarPanel(){
        return enemyAvatarPanel;
    }

    public JPanel getEnemyNamePanel(){
        return enemyNamePanel;
    }

    public JPanel getEnemyCardsContainerPanel(){
        return enemyCardsContainerPanel;
    }




    public void offEnabledLabel(){
        enemyAvatarLabel.setEnabled(false);
    }

    public void onEnabledLabel(){
        enemyAvatarLabel.setEnabled(true);
    }

}
