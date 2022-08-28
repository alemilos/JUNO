package juno.game.gameView;

import juno.game.gameModel.Game;
import juno.game.gameModel.Player;

import javax.swing.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private JPanel gamePanel;

    public GamePanel(JPanel userPanel, OneEnemyGUI oneEnemyGUI){
        // 1 Opponent

    }

    public GamePanel(JPanel userPanel, MultipleEnemiesTopGUI multipleEnemiesTopGUI){
        // 2 Opponents

    }

    public GamePanel(JPanel userPanel, OneEnemyGUI oneEnemyGUI,
                     LeftEnemyPanel leftEnemyPanel, RightEnemyPanel rightEnemyPanel){
        // 3 Opponents
    }

    public GamePanel(JPanel userPanel, MultipleEnemiesTopGUI multipleEnemiesTopGUI,
                     LeftEnemyPanel leftEnemyPanel, RightEnemyPanel rightEnemyPanel){
        // from 4 to 9 Opponents

    }

    public JPanel getGamePanel(){
        return gamePanel;
    }

}
