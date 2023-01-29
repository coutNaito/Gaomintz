package Project3_123.MyDialog;

import Project3_123.MyClass.MyImageIcon;

import javax.swing.*;
import java.awt.*;

public class HelpMenu extends MyDialog {
    public HelpMenu(Frame parent) { super(parent); }
    @Override
    public void AddComponents() {
        //set top text
        JLabel topText = new JLabel( new MyImageIcon(componentPath+"HowToPlay.png"));
        topText.setBounds(0,20,width,height);
        topText.setVerticalAlignment((JLabel.NORTH));
        panel.add(topText,Integer.valueOf(1));

        //set how to play image
        MyImageIcon howToPlayPicture = new MyImageIcon("src/main/java/Project3_123/resource/PopUp/HowToPlayBG.png").resize(689,494);
        JLabel howToPlay = new JLabel(howToPlayPicture);
        howToPlay.setBounds(width/18,height/16,689,494);
        howToPlay.setOpaque(false);
        panel.add(howToPlay,Integer.valueOf(1));
    }

}
