package juno.game.gameView;

import juno.game.gameModel.*;
import juno.menu.menuModel.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameGUI {

    private Table table;
    private Game game;

    public GameGUI(User user, int playersNumber, Difficulty difficulty){
        ArrayList<String> names = Stream.of("Alessandro M.", "Marcello C.", "Federico D.S.", "Valentina L.P.", "Denis L.", "Edoardo D.F.", "Sara T.", "Chiara M.", "Francesco S.", "Luca G.N.", "Valerio L.", "Steve Jobs", "James Gosling", "Bill Gates", "Mark Zuckerberg", "Lex Fridman", "Alan Turing", "Kurt Godel", "Linus Torvald", "Ludwig Wittgenstein", "Fedor Dostoevskij", "J.K. Rowling", "George R.R. Martin", "Steven Spielberg", "Stanley Kubrick", "Liam Neeson", "Ada Lovelace", "Donald Knuth", "Kevin Mitnick", "George Hotz", "Elliot Alderson", "Ezio Auditore", "Natan Drake", "Peter Parker", "Harry Potter", "Jordan Peterson", "Karl Marx", "Adam Smith", "John Nash", "Steve Wozniak", "Aaron Swartz", "John Bradshaw", "Andrew Ng", "Friedrich Nietzsche", "Pier Paolo Pasolini", "Elsa Morante", "Alberto Moravia", "Giacomo Leopardi", "Dante Alighieri", "Omero", "Publio Virgilio Marone", "Lucio Anneo Seneca", "Sigmund Freud", "Carl Jung", "Franz Kafka", "Carmelo Bene", "Tarek Lurchic", "Michele Salvemini", "Jack Nicholson", "Robert De Niro" ,
                            "Merle Robbin").collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Player> players = createPlayers(user, playersNumber, names);
        Player userPlayer = players.get(0);
        this.game = new Game(new Preparations(new Table(players)));

        /*
        while("game is running"){
            "run a function which needs to wait until user INPUT is took"
                    "than repeat this cicle until game is not OVER"
        }
         */

        JFrame frame = getGameGUI(userPlayer);

    }
    public ArrayList<Player> createPlayers(User user, int playersNumber, ArrayList<String> names){
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(user.getUsername(), false));
        System.out.println(playersNumber);
        for(int i = 0; i < playersNumber-1; i++){
            // this players will call the Player constructor which will set isBot to true by default
            players.add(new Player(getRandomName(names)));
        }
        return players;
    }

    public String getRandomName(ArrayList<String> names){
        Random rand = new Random();
        int randIdx = rand.nextInt(names.size());
        return names.remove(randIdx);
    }

    public JFrame getGameGUI(Player userPlayer){
        JFrame frame = new JFrame();
        frame.setSize(1000,900);
        frame.setLocationRelativeTo(null);

        System.out.println(userPlayer.getHand());
        return frame;
    }
}
