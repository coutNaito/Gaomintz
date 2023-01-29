package Project3_123.StartGame;

import Project3_123.StartGame.Tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class BackgroundPannel extends JPanel {

    TileManager tileM;
    String scene;
    private gameManagement gm;

    public BackgroundPannel(gameManagement gm, String scene){
        setDefaultValue(gm,scene);
        startDrawMap();
    }
    private void setDefaultValue(gameManagement gm, String scene){
        this.gm = gm;
        this.scene = scene;
        setPreferredSize(new Dimension(gm.getScreenWidth(), gm.getScreenHeight()));
        setOpaque(true);
        setBackground(Color.YELLOW);
        setBounds(0,0,gm.getScreenWidth(),gm.getScreenHeight());
        setDoubleBuffered(true);
        setFocusable(true);
        setVisible(true);
        setLayout(null);

    }
    public void startDrawMap(){
        tileM = new TileManager(gm,scene);
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
    }

}
