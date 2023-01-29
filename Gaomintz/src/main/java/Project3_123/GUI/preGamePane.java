package Project3_123.GUI;

import Project3_123.MyClass.MyButton;
import Project3_123.MyClass.MyImageIcon;
import Project3_123.MyClass.MyLayerPane;
import Project3_123.MyClass.MyRadioButton;
import Project3_123.MyClass.MySoundCtrl;
import Project3_123.StartGame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class preGamePane extends MyLayerPane implements ActionListener {
    gameOverPane gameOverPane;
    ButtonGroup skinGroup, typeGroup;
    JFrame jFrame;
    MyButton backButton, okButton;
    JComboBox difficultyBox;
    JLabel character;
    String path;
    String charImagePath;
    String charPath = "src/main/java/Project3_123/resource/CharShow/";
    String mainCharacter = "HumanNormal";//default value
    preGamePane(JFrame jFrame, gameOverPane gameOverPane,MySoundCtrl sound){
        super(sound);
        this.jFrame = jFrame;
        this.gameOverPane = gameOverPane;
    }
    @Override
    protected void addBasicComponent(){
        path = componentPath+"preGame/";
        //set bg image
        addBgImage("PreGameBG.png");

        //set button pane
        JPanel buttonMenu = new JPanel();
        buttonMenu.setOpaque(false);
        buttonMenu.setBounds(20,height-100,width-40,70);
        buttonMenu.setLayout(new BorderLayout());

        //button back
        backButton = new MyButton("Back",true);
        backButton.setSoundCtrl(sound);
        //button ok
        okButton = new MyButton("Ok",true){
            @Override
            public void additionFeature() {
                sound.playSFX(sound.S_GameStart);
                jFrame.setVisible(false);
                String difficulty = difficultyBox.getSelectedItem().toString();
                System.out.println("=====================================");
                System.out.println("User Input: "+ difficulty+" "+mainCharacter);
                System.out.println("=====================================");
                Thread t = new Thread(() -> new MainFrame(jFrame,gameOverPane,difficulty,mainCharacter,sound));
                t.start();
            }
        };
        okButton.setSoundCtrl(sound);

        buttonMenu.add(backButton, BorderLayout.WEST);
        buttonMenu.add(okButton, BorderLayout.EAST);
        add(buttonMenu, JLayeredPane.DRAG_LAYER);
    }
    @Override
    protected void addComponent() {
        addRightComponent();
        //show default main character
        charImagePath = "src/main/java/Project3_123/resource/CharShow/HumanNormal.png";//default image
        System.out.println(charImagePath);
        character = new JLabel(new MyImageIcon(charImagePath).resize(120,240));
        character.setBounds(120,65,width/2,height/2);
        add(character, JLayeredPane.DRAG_LAYER);

        //set platform
        JLabel platform = new JLabel(new MyImageIcon(path+"platform.png"));
        platform.setBounds(250,height-300,300,100);
        add(platform, JLayeredPane.DRAG_LAYER);

    }
    private void addRightComponent() {
        JPanel rightPane = new JPanel(new GridLayout(6,1,0,0));
        rightPane.setOpaque(false);
        rightPane.setBounds(width/2 + 125,height/4-100,width/2 - 50,height-150);

        //set Right component (Text image and JRadioButton)
        //type
        JLabel typeText = new JLabel();
        typeText.setIcon(new MyImageIcon(path+"Type.png"));
        typeText.setHorizontalAlignment(JLabel.LEFT);
        typeText.setOpaque(false);

        JLabel type = new JLabel();
        type.setLayout(new FlowLayout(FlowLayout.LEADING,20,0));
        type.setOpaque(false);
        type.setPreferredSize(new Dimension(width,25));
        type.setHorizontalAlignment(JLabel.LEADING);

        MyRadioButton type1 = new MyRadioButton("Human");
        type1.setSelected(true);
        type1.addActionListener(this);
        MyRadioButton type2 = new MyRadioButton("Undead");
        type2.addActionListener(this);

        typeGroup = new ButtonGroup();
        typeGroup.add(type1);
        typeGroup.add(type2);

        type.add(type1);
        type.add(type2);

        //skin
        JLabel skinText = new JLabel();
        skinText.setIcon(new MyImageIcon(path+"Skin.png"));
        skinText.setHorizontalAlignment(JLabel.LEFT);
        skinText.setOpaque(false);

        JLabel skin = new JLabel();
        skin.setLayout(new FlowLayout(FlowLayout.LEADING,20,0));
        skin.setOpaque(false);
        skin.setPreferredSize(new Dimension(width,25));
        skin.setHorizontalAlignment(JLabel.LEADING);

        MyRadioButton skin1 = new MyRadioButton("Normal");
        skin1.setSelected(true);
        skin1.addActionListener(this);
        MyRadioButton skin2 = new MyRadioButton("Suit");
        skin2.addActionListener(this);

        skinGroup = new ButtonGroup();
        skinGroup.add(skin1);
        skinGroup.add(skin2);

        skin.add(skin1);
        skin.add(skin2);

        //set difficulty text image
        JLabel diffText = new JLabel();
        diffText.setLayout(new FlowLayout());
        diffText.setOpaque(false);
        diffText.setIcon(new MyImageIcon(path+"mode.png"));

        //mode setting
        String[] difficulty = { "Baby", "Easy", "Normal", "Hard", "Nightmare" };
        difficultyBox = new JComboBox(difficulty);
        difficultyBox.setPreferredSize(new Dimension(250, 50));
        difficultyBox.setFont(new Font("Luckiest Guy", Font.BOLD, 20));
        ((JLabel)difficultyBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        diffText.add(difficultyBox);

        rightPane.add(typeText);
        rightPane.add(type);
        rightPane.add(skinText);
        rightPane.add(skin);
        rightPane.add(diffText);
        add(rightPane,JLayeredPane.DRAG_LAYER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainCharacter = typeGroup.getSelection().getActionCommand() + skinGroup.getSelection().getActionCommand();
        charImagePath = charPath+mainCharacter+".png";
        character.setIcon(new MyImageIcon(charImagePath).resize(120,240));
    }

    public MyButton getBackButton() { return backButton; }

    public MyButton getOkButton() { return okButton; }
}
