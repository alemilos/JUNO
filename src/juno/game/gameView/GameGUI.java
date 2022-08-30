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

    private GameFrame gameFrame;

    private SidePanel sidePanel;

    private UserPanel userPanel;

    private OneEnemyGUI enemyGUI;

    private LeftEnemyPanel  leftEnemyPanel = new LeftEnemyPanel();

    private RightEnemyPanel rightEnemyPanel = new RightEnemyPanel();

    private MultipleEnemiesTopGUI multipleEnemiesTopGUI; // aggiungere un costruttore vuoto

    private int playersNumber;

    private Player userPlayer;

    private GamePanel gamePanel;

    private JPanel deckContainer;

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

        userPanel = new UserPanel(user, userPlayer);
        Player nextToLeftUser = enemyPlayers.get(enemyPlayers.size()-1);
        Player nextToRightUser = enemyPlayers.get(0);
        String nextToRightUserAvatarPath = enemyAvatarPaths.get(0);
        String nextToLeftUserAvatarPath = enemyAvatarPaths.get(enemyPlayers.size()-1);
        deckContainer = getCenterDDeckAndGCard(cardBackPath, game.getTable().getGroundCard());


        if(playersNumber == 2){
            enemyGUI = new OneEnemyGUI(cardBackPath, nextToRightUser, nextToRightUserAvatarPath, playersNumber);
            gamePanel = new GamePanel(userPanel, enemyGUI, deckContainer);
        }
        else if(playersNumber == 3){
            System.out.println(enemyPlayers);
            multipleEnemiesTopGUI = new MultipleEnemiesTopGUI(cardBackPath,enemyPlayers,enemyAvatarPaths);
            gamePanel = new GamePanel(userPanel, multipleEnemiesTopGUI, deckContainer);
        }else if (playersNumber > 3) {
            multipleEnemiesTopGUI = new MultipleEnemiesTopGUI(cardBackPath, getTopEnemiesArray(enemyPlayers), getTopEnemiesArray(enemyAvatarPaths));
            leftEnemyPanel = new LeftEnemyPanel(cardBackPath, nextToLeftUser, nextToLeftUserAvatarPath);
            rightEnemyPanel = new RightEnemyPanel(cardBackPath, nextToRightUser, nextToRightUserAvatarPath);
            gamePanel = new GamePanel(userPanel, multipleEnemiesTopGUI, leftEnemyPanel, rightEnemyPanel, deckContainer);
        }

        /**TODO disable players avatar labels if player != table.getPlayingPlayer()**/
        //disableNonPlayingPlayers();

        sidePanel = new SidePanel(user);
        gameFrame = new GameFrame(gamePanel, sidePanel);
        sidePanel.setGameFrame(gameFrame.getGameFrame());
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
        GameGUI gg = new GameGUI(newUs,10, Difficulty.EASY, "src/Images/CardsBack/yugioh.png");
    }

}
