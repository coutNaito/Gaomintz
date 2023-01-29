package Project3_123.GUI;

import Project3_123.MyClass.MySoundCtrl;

import javax.swing.*;
import java.awt.*;

public class startFrame extends JFrame{
    private JFrame currentFrame;
    private JLayeredPane panel;
    private startPane startPane;
    private preGamePane preGamePane;
    private gameOverPane gameOverPane;
    private leaderboardPane leaderboardPane;
    private creditPane creditPane;
    private MySoundCtrl sound;
    private int score = 10;
    private int width = 1176, height = 664;
    public startFrame() {
        sound = new MySoundCtrl();
        //set JFrame
        setTitle("Gaomintz");
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        setVisible(true);
        currentFrame = this;

        //set content pane
        panel = new JLayeredPane();
        panel.setLayout(null);
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(width, height));
        getContentPane().add(panel);

        //add component
        addPane();
        pack();
        setLocationRelativeTo(null);

        sound.playM();
    }
    private void addPane() {
        //create all pane and setting
        startPane = new startPane(this,sound);
        leaderboardPane = new leaderboardPane(sound);
        creditPane = new creditPane(sound);
        gameOverPane = new gameOverPane(sound);
        preGamePane = new preGamePane(this, gameOverPane, sound);
        
        
        //link button
        startPane.getStartButton().linkPane(startPane,preGamePane);
        startPane.getLeaderButton().linkPane(startPane,leaderboardPane);
        startPane.getCreditButton().linkPane(startPane,creditPane);

        leaderboardPane.getMainMenuButton().linkPane(leaderboardPane,startPane);

        creditPane.getBackButton().linkPane(creditPane,startPane);

        preGamePane.getBackButton().linkPane(preGamePane,startPane);
        preGamePane.getOkButton().linkPane(preGamePane,gameOverPane);

        gameOverPane.getConfirmButton().linkPane(gameOverPane,leaderboardPane);

        panel.add(startPane,JLayeredPane.DRAG_LAYER);
        panel.add(leaderboardPane,JLayeredPane.POPUP_LAYER);
        panel.add(creditPane,JLayeredPane.POPUP_LAYER);
        panel.add(preGamePane,JLayeredPane.POPUP_LAYER);
        panel.add(gameOverPane,JLayeredPane.POPUP_LAYER);
    }
    
    

}
