package juno.game.gameView;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private JFrame gameFrame;

    public GameFrame(GamePanel gamePanel, SidePanel sidePanel){
        gameFrame = new JFrame();
        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameFrame.setUndecorated(true);

        gameFrame.add(gamePanel.getGamePanel(), BorderLayout.CENTER);
        gameFrame.add(sidePanel.getSidePanel(), BorderLayout.EAST);

        gameFrame.setVisible(true);

    }

    public JFrame getGameFrame(){
        return gameFrame;
    }
}
