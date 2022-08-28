package juno.game.gameView;

import juno.game.gameModel.Card;
import juno.game.gameModel.Player;
import juno.menu.menuModel.User;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

public class UserPanel extends JPanel {

    /**
     * WIDTH : 150-700-150 = 1000
     * HEIGHT: 280
     * **/

    private JPanel userPanelContainer;

    private JPanel infoPanel;

    private JPanel avatarPanel;

    private JLabel avatarLabel;

    private JPanel namePanel;


    private JPanel cardsContainer;

    private JPanel firstCardsLayer;

    private JPanel secondCardsLayer;

    private JPanel unoPanel;

    private JButton unoBtn;


    public UserPanel(User user, Player userPlayer){
        String avatarPath = user.getAvatar().getAvatarPath();
        ArrayList<Card> hand = userPlayer.getHand();

        userPanelContainer = new JPanel(new BorderLayout());
        userPanelContainer.setSize(1000, 280);

        /** INFO PANEL **/

        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setSize(new Dimension(150,280));

        avatarPanel = new JPanel(new GridBagLayout());
        avatarPanel.setPreferredSize(new Dimension(150,200));

        namePanel = new JPanel(new BorderLayout());
        namePanel.setPreferredSize(new Dimension(150, 80));

        JLabel nameLabel = new JLabel(user.getUsername());
        nameLabel.setFont(new Font("Sans Serif", Font.BOLD, 14));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.TOP);
        namePanel.add(nameLabel);

        ImageIcon avatarIcon = new ImageIcon(avatarPath);
        Image avatarImage = avatarIcon.getImage();
        Image newAvatarImage = avatarImage.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        avatarIcon = new ImageIcon(newAvatarImage);
        avatarLabel = new JLabel();
        avatarLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        avatarLabel.setIcon(avatarIcon);

        avatarPanel.add(avatarLabel);

        infoPanel.add(avatarPanel, BorderLayout.CENTER);
        infoPanel.add(namePanel, BorderLayout.SOUTH);

        /** CARD PANEL **/

        cardsContainer = new JPanel(new BorderLayout());
        cardsContainer.setSize(new Dimension(700, 280));
        cardsContainer.setBackground(new Color(200, 100, 29));

        firstCardsLayer = new JPanel(new FlowLayout());
        firstCardsLayer.setPreferredSize(new Dimension(700, 140));


        secondCardsLayer = new JPanel(new FlowLayout());
        secondCardsLayer.setPreferredSize(new Dimension(700, 140));

        for(int i = 0; i < hand.size(); i++){
            Card card = hand.get(i);
            ImageIcon cardIcon = new ImageIcon(card.getImagePath());
            Image cardImage = cardIcon.getImage();
            Image newCardImage = cardImage.getScaledInstance(65, 120, Image.SCALE_SMOOTH);
            cardIcon = new ImageIcon(newCardImage);
            JButton cardButton = new JButton(cardIcon);
            if (i < 11 ){
                firstCardsLayer.add(cardButton);
            }else{
                secondCardsLayer.add(cardButton);
            }
            cardsContainer.add(firstCardsLayer, BorderLayout.CENTER);
            cardsContainer.add(secondCardsLayer, BorderLayout.SOUTH);
        }

        /** UNO PANEL **/

        unoPanel = new JPanel(new GridBagLayout());
        unoPanel.setPreferredSize(new Dimension(150, 280));

        unoBtn = new JButton();
        unoBtn.setPreferredSize(new Dimension(150,150));
        unoBtn.setOpaque(true);
        unoBtn.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        unoBtn.setBackground(Color.red);

        JLabel unoLabel = new JLabel("UNO");
        unoLabel.setFont(new Font("Cabin", Font.BOLD, 50));
        unoLabel.setForeground(Color.yellow);
        unoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        unoBtn.add(unoLabel);
        unoPanel.add(unoBtn);

        userPanelContainer.add(infoPanel, BorderLayout.WEST);
        userPanelContainer.add(cardsContainer, BorderLayout.CENTER);
        userPanelContainer.add(unoPanel, BorderLayout.EAST);
    }

    public void addCard(){
        // if hand.size() > 10 add to second layer
        // else add to first layer
    }

    public void removeCard(){
        // search card in first layer, if is there remove it and let one card of the
        // 2nd layer go there
    }

    public JPanel getUserPanelContainer() {
        return userPanelContainer;
    }

    public void setUserPanelContainer(JPanel userPanelContainer) {
        this.userPanelContainer = userPanelContainer;
    }

    public JPanel getInfoPanel() {
        return infoPanel;
    }

    public void setInfoPanel(JPanel infoPanel) {
        this.infoPanel = infoPanel;
    }

    public JPanel getAvatarPanel() {
        return avatarPanel;
    }

    public void setAvatarPanel(JPanel avatarPanel) {
        this.avatarPanel = avatarPanel;
    }

    public JLabel getAvatarLabel() {
        return avatarLabel;
    }

    public void setAvatarLabel(JLabel avatarLabel) {
        this.avatarLabel = avatarLabel;
    }

    public JPanel getNamePanel() {
        return namePanel;
    }

    public void setNamePanel(JPanel namePanel) {
        this.namePanel = namePanel;
    }

    public JPanel getCardsContainer() {
        return cardsContainer;
    }

    public void setCardsContainer(JPanel cardsContainer) {
        this.cardsContainer = cardsContainer;
    }

    public JPanel getFirstCardsLayer() {
        return firstCardsLayer;
    }

    public void setFirstCardsLayer(JPanel firstCardsLayer) {
        this.firstCardsLayer = firstCardsLayer;
    }

    public JPanel getSecondCardsLayer() {
        return secondCardsLayer;
    }

    public void setSecondCardsLayer(JPanel secondCardsLayer) {
        this.secondCardsLayer = secondCardsLayer;
    }

    public JPanel getUnoPanel() {
        return unoPanel;
    }

    public void setUnoPanel(JPanel unoPanel) {
        this.unoPanel = unoPanel;
    }

    public JButton getUnoBtn() {
        return unoBtn;
    }

    public void setUnoBtn(JButton unoBtn) {
        this.unoBtn = unoBtn;
    }
}
