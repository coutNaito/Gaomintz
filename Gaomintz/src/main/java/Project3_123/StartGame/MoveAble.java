package Project3_123.StartGame;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public abstract class MoveAble extends JLabel implements Runnable, GameConfig {

    private String name;
    protected int curX, curY;
    protected int width, height;
    protected int availImg_col = 6;
    private String[] availImg = {"u", "d", "l", "r"};
    protected MyImageIcon[] upImg, downImg, leftImg, rightImg;
    protected MyImageIcon[] idle_upImg, idle_downImg, idle_leftImg, idle_rightImg;
    protected MyImageIcon[] deadImg;

    private CharactorImage walkImg, idleImg, dead;
    protected int step = 1, blockStep, stepCount = 0, stepLength = 0;

    protected String direction = "stop";
    protected boolean walk = false, isFinishWalk;
    protected boolean isAlive = true;

    protected Project3_123.StartGame.gameManagement gameManagement;

    protected int frame_per_sec = 40, Walk_frame_per_sec = 25, Idle_frame_per_sec = 15;
    protected FPS fps = new FPS(frame_per_sec);
    protected FPS walk_fps = new FPS(Walk_frame_per_sec),
            idle_fps = new FPS(Idle_frame_per_sec);

    protected Thread moveThread, walkThread, idleThread, flowThread;
    
    protected BlockingTile objectBounds;
    protected double boundsScale = 1.0;
    protected int padding_length = 0;
    public MoveAble() {
    }

    public MoveAble(String n, int x, int y, Project3_123.StartGame.gameManagement gameManagement) {
        name = n;
        curX = (x-1)*tileSize;
        curY = (y-1)*tileSize;
        this.gameManagement = gameManagement;
        width = tileSize;
        height = tileSize;
        blockStep = tileSize;
        step = tileSize / 8;  //8 -> tilesSize = 64

    }

    public void setWalk(boolean w) {
        walk = w;
        stepLength = 0;
        stepCount = 0;
        isFinishWalk = !w;
    }

    public boolean isWalk() {
        return walk;
    }
    public void setLocation_OnMap(int x, int y){
        curX = (x-1)*tileSize;
        curY = (y-1)*tileSize;
        setLocation(curX, curY);
    }

    public void setAnimationFrame(int a_col) {
        availImg_col = a_col;
    }

    public void setAvailDirection(String[] a_img) {
        availImg = a_img;
    }

    public void setFPS(int f) {
        fps.setFPS(f);
    }
    public void addFps(FPS f) {
        fps = f;
    }

    public void setWalkFps(int f) {
        walk_fps.setFPS(f);
    }
    public void addWalkFps(FPS f) {
        walk_fps = f;
    }

    public void setIdleFps(int f) {
        idle_fps.setFPS(f);
    }
    public void addIdleFps(FPS f) {
        idle_fps = f;
    }
    
    public void setSpeed_Baby(){
        fps.setFPS(60);
        walk_fps.setFPS(35);
    }
    public void setSpeed_Easy(){
        fps.setFPS(65);
        walk_fps.setFPS(45);
    }
    public void setSpeed_Normal(){
        fps.setFPS(70);
        walk_fps.setFPS(55);
    }
    public void setSpeed_Hard(){
        fps.setFPS(75);
        walk_fps.setFPS(65);
    }
    public void setSpeed_Nightmare(){
        fps.setFPS(80);
        walk_fps.setFPS(75);
    }

    public void LabelSetting() {

        setBounds(curX, curY, width, height);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
    }
    public void setBoundScale(double b){
        boundsScale = b;
        padding_length = (int)((tileSize -(tileSize*boundsScale)) * 0.5);
        objectBounds = new BlockingTile(gameManagement);

        objectBounds.setTile_Location(curX+padding_length, curY+padding_length);
        objectBounds.setWidth_Height_Block(boundsScale, boundsScale);
        objectBounds.setBlock(false);
    }
    public BlockingTile getObjectTile(){
        return objectBounds;
    }
    public Rectangle getObjectBounds(){
        return objectBounds.getBounds();
    }

    public void moveUp() {
        direction = "up";
    }

    public void moveDown() {
        direction = "down";

    }
    public void moveLeft() {
        direction = "left";
    }
    public void moveRight() {
        direction = "right";
    }
    public void updateFrame() {
        stepCount += 1;
        if (stepCount > availImg_col) {
            stepCount = 0;
        }
    }

    public void updateStep() {
        stepLength += step;
    }

    public void updateLocation() {
    }

    public int get_curX() {
        return curX;
    }

    public int get_curY() {
        return curY;
    }

    public void setAnimation(MyImageIcon[] img, int update) {
        setIcon(img[(int) (update) % availImg_col]);
    }

    public void drawWalkAnimation() {

        if (direction.equals("left")) {
            setAnimation(leftImg, stepCount);
        }
        if (direction.equals("right")) {
            setAnimation(rightImg, stepCount);
        }
        if (direction.equals("up")) {
            setAnimation(upImg, stepCount);

        }
        if (direction.equals("down")) {
            setAnimation(downImg, stepCount);
        }
    }

    public void drawIdleAnimation() {

        if (direction.equals("stop")) {
            setAnimation(idle_upImg, stepCount);
        }
        if (direction.equals("left")) {
            setAnimation(idle_leftImg, stepCount);
        }
        if (direction.equals("right")) {
            setAnimation(idle_rightImg, stepCount);
        }
        if (direction.equals("up")) {
            setAnimation(idle_upImg, stepCount);
        }
        if (direction.equals("down")) {
            setAnimation(idle_downImg, stepCount);
        }

    }

    public void setWalkAnimation(int size_r, int size_col) {

        walkImg = new CharactorImage(name, availImg, true, availImg_col, new int[]{size_r, size_col}, 1.0);
        setAllWalkImg(walkImg);
    }

    public void setWalkAnimation(int size_r, int size_col, double sc) {

        walkImg = new CharactorImage(name, availImg, true, availImg_col, new int[]{size_r, size_col}, sc);
        setAllWalkImg(walkImg);
    }

    public void setIdleAnimation(int size_r, int size_col) {
        idleImg = new CharactorImage(name, availImg, false, availImg_col, new int[]{size_r, size_col}, 1.0);
        setAllIdleImg(idleImg);
    }

    public void setIdleAnimation(int size_r, int size_col, double sc) {
        idleImg = new CharactorImage(name, availImg, false, availImg_col, new int[]{size_r, size_col}, sc);
        setAllIdleImg(idleImg);
    }

    public void setDeadAnimation(double sc) {
        dead = new CharactorImage(sc);
        deadImg = dead.getAllImg()[0];
    }

    public void playDeadAnimation() {
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        
        setIcon(null);
        
        FPS f = new FPS(7);
        for (MyImageIcon i : deadImg) {
            setIcon(i);

            validate();
            repaint();
            f.implementFPS();
        }
        setIcon(null);
        validate();
        repaint();
    }

    public void setAllWalkImg(CharactorImage charImg) {
        for (String i : availImg) {
            switch (i) {
                case ("u"):
                    upImg = charImg.getAllImg()[Arrays.asList(availImg).indexOf(i)];
                    break;
                case ("d"):
                    downImg = charImg.getAllImg()[Arrays.asList(availImg).indexOf(i)];
                    break;
                case ("l"):
                    leftImg = charImg.getAllImg()[Arrays.asList(availImg).indexOf(i)];
                    break;
                case ("r"):
                    rightImg = charImg.getAllImg()[Arrays.asList(availImg).indexOf(i)];
                    break;
                default:
                    break;

            }
        }
    }

    public void setAllIdleImg(CharactorImage charImg) {

        for (String i : availImg) {
            switch (i) {
                case ("u"):
                    idle_upImg = charImg.getAllImg()[Arrays.asList(availImg).indexOf(i)];
                    break;
                case ("d"):
                    idle_downImg = charImg.getAllImg()[Arrays.asList(availImg).indexOf(i)];
                    break;
                case ("l"):
                    idle_leftImg = charImg.getAllImg()[Arrays.asList(availImg).indexOf(i)];
                    break;
                case ("r"):
                    idle_rightImg = charImg.getAllImg()[Arrays.asList(availImg).indexOf(i)];
                    break;
                default:
                    break;

            }
        }

    }
    //Present 1
    @Override
    public void run() {

        if (moveThread != null) {
            moveThread.start();
        }
        if (flowThread != null) {
            flowThread.start();
        }

        if (walkImg != null) {
            walkThread = new Thread() {
                public void run() {
                    walk_fps.setInitialTime();
                    while (isAlive && !gameManagement.getChangeScene() && gameManagement.isCharactorAlive() && !gameManagement.isCharactorWin()) {
                        if (walk) {
                            updateFrame();
                            drawWalkAnimation();

                            validate();
                            repaint();

                        }
                        walk_fps.implementFPS();
                    }
                    
                    
                }
            ;
            };
        walkThread.start();
        }
        if (idleImg != null) {
            idleThread = new Thread() {
                public void run() {
                    idle_fps.setInitialTime();
                    while (isAlive && !gameManagement.getChangeScene() && gameManagement.isCharactorAlive() && !gameManagement.isCharactorWin()) {
                        if (!walk) {
                            updateFrame();
                            drawIdleAnimation();

                            validate();
                            repaint();

                        }
                        idle_fps.implementFPS();
                    }
                    
                    
                }
            ;
            };
        idleThread.start();
        }

    }

    public void getFlowThread(Thread t) {
        flowThread = t;
    }

};
