package Project3_123.MyClass;

//import Project3_6413123.MyImageIcon;

import javax.swing.*;
import java.awt.*;

public class MyRadioButton extends JRadioButton {
    String path = "src/main/java/Project3_123/resource/";
    public MyRadioButton(String text){
        super(text);
        setActionCommand(text);
        basicSetting();
    }

    private void basicSetting() {
        setFont(new Font("Luckiest Guy", Font.BOLD, 25));
        setOpaque(false);
        setFocusable(false);
        setIcon(new MyImageIcon(path+"radioUncheck.png").resize(30,30));
        setSelectedIcon(new MyImageIcon(path+"radioCheck.png").resize(30,30));
    }

}
