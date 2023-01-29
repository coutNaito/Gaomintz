
package Project3_123.StartGame;

public class gameManagement{
    
    // Screen Settings
    final private int originalTileSize = 16; // file tile size = 16 * 16 pixel^2
    final private int scale = 4;
    final private int tileSize = originalTileSize * scale; // 96*96 pixel^2
    
    final private int maxScreenCol =  18;
    final private int maxScreenRow = 10;
    final private int screenWidth = tileSize * maxScreenCol; // 14; 1344 pixel // 576
    final private int screenHeight = tileSize * maxScreenRow;  //8; 768 pixel //320
    private CharactorLabel charData;
    private int CurrentScene = 1;
    private boolean chagenScene = false;
    
    public gameManagement(String n){
        ////Get Charactor input EX Skin asscessory
        charData = new CharactorLabel(n, 9, 10,this);
    }
    public int getScreenWidth(){
        return screenWidth;
    }
    
    public int getScreenHeight(){
        return screenHeight;
    }
    
    public int getTileSize(){
        return tileSize;
    }

    public int getMaxScreenCol(){
        return maxScreenCol;
    }
    public int getMaxScreenRow(){
        return maxScreenRow;
    }
    public int getCurrentScene() { return CurrentScene;}
    public void setCurrentScene(int scene) {
        chagenScene = true;
        CurrentScene = scene;
    }
    public void setChangeScene_False() {chagenScene = false;}
    public boolean getChangeScene() { return chagenScene;}
    public boolean isCharactorAlive() { return charData.isAlive();}
    public boolean isCharactorWin() { return charData.isWin();}
    public CharactorLabel getCharData() {return charData;}

}
