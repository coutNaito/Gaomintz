package Project3_123.StartGame;

import java.util.ArrayList;

public class Ghost extends Monster implements GameConfig {

    private int range_X = 1;
    private int range_Y = 4;
    private boolean isCCW = false;

    public Ghost(String n, int x, int y, Project3_123.StartGame.gameManagement gameManagement) {

        super(n, x, y, gameManagement);
        setAnimationFrame(10);
        setAvailDirection(new String[]{"u", "d"});
        setWalkAnimation(44, 30, 1.5);
        
        leftImg = upImg;
        rightImg = downImg;
        
        
        moveRight();
        setFPS(10);
        LabelSetting();
        setVerticalAlignment(TOP);

        setMoveThread();
    }

    public Ghost addInList(ArrayList<Monster> al) {
        al.add(this);
        return this;

    }
    public Ghost addFPS(FPS f){
        fps = f;
        return this;
    }
    public Ghost setFirstDirection(String d){
        if(d.equals("u")) moveUp();
        if(d.equals("d")) moveDown();
        if(d.equals("l")) moveLeft();
        if(d.equals("r")) moveRight();
        
        return this;
    }

    public Ghost setRange_X(int x) {
        range_X = x;
        range_Y = 1;
        return this;
    }

    public Ghost setRange_Y(int y) {
        range_Y = y;
        return this;
    }

    public Ghost setRange(int x, int y) {
        range_X = x;
        range_Y = y;
        return this;
    }
    public Ghost setPosition(int pos){
        
        stepLength = stepLength + tileSize*pos;
        return this;
    }
    
    public Ghost setCounterClockwise(){
        isCCW = true;
//        step = step * (-1);
        // Inverse direction
        if(direction.equals("left")) moveUp();
        else if(direction.equals("right")) moveDown();
        else if(direction.equals("up")) moveLeft();
        else if(direction.equals("down")) moveRight();

        int temp_c = curY;
        curY = curX;
        curX = temp_c; 
        
        int temp = range_Y;
        range_Y = range_X;
        range_X = temp;
        
        
        
            
        
        
        
        return this;
    }

    @Override
    public void updateLocation() {
        if(range_Y > 1){
        if (direction.equals("up")) {
            curY -= step;

        } if (direction.equals("down")) {
            curY += step;
        }
        }
        if(range_X > 1){
        if(direction.equals("left")){
            curX -= step;
        }
        if(direction.equals("right")){
            curX += step;
        }
        }
        
        if(!isCCW){
        setLocation(curX, curY);
        objectBounds.setLocation(curX+padding_length, curY+padding_length);
        }
        else {
            setLocation(curY, curX);
            objectBounds.setLocation(curY+padding_length, curX+padding_length);
        }
    }

    public void setMoveThread() {
        moveThread = new Thread() {
            public void run() {

                fps.setInitialTime();

                while (gameManagement.getChangeScene() == false && gameManagement.isCharactorAlive() && !gameManagement.isCharactorWin()) {
                    if (walk) {
                        updateStep();
                        updateLocation();
                        
                        if(Math.abs(stepLength) >= tileSize*range_Y -tileSize && (direction.equals("up") || direction.equals("down"))){
                            
                            if(direction.equals("up")) moveRight();
                            else if(direction.equals("down")) moveLeft();
                            stepLength = 0;
                        }
                        if(Math.abs(stepLength) >= tileSize*range_X - tileSize && (direction.equals("left") || direction.equals("right"))){
                            if(direction.equals("left")) moveUp();
                            else if(direction.equals("right")) moveDown();
                            stepLength = 0;
                        }
                        validate();
                        repaint();
                    }
                    fps.implementFPS();
                }
                setMoveThread();
            }
        ;
    }
;
}
}
