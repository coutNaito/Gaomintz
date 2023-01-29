package Project3_123.GUI;

import Project3_123.MyClass.MyButton;
import Project3_123.MyClass.MyImageIcon;
import Project3_123.MyClass.MyLayerPane;
import Project3_123.MyClass.MySoundCtrl;

import javax.swing.*;
import java.awt.*;

public class creditPane extends MyLayerPane {
    MyButton backButton;
    //private MySoundCtrl sound;
    creditPane(MySoundCtrl sound){ 
        super(sound);
    }

    @Override
    protected void addComponent() {
        String path = componentPath+"credits/";
        //set bg image
        addBgImage("varmintzBg.png");

        //set top text
        JLabel topText = new JLabel(new MyImageIcon(path+"Credit.png"));
        topText.setOpaque(false);
        topText.setBounds(0,20,width,height);
        topText.setHorizontalAlignment(JLabel.CENTER);
        topText.setVerticalAlignment(JLabel.TOP);
        add(topText, JLayeredPane.POPUP_LAYER);

        //set name pane
        JPanel namePane = new JPanel();
        namePane.setBounds(20,175,width/2 + 50,height);
        namePane.setLayout(new FlowLayout(FlowLayout.LEADING,50,25));
        namePane.setOpaque(false);
        //set Jlabel
        JLabel head1 = new JLabel(new MyImageIcon(path+"Developer.png"));
        head1.setOpaque(false);
        JLabel line1 = new JLabel(new MyImageIcon(path+"Line.png"));
        line1.setOpaque(false);
        JLabel name1 = new JLabel(new MyImageIcon(path+"Name1.png"));
        name1.setOpaque(false);
        JLabel name2 = new JLabel(new MyImageIcon(path+"Name2.png"));
        name2.setOpaque(false);
        JLabel name3 = new JLabel(new MyImageIcon(path+"Name3.png"));
        name3.setOpaque(false);
        JLabel name4 = new JLabel(new MyImageIcon(path+"Name4.png"));
        name4.setOpaque(false);

        namePane.add(head1);
        namePane.add(line1);
        namePane.add(name1);
        namePane.add(name2);
        namePane.add(name3);
        namePane.add(name4);

        add(namePane,JLayeredPane.POPUP_LAYER);

        //set name pane
        JPanel tyPane = new JPanel();
        tyPane.setBounds(width/2+100,175,width/4+50,height);
        tyPane.setLayout(new FlowLayout(FlowLayout.LEADING,50,25));
        tyPane.setOpaque(false);
        //set Jlabel
        JLabel head2 = new JLabel(new MyImageIcon(path+"SpecialTy.png"));
        head2.setOpaque(false);
        JLabel line2 = new JLabel(new MyImageIcon(path+"Line.png"));
        line2.setOpaque(false);
        JLabel nameTy = new JLabel(new MyImageIcon(path+"vermintz.png"));
        name4.setOpaque(false);

        tyPane.add(head2);
        tyPane.add(line2);
        tyPane.add(nameTy);

        add(tyPane,JLayeredPane.POPUP_LAYER);

        //set button pane
        JPanel buttonMenu = new JPanel();
        buttonMenu.setOpaque(false);
        buttonMenu.setBounds(20,height-100,width-40,70);
        buttonMenu.setLayout(new BorderLayout());

        //button back
        backButton = new MyButton("Back",true);
        backButton.setSoundCtrl(sound);

        buttonMenu.add(backButton,BorderLayout.WEST);
        add(buttonMenu,JLayeredPane.DRAG_LAYER);
    }
    public MyButton getBackButton() { return backButton; }
 
}
