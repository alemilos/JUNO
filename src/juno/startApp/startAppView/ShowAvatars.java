package juno.startApp.startAppView;

import juno.menu.menuModel.User;
import juno.menu.menuView.MenuGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ShowAvatars {

    private ImageIcon selectedAvatar;
    private String avatarPath;

    public ShowAvatars(){
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel avatarsPanel = new JPanel(new FlowLayout());
        JPanel bottomPanel = new JPanel(new BorderLayout());

        File dir = new File("src/Images/Avatars");
        int numberOfAvatars = 0;
        for (File file: dir.listFiles()){
            if(!file.isDirectory() && !file.getPath().equals("src/Images/Avatars/.DS_Store")){
                numberOfAvatars +=1;
                // Create Images
                ImageIcon icon = new ImageIcon(file.getPath());
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
        JScrollPane avatarsScrollPane = new JScrollPane(avatarsPanel,
                                    JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        // GO BACK ICON
        ImageIcon goBackIcon = new ImageIcon("src/Images/MenuIcons/gobackIcon.png");
        Image goBackImage = goBackIcon.getImage();
        Image newGoBackImage = goBackImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        goBackIcon = new ImageIcon(newGoBackImage);
        JButton goBackBtn = new JButton(goBackIcon);

        goBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

            }
        });

        // CONFIRM ICON
        ImageIcon confirmIcon = new ImageIcon("src/Images/MenuIcons/confirmIcon.png");
        Image confirmImage = confirmIcon.getImage();
        Image newConfirmImage = confirmImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        confirmIcon = new ImageIcon(newConfirmImage);
        JButton confirmBtn = new JButton(confirmIcon);

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**** Add avatar
                 *  selection to
                 *  SET DATA***/
                SetData.addAvatarImage(selectedAvatar, avatarPath);
                frame.dispose();

            }
        });



        int avatarsPanelWidth = 500;
        int avatarsPanelHeight = 300;
        avatarsPanel.setSize(avatarsPanelWidth, avatarsPanelHeight);

        bottomPanel.add(goBackBtn, BorderLayout.WEST);
        bottomPanel.add(confirmBtn,BorderLayout.EAST);

        frame.setSize(avatarsPanelWidth, avatarsPanelHeight);

        frame.add(avatarsScrollPane, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
