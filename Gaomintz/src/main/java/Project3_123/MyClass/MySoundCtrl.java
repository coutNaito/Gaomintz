package Project3_123.MyClass;

public class MySoundCtrl {

    public String soundpath = "src/main/java/Project3_123/resource/Sound/";
    public MySound M_bg = new MySound(soundpath + "BGMusic.wav");
    public MySound S_Click = new MySound(soundpath + "Clicked.wav");
    public MySound S_GameStart = new MySound(soundpath + "GameStart.wav");
    public MySound S_Coin = new MySound(soundpath + "coin.wav");
    public MySound S_Punch = new MySound(soundpath + "punch.wav");
    public MySound S_GameDeath = new MySound(soundpath + "DeathSound.wav");
    
    private float MusicVolume = -17f, SoundVolume = -17f;
    private boolean MusicMute = false, SoundMute = false;
    
    
    public MySoundCtrl() {
    }


    public void playM() {
        M_bg.setCurrentVolume(MusicVolume);
        M_bg.playLoop();
    }

    public void stopM() {
        M_bg.stop();
    }

    public void adjust_MVolume(float volume) {
        MusicVolume = volume;
        M_bg.setCurrentVolume(volume);
    }

    public void adjust_SVolume(float volume) {
        SoundVolume = volume;
    }

    public void playSFX(MySound sfx) {
        if (!SoundMute) {
            sfx.setCurrentVolume(SoundVolume);
            sfx.playOnce();
        }
    }
    
    public void set_Mmute(boolean b) {
        MusicMute = b;
        if (MusicMute) {
            M_bg.stop();
        } else {
            M_bg.playLoop();
        }
    }

    public void set_Smute(boolean b) {
        SoundMute = b;
    }
    
    public boolean getSmute() {
        return SoundMute;
    }
    
    public boolean getMmute() {
        return MusicMute;
    }
    
    public float get_MVolume(){
        return MusicVolume;
    }
    
    public float get_SVolume(){
        return SoundVolume;
    }
    
    
}
