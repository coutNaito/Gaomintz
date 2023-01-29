package Project3_123.MyDialog;

import Project3_123.MyClass.MyCheckBox;
import Project3_123.MyClass.MyImageIcon;
import Project3_123.MyClass.MySoundCtrl;

import javax.swing.*;
import java.awt.*;

public class SettingMenu extends MyDialog {
    String pathCheckBox = "src/main/java/Project3_123/resource/";
    private static MySoundCtrl SettingSound;
    
    public SettingMenu(Frame parent) { 
        super(parent);
    }
    @Override
    public void AddComponents() {
        //set top text
        JLabel topText = new JLabel( new MyImageIcon(componentPath+"Setting.png"));
        topText.setBounds(0,20,width,height);
        topText.setVerticalAlignment((JLabel.NORTH));
        panel.add(topText,Integer.valueOf(1));

        JPanel centerPane = new JPanel(new GridLayout(4,1,0,0));
        centerPane.setOpaque(false);
        centerPane.setBounds(width/4,height/4 - 50,width/2,height/2);
        //set center component (Text image and JRadioButton)
        //Music
        JLabel musicText = new JLabel();
        musicText.setIcon(new MyImageIcon(componentPath+"music.png"));
        musicText.setHorizontalAlignment(JLabel.LEFT);
        musicText.setOpaque(false);

        JLabel music = new JLabel();
        music.setLayout(new FlowLayout());
        music.setOpaque(false);
        music.setIcon(new MyImageIcon(componentPath+"soundIcon.png"));

        JSlider musicSlider = new JSlider(-40,6);
        musicSlider.setOpaque(false);
        musicSlider.setPreferredSize(new Dimension(200, 60));
        musicSlider.setValue(Math.round(SettingSound.get_MVolume()));
        musicSlider.addChangeListener(e -> SettingSound.adjust_MVolume(musicSlider.getValue()));
        
        MyCheckBox musicMute = new MyCheckBox("Mute",SettingSound.getMmute());
        musicMute.addActionListener(e -> {
            if(musicMute.isSelected()){
                SettingSound.set_Mmute(true);
            }
            else if(!musicMute.isSelected()){
                SettingSound.set_Mmute(false);
            }
        });
        
        
        music.add(musicSlider);
        music.add(musicMute);
        centerPane.add(musicText);
        centerPane.add(music);

        //SFX
        JLabel sfxText = new JLabel();
        sfxText.setIcon(new MyImageIcon(componentPath+"sound.png"));
        sfxText.setHorizontalAlignment(JLabel.LEFT);
        sfxText.setOpaque(false);

        JLabel sfx = new JLabel();
        sfx.setLayout(new FlowLayout());
        sfx.setOpaque(false);
        sfx.setIcon(new MyImageIcon(componentPath+"soundIcon.png"));

        JSlider sfxSlider = new JSlider(-40,6);
        sfxSlider.setPreferredSize(new Dimension(200, 60));
        sfxSlider.setOpaque(false);
        sfxSlider.setValue(Math.round(SettingSound.get_SVolume()));
        sfxSlider.addChangeListener(e -> SettingSound.adjust_SVolume(sfxSlider.getValue()));

        MyCheckBox sfxMute = new MyCheckBox("Mute",SettingSound.getSmute());
        sfxMute.addActionListener(e -> {
            if(sfxMute.isSelected()){
                SettingSound.set_Smute(true);
            }
            else if(!sfxMute.isSelected()){
                SettingSound.set_Smute(false);
            }
        });

        sfx.add(sfxSlider);
        sfx.add(sfxMute);
        centerPane.add(sfxText);
        centerPane.add(sfx);

        panel.add(centerPane,JLayeredPane.DRAG_LAYER);
        

    }
    
    //set soundctrl form startPane
    public static void setSoundCtrl(MySoundCtrl soundc){
        SettingSound=soundc;
    }
    
    
}
