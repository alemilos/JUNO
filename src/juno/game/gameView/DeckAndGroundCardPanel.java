package juno.game.gameView;

import juno.game.gameModel.Card;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeckAndGroundCardPanel extends JPanel implements ActionListener {

    private String cardBackPath;

    private JPanel containerPanel;

    private Card groundCard;

    private JButton drawBtn;

    private JLabel groundCardLabel;

    public DeckAndGroundCardPanel(String cardBackPath, Card groundCard){
        this.cardBackPath = cardBackPath;
        this.groundCard = groundCard;

        containerPanel = new JPanel(new BorderLayout());

        JPanel drawBtnPanel = new JPanel(new GridBagLayout());
        drawBtnPanel.setPreferredSize(new Dimension(300,80));

        drawBtn = new JButton("PESCA");
        drawBtn.setFont(new Font("Sans Serif", Font.BOLD, 20));
        drawBtn.setForeground(Color.white);
        drawBtn.setPreferredSize(new Dimension(280,50));
        drawBtn.setOpaque(true);
        drawBtn.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        drawBtn.setBackground(Color.red);

        drawBtnPanel.add(drawBtn);

        JPanel deckAndGroundCardContainerPanel = new JPanel(new GridBagLayout());
        deckAndGroundCardContainerPanel.setSize(new Dimension(300, 160));

        JPanel deckAndGroundCardPanel = new JPanel(new FlowLayout());
        deckAndGroundCardPanel.setSize(new Dimension(300, 160));

        ImageIcon backCardIcon = new ImageIcon(cardBackPath);
        Image backCardImage = backCardIcon.getImage();
        Image newBackCardImage = backCardImage.getScaledInstance(75,120, Image.SCALE_SMOOTH);
        backCardIcon = new ImageIcon(newBackCardImage);
        JLabel backCardLabel = new JLabel();
        backCardLabel.setIcon(backCardIcon);

        ImageIcon groundCardIcon = new ImageIcon(groundCard.getImagePath());
        Image groundCardImage = groundCardIcon.getImage();
        Image newGroundCardImage = groundCardImage.getScaledInstance(75,120, Image.SCALE_SMOOTH);
        groundCardIcon = new ImageIcon(newGroundCardImage);
        groundCardLabel = new JLabel();
        groundCardLabel.setIcon(groundCardIcon);

        deckAndGroundCardPanel.add(backCardLabel);
        deckAndGroundCardPanel.add(Box.createHorizontalStrut(20));
        deckAndGroundCardPanel.add(groundCardLabel);

        deckAndGroundCardContainerPanel.add(deckAndGroundCardPanel);

        containerPanel.add(drawBtnPanel, BorderLayout.NORTH);
        containerPanel.add(deckAndGroundCardContainerPanel, BorderLayout.CENTER);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == drawBtn){

        }
    }

    public void setGroundCard(Card groundCard){
        ImageIcon groundCardIcon = new ImageIcon(groundCard.getImagePath());
        Image groundCardImage = groundCardIcon.getImage();
        Image newGroundCardImage = groundCardImage.getScaledInstance(75,120, Image.SCALE_SMOOTH);
        groundCardIcon = new ImageIcon(newGroundCardImage);
        this.groundCardLabel.setIcon(groundCardIcon);
    }

    public JPanel getContainerPanel(){
        return containerPanel;
    }

}
