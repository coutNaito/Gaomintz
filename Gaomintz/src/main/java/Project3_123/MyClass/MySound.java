package Project3_123.MyClass;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class MySound {

    private Clip clip;
    FloatControl fc;
    float currentVolume = -17f;
    
    public MySound(){}

    public MySound(String filename) {
        try {
            File file = new File(filename);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            fc.setValue(currentVolume);
        } catch (Exception e) {
            System.err.println("Audio '" + filename + "' cannot be opened. " + e);
            clip = null;
        }
    }

    public void playOnce() {
        if (clip != null) {
            clip.setMicrosecondPosition(0);
            clip.start();
        }
    }

    public void playLoop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void setCurrentVolume(float cv) {
        currentVolume = cv;
        if (clip != null) {
            fc.setValue(currentVolume);
        }
    }

}
