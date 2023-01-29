package Project3_123.StartGame;

class CharactorLabel extends MoveAble {

//    private String direction = "stop";
    private int last_curX, last_curY;
    private int buff_curX = 0, buff_curY = 0;
    private int coinSum = 0;

    private boolean isBlocked = false;
    private boolean isFlow = false;
    private boolean isDrown = false;
    private boolean isInFence = false;
    private boolean isWin = false;
    private String blockingDirection = "no";

    private Project3_123.StartGame.gameManagement gm;

    public CharactorLabel(String n, int x, int y, Project3_123.StartGame.gameManagement gameManagement) {
        super(n, x, y, gameManagement);
        gm = gameManagement;
        setAnimationFrame(6);
        //setAvailDirection(new String[]{"u", "d", "l", "r"});      //Has default value on moveable lebel
        setWalkAnimation(16, 32, 0.9);
        setIdleAnimation(16, 32, 0.9);
        setDeadAnimation(0.7);

//        setFPS(30);
//        setWalkFps(25);
//        setIdleFps(15);
        LabelSetting();
        setVerticalAlignment(TOP);
        setBoundScale(0.5);
        setMoveThread();

    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean a) {
        isAlive = a;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean b) {
        isBlocked = b;
    }

    public String getBlockingDirection() {
        return blockingDirection;
    }

    public boolean isFlow() {
        return isFlow;
    }

    public void setIsFlow(boolean f) {
        isFlow = f;
    }
    public boolean isWin() {
        if(curX == 0 && curY == 0 && gameManagement.getCurrentScene() == 3){
            isWin = true;
        }
        else isWin = false;
        return isWin;
    }

    public void setIsWin(boolean w) {
        isWin = w;
    }

    public boolean isDrown() {
        return isDrown;
    }

    public void setIsDrown(boolean f) {
        isDrown = f;
    }

    public void resetLocation() {

        setLocation(last_curX, last_curY);
        curX = last_curX;
        curY = last_curY;

    }

    public void resetLocation(int x, int y) {
        setWalk(false);
        last_curX = x;
        last_curY = y;
        curX = x;
        curY = y;
        setLocation(curX, curY);

    }

    public void getTileLocation(int x, int y) {
        buff_curX = x;
        buff_curY = y;
        curX = buff_curX;
        curY = buff_curY;
    }

    public void updateCoinSum() {
        coinSum++;
    }

    public int getCoinSum() {
        return coinSum;
    }

    @Override
    public void updateLocation() {
        if (isAlive) {

            if (direction.equals("left")) {
//                updateStep();
                curX -= step;
                if (curX < 0) {
                    curX = 0;
                }
            }
            if (direction.equals("right")) {
//                updateStep();
                curX += step;
                if (curX + width > screenWidth) {
                    curX = screenWidth - width;
                }
            }
            if (direction.equals("up")) {
//                updateStep();
                curY -= step;
                if (curY < 0) {
                    if (gm.getCurrentScene() < 3) {
                        gm.setCurrentScene(gm.getCurrentScene() + 1);
                        setWalk(false);
                        curY = screenHeight - tileSize;
                        last_curY = screenHeight - tileSize;

                    }
                    else{
                        curY = 0;
                        last_curY = 0;
                    }
                }
            }
            if (direction.equals("down")) {
//                updateStep();
                curY += step;
                if (curY + height > screenHeight) {
                    if (gm.getCurrentScene() > 1) {
                        gm.setCurrentScene(gm.getCurrentScene() - 1);
                        setWalk(false);
                        curY = 0;
                        last_curY = 0;
                    }
                    else{
                        curY = screenHeight - tileSize;
                        last_curY = screenHeight - tileSize;
                    }
                }
            }
            setLocation(curX, curY);
            objectBounds.setLocation(curX+padding_length, curY+padding_length);
        }
    }

    public void setMoveThread() {

        moveThread = new Thread() {
            public void run() {
                Thread.currentThread().setName("moveThread");
                fps.setInitialTime();

                while (isAlive() && gm.isCharactorAlive() && !gm.isCharactorWin()) {
                    if (walk) {
                        if (stepLength >= blockStep) {
                            setWalk(false);
                            last_curX = curX;
                            last_curY = curY;
                            continue;
                        }
                        updateStep();

                        if (isBlocked) {
                            blockingDirection = direction;
                            resetLocation();
                        } else {
                            blockingDirection = "no";
                            updateLocation();

                        }
                        validate();
                        repaint();

                    }

                    fps.implementFPS();
                }
            }
        ;
    }

    ;
}

    
    
@Override
    public void run() {

        if (moveThread != null) {
            moveThread.start();
        }
        if (flowThread != null) {
            flowThread.start();
        }

        walkThread = new Thread() {
            public void run() {
                walk_fps.setInitialTime();
                while (isAlive  && gameManagement.isCharactorAlive() && !gm.isCharactorWin()) {
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

        idleThread = new Thread() {
            public void run() {
                idle_fps.setInitialTime();
                while (isAlive  && gameManagement.isCharactorAlive() && !gm.isCharactorWin()) {
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
