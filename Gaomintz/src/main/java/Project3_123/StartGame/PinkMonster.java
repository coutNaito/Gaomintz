package Project3_123.StartGame;

import java.util.ArrayList;

public class PinkMonster extends Monster {
    public PinkMonster(String n, int x, int y, Project3_123.StartGame.gameManagement gameManagement){
        
        super(n, x, y, gameManagement);
        setAnimationFrame(6);
        setAvailDirection(new String[]{"r"});
        setWalkAnimation(32, 32, 0.9);
        moveRight();
        setFPS(10);
        LabelSetting();
        setVerticalAlignment(TOP);
        setMoveThread();
    }
    public PinkMonster addInList(ArrayList<Monster> al){
        al.add(this);
        return this;
        
    }
    public PinkMonster addFPS(FPS f){
        fps = f;
        return this;
    }
    
    @Override
    public void updateLocation(){
        curX += step;
        
        if (curX > gameManagement.getScreenWidth()){
            curX = -width;
        }
        
        setLocation(curX, curY);
        objectBounds.setLocation(curX+padding_length, curY+padding_length);
    }
    //Present
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
    
    
    
};
