package juno.game.gameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ChooseBackCard extends JFrame implements ActionListener {
    /**
     * OBSERVABLE
     **/

    private ImageIcon selectedCardBack;

    private ArrayList<String> cardsBackList = new ArrayList<>();

    private String cardBackPath;

    private JFrame frame;

    private JPanel titlePanel;

    private JPanel cardsBackPanel;

    private JPanel bottomPanel;

    private JButton goBackBtn;

    private JButton confirmBtn;

    private GameSettingsGUI subscriberFrame;


    public ChooseBackCard() {

        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 450);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setPreferredSize(new Dimension(0, 120));

        JLabel title = new JLabel("Scegli il retro delle carte");
        title.setForeground(new Color(203, 90, 90));
        title.setFont(new Font("Sans Serif", Font.BOLD, 80));

        titlePanel.add(title);

        cardsBackPanel = new JPanel(new FlowLayout());

        bottomPanel = new JPanel(new BorderLayout());


        File dir = new File("src/Images/CardsBack");
        for (File file : dir.listFiles()) {
            if (!file.getPath().equals("src/Images/CardsBack/.DS_Store")) {
                // Create Images
                String filePath = file.getPath();
                cardsBackList.add(filePath);
                ImageIcon icon = new ImageIcon(filePath);
                Image image = icon.getImage();
                Image newImage = image.getScaledInstance(100, 160, Image.SCALE_SMOOTH);
                icon = new ImageIcon(newImage);
                JButton button = new JButton(icon);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedCardBack = (ImageIcon) button.getIcon();
                        cardBackPath = file.getPath();
                    }
                });

                cardsBackPanel.add(button);
            }
        }

        JScrollPane scrollPane = new JScrollPane(cardsBackPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);


        ImageIcon goBackIcon = new ImageIcon("src/Images/MenuIcons/gobackIcon.jpeg");
        Image goBackImage = goBackIcon.getImage();
        Image newGoBackImage = goBackImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        goBackIcon = new ImageIcon(newGoBackImage);
        goBackBtn = new JButton(goBackIcon);
        goBackBtn.addActionListener(this);

        ImageIcon confirmIcon = new ImageIcon("src/Images/MenuIcons/confirmIcon.png");
        Image confirmImage = confirmIcon.getImage();
        Image newConfirmImage = confirmImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        confirmIcon = new ImageIcon(newConfirmImage);
        confirmBtn = new JButton(confirmIcon);
        confirmBtn.addActionListener(this);

        bottomPanel.add(goBackBtn, BorderLayout.WEST);
        bottomPanel.add(confirmBtn, BorderLayout.EAST);

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goBackBtn) {
            frame.dispose();
        } else if (e.getSource() == confirmBtn) {
            if (cardBackPath != null){
                subscriberFrame.update(cardBackPath);
            }
            frame.dispose();
        }
    }

    public void addSubscriber(GameSettingsGUI gameSettingsGUI) {
        this.subscriberFrame = gameSettingsGUI;
    }
}