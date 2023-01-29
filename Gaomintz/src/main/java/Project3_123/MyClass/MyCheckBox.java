package Project3_123.MyClass;

import javax.swing.*;
import java.awt.*;

public class MyCheckBox extends JCheckBox {
    String path = "src/main/java/Project3_123/resource/";
    boolean mute;
    public MyCheckBox(String text, boolean mute){
        super(text,mute);
        basicSetting();
    }

    private void basicSetting() {
        setFont(new Font("Luckiest Guy", Font.BOLD, 20));
        setOpaque(false);
        setFocusable(false);
        setIcon(new MyImageIcon(path+"Uncheck.png").resize(25,25));
        setSelectedIcon(new MyImageIcon(path+"Check.png").resize(25,25));
    }
}
