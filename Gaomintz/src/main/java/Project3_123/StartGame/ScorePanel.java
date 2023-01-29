
package Project3_123.StartGame;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel implements GameConfig {
    
    private int currentScore = 0;
    private JLabel scoreLabel;
    private int fontSize = tileSize/2;
    
    public ScorePanel(){
        this.setLayout(new BorderLayout());
        this.setBounds(0,0, tileSize * 5, tileSize);
        this.setOpaque(false);
        
        drawScoreLabel();
    }
    
    public void drawScoreLabel(){
        
        scoreLabel = new JLabel("  Score: "+Integer.toString(currentScore));
        scoreLabel.setVerticalAlignment(JLabel.CENTER);
        scoreLabel.setHorizontalAlignment(JLabel.LEFT);
        scoreLabel.setFont(new Font("Ariel", Font.BOLD, fontSize));
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setSize(tileSize*3, tileSize);
        this.add(scoreLabel);
        
    }
    public void updateScore(int newScore){
        currentScore = newScore;
        scoreLabel.setText("  Score: "+Integer.toString(currentScore));
        
        validate();
        repaint();
    }
    
    
}
