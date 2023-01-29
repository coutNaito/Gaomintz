package Project3_123.StartGame;

import Project3_123.GUI.gameOverPane;
import Project3_123.MyClass.MySoundCtrl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.function.Predicate;

public class MainFrame extends JFrame implements GameConfig {

    private JFrame jFrame;
    private JPanel contentpane;
    private gameOverPane gameOverPane;

    private JLabel drawpane;

    private CharactorLabel charactor;
    private GoldCoin coin;
    private Heart heart;
    private ScorePanel scorePanel;
    private MySoundCtrl sound;

    // List of Objects
    private ArrayList<Monster> monster_AL = new ArrayList<>();
    private ArrayList<StaticObject> staticObject = new ArrayList<>();
    private ArrayList<Fence> fence_AL = new ArrayList<>();
    private ArrayList<GoldCoin> goldCoins_AL = new ArrayList<>();
    private ArrayList<BlockingTile> safeTile_AL = new ArrayList<>();
    private ArrayList<WaterTile> waterTile_AL = new ArrayList<>();
    private ArrayList<MovingTile> movingTile_AL = new ArrayList<>();


    private BlockingTile blocking;
    private BlockingTile safe_;
    private MovingTile lilypad_;
    private WaterTile water_;
    private MainFrame currentFrame;
    private gameManagement gameManagement;
    private int frameWidth, frameHeight;
    private String difficulty, mainCharacter;
    private int availLife = 3;
    private int currentScore = 0;
    private double scoreScale = 1.0;

    public MainFrame(JFrame jFrame, gameOverPane gameOverPane, String difficulty, String mainCharacter, MySoundCtrl sound) {
        this.jFrame = jFrame;
        this.gameOverPane = gameOverPane;
        this.difficulty = difficulty;
        this.mainCharacter = mainCharacter;
        this.sound = sound;
        System.out.println("============================");
        System.out.println("difficulty: " + difficulty);
        System.out.println("mainCharacter: " + mainCharacter);
        System.out.println("============================");
        setTitle("Gaomintz|In game");

        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        currentFrame = this;
        gameManagement = new gameManagement(mainCharacter);
        frameWidth = gameManagement.getScreenWidth();
        frameHeight = gameManagement.getScreenHeight();
        Dimension d = new Dimension(frameWidth, frameHeight);
        contentpane = new JPanel(new BorderLayout());
        contentpane.setPreferredSize(d);
        getContentPane().add(contentpane);
        pack();
        setLocationRelativeTo(null);

        heart = new Heart(availLife);

        // ScorePanel
        scorePanel = new ScorePanel();
        charactor = gameManagement.getCharData();
        charactor.run();

        // scoreScore
        setScoreScale_byDifficulty();
        AddKeyListener();
        FPS mainFrame_fps = new FPS(10);
        setVisible(true);
        while (gameManagement.isCharactorAlive() == true && !gameManagement.isCharactorWin()) {
            switch (gameManagement.getCurrentScene()) {
                case 1 -> {
                    System.out.println("Loading Scene1");
                    AddComponents1();
                    this.pack();
                }
                case 2 -> {
                    System.out.println("Loading Scene2");
                    AddComponents2();
                    this.pack();
                    gameManagement.setChangeScene_False();
                }
                case 3 -> {
                    AddComponents3();
                    this.pack();
                    System.out.println("Loading Scene3");
                }
            }
            mainFrame_fps.setInitialTime();
            setVisible(true);
            while (!gameManagement.getChangeScene() && gameManagement.isCharactorAlive() && !gameManagement.isCharactorWin()) {
                mainFrame_fps.implementFPS();
            }
            if (!gameManagement.getCharData().isAlive()) {
                gameManagement.getCharData().playDeadAnimation();
            }

            contentpane.removeAll();
            validate();
            repaint();
            gameManagement.setChangeScene_False();
        }

        dispose();
        jFrame.setVisible(true);
        if(!gameManagement.isCharactorWin()) {
            gameOverPane.addBgImage("Over.png");
            currentScore *= 0.5;
        } else { gameOverPane.addBgImage("Victory.png"); }

        gameOverPane.setScore(currentScore);
    }

    public void setScoreScale_byDifficulty() {
        switch (difficulty) {
            case ("Baby"):
                scoreScale = 1.0;
                break;
            case ("Easy"):
                scoreScale = 1.5;
                break;
            case ("Normal"):
                scoreScale = 2.0;
                break;
            case ("Hard"):
                scoreScale = 2.5;
                break;
            case ("Nightmare"):
                scoreScale = 3.0;
                break;
            default:
                break;
        }
    }

    //Present1
    public void AddKeyListener() {
        currentFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!charactor.isWalk() || charactor.isFlow()) {
                    switch (e.getKeyCode()) {

                        case KeyEvent.VK_UP:
                        case KeyEvent.VK_W:
                            charactor.setWalk(true);
                            charactor.moveUp();
                            charactor.setIsBlocked(false);
                            charactor.setIsFlow(false);
                            break;

                        case KeyEvent.VK_DOWN:
                        case KeyEvent.VK_S:
                            charactor.setWalk(true);
                            charactor.moveDown();
                            charactor.setIsBlocked(false);
                            charactor.setIsFlow(false);
                            break;

                        case KeyEvent.VK_LEFT:
                        case KeyEvent.VK_A:
                            charactor.setWalk(true);
                            charactor.moveLeft();
                            charactor.setIsBlocked(false);
                            charactor.setIsFlow(false);

                            break;
                        case KeyEvent.VK_RIGHT:
                        case KeyEvent.VK_D:
                            charactor.setWalk(true);
                            charactor.moveRight();
                            charactor.setIsBlocked(false);
                            charactor.setIsFlow(false);
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
    }

    );
    }


    public void AddComponents1() {

        staticObject = new ArrayList<>();
        fence_AL = new ArrayList<>();
        monster_AL = new ArrayList<>();

        drawpane = new JLabel();
        drawpane.setLayout(null);

        FPS mons_fps = new FPS(10);
        new PinkMonster("Pink", 1, 9, gameManagement).addInList(monster_AL);  //name, curX, curY, gamePanel
        new PinkMonster("Pink", 12, 9, gameManagement).addInList(monster_AL);

        FPS ghost1 = new FPS(7);
        new Ghost("Ghost", 12, 6, gameManagement).setRange(7, 3).addInList(monster_AL).addFPS(ghost1);

        new Ghost("Ghost", 18, 8, gameManagement).setRange(7, 3).setFirstDirection("l").addInList(monster_AL);
        new Ghost("Ghost", 15, 8, gameManagement).setRange(7, 3).setFirstDirection("l").setPosition(3).addInList(monster_AL);
        new Ghost("Ghost", 12, 8, gameManagement).setRange(7, 3).setFirstDirection("u").addInList(monster_AL);
        new Ghost("Ghost", 18, 6, gameManagement).setRange(7, 3).setFirstDirection("d").addInList(monster_AL);

        new Ghost("Ghost", 1, 2, gameManagement).setRange(6, 4).setFirstDirection("d").setCounterClockwise().addInList(monster_AL);
        new Ghost("Ghost", 6, 2, gameManagement).setRange(6, 4).setFirstDirection("l").setCounterClockwise().addInList(monster_AL);
        new Ghost("Ghost", 4, 2, gameManagement).setRange(6, 4).setFirstDirection("l").setPosition(2).setCounterClockwise().addInList(monster_AL);
        new Ghost("Ghost", 6, 5, gameManagement).setRange(6, 4).setFirstDirection("u").setCounterClockwise().addInList(monster_AL);
        new Ghost("Ghost", 1, 5, gameManagement).setRange(6, 4).setFirstDirection("r").setCounterClockwise().addInList(monster_AL);

        new GreyMonster("Grey", 8, 2, gameManagement).addInList(monster_AL);
        new GreyMonster("Grey", 18, 2, gameManagement).addInList(monster_AL);

        BackgroundPannel BG = new BackgroundPannel(gameManagement, "scene1");

        //Static object setting//////
        new Tree("tree", 12, 9, gameManagement).addInList(staticObject);
        new Tree("tree", 7, 9, gameManagement).addInList(staticObject);
        new Tree("tree", 1, 5, gameManagement).addInList(staticObject);
        new Tree("tree", 2, 5, gameManagement).addInList(staticObject);
        new Tree("tree", 3, 5, gameManagement).addInList(staticObject);
        new Tree("tree", 4, 5, gameManagement).addInList(staticObject);
        new Tree("tree", 7, 3, gameManagement).addInList(staticObject);
        new Tree("tree", 7, 2, gameManagement).addInList(staticObject);

        new Bush("bush", 13, 5, gameManagement).addInList(staticObject);
        new Bush("bush", 14, 5, gameManagement).addInList(staticObject);
        new Bush("bush", 15, 5, gameManagement).addInList(staticObject);
        new Bush("bush", 16, 5, gameManagement).addInList(staticObject);
        new Bush("bush", 17, 5, gameManagement).addInList(staticObject);
        new Bush("bush", 16, 7, gameManagement).addInList(staticObject);
        new Bush("bush", 10, 3, gameManagement).addInList(staticObject);
        new Bush("bush", 10, 4, gameManagement).addInList(staticObject);

        //Group 1;
        new Fence("Fence", "d", 8, 8, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 9, 8, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 10, 8, gameManagement).addInList(fence_AL);

        //Group2
        new Fence("Fence", "d", 13, 7, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 14, 7, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 15, 7, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 16, 7, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 17, 7, gameManagement).addInList(fence_AL);
        new Fence("Fence", "l", 13, 7, gameManagement).addInList(fence_AL);
        new Fence("Fence", "r", 17, 7, gameManagement).addInList(fence_AL);

        //Group 3
        new Fence("Fence", "d", 2, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 3, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 4, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 5, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 7, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 8, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 9, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 10, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "l", 7, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "l", 7, 3, gameManagement).addInList(fence_AL);

        new Fence("Fence", "d", 14, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 15, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 16, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 17, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 18, 4, gameManagement).addInList(fence_AL);

        new Fence("Fence", "d", 2, 1, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 3, 1, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 7, 1, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 8, 1, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 9, 1, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 14, 1, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 15, 1, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 16, 1, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 17, 1, gameManagement).addInList(fence_AL);
        new Fence("Fence", "l", 14, 1, gameManagement).addInList(fence_AL);

        // Stone
        new Stone("stone", 4, 8, gameManagement).addInList(staticObject);
        new Stone("stone", 2, 4, gameManagement).addInList(staticObject);
        new Stone("stone", 3, 4, gameManagement).addInList(staticObject);
        new Stone("stone", 4, 4, gameManagement).addInList(staticObject);

        // House
        new House("redhouse", 14, 6, gameManagement).addInList(staticObject);
        new House("redhouse", 5, 7, gameManagement).addInList(staticObject);
        new House("redhouse", 8, 3, gameManagement).addInList(staticObject);

        // Coin
        if (goldCoins_AL.isEmpty()) {

            new GoldCoin("Gold_Coin", 6, 9, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 2, 9, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 2, 7, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 7, 7, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 10, 5, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 18, 5, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 11, 5, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 11, 4, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 2, 3, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 3, 3, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 4, 3, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 8, 3, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 18, 3, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 18, 2, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 17, 2, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 14, 1, gameManagement).setFps(5).addInList(goldCoins_AL);

            for (GoldCoin i : goldCoins_AL) {
                if (i.getCoinScene() == gameManagement.getCurrentScene()) {
                    i.run();
                }
            }
        }

        ////Add label to draw panel
        addLabeltoPanel();

        // river
        addBG(BG);
    }

    private void addLabeltoPanel() {
        for (int i = 0; i < fence_AL.size(); i++) {
            drawpane.add(fence_AL.get(i));
            drawpane.add(fence_AL.get(i).getBlockingTile());
        }
        for (StaticObject i : staticObject) {
            drawpane.add(i);
            drawpane.add(i.getBlockingTile());
        }
        addGoldCoins();
    }

    private void addGoldCoins() {
        for (int i = 0; i < goldCoins_AL.size(); i++) {   //Note: should add player before coin
            if (goldCoins_AL.get(i).isGetCoin() == false && goldCoins_AL.get(i).getCoinScene() == gameManagement.getCurrentScene()) {
                drawpane.add(goldCoins_AL.get(i));
                drawpane.add(goldCoins_AL.get(i).getObjectTile());

            }
        }
        for (Monster i : monster_AL) {
            drawpane.add(i);
            drawpane.add(i.getObjectTile());

        }

        drawpane.add(charactor);
    }

    public void AddComponents2() {

        //////Create Object////////////////////
        staticObject = new ArrayList<>();
        fence_AL = new ArrayList<>();
        monster_AL = new ArrayList<>();

        drawpane = new JLabel();
        drawpane.setLayout(null);

        BackgroundPannel BG = new BackgroundPannel(gameManagement, "scene2");
        //////Monster/////
        FPS mons_fps = new FPS(10);
        new GreyMonster("Grey", 17, 3, gameManagement).addInList(monster_AL);
        new GreyMonster("Grey", 18, 3, gameManagement).addInList(monster_AL);
        new GreyMonster("Grey", 19, 3, gameManagement).addInList(monster_AL);

        new PinkMonster("Pink", 0, 8, gameManagement).addInList(monster_AL);
        new PinkMonster("Pink", 1, 8, gameManagement).addInList(monster_AL);
        new PinkMonster("Pink", 2, 8, gameManagement).addInList(monster_AL);
        new GreyMonster("Grey", 17, 9, gameManagement).addInList(monster_AL);
        new GreyMonster("Grey", 18, 9, gameManagement).addInList(monster_AL);
        new GreyMonster("Grey", 19, 9, gameManagement).addInList(monster_AL);

        /////Ghost/////
        FPS ghost1 = new FPS(7);
        new Ghost("Ghost", 7, 7, gameManagement).setRange(12, 1).addInList(monster_AL);

        Predicate<GoldCoin> rightsceneCoin_lambda = arg -> arg.getCoinScene() == gameManagement.getCurrentScene();
        if (goldCoins_AL.stream().filter(rightsceneCoin_lambda).toList().isEmpty()) {
            new GoldCoin("Gold_Coin", 2, 6, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 3, 6, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 9, 6, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 10, 6, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 11, 6, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 12, 6, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 17, 6, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 18, 6, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 2, 7, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 3, 7, gameManagement).setFps(5).addInList(goldCoins_AL);
            
            for (GoldCoin i : goldCoins_AL) {
            if (i.getCoinScene() == gameManagement.getCurrentScene()) {
                i.run();
            }
        }

        }

        //////House////
        new House("redhouse", 2, 4, gameManagement).addInList(staticObject);
        new House("redhouse", 9, 4, gameManagement).addInList(staticObject);
        new House("redhouse", 11, 4, gameManagement).addInList(staticObject);
        new House("redhouse", 17, 4, gameManagement).addInList(staticObject);

        /////////Fence////////
        ////Group 1///////
        new Fence("Fence", "d", 1, 2, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 2, 2, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 3, 2, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 4, 2, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 5, 2, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 8, 2, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 9, 2, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 10, 2, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 11, 2, gameManagement).addInList(fence_AL);
        new Fence("Fence", "d", 12, 2, gameManagement).addInList(fence_AL);

        //////Group 2///////
        new Fence("Fence", "u", 1, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "u", 2, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "u", 3, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "u", 4, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "u", 5, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "r", 5, 4, gameManagement).addInList(fence_AL);

        //Group 3/////
        new Fence("Fence", "u", 16, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "l", 16, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "u", 17, 4, gameManagement).addInList(fence_AL);
        new Fence("Fence", "u", 18, 4, gameManagement).addInList(fence_AL);

        new Fence("Fence", "r", 5, 5, gameManagement).addInList(fence_AL);
        new Fence("Fence", "l", 16, 5, gameManagement).addInList(fence_AL);
        new Fence("Fence", "r", 5, 6, gameManagement).addInList(fence_AL);
        new Fence("Fence", "l", 16, 6, gameManagement).addInList(fence_AL);

        /////Bush//////
        new Bush("bush", 1, 2, gameManagement).addInList(staticObject);
        new Bush("bush", 2, 2, gameManagement).addInList(staticObject);
        new Bush("bush", 3, 2, gameManagement).addInList(staticObject);
        new Bush("bush", 4, 2, gameManagement).addInList(staticObject);
        new Bush("bush", 5, 2, gameManagement).addInList(staticObject);

        new Bush("bush", 5, 4, gameManagement).addInList(staticObject);
        new Bush("bush", 16, 4, gameManagement).addInList(staticObject);
        new Bush("bush", 5, 5, gameManagement).addInList(staticObject);
        new Bush("bush", 16, 5, gameManagement).addInList(staticObject);
        new Bush("bush", 5, 6, gameManagement).addInList(staticObject);
        new Bush("bush", 16, 6, gameManagement).addInList(staticObject);

        ////////Tree//////
        new Tree("tree", 1, 4, gameManagement).addInList(staticObject);
        new Tree("tree", 4, 4, gameManagement).addInList(staticObject);


        for (StaticObject i : staticObject) {
            drawpane.add(i);
            drawpane.add(i.getBlockingTile());
        }
        for (int i = 0; i < fence_AL.size(); i++) {
            drawpane.add(fence_AL.get(i));
            drawpane.add(fence_AL.get(i).getBlockingTile());
        }

        addGoldCoins();
        addBG(BG);

    }

    private void addBG(BackgroundPannel BG) {
        drawpane.add(BG);
        contentpane.add(heart);
        contentpane.add(scorePanel);

        contentpane.add(drawpane);
        validate();
        repaint();

        setSpeed_ByDifficulty();
        setGameThread();

        for (Monster i : monster_AL) {
            i.run();
        }
    }

    public void AddComponents3() {
        staticObject = new ArrayList<>();
        fence_AL = new ArrayList<>();
        monster_AL = new ArrayList<>();

        drawpane = new JLabel();
        drawpane.setLayout(null);

        BackgroundPannel BG = new BackgroundPannel(gameManagement, "scene3");
        ////////////////Monster//////////
        FPS mons_fps = new FPS(10);
        new GreyMonster("Grey", 0, 4, gameManagement).addInList(monster_AL);
        new GreyMonster("Grey", 1, 4, gameManagement).addInList(monster_AL);
        new GreyMonster("Grey", 2, 4, gameManagement).addInList(monster_AL);

        new PinkMonster("Pink", 17, 7, gameManagement).addInList(monster_AL);
        new PinkMonster("Pink", 18, 7, gameManagement).addInList(monster_AL);
        new PinkMonster("Pink", 19, 7, gameManagement).addInList(monster_AL);

        //////////Ghost//////////////
        FPS ghost1 = new FPS(7);
        new Ghost("Ghost", 0, 3, gameManagement).setRange(18, 0).addInList(monster_AL);
        new Ghost("Ghost", 6, 8, gameManagement).setRange(0, 3).setFirstDirection("d").addInList(monster_AL);
        new Ghost("Ghost", 14, 8, gameManagement).setRange(0, 3).setFirstDirection("d").addInList(monster_AL);
        //////////House/////////////
        new House("redhouse", 6, 5, gameManagement).addInList(staticObject);
        new House("redhouse", 14, 5, gameManagement).addInList(staticObject);
        new House("redhouse", 17, 8, gameManagement).addInList(staticObject);
        //////////Stone////////////
        new Stone("stone", 4, 5, gameManagement).addInList(staticObject);
        new Stone("stone", 5, 5, gameManagement).addInList(staticObject);
        new Stone("stone", 16, 5, gameManagement).addInList(staticObject);
        //////////Fence/////////////
        new Fence("Fence", "u", 9, 9, gameManagement).addInList(fence_AL);
        new Fence("Fence", "u", 10, 9, gameManagement).addInList(fence_AL);
        new Fence("Fence", "u", 11, 9, gameManagement).addInList(fence_AL);
        new Fence("Fence", "u", 12, 9, gameManagement).addInList(fence_AL);

        //////////Tree//////////////
        new Tree("tree", 2, 7, gameManagement).addInList(staticObject);
        new Tree("tree", 3, 7, gameManagement).addInList(staticObject);
        new Tree("tree", 4, 7, gameManagement).addInList(staticObject);
        new Tree("tree", 17, 7, gameManagement).addInList(staticObject);
        new Tree("tree", 18, 7, gameManagement).addInList(staticObject);

        ////Gold coins/////
        Predicate<GoldCoin> rightsceneCoin_lambda = arg -> arg.getCoinScene() == gameManagement.getCurrentScene();
        if (goldCoins_AL.stream().filter(rightsceneCoin_lambda).toList().isEmpty()) {
            new GoldCoin("Gold_Coin", 1, 5, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 9, 5, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 10, 5, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 11, 5, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 17, 5, gameManagement).setFps(5).addInList(goldCoins_AL);

            new GoldCoin("Gold_Coin", 16, 9, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 2, 10, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 3, 10, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 4, 10, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 9, 10, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 10, 10, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 11, 10, gameManagement).setFps(5).addInList(goldCoins_AL);
            new GoldCoin("Gold_Coin", 12, 10, gameManagement).setFps(5).addInList(goldCoins_AL);
            
            for (GoldCoin i : goldCoins_AL) {
            if (i.getCoinScene() == gameManagement.getCurrentScene()) {
                i.run();
            }
        }

        }

        River river = new River(2, "r", gameManagement);
        safeTile_AL = river.getSafeTile();
        waterTile_AL = river.getWaterTile();
        movingTile_AL = river.getMovingTile();


        addLabeltoPanel();
        movingTile_AL.stream().forEach(arg -> drawpane.add(arg));
        safeTile_AL.stream().forEach(arg -> drawpane.add(arg));
        waterTile_AL.stream().forEach(arg -> drawpane.add(arg));
        addBG(BG);
        river.start();
    }

    public void setSpeed_ByDifficulty() {
        switch (difficulty) {
            case ("Baby") -> {
                charactor.setSpeed_Baby();
                for (Monster i : monster_AL) {
                    i.setSpeed_Baby();
                }
                if (!movingTile_AL.isEmpty()) {
                    for (MovingTile i : movingTile_AL) {
                        i.setSpeed_Baby();
                    }
                }
            }
            case ("Easy") -> {
                charactor.setSpeed_Easy();
                for (Monster i : monster_AL) {
                    i.setSpeed_Easy();
                }
                if (!movingTile_AL.isEmpty()) {
                    for (MovingTile i : movingTile_AL) {
                        i.setSpeed_Easy();
                    }
                }
            }
            case ("Normal") -> {
                charactor.setSpeed_Normal();
                for (Monster i : monster_AL) {
                    i.setSpeed_Normal();
                }
                if (!movingTile_AL.isEmpty()) {
                    for (MovingTile i : movingTile_AL) {
                        i.setSpeed_Normal();
                    }
                }
            }
            case ("Hard") -> {
                charactor.setSpeed_Hard();
                for (Monster i : monster_AL) {
                    i.setSpeed_Hard();
                }
                if (!movingTile_AL.isEmpty()) {
                    for (MovingTile i : movingTile_AL) {
                        i.setSpeed_Hard();
                    }
                }
            }
            case ("Nightmare") -> {
                charactor.setSpeed_Nightmare();
                for (Monster i : monster_AL) {
                    i.setSpeed_Nightmare();
                }
                if (!movingTile_AL.isEmpty()) {
                    for (MovingTile i : movingTile_AL) {
                        i.setSpeed_Nightmare();
                    }
                }
            }
            default -> {
            }
        }
    }
    //Present2
    public void setGameThread() {
        staticObject.add(0, new Fence("Fence", "u", -gameManagement.getTileSize(), gameManagement.getScreenHeight() + gameManagement.getTileSize(), gameManagement));
        Thread gameThread = new Thread() {
            public void run() {

                FPS f = new FPS(60);
                f.setInitialTime();

                while (charactor.isAlive() && !gameManagement.getChangeScene() && !charactor.isWin()) {
                    checkMonster_Hit();
                    checkCoin_Hit();
                    checkStaticObject_Hit();
                    if (gameManagement.getCurrentScene() == 3) {
                        checkMovingTile_Hit();
                    }
                    checkOutOfScreen();
                    f.implementFPS();
                }

            }

        };
        gameThread.start();
    }

    public void updateScore() {
        currentScore += (int) (50 * scoreScale);
        scorePanel.updateScore(currentScore);
    }

    public void reduceLife() {
        if (availLife > 0) {
            availLife--;
            heart.setBounds(screenWidth - availLife * tileSize, 0, tileSize * availLife, tileSize);
            heart.remove(availLife);

            validate();
            repaint();
            if (availLife <= 0) {
                sound.playSFX(sound.S_GameDeath);
                System.out.print("Die ");
                charactor.setAlive(false);
            }
        }
    }

    public void drowning() {
        for (int i = availLife; i > 0; i--) {
            reduceLife();
        }
        availLife = 0;
        charactor.setAlive(false);
    }

    public void checkMonster_Hit() {
        Predicate<Monster> hitMonster_lambda = arg -> arg.getObjectBounds().intersects(charactor.getObjectBounds());
        Predicate<Monster> isHitMonster_lambda = arg -> arg.isHit() == true;

        if (monster_AL.stream().anyMatch(hitMonster_lambda)) {

            monster_AL.stream().filter(hitMonster_lambda).filter(isHitMonster_lambda.negate()).forEach(arg -> {
                arg.setHit(true);
                sound.playSFX(sound.S_Punch);
                reduceLife();
            });

        } else {
            monster_AL.stream().forEach(arg -> arg.setHit(false));
        }
    }

    public void checkCoin_Hit() {
        Predicate<GoldCoin> hitCoin_lambda = arg -> arg.getObjectBounds().intersects(charactor.getBounds());
        Predicate<GoldCoin> availableCoin_lambda = arg -> arg.isGetCoin() == false;
        Predicate<GoldCoin> rightsceneCoin_lambda = arg -> arg.getCoinScene() == gameManagement.getCurrentScene();

        if (goldCoins_AL.stream().filter(rightsceneCoin_lambda).filter(availableCoin_lambda).anyMatch(hitCoin_lambda)) {
            goldCoins_AL.stream().filter(rightsceneCoin_lambda).filter(hitCoin_lambda).filter(availableCoin_lambda).forEach(arg -> {
                arg.setGetCoin(true);
                drawpane.remove(arg);
                coin = arg;
                charactor.updateCoinSum();
                updateScore();
            });
            sound.playSFX(sound.S_Coin);

        }
    }

    public void checkStaticObject_Hit() {
        Predicate<StaticObject> hitObject_lambda = arg -> charactor.getBounds().intersects(arg.getBlockingTile().getBounds());
        Predicate<Fence> hitBlocking_lambda = arg -> charactor.getBounds().intersects(arg.getBlockingTile().getBounds());
        Predicate<Fence> hitSafe_lambda = arg -> charactor.getBounds().intersects(arg.getSafeTile().getBounds());
        Predicate<Fence> isBlock_lambda = arg -> arg.getBlockingTile().isBlock() == true;

        if (staticObject.stream().anyMatch(hitObject_lambda)) {  // hit blocking 
            staticObject.stream().filter(hitObject_lambda).forEach(arg -> blocking = arg.getBlockingTile());
        } else if (fence_AL.stream().anyMatch(hitBlocking_lambda)) {   // hit fence
            fence_AL.stream().filter(hitBlocking_lambda).forEach(arg -> {
                arg.getSafeTile().setBlock(true);
                arg.getBlockingTile().setBlock(false);
                arg.getBlockingTile().setIsTop(true);
                blocking = arg.getSafeTile();
            });
        } else if (fence_AL.stream().anyMatch(hitSafe_lambda)) {
            fence_AL.stream().filter(hitSafe_lambda).forEach(arg -> {
                arg.getSafeTile().setBlock(false);
                arg.getBlockingTile().setBlock(true);
                arg.getBlockingTile().setIsTop(true);
                blocking = arg.getBlockingTile();
            });
        }
        else { // if didn't hit anything
            fence_AL.stream().forEach(arg -> {
                arg.getSafeTile().setBlock(false);
                arg.getBlockingTile().setBlock(true);
            });
            blocking = staticObject.get(0).getBlockingTile();
            blocking.setBlock(false);
        }

        if (charactor.getBounds().intersects(blocking.getBounds())) {
            charactor.setIsBlocked(true);
        }
    }

    public void checkMovingTile_Hit() {
        Predicate<MovingTile> hitMoving_lambda = arg -> charactor.getBounds().intersects(arg.getBounds());
        Predicate<MovingTile> biggerIntersects_move_lambda = arg -> arg.getTile_CurX() - charactor.get_curX() <= gameManagement.getTileSize() / 2;
        Predicate<MovingTile> empty_lambda = arg -> arg.isTop() != true;
        Predicate<MovingTile> getAnother_lambda = arg -> arg != lilypad_;
        Predicate<BlockingTile> inSafeTile_lambda = arg -> charactor.getBounds().intersects(arg.getBounds());
        Predicate<BlockingTile> biggerIntersects_lambda = arg -> arg.getTile_CurX() - charactor.get_curX() <= gameManagement.getTileSize() / 2;
        Predicate<Fence> hitSafe_lambda = arg -> charactor.getBounds().intersects(arg.getSafeTile().getBounds());
        Predicate<WaterTile> hitWater_lambda = arg -> charactor.getBounds().intersects(arg.getBounds());
        Predicate<WaterTile> biggerIntersects_water_lambda = arg -> Math.abs(charactor.get_curX() - arg.getTile_CurX()) < tileSize * 0.25;
        Predicate<WaterTile> isDrowning_lambda = arg -> arg.isDrown() == true;
        
        
        if (movingTile_AL.stream().filter(biggerIntersects_move_lambda).anyMatch(hitMoving_lambda)) { // hit some moving
            if (!(fence_AL.stream().anyMatch(hitSafe_lambda) && !charactor.isFlow())) {
                if (!lilypad_.isTop() && !charactor.isFlow()) {   // from ground to moving tile
                    movingTile_AL.stream().filter(hitMoving_lambda).filter(biggerIntersects_move_lambda).forEach(arg -> lilypad_ = arg);
                    charactor.setIsFlow(true);
                    charactor.resetLocation(lilypad_.getTile_CurX(), lilypad_.getTile_CurY());
                    lilypad_.setCharactor(charactor);
                    movingTile_AL.stream().filter(getAnother_lambda).forEach(arg -> arg.setIsTop(false));
                    movingTile_AL.stream().filter(getAnother_lambda.negate()).forEach(arg -> arg.setIsTop(true));
                    lilypad_.setIsTop(true);
                }
                if(charactor.isFlow()){
                    waterTile_AL.stream().forEach(arg -> arg.setIsEnabled(true));
                    waterTile_AL.stream().filter(hitWater_lambda).filter(biggerIntersects_water_lambda).forEach(arg -> {
                        arg.setIsEnabled(false);
                       
                    });
        }
                //move to another moving while flowing
                if (lilypad_.isTop() && !charactor.isFlow() && movingTile_AL.stream().filter(getAnother_lambda).filter(empty_lambda).anyMatch(hitMoving_lambda)) {  
                    movingTile_AL.stream().filter(getAnother_lambda.negate()).forEach(arg -> arg.setIsTop(false));
                    movingTile_AL.stream().filter(getAnother_lambda).filter(hitMoving_lambda).forEach(arg -> arg.setIsTop(true));
                    lilypad_.setIsTop(false);
                    charactor.setIsFlow(false);
                    movingTile_AL.stream().filter(empty_lambda.negate()).forEach(arg -> {
                        lilypad_ = arg;
                    });
                    charactor.resetLocation(lilypad_.getTile_CurX(), lilypad_.getTile_CurY());
                    lilypad_.setCharactor(charactor);
                    lilypad_.setIsTop(true);
                    charactor.setIsFlow(true);
                }
                
                //from tile to ground
                if (lilypad_.isTop() && safeTile_AL.stream().anyMatch(inSafeTile_lambda)) {
                    if (charactor.isBlocked()) {
                        charactor.setIsBlocked(true);
                        charactor.setWalk(false);
                    } else {
                        lilypad_.setIsTop(false);
                        charactor.setIsFlow(false);
                        charactor.setWalk(true);
                        safeTile_AL.stream().filter(inSafeTile_lambda).filter(biggerIntersects_lambda).forEach(arg -> {
                            safe_ = arg;
                        });
                        charactor.resetLocation(safe_.getTile_CurX(), safe_.getTile_CurY());
                        movingTile_AL.stream().forEach(arg -> arg.setIsTop(false));
                        lilypad_ = movingTile_AL.get(0);
                    }
                }
                
                
                //still flow but hit water
                if (lilypad_.isTop() && !charactor.isFlow() && charactor.isWalk() && waterTile_AL.stream().anyMatch(hitWater_lambda) && !safeTile_AL.stream().anyMatch(inSafeTile_lambda) && !movingTile_AL.stream().filter(getAnother_lambda).filter(empty_lambda).anyMatch(hitMoving_lambda)) {

                    waterTile_AL.stream().filter(hitWater_lambda).filter(arg -> arg.isEnabled()).filter(isDrowning_lambda.negate()).forEach(arg -> {
                        water_ = arg;
                    });
                    if (Math.abs(water_.getTile_CurX() - charactor.get_curX()) <= tileSize * 0.25) {
                        water_.setIsDrown(true);
                        charactor.setWalk(false);
                        charactor.resetLocation(water_.getTile_CurX(), water_.getTile_CurY());
                        charactor.setIsDrown(true);
                        drowning();
                    } else {
                        water_ = waterTile_AL.get(0);
                    }

                } else {   //still flow
                    movingTile_AL.stream().filter(hitMoving_lambda).filter(biggerIntersects_move_lambda).filter(empty_lambda.negate()).forEach(arg -> lilypad_ = arg);
                    charactor.setIsFlow(true);
                }

                

            }
        } else if (waterTile_AL.stream().filter(isDrowning_lambda.negate()).anyMatch(hitWater_lambda)) {    // didn't hit moving but hit water
            if (!(fence_AL.stream().anyMatch(hitSafe_lambda) && !charactor.isFlow())) {
                waterTile_AL.stream().filter(hitWater_lambda).filter(isDrowning_lambda.negate()).forEach(arg -> {
                    arg.setIsDrown(true);
                    water_ = arg;
                });
                charactor.setWalk(false);
                charactor.resetLocation(water_.getTile_CurX(), water_.getTile_CurY());
                charactor.setIsDrown(true);
                drowning();
            }
        } else {
            lilypad_ = movingTile_AL.get(0);
            water_ = waterTile_AL.get(0);
            safe_ = safeTile_AL.get(0);

            movingTile_AL.stream().filter(getAnother_lambda).forEach(arg -> arg.setIsTop(false));
            if (!charactor.isDrown()) {
                waterTile_AL.stream().forEach(arg -> arg.setIsDrown(false));
            }

            charactor.setIsFlow(false);

        }
    }

    public void checkOutOfScreen() {
        Predicate<WaterTile> hitWater_lambda = arg -> charactor.getBounds().intersects(arg.getBounds());
        Predicate<WaterTile> isDrowning_lambda = arg -> arg.isDrown();
        Predicate<WaterTile> inScreen_lambda = arg -> arg.getTile_CurX() < screenWidth;

        if (charactor.isFlow() && (charactor.get_curX() + tileSize * 0.25 > screenWidth || charactor.get_curX() + tileSize * 0.75 < 0)) {
            waterTile_AL.stream().filter(hitWater_lambda).filter(isDrowning_lambda.negate()).filter(inScreen_lambda).forEach(arg -> {
                arg.setIsDrown(true);
                water_ = arg;
            });
            charactor.setWalk(false);
            charactor.resetLocation(water_.getTile_CurX(), water_.getTile_CurY());
            charactor.setIsDrown(true);
            drowning();
        }
    }

}

class MyImageIcon extends ImageIcon {

    public MyImageIcon(String fname) {
        super(fname);
    }

    public MyImageIcon(Image image) {
        super(image);
    }

    public MyImageIcon resize(int width, int height) {
        Image oldimg = this.getImage();
        Image newimg = oldimg.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new MyImageIcon(newimg);
    }
};
