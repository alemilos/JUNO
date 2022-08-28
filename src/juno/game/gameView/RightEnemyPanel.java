package juno.game.gameView;

import juno.game.gameModel.Player;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class RightEnemyPanel extends JPanel {

    /**
     * WIDTH:  350
     * HEIGHT: 260
     * **/

    private JLayeredPane rightCardsLayeredPane;

    private int componentHeight = 260;

    private JPanel panelForLayPane;
    private JPanel rightInfoPanel;
    private JPanel rightAvatarPanel;

    private JLabel rightAvatarLabel;
    private JPanel rightNamePanel;

    public RightEnemyPanel(){
        // in case we have 2 players...
        rightCardsLayeredPane = new JLayeredPane();
        rightCardsLayeredPane.setSize(200, 260);

        rightInfoPanel = new JPanel();
        rightInfoPanel.setSize(150, 260);
    }

    /**
     * The Enemies ArrayList will be like this [enemy1, enemy2]. It comes from the list [User, enemy1, enemy2]
     * this means: enemy1 is the player at User's right, enemy2 is at User's left.
     * The player at User's right goes in the top, while the enemy at User's left goes in the left
     * **/
    public RightEnemyPanel(String cardBackPath, Player enemy, String enemyAvatarPath){

        /**
         * THIS will call the layred card constructor to build the layered cards
         **/

        /**
         * Left enemy container panel*
         **/

        rightCardsLayeredPane = new JLayeredPane();
        rightCardsLayeredPane.setBounds(0,0,200,260);

        int y = 0;

        for (int i = 0; i<enemy.getHand().size(); i++) {
            ImageIcon cardBackIcon = new ImageIcon(cardBackPath);
            Image cardBackImage = cardBackIcon.getImage();
            Image newCardBackImage = cardBackImage.getScaledInstance(65, 100, Image.SCALE_SMOOTH);
            cardBackIcon = new ImageIcon(newCardBackImage);
            RotatedIcon rotatedCardBackIcon = new RotatedIcon(cardBackIcon, RotatedIcon.Rotate.UP);
            JLabel cardLabel = new JLabel(rotatedCardBackIcon);
            cardLabel.setBounds(25,y,100,65);
            rightCardsLayeredPane.add(cardLabel, Integer.valueOf(enemy.getHand().size()-i));
            y += (int)((componentHeight-75)/(enemy.getHand().size()-1));;
        }

        panelForLayPane = new JPanel(null);
        panelForLayPane.add(rightCardsLayeredPane);

        rightInfoPanel = new JPanel(new BorderLayout());
        rightInfoPanel.setSize(150,260);

        rightAvatarPanel= new JPanel(new GridBagLayout());
        rightAvatarPanel.setPreferredSize(new Dimension(150, 120));

        ImageIcon rightAvatarIcon = new ImageIcon(enemyAvatarPath);
        Image rightAvatarImage = rightAvatarIcon.getImage();
        Image newRightAvatarImage = rightAvatarImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        rightAvatarIcon = new ImageIcon(newRightAvatarImage);
        rightAvatarLabel = new JLabel();
        rightAvatarLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        rightAvatarLabel.setIcon(rightAvatarIcon);

        rightAvatarPanel.add(rightAvatarLabel);

        rightNamePanel = new JPanel(new BorderLayout());
        rightNamePanel.setPreferredSize(new Dimension(150, 140));

        JLabel nameLabel = new JLabel(enemy.getName());
        ////rightNamePanel.add(nameLabel);
        nameLabel.setFont(new Font("Sans Serif", Font.BOLD, 12));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setVerticalAlignment(JLabel.TOP);
        rightNamePanel.add(nameLabel);

        rightInfoPanel.add(rightAvatarPanel, BorderLayout.CENTER);
        rightInfoPanel.add(rightNamePanel, BorderLayout.SOUTH);

    }

    public JPanel getRightInfoPanel(){
        return rightInfoPanel;
    }

    public JLayeredPane getRightCardsLayeredPane(){
        return rightCardsLayeredPane;
    }

    public JPanel getPanelForLayPane(){
        return panelForLayPane;
    }

    public void setRightAvatarLabel(boolean enabled){
        this.rightAvatarLabel.setEnabled(enabled);
    }
}
