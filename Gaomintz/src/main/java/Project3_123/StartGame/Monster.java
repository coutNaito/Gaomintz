package Project3_123.StartGame;

import javax.swing.*;
import java.awt.*;

public abstract class Monster extends MoveAble {
    
    private boolean isHit = false;
    private MySoundEffect hitSound;
    public Monster(){}
    public Monster(String n, int x, int y, Project3_123.StartGame.gameManagement gameManagement){
        super(n, x, y, gameManagement);
        setWalk(true);
        setVerticalAlignment(TOP);
        setBoundScale(0.5); // padding_length is 50% of original size
        
        
    }
    
    public void setSpeed_Baby(){
        fps.setFPS(8);
    }
    public void setSpeed_Easy(){
        fps.setFPS(15);
    }
    public void setSpeed_Normal(){
        fps.setFPS(20);
    }
    public void setSpeed_Hard(){
        fps.setFPS(25);
    }
    public void setSpeed_Nightmare(){
        fps.setFPS(30);
    }

    public void setHit(boolean h){
        isHit = h;
    }
    public boolean isHit(){
        return isHit;
    }
 
    
    
}
