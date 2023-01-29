package Project3_123.MyClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyButton extends JButton implements MouseListener {
    private String defaultPath = "src/main/java/Project3_123/resource/buttonImage/normal/";
    private String pressPath = "src/main/java/Project3_123/resource/buttonImage/press/";
    private String hoverPath = "src/main/java/Project3_123/resource/buttonImage/hover/";
    private String name;
    private boolean animation;
    private MyLayerPane currentPane, destinationPane;
    private MySoundCtrl sound;
    public MyButton(String name) {
        super(name);
        this.name = name;
        setting();
    }
    public MyButton(String name,boolean animation) {
        this.name = name;
        this.animation = animation;
        setting();

    }
    public MyButton(String name, boolean animation, int width, int height) {
        this.name = name;
        this.animation = animation;
        setPreferredSize(new Dimension(width,height));
        setting();
    }
    public void setting(){
        if(animation) {
            MyImageIcon hoverImage = new MyImageIcon(hoverPath+name+".png");
            setSelectedIcon(hoverImage);

            MyImageIcon pressImage = new MyImageIcon(pressPath+name+".png");
            setPressedIcon(pressImage);
        }
        MyImageIcon defaultImage = new MyImageIcon(defaultPath+name+".png");
        setIcon(defaultImage);

        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusable(false);

        addMouseListener(this); //add mouse listener
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        additionFeature();
        destinationPane.setVisible(true);
        currentPane.setVisible(false);
        
    }
    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { setSelected(true); }
    @Override
    public void mouseExited(MouseEvent e) { setSelected(false); }
    public void linkPane(MyLayerPane paneCur, MyLayerPane paneDes) {
        currentPane = paneCur;
        destinationPane = paneDes;
    }
    public void additionFeature() {
        sound.playSFX(sound.S_Click);
    }
    
    public void setSoundCtrl(MySoundCtrl soundc){
        sound=soundc;
    }
}
