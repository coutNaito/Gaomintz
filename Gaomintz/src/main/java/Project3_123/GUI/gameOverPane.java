package Project3_123.GUI;

import Project3_123.MyClass.MyButton;
import Project3_123.MyClass.MyCsv;
import Project3_123.MyClass.MyImageIcon;
import Project3_123.MyClass.MyLayerPane;
import Project3_123.MyClass.MySoundCtrl;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class gameOverPane extends MyLayerPane {
    MyCsv scorefile;
    MyButton confirmButton;
    JTextField nameTextField;
    JLabel scoreShow;
    private int score;
    
    gameOverPane(MySoundCtrl sound){ 
        super(sound);
    }
    @Override
    protected void addComponent() {
        try { scorefile = new MyCsv(); }
        catch (IOException e) { throw new RuntimeException(e); }
        String path = componentPath+"gameover/";
        //set bg image

        //set information
        JPanel informationPane = new JPanel(new GridLayout(1,2,50,0));
        informationPane.setOpaque(false);
        informationPane.setBounds(200,300,width,height-450);

        JPanel namePane = new JPanel(new GridLayout(2,1,0,50));
        namePane.setOpaque(false);

        JPanel scorePane = new JPanel(new GridLayout(2,1,0,50));
        scorePane.setOpaque(false);

        JLabel nameText = new JLabel(new MyImageIcon(path+"Name.png"));
        nameText.setHorizontalAlignment(JLabel.LEFT);
        //set name text field
        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Luckiest Guy",Font.LAYOUT_LEFT_TO_RIGHT,30));
        nameTextField.setText("Player name");

        namePane.add(nameText);
        namePane.add(nameTextField);
        //show player score
        JLabel scoreText = new JLabel(new MyImageIcon(path+"Score.png"));
        scoreText.setHorizontalAlignment(JLabel.LEFT);
        scoreShow = new JLabel();
        scoreShow.setFont(new Font("Luckiest Guy",Font.BOLD,30));
        scoreShow.setHorizontalAlignment(JLabel.LEFT);
        Color color = new Color(181, 143, 18);
        scoreShow.setForeground(color);
        

        scorePane.add(scoreText);
        scorePane.add(scoreShow);

        informationPane.add(namePane);
        informationPane.add(scorePane);

        add(informationPane,JLayeredPane.DRAG_LAYER);

        //set button pane
        JPanel buttonMenu = new JPanel();
        buttonMenu.setOpaque(false);
        buttonMenu.setBounds(20,height-100,width-40,70);
        buttonMenu.setLayout(new BorderLayout());
        //button confirm
        confirmButton = new MyButton("Confirm",true){
            @Override
            public void additionFeature(){
                sound.playSFX(sound.S_Click);
                scorefile.IncreaseArraySize();
                scorefile.FileReader();
                scorefile.setData(scorefile.getRows()-1, nameTextField.getText(), score);
                scorefile.sortScore(scorefile.getData());
                scorefile.FileWriter();
                leaderboardPane.updateTable();
            }
        };
        confirmButton.setSoundCtrl(sound);
        

        buttonMenu.add(confirmButton, BorderLayout.EAST);
        add(buttonMenu, JLayeredPane.DRAG_LAYER);
    }

    public MyButton getConfirmButton() { return confirmButton; }

    public void setScore(int score) {
        this.score = score;
        scoreShow.setText(String.valueOf(score));
    }
    
}
