package juno.startApp.startAppView;

import juno.menu.menuModel.User;
import juno.menu.menuView.MenuGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ShowAvatars extends  JFrame implements ActionListener{

    /** OBSERVABLE **/

    private ImageIcon selectedAvatar;

    private ArrayList<String> avatarsPathList = new ArrayList<>();

    private String avatarPath;

    private JFrame frame;

    private JPanel titlePanel;

    private JPanel avatarsPanel;

    private JPanel bottomPanel;

    private JButton goBackBtn;

    private JButton confirmBtn;

    private SetData subscriberFrame;


    public ShowAvatars(){

        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setPreferredSize(new Dimension(0,120));

        JLabel title = new JLabel("Scegli un'avatar");
        title.setForeground(new Color(203, 90, 90));
        title.setFont(new Font("Sans Serif", Font.BOLD, 80));

        titlePanel.add(title);

        avatarsPanel = new JPanel(new FlowLayout());

        bottomPanel = new JPanel(new BorderLayout());



        File dir = new File("src/Images/Avatars");
        int numberOfAvatars = 0;
        for (File file: dir.listFiles()){
            if(!file.getPath().equals("src/Images/Avatars/.DS_Store")){
                numberOfAvatars +=1;
                // Create Images
                String filePath = file.getPath();
                avatarsPathList.add(filePath);
                ImageIcon icon = new ImageIcon(filePath);
                Image image = icon.getImage();
                Image newImage = image.getScaledInstance(100,100, Image.SCALE_SMOOTH);
                icon = new ImageIcon(newImage);
                JButton button = new JButton(icon);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedAvatar = (ImageIcon) button.getIcon();
                        avatarPath = file.getPath();
                    }
                });

                avatarsPanel.add(button);
            }
        }

        JScrollPane scrollPane = new JScrollPane(avatarsPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);


        ImageIcon goBackIcon = new ImageIcon("src/Images/MenuIcons/gobackIcon.png");
        Image goBackImage = goBackIcon.getImage();
        Image newGoBackImage = goBackImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        goBackIcon = new ImageIcon(newGoBackImage);
        goBackBtn = new JButton(goBackIcon);
        goBackBtn.addActionListener(this);

        ImageIcon confirmIcon = new ImageIcon("src/Images/MenuIcons/confirmIcon.png");
        Image confirmImage = confirmIcon.getImage();
        Image newConfirmImage = confirmImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        confirmIcon = new ImageIcon(newConfirmImage);
        confirmBtn = new JButton(confirmIcon);
        confirmBtn.addActionListener(this);

        bottomPanel.add(goBackBtn, BorderLayout.WEST);
        bottomPanel.add(confirmBtn,BorderLayout.EAST);

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goBackBtn ){
            frame.dispose();
        }
        else if(e.getSource() == confirmBtn){
            if (avatarPath == null){
                subscriberFrame.update(avatarsPathList.get(0));
            }else{
                subscriberFrame.update(avatarPath);
            }
            frame.dispose();
        }
    }

    public void addSubscriber(SetData setDataFrame){
        subscriberFrame = setDataFrame;
    }
}
