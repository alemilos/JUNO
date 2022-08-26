package juno.game.gameView;

import juno.game.gameModel.*;
import juno.menu.menuModel.User;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameGUI {

    /*
    * IF THE CONTAINER WILL CONTAIN OTHER CONTAINERS --> USE setSize
    * ELSE USE setPreferredSize.
    * */

    private Game game;

    private JFrame gameFrame;

    private int frameWidth = 1200;

    private int frameHeight = 800;

    public GameGUI(User user, int playersNumber, Difficulty difficulty, String backPath){

        ArrayList<Player> players = createPlayers(user, playersNumber);
        Player userPlayer = players.get(0);

        DrawDeck drawDeck = new DrawDeck(); // the setter is called at construction phase
        addImagePathNamesToCards(drawDeck.getDeck()); // adding pathnames to cards
        drawDeck.setDeck(drawDeck.shuffleCards(drawDeck.getDeck())); // the deck is now shuffled

        game = new Game(new Preparations(new Table(players, drawDeck)));
        game.getTable().setUserPlayer(userPlayer);


        /*
        while("game is running"){
            "run a function which needs to wait until user INPUT is took"
                    "than repeat this cicle until game is not OVER"
        }
         */


        /**START TO REFACTOR*/
        gameFrame = new JFrame();
        gameFrame.setSize(frameWidth,frameHeight);
        JPanel infoPanel = getInfoPanel(userPlayer, user);
        JPanel cardsPanel = getUserCardsPanel(userPlayer);
        JPanel unoPanel = getUnoPanel();
        JPanel middleConta = getCenterDDeckAndGCard(backPath, game.getTable().getGroundCard());
        JPanel gamePanel = getGamePanel(infoPanel, cardsPanel, unoPanel, middleConta);
        JPanel sidePanel = getSidePanel();
        gameFrame.add(gamePanel, BorderLayout.CENTER);
        gameFrame.add(sidePanel, BorderLayout.EAST);


        /**
         * TO remove because the game will only run on full screen. Why would someone play without full screen
         * **/
        gameFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                if(e.getComponent().getWidth() < frameWidth || e.getComponent().getHeight() < frameHeight){
                    gameFrame.setSize(frameWidth, frameHeight);
                }
            }
        });

        //frame.add(sidePanel, BorderLayout.EAST);
        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //gameFrame.setUndecorated(true);
        gameFrame.setVisible(true);
        /**END TO REFACTOR**/

    }

    public ArrayList<Player> createPlayers(User user, int playersNumber){
        // Create a random list of players for the game
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(user.getUsername(), false));
        System.out.println(playersNumber);
        for(int i = 0; i < playersNumber-1; i++){
            // this players will call the Player constructor which will set isBot to true by default
            players.add(new Player(getRandomName()));
        }
        return players;
    }

    public String getRandomName(){
        // Generate a random name from the underneath list
        /**TODO
         * add a list of pathnames for the folder with avatars related to the names
         * **/
        ArrayList<String> names = Stream.of("Steve Jobs", "James Gosling", "Bill Gates", "Mark Zuckerberg", "Lex Fridman", "Alan Turing", "Kurt Godel", "Linus Torvald", "Terry A. Davis","Ludwig Wittgenstein", "J. R. R. Tolkien","Fedor Dostoevskij", "J.K. Rowling", "George R.R. Martin", "Steven Spielberg", "Stanley Kubrick", "Liam Neeson", "Ada Lovelace", "Donald Knuth", "Kevin Mitnick", "George Hotz", "Elliot Alderson", "Tyrion Lannister" , "Walter White","Ezio Auditore", "Natan Drake", "Spiderman", "Harry Potter", "Ron Weasley", "Hermione Granger", "Jordan Peterson", "Karl Marx", "John Nash", "Aaron Swartz", "John Bradshaw", "Andrew Ng", "Friedrich Nietzsche", "Pier Paolo Pasolini", "Elsa Morante", "Alberto Moravia", "Giacomo Leopardi", "Dante Alighieri", "Publio Virgilio Marone", "Lucio Anneo Seneca", "Sigmund Freud", "Carl Jung", "Franz Kafka", "Carmelo Bene", "Rancore", "Caparezza", "Jack Nicholson","Francesco Guccini" ,"Fabrizio De André","Federico Fellini", "Robert De Niro" ,"Jennifer Connelly", "Neo",
                "Merle Robbin").collect(Collectors.toCollection(ArrayList::new));

        ArrayList<String> avatarsPath = Stream.of("steve_jobs.jpeg","james_gosling.png" , "bill_gates.jpeg","mark_zuckerberg.jpeg", "lex_fridman.png", "alan_turing.jpeg", "kurt_godel.jpeg", "linus_torvalds.png", "terry_a_davis.jpeg", "ludwig_wittgenstein.jpeg", "j_r_r_tolkien.jpeg", "fedor_dostoevskij.jpeg", "j_k_rowling.jpeg", "george_r_r_martin.jpg", "steven_spielberg.jpeg", "stanley_kubrick.jpeg", "liam_neeson.jpeg", "ada_lovelace.jpeg", "donald_knuth.jpeg", "kevin_mitnick.jpeg", "george_hotz.jpeg", "elliot_alderson.jpeg", "tyrion_lannister.jpeg", "walter_white.png", "ezio_auditore.png", "natan_drake.png", "spiderman.jpeg", "harry_potter.png", "ron_weasley.jpeg", "hermione_granger.jpeg", "jordan_peterson.jpeg", "karl_marx.jpeg", "john nash.jpeg", "aaron_swartz.jpeg", "john bradshaw.jpeg", "andrew_ng.jpeg", "friedrich_nietzsche.jpeg", "pier_paolo_pasolini.jpeg", "elsa_morante.jpeg", "alberto_moravia.jpeg",  "giacomo_leopardi.jpeg", "dante_alighieri.jpeg",  "virgilio.png", "lucio_anneo_seneca.jpeg",  "sigmund_freud.jpeg", "carl_jung.jpeg",  "franz_kafka.jpeg", "carmelo_bene.jpeg",  "rancore.jpeg", "caparezza.jpeg",  "jack-nicholson.jpeg", "francesco_guccini.png",  "fabrizio_de_andre.jpeg", "federico_fellini.jpeg",  "robert_de_niro.jpeg", "jennifer_connelly.png", "neo.jpeg"
                        , "merle_robbins.jpeg" )
                                .map(name -> "src/BotAvatars/" + name)
                                .collect(Collectors.toCollection(ArrayList::new));

        Random rand = new Random();
        int randIdx = rand.nextInt(names.size());
        return names.remove(randIdx);
    }

    public JFrame getGameGUI(Player userPlayer){
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(1200,800);
        //frame.setResizable(false); ALLOW THIS ONLY FROM SETTINGS
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }


    public JPanel getGamePanel(JPanel infoPanel, JPanel cardsPanel, JPanel unoPanel, JPanel middleConta ){
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setSize(1000,800);
        //frame.setResizable(false); ALLOW THIS ONLY FROM SETTINGS

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.black);
        panel1.setPreferredSize(new Dimension(1000,280));
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.red);
        panel2.setPreferredSize(new Dimension(350,260));
        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBackground(Color.green);
        panel3.setSize(new Dimension(300,260));
        panel3.add(middleConta);
        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.magenta);
        panel4.setPreferredSize(new Dimension(350,260));
        JPanel panel5 = new JPanel(new BorderLayout());
        panel5.setBackground(Color.cyan);
        panel5.setSize(new Dimension(1000,280));
        panel5.add(infoPanel, BorderLayout.WEST);
        panel5.add(cardsPanel, BorderLayout.CENTER);
        panel5.add(unoPanel, BorderLayout.EAST);

        mainPanel.add(panel1, BorderLayout.NORTH);
        mainPanel.add(panel2, BorderLayout.EAST);
        mainPanel.add(panel3, BorderLayout.CENTER);
        mainPanel.add(panel4, BorderLayout.WEST);
        mainPanel.add(panel5, BorderLayout.SOUTH);

        return mainPanel;
    }

    public JPanel getSidePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(200,800);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.yellow);
        panel1.setPreferredSize(new Dimension(200,400));
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.green);
        panel2.setPreferredSize(new Dimension(200,400));

        panel.add(panel1, BorderLayout.NORTH);
        panel.add(panel2, BorderLayout.SOUTH);

        return panel;
    }

    public JPanel getInfoPanel(Player userPlayer, User user){
        ArrayList<Card> hand = userPlayer.getHand();
        String avatarPath = user.getAvatar().getAvatarPath();

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setSize(new Dimension(150,280));
        JPanel avatarPanel = new JPanel(new GridBagLayout());
        avatarPanel.setPreferredSize(new Dimension(150,200));
        avatarPanel.setBackground(new Color(100,100,100));
        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.setPreferredSize(new Dimension(150, 80));
        namePanel.setBackground(new Color(110,110,200));

        JLabel nameLabel = new JLabel(user.getUsername());
        nameLabel.setFont(new Font("Sans Serif", Font.BOLD, 14));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.TOP);
        namePanel.add(nameLabel);

        ImageIcon avatarIcon = new ImageIcon(user.getAvatar().getAvatarPath());
        Image avatarImage = avatarIcon.getImage();
        Image newAvatarImage = avatarImage.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        avatarIcon = new ImageIcon(newAvatarImage);
        JLabel avatarLabel = new JLabel();
        avatarLabel.setIcon(avatarIcon);

        avatarPanel.add(avatarLabel);

        infoPanel.add(avatarPanel, BorderLayout.CENTER);
        infoPanel.add(namePanel, BorderLayout.SOUTH);

        return infoPanel;
    }

    public JPanel getUserCardsPanel(Player player){
        ArrayList<Card> hand = player.getHand();

        JPanel containerOfCards = new JPanel();
        containerOfCards.setLayout(new BorderLayout());
        containerOfCards.setSize(new Dimension(700, 280));
        containerOfCards.setBackground(new Color(200, 100, 29));
        JPanel firstLayerPanel = new JPanel(new FlowLayout());
        firstLayerPanel.setPreferredSize(new Dimension(700, 140));

        JPanel secondLayerPanel = new JPanel(new FlowLayout());
        secondLayerPanel.setPreferredSize(new Dimension(700, 140));
        System.out.println(hand.size());

        if (hand.size() < 8) {
            for (Card card : hand) {
                ImageIcon cardIcon = new ImageIcon(card.getImagePath());
                Image cardImage = cardIcon.getImage();
                Image newCardImage = cardImage.getScaledInstance(65, 120, Image.SCALE_SMOOTH);
                cardIcon = new ImageIcon(newCardImage);
                JButton cardButton = new JButton(cardIcon);
                firstLayerPanel.add(cardButton);
            }
            containerOfCards.add(firstLayerPanel);
        }else{
            for(int i = 0; i < hand.size(); i++){
                Card card = hand.get(i);
                ImageIcon cardIcon = new ImageIcon(card.getImagePath());
                Image cardImage = cardIcon.getImage();
                Image newCardImage = cardImage.getScaledInstance(65, 120, Image.SCALE_SMOOTH);
                cardIcon = new ImageIcon(newCardImage);
                JButton cardButton = new JButton(cardIcon);
                /**TODO
                 * style this as a full screen game, without worrying too much on resizing
                 * **/

                if (i < 8 && gameFrame.getWidth() >= 1200){
                    firstLayerPanel.add(cardButton);
                }else if (i < 9 && (gameFrame.getWidth() >= 1300 && gameFrame.getWidth() < 1400)){
                    firstLayerPanel.add(cardButton);
                }else if(i < 10 && (gameFrame.getWidth() >= 1400 && gameFrame.getWidth() < 1500)){
                    firstLayerPanel.add(cardButton);
                }else{
                    secondLayerPanel.add(cardButton);
                }
            }
            containerOfCards.add(firstLayerPanel, BorderLayout.CENTER);
            containerOfCards.add(secondLayerPanel, BorderLayout.SOUTH);
        }

        return containerOfCards;
    }

    public  JPanel getUnoPanel(){

        JPanel unoPanel = new JPanel(new GridBagLayout());
        unoPanel.setPreferredSize(new Dimension(150, 280));

        JButton unoBtn = new JButton();
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

        return unoPanel;
    }

    public JPanel getCenterDDeckAndGCard(String backCardPath, Card groundCard){

        JPanel containerPanel = new JPanel(new BorderLayout());

        JPanel drawBtnPanel = new JPanel(new GridBagLayout());
        drawBtnPanel.setPreferredSize(new Dimension(300,80));
        drawBtnPanel.setBackground(Color.gray);

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
        for (Card card : deck){
            CardTypes type = card.getType();
            switch (type){
                case CLASSIC -> {
                    String color = card.getColor().toString().toLowerCase();
                    int cardNumber = ((ClassicCard)card).getNumber();
                    String imagePath = "src/cards/" + color + "_" + cardNumber + ".png";
                    card.setImagePath(imagePath);
                }
                case REVERSE -> {
                    String color = card.getColor().toString().toLowerCase();
                    String imagePath = "src/cards/" + color + "_reverse.png";
                    card.setImagePath(imagePath);
                }
                case SKIP -> {
                    String color = card.getColor().toString().toLowerCase();
                    String imagePath = "src/cards/" + color + "_skip.png";
                    card.setImagePath(imagePath);
                }
                case DRAW_TWO -> {
                    String color = card.getColor().toString().toLowerCase();
                    String imagePath = "src/cards/" + color + "_draw2.png";
                    card.setImagePath(imagePath);
                }
                case WILD -> {
                    String imagePath = "src/cards/wild.png";
                    card.setImagePath(imagePath);
                }
                case WILD_DRAW_FOUR -> {
                    String imagePath = "src/cards/wild_draw4.png";
                    card.setImagePath(imagePath);
                }
            }
        }
    }

    public static void main(String[] args) {
        User newUs = new User("Pier Paolo Pasolini", 10, "src/Avatars/me.jpg");
        GameGUI gg = new GameGUI(newUs,10, Difficulty.EASY, "src/cards/yugioh.png");
    }

}
