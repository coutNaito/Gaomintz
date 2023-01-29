
package Project3_123.StartGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MySoundEffect
{
    private Clip clip;
    private String filePath = "src/main/java/Project3_123/StartGame/resources/Sound/";

    public MySoundEffect(String n)
    {
	try
	{
            java.io.File file = new java.io.File(filePath + n + ".wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
	}
	catch (Exception e) { e.printStackTrace(); }
    }
    public void playOnce()   { clip.setMicrosecondPosition(0); clip.start(); }
    public void playLoop()   { clip.loop(Clip.LOOP_CONTINUOUSLY); }
    public void stop()       { clip.stop(); }
}