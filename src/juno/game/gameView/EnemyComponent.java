package juno.game.gameView;

import juno.game.gameModel.Player;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class EnemyComponent extends JPanel {

    /**
     * WIDTH: ???
     * HEIGHT: 280
     * **/

    private JPanel containerPanel; // everything goes here

    private JPanel infoPanel;
    private JPanel avatarPanel;
    private JLabel avatarLabel;
    private JPanel namePanel;

    private JLabel nameLabel;

    private JPanel layPanePanel;
    private JLayeredPane cardsPane;


    public EnemyComponent(int componentWidth, Player enemy, String avatarPath, String cardBackPath){

        containerPanel = new JPanel(new BorderLayout());
        containerPanel.setSize(componentWidth,280);

        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setSize(componentWidth, 140);

        avatarPanel = new JPanel(new GridBagLayout());
        avatarPanel.setPreferredSize(new Dimension(componentWidth, 80));


        ImageIcon avatarIcon = new ImageIcon(avatarPath);
        Image avatarImage = avatarIcon.getImage();
        Image newAvatarImage = avatarImage.getScaledInstance(60,60,Image.SCALE_SMOOTH);
        avatarIcon = new ImageIcon(newAvatarImage);
        avatarLabel = new JLabel(avatarIcon);
        avatarLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));


        avatarPanel.add(avatarLabel);

        namePanel = new JPanel(new BorderLayout());
        namePanel.setPreferredSize(new Dimension(componentWidth, 40));

        nameLabel = new JLabel(enemy.getName());
        nameLabel.setFont(new Font("Sans Serif", Font.BOLD, 12));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setVerticalAlignment(JLabel.TOP);
        namePanel.add(nameLabel);

        // setting infopanel

        infoPanel.add(avatarPanel, BorderLayout.CENTER);
        infoPanel.add(namePanel, BorderLayout.SOUTH);

        // setting cards panel
        layPanePanel = new JPanel(null);
        layPanePanel.setPreferredSize(new Dimension(componentWidth, 160));

        cardsPane = new JLayeredPane();
        cardsPane.setBounds(0,0, componentWidth, 140);

        int x = 0; // shift for each card
        for (int i = 0; i < enemy.getHand().size(); i++){
            ImageIcon cardBackIcon = new ImageIcon(cardBackPath);
            Image cardBackImage = cardBackIcon.getImage();
            Image newCardBackImage = cardBackImage.getScaledInstance(65, 100, Image.SCALE_SMOOTH);
            cardBackIcon = new ImageIcon(newCardBackImage);
            JLabel cardLabel = new JLabel(cardBackIcon);
            cardLabel.setBounds(x,0,65,100);
            cardsPane.add(cardLabel, Integer.valueOf(i));
            x += (int)((componentWidth-65)/(enemy.getHand().size()-1)); //     space/nCard*cardWidth

        }

        layPanePanel.add(cardsPane);

        containerPanel.add(infoPanel, BorderLayout.NORTH);
        containerPanel.add(layPanePanel, BorderLayout.CENTER);

    }

    public JPanel getContainerPanel(){
        return containerPanel;
    }

    public JPanel getInfoPanel(){
        return infoPanel;
    }

    public JLayeredPane getCardsPane(){
        return cardsPane;
    }

    public void setAvatarLabel(boolean enabled){
        avatarLabel.setEnabled(enabled);
    }


}
