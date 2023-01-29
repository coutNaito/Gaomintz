package Project3_123.GUI;

import Project3_123.MyClass.MyButton;
import Project3_123.MyClass.MyCsv;
import Project3_123.MyClass.MyImageIcon;
import Project3_123.MyClass.MyLayerPane;
import Project3_123.MyClass.MySoundCtrl;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class leaderboardPane extends MyLayerPane {
    MyButton mainMenuButton;
    static MyCsv scorefile;
    static JTable scoreTable;
    //private MySoundCtrl sound;
    leaderboardPane(MySoundCtrl sound){ 
        super(sound);
    }
    @Override
    protected void addComponent() {
        String[] columnNames = {"Rank", "Name", "Score"};
        try { scorefile = new MyCsv();  } catch (IOException e) { throw new RuntimeException(e); }
        //set bg image
        addBgImage("OrangeBg.png");
        //set top text
        JLabel topText = new JLabel(new MyImageIcon(componentPath+"Leaderboard.png"));
        topText.setOpaque(false);
        topText.setBounds(20,30,width,height);
        topText.setHorizontalAlignment(JLabel.LEFT);
        topText.setVerticalAlignment(JLabel.TOP);
        add(topText, JLayeredPane.DRAG_LAYER);
        //set button pane
        JPanel buttonMenu = new JPanel();
        buttonMenu.setOpaque(false);
        buttonMenu.setBounds(20,height-80,width-40,70);
        buttonMenu.setLayout(new BorderLayout());

        //button back to main menu
        mainMenuButton = new MyButton("mainMenu",true);
        mainMenuButton.setSoundCtrl(sound);

        buttonMenu.add(mainMenuButton, BorderLayout.EAST);
        
        //create Score Table
        scorefile.FileReader();
        scoreTable = new JTable(scorefile.getData(),columnNames);
        scoreTable.setFont(new Font("Serif", Font.PLAIN, 20));
        scoreTable.setBackground(Color.orange);
        scoreTable.setRowHeight(50);
        scoreTable.setShowGrid(true);
        scoreTable.setGridColor(Color.BLACK);
        //
        JTableHeader headTable = scoreTable.getTableHeader();
        headTable.setBackground(Color.WHITE);
        headTable.setForeground(Color.ORANGE);
        headTable.setFont(new Font("Serif", Font.BOLD, 30));
        JScrollPane sp = new JScrollPane(scoreTable);
        sp.setBounds(100, 100, 976, 464);
        sp.setOpaque(false);
        
        add(sp, JLayeredPane.DRAG_LAYER);


        add(buttonMenu, JLayeredPane.DRAG_LAYER);
    }
    public MyButton getMainMenuButton() { return mainMenuButton; }
    
    public static void updateTable(){
        String[] columnNames = {"Rank", "Name", "Score"};
        scorefile.IncreaseArraySize();
        scorefile.FileReader();
        DefaultTableModel model = new DefaultTableModel(scorefile.getData(),columnNames);
        scoreTable.setModel(model);
    }
    
}
