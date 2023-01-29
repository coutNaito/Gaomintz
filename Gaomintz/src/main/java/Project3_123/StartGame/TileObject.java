
package Project3_123.StartGame;

import javax.swing.*;

public abstract class TileObject extends JLabel {
    
    private Project3_123.StartGame.gameManagement gameManagement;
    protected int curX = 0, curY = 0;
    private double width_block = 1, height_block= 1;
    
    private int tileSize;
    
    private boolean isTop = false;
    private boolean isDisabled = false;
    
    public TileObject() {}
    public TileObject(Project3_123.StartGame.gameManagement gm){
        
        gameManagement = gm;
        tileSize = gm.getTileSize();
        curX = -tileSize; curY = -tileSize;
        LabelSetting();
    }
    
    public void LabelSetting(){
        setBounds(curX, curY, (int)(width_block * tileSize), (int)(height_block * tileSize));
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
    }
    
    public void setTile_Location(int x, int y){
        curX = x; curY = y;
        LabelSetting();
    }
    
    public void setWidth_Height_Block(double w, double h){
        width_block = w; 
        height_block = h;
        LabelSetting();
    }
    public void setWidth_Height_Custom(int w, int h){
        setBounds(curX, curY, w, h);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
    }
    
    public boolean isTop(){
        return isTop;
    }
    
    public void setIsTop(boolean t){
        isTop = t;
    }
    
    public void setIsDisabled(boolean d){
        isDisabled = d;
    }
    
    public boolean isDisabled(){
        return isDisabled;
    }
    
    public int getTile_CurX(){
        return curX;
    }
    public int getTile_CurY(){
        return curY;
    }
}
