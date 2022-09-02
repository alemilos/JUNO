package juno.game.gameView;

import juno.game.gameModel.Card;
import juno.game.gameModel.Player;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

public class LeftEnemyPanel extends JPanel {

    /**
     * WIDTH:  350
     * HEIGHT: 260
     * **/

    private JLayeredPane leftCardsLayeredPane;

    private JPanel panelForLayPane;
    private JPanel leftInfoPanel;
    private JPanel leftAvatarPanel;

    private JLabel leftAvatarLabel;
    private JPanel leftNamePanel;

    private int componentHeight = 260;

    private Player enemy;

    /**Todo delete*/
    public LeftEnemyPanel(){
        // in case we have 2 players...
        leftCardsLayeredPane = new JLayeredPane();
        leftCardsLayeredPane.setSize(200, 260);

        leftInfoPanel = new JPanel();
        leftInfoPanel.setSize(150, 260);
    }

    /**
     * The Enemies ArrayList will be like this [enemy1, enemy2]. It comes from the list [User, enemy1, enemy2]
     * this means: enemy1 is the player at User's right, enemy2 is at User's left.
     * The player at User's right goes in the top, while the enemy at User's left goes in the left
     * **/
    public LeftEnemyPanel(String cardBackPath, Player enemy, String enemyAvatarPath){
        this.enemy = enemy;
        /**
         * THIS will call the layred card constructor to build the layered cards
        **/

        /**
         * Left enemy container panel*
         **/

         leftCardsLayeredPane = new JLayeredPane();
         //leftCardsLayeredPane.setPreferredSize(new Dimension(200, 260));
         leftCardsLayeredPane.setBounds(0,0,200,260);

         int y = 0;

         for (int i = 0; i<enemy.getHand().size(); i++) {
             ImageIcon cardBackIcon = new ImageIcon(cardBackPath);
             Image cardBackImage = cardBackIcon.getImage();
             Image newCardBackImage = cardBackImage.getScaledInstance(65, 100, Image.SCALE_SMOOTH);
             cardBackIcon = new ImageIcon(newCardBackImage);
             RotatedIcon rotatedCardBackIcon = new RotatedIcon(cardBackIcon, RotatedIcon.Rotate.DOWN);
             JLabel cardLabel = new JLabel(rotatedCardBackIcon);
             cardLabel.setBounds(25,y,100,65);
             leftCardsLayeredPane.add(cardLabel, Integer.valueOf(i));

             // calculates the space to divide the cards (the -1 is just a guess, there is no math behind)
             y += (int)((componentHeight-75)/(enemy.getHand().size()-1));
         }

         panelForLayPane = new JPanel(null);
         panelForLayPane.add(leftCardsLayeredPane);

         leftInfoPanel = new JPanel(new BorderLayout());
         leftInfoPanel.setSize(150,260);

         leftAvatarPanel= new JPanel(new GridBagLayout());
         leftAvatarPanel.setPreferredSize(new Dimension(150, 120));

         ImageIcon leftAvatarIcon = new ImageIcon(enemyAvatarPath);
         Image leftAvatarImage = leftAvatarIcon.getImage();
         Image newLeftAvatarImage = leftAvatarImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
         leftAvatarIcon = new ImageIcon(newLeftAvatarImage);
         leftAvatarLabel = new JLabel();
         leftAvatarLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
         leftAvatarLabel.setIcon(leftAvatarIcon);

         leftAvatarPanel.add(leftAvatarLabel);

         leftNamePanel = new JPanel(new BorderLayout());
         leftNamePanel.setPreferredSize(new Dimension(150, 140));

         JLabel nameLabel = new JLabel(enemy.getName());
         leftNamePanel.add(nameLabel);
         nameLabel.setFont(new Font("Sans Serif", Font.BOLD, 12));
         nameLabel.setHorizontalAlignment(JLabel.CENTER);
         nameLabel.setVerticalAlignment(JLabel.TOP);
         leftNamePanel.add(nameLabel);

         leftInfoPanel.add(leftAvatarPanel, BorderLayout.CENTER);
         leftInfoPanel.add(leftNamePanel, BorderLayout.SOUTH);

    }

    public JPanel getLeftInfoPanel(){
        return leftInfoPanel;
    }

    public JLayeredPane getLeftCardsLayeredPane(){
        return leftCardsLayeredPane;
    }

    public void setLeftAvatarLabel(boolean enabled){
        this.leftAvatarLabel.setEnabled(enabled);
    }

    public JPanel getPanelForLayPane(){
        return panelForLayPane;
    }

    public Player getEnemy(){
        return enemy;
    }
}
