package juno.game.gameView;

import juno.game.gameModel.Game;
import juno.game.gameModel.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private JPanel gamePanel;

    private JPanel middleContainer; /**todo change into deckContainer CLASS (todo)*/

    private UserPanel userPanel;

    private LeftEnemyPanel leftEnemyPanel;

    private RightEnemyPanel rightEnemyPanel;

    private OneEnemyGUI oneEnemyGUI;

    private MultipleEnemiesTopGUI multipleEnemiesTopGUI;

    public GamePanel(UserPanel userPanel, OneEnemyGUI oneEnemyGUI, JPanel middleContainer){
        this.userPanel = userPanel;
        this.oneEnemyGUI = oneEnemyGUI;
        this.middleContainer = middleContainer;
        // 1 Opponent
        gamePanel = new JPanel(new BorderLayout());

        gamePanel.setSize(1000,800);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setSize(1000,280);
        panel1.add(oneEnemyGUI.getInfoPanel(), BorderLayout.WEST);
        JPanel innerPanel1 = new JPanel(new GridBagLayout()); // to center everything in case of 2 nplayers
        innerPanel1.add(oneEnemyGUI.getCardsContainerPanel());
        panel1.add(innerPanel1, BorderLayout.CENTER);
        panel1.add(Box.createHorizontalStrut(150), BorderLayout.EAST);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setPreferredSize(new Dimension(350, 260));

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBackground(Color.green);
        panel3.setSize(new Dimension(300,260));
        panel3.add(middleContainer);

        JPanel panel4 = new JPanel(new BorderLayout());
        panel4.setPreferredSize(new Dimension(350,260));

        JPanel panel5 = new JPanel(new BorderLayout());
        panel5.setSize(1000,280);
        panel5.add(userPanel.getUserPanelContainer());

        gamePanel.add(panel1, BorderLayout.NORTH);
        gamePanel.add(panel2, BorderLayout.WEST);
        gamePanel.add(panel3, BorderLayout.CENTER);
        gamePanel.add(panel4, BorderLayout.EAST);
        gamePanel.add(panel5, BorderLayout.SOUTH);

    }

    public GamePanel(UserPanel userPanel, MultipleEnemiesTopGUI multipleEnemiesTopGUI, JPanel middleContainer){
        this.userPanel = userPanel;
        this.multipleEnemiesTopGUI = multipleEnemiesTopGUI;
        this.middleContainer = middleContainer;
        // 2 Opponents
        gamePanel = new JPanel(new BorderLayout());

        gamePanel.setSize(1000,800);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setSize(1000,280);
        panel1.add(multipleEnemiesTopGUI.getContainerPanel(), BorderLayout.CENTER);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setPreferredSize(new Dimension(350, 260));

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBackground(Color.green);
        panel3.setSize(new Dimension(300,260));
        panel3.add(middleContainer);

        JPanel panel4 = new JPanel(new BorderLayout());
        panel4.setPreferredSize(new Dimension(350,260));

        JPanel panel5 = new JPanel(new BorderLayout());
        panel5.setSize(1000,280);
        panel5.add(userPanel.getUserPanelContainer());

        gamePanel.add(panel1, BorderLayout.NORTH);
        gamePanel.add(panel2, BorderLayout.WEST);
        gamePanel.add(panel3, BorderLayout.CENTER);
        gamePanel.add(panel4, BorderLayout.EAST);
        gamePanel.add(panel5, BorderLayout.SOUTH);

    }

    public GamePanel(UserPanel userPanel, MultipleEnemiesTopGUI multipleEnemiesTopGUI,
                     LeftEnemyPanel leftEnemyPanel,
                     RightEnemyPanel rightEnemyPanel,
                     JPanel middleContainer){
        // 3-9 Opponents
        this.userPanel = userPanel;
        this.multipleEnemiesTopGUI = multipleEnemiesTopGUI;
        this.leftEnemyPanel = leftEnemyPanel;
        this.rightEnemyPanel = rightEnemyPanel;
        this.middleContainer = middleContainer;

        gamePanel = new JPanel(new BorderLayout());

        gamePanel.setSize(1000,800);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setSize(1000,280);
        panel1.add(multipleEnemiesTopGUI.getContainerPanel(), BorderLayout.CENTER);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setPreferredSize(new Dimension(350, 260));
        panel2.add(leftEnemyPanel.getLeftInfoPanel(), BorderLayout.WEST);
        panel2.add(leftEnemyPanel.getPanelForLayPane(), BorderLayout.CENTER);

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBackground(Color.green);
        panel3.setSize(new Dimension(300,260));
        panel3.add(middleContainer);

        JPanel panel4 = new JPanel(new BorderLayout());
        panel4.setPreferredSize(new Dimension(350,260));
        panel4.add(rightEnemyPanel.getPanelForLayPane(),BorderLayout.CENTER);
        panel4.add(rightEnemyPanel.getRightInfoPanel(),BorderLayout.EAST);

        JPanel panel5 = new JPanel(new BorderLayout());
        panel5.setSize(1000,280);
        panel5.add(userPanel.getUserPanelContainer());

        gamePanel.add(panel1, BorderLayout.NORTH);
        gamePanel.add(panel2, BorderLayout.WEST);
        gamePanel.add(panel3, BorderLayout.CENTER);
        gamePanel.add(panel4, BorderLayout.EAST);
        gamePanel.add(panel5, BorderLayout.SOUTH);

    }


    public JPanel getGamePanel(){
        return gamePanel;
    }

}
