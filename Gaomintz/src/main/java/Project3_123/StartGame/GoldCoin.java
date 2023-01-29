
package Project3_123.StartGame;

import static Project3_123.StartGame.GameConfig.tileSize;
import java.awt.Color;
import static java.lang.Thread.currentThread;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

public class GoldCoin extends MoveAble {
    
    private MySoundEffect coinSound;
    private boolean isGetCoin = false;
    private int coinScene = 0;
    private Thread coinThread;
    private FPS coin_fps;
    
            
    public GoldCoin(String n, int x, int y, Project3_123.StartGame.gameManagement gameManagement){
        super(n, x, y, gameManagement);
        setAnimationFrame(4);
        setAvailDirection(new String[]{"u"});
        setIdleAnimation(10, 10, 0.5);
        setIdleFps(5);
        LabelSetting();
        setBoundScale(0.4);
        coinScene = gameManagement.getCurrentScene();
    }
    
    
    public void setGetCoin(boolean g){
        isGetCoin = g;
    }
    public boolean isGetCoin(){
        return isGetCoin;
    }
    
    public GoldCoin setFps(int f) {
        idle_fps.setFPS(f);
        return this;
    }
    public GoldCoin addInList(ArrayList<GoldCoin> l){
        l.add(this);
        return this;
    }
    
    public int getCoinScene(){
        return coinScene;
    }
    
    
    
    
    public void setIdleThread(){
        idleThread = new Thread("coin") {
                public void run() {
                    idle_fps.setInitialTime();
                    while (isAlive && gameManagement.isCharactorAlive() && !isGetCoin() && !gameManagement.isCharactorWin()) {
                        if (!walk) {
                            updateFrame();
                            drawIdleAnimation();

                            validate();
                            repaint();
                        }
                        idle_fps.implementFPS();
                    }
                }
            };
        
    }
    
    @Override
    public void run(){
        setIdleThread();
        idleThread.start();


    }

}
