package Project3_123.GUI;

import Project3_123.MyClass.MyButton;
import Project3_123.MyClass.MyImageIcon;
import Project3_123.MyClass.MyLayerPane;
import Project3_123.MyClass.MySoundCtrl;
import Project3_123.MyDialog.HelpMenu;
import Project3_123.MyDialog.SettingMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class startPane extends MyLayerPane {
    private JFrame jFrame;
    private MyButton startButton, leaderButton, creditButton;
    
    startPane(JFrame jFrame,MySoundCtrl sound) {
        super(sound);
        this.jFrame = jFrame;
    }
    @Override
    protected void addComponent() {
        setVisible(true);
        addBgImage("MainBG.png");
        addIconComponent();
        addCenterComponent();
    }

    private void addIconComponent() {
        //pane for icon
        JPanel iconPane = new JPanel();
        iconPane.setOpaque(false);
        iconPane.setLayout(new GridLayout(1,2,0,20));
        iconPane.setBounds(width-170,15,150,50);
        

        //setting icon
        MyButton settingButton = new MyButton("Settings", false,50,50){
            @Override
            public void mouseClicked(MouseEvent e) {
                sound.playSFX(sound.S_Click);
                SettingMenu.setSoundCtrl(sound);
                new SettingMenu(jFrame).run();
            }
        };

        //how to play icon
        MyButton helpButton = new MyButton("Help",false,50,50){
            @Override
            public void mouseClicked(MouseEvent e) {
                sound.playSFX(sound.S_Click);
                new HelpMenu(jFrame).run();
            }
        };

        //add into setting icon pane
        iconPane.add(helpButton);
        iconPane.add(settingButton);
        //add iconPane into Frame
        add(iconPane,Integer.valueOf(2));
    }

    private void addCenterComponent() {
        //set menu pane
        JPanel menuPane = new JPanel();
        menuPane.setBounds(0,50,width,height);
        menuPane.setLayout(new FlowLayout(FlowLayout.CENTER,width,15));
        menuPane.setOpaque(false);
        //logo
        JLabel logo = new JLabel();
        logo.setIcon(new MyImageIcon("src/main/java/Project3_123/resource/componentImage/Gaomintz-2.png").resize(400, 150));
        //button
        startButton = new MyButton("Start",true);
        startButton.setSoundCtrl(sound);
        leaderButton = new MyButton("Leader",true);
        leaderButton.setSoundCtrl(sound);
        creditButton = new MyButton("Credits",true);
        creditButton.setSoundCtrl(sound);
        
        MyButton exitButton = new MyButton("Exit",true){
            @Override
            public void mouseClicked(MouseEvent e) {
                
                sound.playSFX(sound.S_Click);
                System.exit(0);
            }
        };

        menuPane.add(logo);
        menuPane.add(startButton);
        menuPane.add(leaderButton);
        menuPane.add(creditButton);
        menuPane.add(exitButton);
        add(menuPane,Integer.valueOf(1));

    }

    public MyButton getStartButton() { return startButton; }
    public MyButton getLeaderButton() { return leaderButton; }
    public MyButton getCreditButton() { return creditButton; }
    
   
}
