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

    private JPanel infoPanel;
    private JPanel avatarPanel;
    private JLabel avatarLabel;
    private JPanel namePanel;
    private JLabel nameLabel;
    private JPanel cardsContainerPanel;

    private JPanel cardsPanel;

    private Player enemy;

    public OneEnemyGUI(String cardBackPath, Player enemy, String enemyAvatarPath, int playersNumber) {
        this.enemy = enemy;

        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setSize(150, 280);

        avatarPanel = new JPanel(new GridBagLayout());
        avatarPanel.setPreferredSize(new Dimension(150, 140));


        namePanel = new JPanel(new BorderLayout());
        namePanel.setPreferredSize(new Dimension(150, 140));
        if(playersNumber == 2){
            avatarPanel.setPreferredSize(new Dimension(150, 200));
            namePanel.setPreferredSize(new Dimension(150, 80));

        }

        nameLabel = new JLabel(enemy.getName());
        nameLabel.setFont(new Font("Sans Serif", Font.BOLD, 12));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.TOP);
        namePanel.add(nameLabel);

        ImageIcon enemyAvatarIcon = new ImageIcon(enemyAvatarPath);
        Image enemyAvatarImage = enemyAvatarIcon.getImage();
        Image newEnemyAvatarImage = enemyAvatarImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        enemyAvatarIcon = new ImageIcon(newEnemyAvatarImage);
        avatarLabel = new JLabel();
        avatarLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        avatarLabel.setIcon(enemyAvatarIcon);

        avatarPanel.add(avatarLabel);

        infoPanel.add(avatarPanel, BorderLayout.CENTER);
        infoPanel.add(namePanel, BorderLayout.SOUTH);

        cardsContainerPanel = new JPanel(new BorderLayout());
        cardsContainerPanel.setSize(700, 280);

        cardsPanel = new JPanel(new FlowLayout());
        cardsPanel.setPreferredSize(new Dimension(700, 140));

        ArrayList<Card> enemyHand = enemy.getHand();

        for (Card card : enemyHand) {
            ImageIcon cardIcon = new ImageIcon(cardBackPath);
            Image cardImage = cardIcon.getImage();
            Image newCardImage = cardImage.getScaledInstance(75, 120, Image.SCALE_SMOOTH);
            cardIcon = new ImageIcon(newCardImage);
            JLabel cardLabel = new JLabel();
            cardLabel.setIcon(cardIcon);
            cardsPanel.add(cardLabel);
        }

        cardsContainerPanel.add(cardsPanel);
    }

    public JPanel getInfoPanel(){
        return infoPanel;
    }

    public JPanel getAvatarPanel(){
        return avatarPanel;
    }

    public JPanel getNamePanel(){
        return namePanel;
    }

    public JPanel getCardsContainerPanel(){
        return cardsContainerPanel;
    }

    public void setAvatarLabel(boolean enabled){
        this.avatarLabel.setEnabled(enabled);
    }

    public Player getEnemy(){
        return enemy;
    }


}
