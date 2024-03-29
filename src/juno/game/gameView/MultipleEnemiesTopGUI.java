package juno.game.gameView;

import juno.game.gameModel.Player;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MultipleEnemiesTopGUI {
    /**
     * WIDTH: 1000
     * HEIGHT: 280
     * **/

    private JPanel containerPanel; // gridbaglayout

    private JPanel enemyComponentsContainerPanel; // flowLayout [<- <-]

    private int playersNumber;

    private EnemyComponent enemyComponent;

    private ArrayList<EnemyComponent> enemiesComponentsList = new ArrayList<>();

    public MultipleEnemiesTopGUI(){
        // this sets everything to default
    }

    public MultipleEnemiesTopGUI(String cardBackPath ,ArrayList<Player> enemies, ArrayList<String> avatarPaths){

        containerPanel = new JPanel(new GridBagLayout());

        enemyComponentsContainerPanel = new JPanel(new FlowLayout());
        enemyComponentsContainerPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);


        playersNumber = enemies.size();

        int enemyComponentWidth = (int)(1000/playersNumber); // panel width divided by number of players

        for (int i= 0; i < playersNumber; i++){
            enemyComponent =
                    new EnemyComponent(enemyComponentWidth, enemies.get(i), avatarPaths.get(i), cardBackPath);

            enemiesComponentsList.add(enemyComponent);

            enemyComponentsContainerPanel.add(enemyComponent.getContainerPanel());

        }

        containerPanel.add(enemyComponentsContainerPanel);

    }

    public JPanel getContainerPanel(){
        return containerPanel;
    }

    public EnemyComponent getEnemyComponent(){
        return enemyComponent;
    }

    public ArrayList<EnemyComponent> getEnemiesComponentsList(){
        return enemiesComponentsList;
    }
}
