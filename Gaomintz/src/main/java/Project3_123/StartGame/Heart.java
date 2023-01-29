package Project3_123.StartGame;

import javax.swing.*;
import java.awt.*;

public class Heart extends JPanel implements GameConfig {

   
    private double width, height;
    private String path = "src/main/java/Project3_123/StartGame/resources/Heart.png";
    private MyImageIcon Obj_Img;
    private int availLife;

    public Heart(int l) {
        availLife = l;
        width = tileSize * 0.5;
        height = tileSize * 0.5;
        
        this.setLayout(new GridLayout(1,availLife));
        this.setBounds(screenWidth - availLife*tileSize, 0, tileSize*availLife, tileSize);
//        this.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        this.setOpaque(false);
        
        generateAllHeart();
    }

    public void reduceLife() {
        
        validate();
        repaint();
    }

    public void setObjectImage(JLabel l, int x) {
        
        l.setHorizontalAlignment(JLabel.CENTER );
        l.setVerticalAlignment(JLabel.CENTER);
        
        Obj_Img = new MyImageIcon(path).resize((int) width, (int) height);
        
        l.setIcon(Obj_Img);
        l.setBounds(x, 0, tileSize, tileSize);
        
        
        this.add(l);
    }

    public void generateAllHeart() {
        JLabel temp;
        int curX;
        
        for (int i = availLife; i > 0; i--) {
            temp = new JLabel();
            curX = screenWidth - (i*tileSize);
            setObjectImage(temp, curX);
        }
    }
}
