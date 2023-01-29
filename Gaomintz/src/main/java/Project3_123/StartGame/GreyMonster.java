
package Project3_123.StartGame;

import java.util.ArrayList;


public class GreyMonster extends Monster {
    
    public GreyMonster(){}
    
    public GreyMonster(String n, int x, int y, Project3_123.StartGame.gameManagement gameManagement){
        
        super(n, x, y, gameManagement);
        setAnimationFrame(6);
        setAvailDirection(new String[]{"l"});
        setWalkAnimation(32, 32);
        moveLeft();
        setFPS(7);
        LabelSetting();
        
        setMoveThread();
        
    }
    public GreyMonster addInList(ArrayList<Monster> al){
        al.add(this);
        return this;
        
    }
    public GreyMonster addFPS(FPS f){
        fps = f;
        return this;
    }
    @Override
    public void updateLocation(){
        setAnimation(leftImg, stepCount);
        curX -= step;
        
        if (curX + width < 0){
            curX = gameManagement.getScreenWidth();
        }
        
        setLocation(curX, curY);
        objectBounds.setLocation(curX+padding_length, curY+padding_length);
    }
    
    public void setMoveThread(){
        moveThread = new Thread(){
            public void run(){
                
                fps.setInitialTime();
                
                while (gameManagement.getChangeScene() == false && gameManagement.isCharactorAlive() && !gameManagement.isCharactorWin()) {
                    if(walk){

                        updateLocation();

                        validate();
                        repaint();
                    
                    }
                    fps.implementFPS();
                }
                setMoveThread();
            };
        };
    }
    
}
