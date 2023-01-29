
package Project3_123.StartGame;

import javax.swing.*;
import java.awt.*;

public class BlockingTile extends TileObject {
    
    private boolean isBlock = false;

    public BlockingTile(){
    }
    public BlockingTile(Project3_123.StartGame.gameManagement gm){
        super(gm);
    }
    public void setBlock(boolean b){
        isBlock = b;
        if(isBlock) setIsTop(false);
        else setIsTop(true);
    }
    public boolean isBlock(){
        return isBlock;
    }
}
