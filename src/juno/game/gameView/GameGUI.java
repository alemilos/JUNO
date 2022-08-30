package juno.game.gameView;

import juno.game.gameModel.*;
import juno.menu.menuModel.User;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameGUI {

    /*
    * IF THE CONTAINER WILL CONTAIN OTHER CONTAINERS --> USE setSize
    * ELSE USE setPreferredSize.
    *
    * To listen or resize use COMPONENT LISTENER (NEW COMPONENT ADAPTER){}
    * */

    private Game game;

    private User user;

    private Difficulty difficulty;

    private String cardBackPath;

    private JFrame gameFrame;

    private SidePanel sidePanel;

    private UserPanel userPanel;

    private OneEnemyGUI enemyGUI;

    private LeftEnemyPanel  leftEnemyPanel = new LeftEnemyPanel();

    private RightEnemyPanel rightEnemyPanel = new RightEnemyPanel();

    private MultipleEnemiesTopGUI multipleEnemiesTopGUI; // aggiungere un costruttore vuoto

    private int playersNumber;

    private Player userPlayer;

    public GameGUI(User user, int playersNumber, Difficulty difficulty, String cardBackPath){
        this.user = user;
        this.playersNumber = playersNumber;
        this.difficulty = difficulty;
        this.cardBackPath = cardBackPath;

        LinkedHashMap<Player, String> playerToAvatarPath = createPlayerToAvatarMap(user, playersNumber);
        ArrayList<Player> players = new ArrayList<>(playerToAvatarPath.keySet());
        ArrayList<String> avatarsPath = new ArrayList<>(playerToAvatarPath.values());

        userPlayer = players.get(0);

        DrawDeck drawDeck = new DrawDeck(); // the setter is called at construction phase
        addImagePathNamesToCards(drawDeck.getDeck()); // adding pathnames to cards
        drawDeck.setDeck(drawDeck.shuffleCards(drawDeck.getDeck())); // the deck is now shuffled

        game = new Game(new Preparations(new Table(players, drawDeck)));
        game.getTable().setUserPlayer(userPlayer);

        /**
         * After preparation PHASE the players list order will be changed
         * I need to find the position of the USER in the array [enemy1, enemy2, enemy3, USER, enemy4, enemy5]
         * the game goes in clockwise order. Enemy3 goes at the left of USER while enemy 4 goes at his right.
         *
         * **/

        // Players in the order of play (with userPlayer included)
        ArrayList<Player> playersWithUser = game.getTable().getPlayers();
        /*
         * the avatarsPath are now ordered like the players List took in the beginning.
         * while the playersList has now changed, the avatarsPath one did NOT.
         * it has to change according to the position of the players. We will do that with a function
         */

        ArrayList<String> avatarsPathWithUser = changePositionOfAvatarsPath(avatarsPath, players, playersWithUser);

        ArrayList<Player> enemyPlayers     = getPlayersWithoutUser(playersWithUser, userPlayer);

        ArrayList<String> enemyAvatarPaths = getPlayersWithoutUser(avatarsPathWithUser, user.getAvatar().getAvatarPath());

        /**ENEMY GUI WILL BE FOR 2, 3 and 4 players since we will have 1 enemy at the top
         *
         * All of this code will go in the constructors of GamePanel
         * **/
        if(playersNumber == 2){
            Player enemy = enemyPlayers.get(0);
            String enemyAvatarPath = enemyAvatarPaths.get(0);
            enemyGUI = new OneEnemyGUI(cardBackPath,enemy, enemyAvatarPath, playersNumber);
        }
        else if(playersNumber == 3){
            Player leftEnemy = enemyPlayers.get(1);
            String leftAvatarPath = enemyAvatarPaths.get(1);
            leftEnemyPanel = new LeftEnemyPanel(cardBackPath, leftEnemy , leftAvatarPath);
            Player topEnemy = enemyPlayers.get(0);
            String topAvatarPath = enemyAvatarPaths.get(0);
            enemyGUI = new OneEnemyGUI(cardBackPath, topEnemy, topAvatarPath, playersNumber );
        }else if (playersNumber == 4){
            Player leftEnemy = enemyPlayers.get(2);
            String leftAvatarPath = enemyAvatarPaths.get(2);
            leftEnemyPanel = new LeftEnemyPanel(cardBackPath, leftEnemy , leftAvatarPath);
            Player topEnemy = enemyPlayers.get(1);
            String topAvatarPath = enemyAvatarPaths.get(1);
            enemyGUI = new OneEnemyGUI(cardBackPath, topEnemy, topAvatarPath, playersNumber );
            Player rightEnemy = enemyPlayers.get(0);
            String rightAvatarPath = enemyAvatarPaths.get(0);
            rightEnemyPanel = new RightEnemyPanel(cardBackPath, rightEnemy, rightAvatarPath);
        }else if(playersNumber > 4){
            Player leftEnemy = enemyPlayers.get(enemyPlayers.size()-1);
            String leftAvatarPath = enemyAvatarPaths.get(enemyPlayers.size()-1);
            leftEnemyPanel = new LeftEnemyPanel(cardBackPath, leftEnemy , leftAvatarPath);
            Player rightEnemy = enemyPlayers.get(0);
            String rightAvatarPath = enemyAvatarPaths.get(0);
            rightEnemyPanel = new RightEnemyPanel(cardBackPath, rightEnemy, rightAvatarPath);
            ArrayList<Player> topEnemies = getTopEnemiesArray(enemyPlayers);
            ArrayList<String> topAvatarPaths = getTopEnemiesArray(enemyAvatarPaths);
            multipleEnemiesTopGUI = new MultipleEnemiesTopGUI(cardBackPath,topEnemies, topAvatarPaths);
        }

        /**TODO disable players avatar labels if player != table.getPlayingPlayer()**/
        //disableNonPlayingPlayers();


        /*
        while("game is running"){
            "run a function which needs to wait until user INPUT is took"
                    "than repeat this cicle until game is not OVER"
        }
         */


        /**START TO REFACTOR
         *
         * Make a JFRAME extend in a class for this
         * */
        gameFrame = new JFrame();
        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameFrame.setUndecorated(true);
        userPanel = new UserPanel(user, userPlayer);
        JPanel middleConta = getCenterDDeckAndGCard(cardBackPath, game.getTable().getGroundCard());
        JPanel gamePanel = getGamePanel(userPanel,
                                        middleConta,
                                        enemyGUI,
                                        leftEnemyPanel,
                                        rightEnemyPanel,
                                        multipleEnemiesTopGUI);
        sidePanel = new SidePanel(user, gameFrame);
        gameFrame.add(gamePanel, BorderLayout.CENTER);
        gameFrame.add(sidePanel.getSidePanel(), BorderLayout.EAST);



        //frame.add(sidePanel, BorderLayout.EAST);
        gameFrame.setVisible(true);
        /**END TO REFACTOR**/

    }

    /*
    public void disableNonPlayingPlayers(){
        if (this.playersNumber == 2){
            Player playingPlayer = game.getTable().getPlayingPlayer();
            enemyGUI.setAvatarLabel(playingPlayer == enemyGUI.getEnemy() );
            userAvatarLabel.setEnabled(playingPlayer == userPlayer);
        }else if(this.playersNumber == 3){

        }else if(this.playersNumber == 4){

        }
    }

     */

    public <T> ArrayList<T> getTopEnemiesArray(ArrayList<T> array){
        // remove first and last element of the array which represent the left and right players
        array.remove(0);
        array.remove(array.size()-1);
        return array;
    }

    public LinkedHashMap<Player, String> createPlayerToAvatarMap(User user, int playersNumber){
        // create names and avatarsPath lists
        ArrayList<String> names = Stream.of("Steve Jobs", "James Gosling", "Bill Gates", "Mark Zuckerberg", "Lex Fridman", "Alan Turing", "Kurt Godel", "Linus Torvald", "Terry A. Davis","Ludwig Wittgenstein", "J. R. R. Tolkien","Fedor Dostoevskij", "J.K. Rowling", "George R.R. Martin", "Steven Spielberg", "Stanley Kubrick", "Liam Neeson", "Ada Lovelace", "Donald Knuth", "Kevin Mitnick", "George Hotz", "Elliot Alderson", "Tyrion Lannister" , "Walter White","Ezio Auditore", "Natan Drake", "Spiderman", "Harry Potter", "Ron Weasley","Hermione Granger", "Jordan Peterson", "Karl Marx", "John Nash", "Aaron Swartz", "John Bradshaw", "Andrew Ng", "Friedrich Nietzsche", "Pier Paolo Pasolini", "Elsa Morante", "Alberto Moravia", "Giacomo Leopardi", "Dante Alighieri", "Virgilio", "Lucio Anneo Seneca", "Sigmund Freud", "Carl Jung", "Franz Kafka", "Carmelo Bene", "Rancore", "Caparezza", "Jack Nicholson","Francesco Guccini" ,"Fabrizio De André","Federico Fellini", "Robert De Niro" ,"Jennifer Connelly", "Neo",
                "Merle Robbin", "Albus Silente", "Magnus Carlsen", "Rubeus Hagrid", "Rino Gaetano", "Edward Snowden").collect(Collectors.toCollection(ArrayList::new));

        ArrayList<String> avatarsPath = Stream.of("steve_jobs.jpeg","james_gosling.png" , "bill_gates.jpeg","mark_zuckerberg.jpeg", "lex_fridman.png", "alan_turing.jpeg", "kurt_godel.jpeg", "linus_torvalds.png", "terry_a_davis.jpeg", "ludwig_wittgenstein.jpeg", "j_r_r_tolkien.jpeg", "fedor_dostoevskij.jpeg", "j_k_rowling.jpeg", "george_r_r_martin.jpg", "steven_spielberg.jpeg", "stanley_kubrick.jpeg", "liam_neeson.jpeg", "ada_lovelace.jpeg", "donald_knuth.jpeg", "kevin_mitnick.jpeg", "george_hotz.jpeg", "elliot_alderson.jpeg", "tyrion_lannister.jpeg", "walter_white.png", "ezio_auditore.png", "natan_drake.png", "spiderman.jpeg", "harry_potter.png", "ron_weasley.jpeg", "hermione_granger.jpeg", "jordan_peterson.jpeg", "karl_marx.jpeg", "john nash.jpeg", "aaron_swartz.jpeg", "john bradshaw.jpeg", "andrew_ng.jpeg", "friedrich_nietzsche.jpeg", "pier_paolo_pasolini.jpeg", "elsa_morante.jpeg", "alberto_moravia.jpeg",  "giacomo_leopardi.jpeg", "dante_alighieri.jpeg",  "virgilio.png", "lucio_anneo_seneca.jpeg",  "sigmund_freud.jpeg", "carl_jung.jpeg",  "franz_kafka.jpeg", "carmelo_bene.jpeg",  "rancore.jpeg", "caparezza.jpeg",  "jack-nicholson.jpeg", "francesco_guccini.png",  "fabrizio_de_andre.jpeg", "federico_fellini.jpeg",  "robert_de_niro.jpeg", "jennifer_connelly.png", "neo.jpeg"
                        , "merle_robbins.jpeg", "albus_silente.jpeg", "magnus_carlsen.jpeg", "rubeus_hagrid.png", "rino_gaetano.jpeg", "edward_snowden.jpeg" )
                .map(name -> "src/Images/BotAvatars/" + name)
                .collect(Collectors.toCollection(ArrayList::new));

        // the map keeps a player object and an avatarPath String
        LinkedHashMap<Player, String> playerToAvatarMap = new LinkedHashMap<>();
        playerToAvatarMap.put(new Player(user.getUsername(), false), user.getAvatar().getAvatarPath());

        for(int i = 0; i < playersNumber-1 ;i++){
            Random rand = new Random();
            int randIdx = rand.nextInt(names.size());
            playerToAvatarMap.put(new Player(names.remove(randIdx)), avatarsPath.remove(randIdx));
        }
        return playerToAvatarMap;
    }

    public <P,S> ArrayList<P> getPlayersWithoutUser(ArrayList<P> playersWithUser, S userPlayer){
        /*
        * Returns a list that behaves like the demonstration here:
        * StartingList1 = [enemy1, userPlayer, enemy3] --> FinalList1 = [enemy3, enemy1]
        * StartingList2 = [userPlayer, enemy1, enemy2] --> FinalList2 = [enemy1, enemy3]
        * The last element will be the enemy at the left of the user, while the first will be the enemy at his right
        * All the others (in case there are others) will be displayed in a reverse FlowLayout in the fourToNineEnemies class
        * */
        if (playersWithUser.get(0) == userPlayer){
            playersWithUser.remove(0);
            return playersWithUser;
        }
        else{
            int i = 0;
            ArrayList<P> beforeUser = new ArrayList<>();
            ArrayList<P> afterUser = new ArrayList<>();
            while(playersWithUser.get(i) != userPlayer){
                beforeUser.add(playersWithUser.get(i++));
            }
            i+=1;
            while(i < playersWithUser.size()){
                afterUser.add(playersWithUser.get(i++));
            }
            afterUser.addAll(beforeUser);
            return afterUser;
        }
    }

    public ArrayList<String> changePositionOfAvatarsPath(ArrayList<String> oldAvatarPaths
                                                        , ArrayList<Player> oldPlayers
                                                        , ArrayList<Player> newPlayers ){

        /**
         * finds the userPlayer, and build an array in this way
         * getTable.getPlayers = [enemy1, enemy2 , user, enemy3, enemy4] --> returns [user,enemy3,enemy4,enemy1, enemy2]
         * so the last one goes in the left, while the index 1 goes at his right. All the others go in the top
         * **/
        /*
        * L1 and L2 are ordered together but...
        * L1old = [a,b,c,d,e,f] -> has changed to -> [b,e,f,c,a,d] = L1new
        * L2old = [1,2,3,4,5,6] ...we want to have...[2,5,6,3,1,4] = L2new
        */
        ArrayList<String> newAvatarPaths = new ArrayList<>();
        for(Player player: newPlayers){
            newAvatarPaths.add(oldAvatarPaths.get(oldPlayers.indexOf(player)));
        }
        return newAvatarPaths;
    }

    /**
     * Will everything go here???
     * Else TODO remove this.
     * **/
    public JFrame getGameGUI(Player userPlayer){
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(1200,800);
        //frame.setResizable(false); ALLOW THIS ONLY FROM SETTINGS
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }


    public JPanel getGamePanel(UserPanel userPanel,
                               JPanel middleContainer, OneEnemyGUI enemyGUI,
                               LeftEnemyPanel leftEnemyPanel,
                               RightEnemyPanel rightEnemyPanel,
                               MultipleEnemiesTopGUI multipleEnemiesTopGUI){

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setSize(1000,800);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setSize(1000,280);
        if (playersNumber <= 4) {
            panel1.add(enemyGUI.getInfoPanel(), BorderLayout.WEST);
            JPanel innerPanel1 = new JPanel(new GridBagLayout()); // to center everything in case of 2 nplayers
            innerPanel1.add(enemyGUI.getCardsContainerPanel());
            panel1.add(innerPanel1, BorderLayout.CENTER);
            panel1.add(Box.createHorizontalStrut(150), BorderLayout.EAST);
        }
        if (playersNumber > 4){
            panel1.add(multipleEnemiesTopGUI.getContainerPanel(), BorderLayout.CENTER);
        }

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setPreferredSize(new Dimension(350, 260));
        if (this.playersNumber > 2) {
            panel2.add(leftEnemyPanel.getLeftInfoPanel(), BorderLayout.WEST);
            panel2.add(leftEnemyPanel.getPanelForLayPane(), BorderLayout.CENTER);
        }

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBackground(Color.green);
        panel3.setSize(new Dimension(300,260));
        panel3.add(middleContainer);

        JPanel panel4 = new JPanel(new BorderLayout());
        panel4.setPreferredSize(new Dimension(350,260));
        if (this.playersNumber > 3){
            panel4.add(rightEnemyPanel.getPanelForLayPane(),BorderLayout.CENTER);
            panel4.add(rightEnemyPanel.getRightInfoPanel(),BorderLayout.EAST);
        }

        JPanel panel5 = new JPanel(new BorderLayout());
        panel5.setSize(1000,280);
        panel5.add(userPanel.getUserPanelContainer());
        /*panel5.add(infoPanel, BorderLayout.WEST);
        panel5.add(cardsPanel, BorderLayout.CENTER);
        panel5.add(unoPanel, BorderLayout.EAST);


         */

        mainPanel.add(panel1, BorderLayout.NORTH);
        mainPanel.add(panel2, BorderLayout.WEST);
        mainPanel.add(panel3, BorderLayout.CENTER);
        mainPanel.add(panel4, BorderLayout.EAST);
        mainPanel.add(panel5, BorderLayout.SOUTH);

        return mainPanel;
    }

    public JPanel getCenterDDeckAndGCard(String backCardPath, Card groundCard){

        JPanel containerPanel = new JPanel(new BorderLayout());

        JPanel drawBtnPanel = new JPanel(new GridBagLayout());
        drawBtnPanel.setPreferredSize(new Dimension(300,80));

        JButton drawBtn = new JButton("PESCA");
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

        ImageIcon backCardIcon = new ImageIcon(backCardPath);
        Image backCardImage = backCardIcon.getImage();
        Image newBackCardImage = backCardImage.getScaledInstance(75,120, Image.SCALE_SMOOTH);
        backCardIcon = new ImageIcon(newBackCardImage);
        JLabel backCardLabel = new JLabel();
        backCardLabel.setIcon(backCardIcon);

        ImageIcon groundCardIcon = new ImageIcon(groundCard.getImagePath());
        Image groundCardImage = groundCardIcon.getImage();
        Image newGroundCardImage = groundCardImage.getScaledInstance(75,120, Image.SCALE_SMOOTH);
        groundCardIcon = new ImageIcon(newGroundCardImage);
        JLabel groundCardLabel = new JLabel();
        groundCardLabel.setIcon(groundCardIcon);

        deckAndGroundCardPanel.add(backCardLabel);
        deckAndGroundCardPanel.add(Box.createHorizontalStrut(20));
        deckAndGroundCardPanel.add(groundCardLabel);

        deckAndGroundCardContainerPanel.add(deckAndGroundCardPanel);

        containerPanel.add(drawBtnPanel, BorderLayout.NORTH);
        containerPanel.add(deckAndGroundCardContainerPanel, BorderLayout.CENTER);

        return containerPanel;
    }


    public ArrayList<JPanel> getPlayerAvatarAndNameDisplay(Player player){
        // returns an avatar image in the top and a name in the bottom
        JPanel avatarAndNamePanel = new JPanel();
        ArrayList<JPanel> arrayPanel = new ArrayList<>();
        arrayPanel.add(avatarAndNamePanel);
        return arrayPanel;
    }

    public void addImagePathNamesToCards(ArrayList<Card> deck){
        int i = 0;
        String initialPath = "src/Images/Cards/";
        for (Card card : deck){
            CardTypes type = card.getType();
            switch (type){
                case CLASSIC -> {
                    String color = card.getColor().toString().toLowerCase();
                    int cardNumber = ((ClassicCard)card).getNumber();
                    String imagePath = initialPath + color + "_" + cardNumber + ".png";
                    card.setImagePath(imagePath);
                }
                case REVERSE -> {
                    String color = card.getColor().toString().toLowerCase();
                    String imagePath = initialPath + color + "_reverse.png";
                    card.setImagePath(imagePath);
                }
                case SKIP -> {
                    String color = card.getColor().toString().toLowerCase();
                    String imagePath = initialPath + color + "_skip.png";
                    card.setImagePath(imagePath);
                }
                case DRAW_TWO -> {
                    String color = card.getColor().toString().toLowerCase();
                    String imagePath = initialPath + color + "_draw2.png";
                    card.setImagePath(imagePath);
                }
                case WILD -> {
                    String imagePath = initialPath + "wild.png";
                    card.setImagePath(imagePath);
                }
                case WILD_DRAW_FOUR -> {
                    String imagePath = initialPath + "wild_draw4.png";
                    card.setImagePath(imagePath);
                }
            }
        }
    }

    public static void main(String[] args) {
        User newUs = new User("AleMilos", 10, "src/Images/Avatars/me.jpg");
        GameGUI gg = new GameGUI(newUs,2, Difficulty.EASY, "src/Images/CardsBack/yugioh.png");
    }

}
