package Project3_123.MyClass;

import javax.swing.*;
import java.awt.*;

public abstract class MyLayerPane extends JLayeredPane {
    protected Project3_123.MyClass.shareValue shareValue;
    protected int width = 1176, height = 664;
    private JLabel bgPane;
    protected String imagePath = "src/main/java/Project3_123/resource/bgImage/";
    protected String componentPath = "src/main/java/Project3_123/resource/componentImage/";
    protected MySoundCtrl sound;
    public MyLayerPane(MySoundCtrl sound){
        //set JLayerPane
        this.sound=sound;
        setLayout(null);
        setOpaque(true);
        setBounds(new Rectangle(width,height));
        setVisible(false);

        bgPane = new JLabel();
        bgPane.setOpaque(false);
        bgPane.setVerticalAlignment(JLabel.NORTH);
        bgPane.setHorizontalAlignment(JLabel.LEFT);
        bgPane.setBounds(0,0,width,height);
        add(bgPane,Integer.valueOf(0));

        addBasicComponent();
        addComponent();

    }
    public void addBgImage(String bgName){
        MyImageIcon bg = new MyImageIcon(imagePath+bgName).resize(width,height);
        bgPane.setIcon(bg);

    }
    protected void addBasicComponent(){ }
    protected abstract void addComponent();
}
