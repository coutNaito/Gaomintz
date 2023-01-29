package Project3_123.MyDialog;

import Project3_123.MyClass.MyButton;
import Project3_123.MyClass.MyImageIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyDialog extends JDialog implements ActionListener {
    protected String bgImagePath = "src/main/java/Project3_123/resource/bgImage/";
    protected String componentPath = "src/main/java/Project3_123/resource/PopUp/";
    protected int width = 776, height = 564;
    protected JLayeredPane panel;
    private MyButton closeButton;
    private MyImageIcon bg, subBg;
    public MyDialog(Frame parent) {
        //set name of dialog by pass "Jframe" into this
        super(parent,true);
        setUndecorated(true);

        //move the location into the center of the screen
        Point loc = parent.getLocation();
        setLocation(loc.x+200,loc.y+50);

        //set size of the contentpane
        panel = new JLayeredPane();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(width, height));
        getContentPane().add(panel);

        //add component
        AddBasicComponents();
        AddComponents();
        pack();
    }

    public void actionPerformed(ActionEvent ae) { dispose(); }

    public void AddBasicComponents(){
        //set image background
        bg = new MyImageIcon(bgImagePath+"PopBG.png");
        JPanel drawPane = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg.getImage(), 0, 0, null);
            }
        };
        drawPane.setLayout(new GridBagLayout());
        drawPane.setOpaque(false);
        drawPane.setBounds(0,0,width,height);

        subBg = new MyImageIcon(bgImagePath+"SubPopBG.png");
        JLabel subBgPane = new JLabel(subBg);

        drawPane.add(subBgPane,new GridBagConstraints());

        panel.add(drawPane,Integer.valueOf(0));

        //set close button
        closeButton = new MyButton("Close",false,50,50);
        closeButton.setBounds(width-50,0,50,50);
        closeButton.setOpaque(false);
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.addActionListener(this);

        panel.add(closeButton,Integer.valueOf(2));
    }

    public void AddComponents() { }
    public void run() { this.setVisible(true); }
}
