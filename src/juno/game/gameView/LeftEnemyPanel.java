package juno.game.gameView;

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
    private JPanel leftInfoPanel;
    private JPanel leftAvatarPanel;

    private JLabel leftAvatarLabel;
    private JPanel leftNamePanel;

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

        /**
         * THIS will call the layred card constructor to build the layered cards
        **/

        /**
         * Left enemy container panel*
         **/

         leftCardsLayeredPane = new JLayeredPane();
         leftCardsLayeredPane.setPreferredSize(new Dimension(200, 260));

         leftInfoPanel = new JPanel(new BorderLayout());
         leftInfoPanel.setSize(150,260);

         leftAvatarPanel= new JPanel(new GridBagLayout());
         leftAvatarPanel.setPreferredSize(new Dimension(150, 200));

         ImageIcon leftAvatarIcon = new ImageIcon(enemyAvatarPath);
         Image leftAvatarImage = leftAvatarIcon.getImage();
         Image newLeftAvatarImage = leftAvatarImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
         leftAvatarIcon = new ImageIcon(newLeftAvatarImage);
         leftAvatarLabel = new JLabel();
         leftAvatarLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
         leftAvatarLabel.setIcon(leftAvatarIcon);

         leftAvatarPanel.add(leftAvatarLabel);

         leftNamePanel = new JPanel(new BorderLayout());
         leftNamePanel.setPreferredSize(new Dimension(150, 60));

         JLabel nameLabel = new JLabel(enemy.getName());
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

}
