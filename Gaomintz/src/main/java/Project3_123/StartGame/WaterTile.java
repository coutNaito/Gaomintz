package Project3_123.StartGame;

import javax.swing.*;
import java.awt.*;

public class WaterTile extends TileObject {
    
    private boolean isDrown = false;
    private boolean isEnabled = false;
    
    public WaterTile(Project3_123.StartGame.gameManagement gm){
        super(gm);
    }
    
    public boolean isDrown(){
        return isDrown;
    }
    
    public void setIsDrown(boolean d){
        isDrown = d;
    }
    public boolean isEnabled(){
        return isEnabled;
    }
    
    public void setIsEnabled(boolean e){
        isEnabled = e;
    }
}
