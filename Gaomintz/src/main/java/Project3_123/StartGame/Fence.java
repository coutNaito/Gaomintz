package Project3_123.StartGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Fence extends StaticObject {
    
    private int fileSize_width, fileSize_height;
    private BlockingTile safeBlock;
    
    public Fence(String n, String align, int x, int y, Project3_123.StartGame.gameManagement gm){
        
        super(n, align, x, y, gm);
        
        if(align.equals("l") || align.equals("r")) setFileSize(16, 48);
        else if(align.equals("u") || align.equals("d")) setFileSize(48, 16);
        setSize_of_Block(1,1);
        
        // Set Blocking Area
        BlockingTile block = new BlockingTile(gm);
        
        if(align.equals("l")) block.setTile_Location(curX - gm.getTileSize(), curY );
        if(align.equals("r")) block.setTile_Location(curX + gm.getTileSize(), curY );
        if(align.equals("u")) block.setTile_Location(curX, curY - gm.getTileSize());
        if(align.equals("d")) block.setTile_Location(curX, curY + gm.getTileSize());
        block.setWidth_Height_Block(1, 1);
        
        setBlockingTile(block);
        
        // Set Initial safe Area
        safeBlock = new BlockingTile(gm);
        safeBlock.setTile_Location(curX, curY);
        safeBlock.setWidth_Height_Block(1, 1);
        safeBlock.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        safeBlock.setBlock(false);
    }
    public BlockingTile getSafeTile(){
        return safeBlock;
    }
    public Fence addInList(ArrayList<Fence> al) {
        al.add(this);
        return this;

    }
}
