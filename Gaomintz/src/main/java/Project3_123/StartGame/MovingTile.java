package Project3_123.StartGame;

import javax.swing.*;
import java.awt.*;

public class MovingTile extends StaticObject {

    private int step;
    private String direction = "r";
    private boolean outScreen = false;
    private boolean isDummy = false;
    private boolean isTop = false;
    private boolean isDisabled = false;
    private FPS f = new FPS(7);
    private Thread moveThread;
    private CharactorLabel charactor;
    
    public MovingTile(){
        isDummy = true;
    }

    public MovingTile(String n, int x, int y, String d, Project3_123.StartGame.gameManagement gm) {
        super(n, x, y, gm);
        setFileSize(16, 17);
        setFileScale(0.9);
        this.setSize_of_Block(1, 1);
        this.setVerticalAlignment(JLabel.BOTTOM);
        direction = d;
        step = tileSize / 24;
        if(direction.equals("r")) {
            step = step * 1;
        }
        else if(direction.equals("l")) {
            step = step * -1;
        }

        BlockingTile block = new BlockingTile(gm);

        block.setTile_Location(curX, curY);
        block.setWidth_Height_Block(1, 1);
        block.setBlock(false);
        setBlockingTile(block);
    }
    public MovingTile setSpace(int x){
        curX = curX +x;
        setLocation(curX, curY);
        return this;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setIsTop(boolean t) {
        isTop = t;
    }

    public void setIsDisabled(boolean d) {
        isDisabled = d;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDirection(String d) {
        direction = d;
        if(direction.equals("r")) step = step * 1;
        else if(direction.equals("l")) step = step * -1;
    }

    public void updateLocation() {
        curX += step;
        
        if(direction.equals("r")){
            if (curX > gameManagement.getScreenWidth()) {
                curX = (int) -width;
            }
        }
        else{
            if (curX + width < 0) {
            curX = screenWidth + (int)width;
            }
        }
        setLocation(curX, curY);
    }

    public void setCharactor(CharactorLabel c) {
        charactor = c;

    }
    public void setSpeed_Baby(){
        f.setFPS(7);
    }
    public void setSpeed_Easy(){
        f.setFPS(12);
    }
    public void setSpeed_Normal(){
        f.setFPS(17);
    }
    public void setSpeed_Hard(){
        f.setFPS(22);
    }
    public void setSpeed_Nightmare(){
        f.setFPS(27);
    }

    public int getTile_CurX() {
        return curX;
    }

    public int getTile_CurY() {
        return curY;
    }

    public void run() {

        moveThread = new Thread() {
            public void run() {
                f.setInitialTime();
                while (!outScreen && !isDummy && gameManagement.isCharactorAlive() && !gameManagement.isCharactorWin()) {

                    updateLocation();
                    if (isTop && charactor.isFlow() && !charactor.isWalk()) {
                        charactor.resetLocation(curX, curY);
                    } else {
                        charactor = null;
                    }
                    validate();
                    repaint();
                    f.implementFPS();
                }
            }
        };
        moveThread.start();

    }
    public void getFPS(FPS fps){
        f = fps;
    }
    
    public Thread getMoveThread(){
        return moveThread;
    }
}
