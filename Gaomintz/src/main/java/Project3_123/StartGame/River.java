package Project3_123.StartGame;

import static Project3_123.StartGame.GameConfig.screenWidth;
import static Project3_123.StartGame.GameConfig.tileSize;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import java.util.ArrayList;
import java.util.function.Predicate;

public class River extends Thread implements GameConfig {

    private int curY;
    private String direction = "r";

    private Project3_123.StartGame.gameManagement gameManagement;
    private JPanel contentpane;
    private JLabel drawpane;
    private MovingTile lilypad;
    private ArrayList<BlockingTile> safeTile_AL = new ArrayList<>();
    private ArrayList<WaterTile> waterTile_AL = new ArrayList<>();
    private ArrayList<MovingTile> movingTile_AL = new ArrayList<>();
   

    private FPS f = new FPS(60);
    private int randomDirection = 1;
    private int randomTime_generate = 3;

    public River(int y, String d, gameManagement gm) {

        gameManagement = gm;
        
        
        curY = y;
        direction = d;
        addWaterTile();
        addSafeTile();
        addMovingTile();
    }

    public void addWaterTile() {
        WaterTile water;
        WaterTile dummy = new WaterTile(gameManagement);
        dummy.setTile_Location(-tileSize, screenHeight + tileSize);
        waterTile_AL.add(dummy);

        for (int i = 0; (i * tileSize) <= screenWidth; i = i + 1) {
            water = new WaterTile(gameManagement);
            water.setTile_Location((tileSize * i), (curY-1)*tileSize);
            waterTile_AL.add(water);
        }
    }

    public void addSafeTile() {
        BlockingTile dummy = new BlockingTile(gameManagement);
        dummy.setTile_Location(-tileSize, -tileSize);
        dummy.setBlock(false);
        safeTile_AL.add(dummy);

        BlockingTile safe;
        for (int i = 0; (i * tileSize) <= screenWidth; i = i + 1) {
            safe = new BlockingTile(gameManagement);
            safe.setTile_Location((tileSize * i), (curY-1)*tileSize - tileSize);
            safe.setBlock(false);
            safeTile_AL.add(safe);
        }

        for (int i = 0; (i * tileSize) <= screenWidth; i = i + 1) {
            safe = new BlockingTile(gameManagement);
            safe.setTile_Location((tileSize * i), (curY-1)*tileSize + tileSize);
            safe.setBlock(false);
            safeTile_AL.add(safe);
        }
    }

    public void addMovingTile() {

        MovingTile dummy = new MovingTile();
        movingTile_AL.add(dummy);
        
        

        int space = tileSize / 16;

        lilypad = new MovingTile("lilypad", 1, curY, direction, gameManagement);
        movingTile_AL.add(lilypad);

        lilypad = new MovingTile("lilypad", 3, curY, direction, gameManagement);
        movingTile_AL.add(lilypad);


        lilypad = new MovingTile("lilypad", 7, curY, direction, gameManagement);
        movingTile_AL.add(lilypad);
        

        lilypad = new MovingTile("lilypad", 9, curY, direction, gameManagement).setSpace(space*2);
        movingTile_AL.add(lilypad);
        

        lilypad = new MovingTile("lilypad", 13, curY, direction, gameManagement);
        movingTile_AL.add(lilypad);
        
        
        lilypad = new MovingTile("lilypad", 15, curY, direction, gameManagement).setSpace(space*2);
        movingTile_AL.add(lilypad);

        FPS f = new FPS(10);
        movingTile_AL.stream().forEach(arg -> arg.getFPS(f));
    }

    public void generate_MovingTile() {

        lilypad = new MovingTile("lilypad", 96, 96, direction, gameManagement);
        movingTile_AL.add(lilypad);
        movingTile_AL.stream().forEach(arg -> arg.run());
    }

    public ArrayList<BlockingTile> getSafeTile() {
        return safeTile_AL;
    }

    public ArrayList<WaterTile> getWaterTile() {
        return waterTile_AL;
    }

    public ArrayList<MovingTile> getMovingTile() {
        return movingTile_AL;
    }

    public void run() {
        Predicate<MovingTile> inScreen_lambda = arg -> arg.getMoveThread().isAlive();
//      
        movingTile_AL.stream().forEach(arg -> arg.run());
       FPS f = new FPS(7);
       f.setInitialTime();
       while(gameManagement.isCharactorAlive() && !gameManagement.isCharactorWin()){
           f.implementFPS();
       }
    }

}
