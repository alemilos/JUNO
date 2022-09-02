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

    private DeckAndGroundCardPanel deckContainer;

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

        System.out.println("players1: " + game.getTable().getPlayers());
        // Players in the order of play (with userPlayer included)
        ArrayList<Player> playersWithUser = game.getTable().getPlayers();
        /*
         * the avatarsPath are now ordered like the players List took in the beginning.
         * while the playersList has now changed, the avatarsPath one did NOT.
         * it has to change according to the position of the players. We will do that with a function
         */

        ArrayList<String> avatarsPathWithUser = changePositionOfAvatarsPath(avatarsPath, players, playersWithUser);

        System.out.println("players2: " + game.getTable().getPlayers());

        ArrayList<Player> enemyPlayers = getPlayersWithoutUser(playersWithUser, userPlayer);

        System.out.println("players3: " + game.getTable().getPlayers());

        ArrayList<String> enemyAvatarPaths = getPlayersWithoutUser(avatarsPathWithUser, user.getAvatar().getAvatarPath());

        System.out.println("players4: " + game.getTable().getPlayers());

        /**Setting panels**/
        userPanel = new UserPanel(user, userPlayer);
        Player nextToLeftUser = enemyPlayers.get(enemyPlayers.size()-1);
        Player nextToRightUser = enemyPlayers.get(0);
        String nextToRightUserAvatarPath = enemyAvatarPaths.get(0);
        String nextToLeftUserAvatarPath = enemyAvatarPaths.get(enemyPlayers.size()-1);
        deckContainer = new DeckAndGroundCardPanel(cardBackPath, game.getTable().getGroundCard());

        if(playersNumber == 2){
            enemyGUI = new OneEnemyGUI(cardBackPath, nextToRightUser, nextToRightUserAvatarPath, playersNumber);
            gamePanel = new GamePanel(userPanel, enemyGUI, deckContainer.getContainerPanel());
        }
        else if(playersNumber == 3){
            multipleEnemiesTopGUI = new MultipleEnemiesTopGUI(cardBackPath,enemyPlayers,enemyAvatarPaths);
            gamePanel = new GamePanel(userPanel, multipleEnemiesTopGUI, deckContainer.getContainerPanel());
        }else if (playersNumber > 3) {
            multipleEnemiesTopGUI = new MultipleEnemiesTopGUI(cardBackPath, getTopEnemiesArray(enemyPlayers), getTopEnemiesArray(enemyAvatarPaths));
            leftEnemyPanel = new LeftEnemyPanel(cardBackPath, nextToLeftUser, nextToLeftUserAvatarPath);
            rightEnemyPanel = new RightEnemyPanel(cardBackPath, nextToRightUser, nextToRightUserAvatarPath);
            gamePanel = new GamePanel(userPanel, multipleEnemiesTopGUI, leftEnemyPanel, rightEnemyPanel, deckContainer.getContainerPanel());
        }
        System.out.println("players5: " + game.getTable().getPlayers());
        /**todo run this each turn**/
        disableNonPlayingPlayers();

        System.out.println("players6: " + game.getTable().getPlayers());


        sidePanel = new SidePanel(user);
        gameFrame = new GameFrame(gamePanel, sidePanel);
        sidePanel.setGameFrame(gameFrame.getGameFrame());
        Scanner sc = new Scanner(System.in);
        //while(!game.isOver()){
            game.getTable().nextPlayer();
            disableNonPlayingPlayers();
       // }
    }


    public void disableNonPlayingPlayers(){
        if (this.playersNumber == 2){
            if(game.getTable().getPlayingPlayer() == userPlayer){
                userPanel.setAvatarLabel(true);
                enemyGUI.setAvatarLabel(false);
            }else{
                enemyGUI.setAvatarLabel(true);
                userPanel.setAvatarLabel(false);
            }
        }else if(this.playersNumber == 3){
            Player playingPlayer = game.getTable().getPlayingPlayer();
            if(playingPlayer == userPlayer){
                userPanel.setAvatarLabel(true);

            }else{
                userPanel.setAvatarLabel(false);
            }
            for (EnemyComponent enemyComponent: multipleEnemiesTopGUI.getEnemiesComponentsList()){
                if(playingPlayer == enemyComponent.getEnemy()){
                    enemyComponent.setAvatarLabel(true);
                }else{
                    enemyComponent.setAvatarLabel(false);
                }
            }
        }else if(this.playersNumber >= 4){
            Player playingPlayer = game.getTable().getPlayingPlayer();
            if(playingPlayer == userPlayer){
                userPanel.setAvatarLabel(true);
                leftEnemyPanel.setLeftAvatarLabel(false);
                rightEnemyPanel.setRightAvatarLabel(false);
                for(EnemyComponent enemyComponent : multipleEnemiesTopGUI.getEnemiesComponentsList()){
                    enemyComponent.setAvatarLabel(false);
                }

            }else{
                userPanel.setAvatarLabel(false);
                if (playingPlayer == leftEnemyPanel.getEnemy()){
                    leftEnemyPanel.setLeftAvatarLabel(true);
                    rightEnemyPanel.setRightAvatarLabel(false);
                }
                /**Evaluating right GUI**/
                else if (playingPlayer == rightEnemyPanel.getEnemy()){
                    rightEnemyPanel.setRightAvatarLabel(true);
                    leftEnemyPanel.setLeftAvatarLabel(false);
                }
                else if(playingPlayer != rightEnemyPanel.getEnemy() && playingPlayer != leftEnemyPanel.getEnemy()){
                    rightEnemyPanel.setRightAvatarLabel(false);
                    leftEnemyPanel.setLeftAvatarLabel(false);
                }
                for(EnemyComponent enemyComponent: multipleEnemiesTopGUI.getEnemiesComponentsList()){
                    /**Evaluating TOP GUI**/
                    if(enemyComponent.getEnemy() == game.getTable().getPlayingPlayer()){
                        enemyComponent.setAvatarLabel(true);
                    }
                    else{
                        enemyComponent.setAvatarLabel(false);
                    }
                }
            }
        }
    }

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
            P user  = playersWithUser.remove(0);
            ArrayList<P> playersWithoutUser = new ArrayList<>();
            playersWithoutUser.addAll(playersWithUser);
            playersWithUser.add(0,user);
            return playersWithoutUser;
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
