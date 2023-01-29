package Project3_123.StartGame;

import java.util.ArrayList;

public class Bush extends StaticObject {
    public Bush (String n, int x, int y, Project3_123.StartGame.gameManagement gm){
        super(n, x, y, gm);

        setFileSize(32, 17);
        setFileScale(0.9);
        setFileScale_Width(0.9);
        setSize_of_Block(1, 1);

        // Set Blocking Area
        BlockingTile block = new BlockingTile(gm);
        block.setTile_Location(curX, curY);
        block.setWidth_Height_Block(1, 1);
        block.setBlock(true);
        setBlockingTile(block);
    }
    public Bush addInList(ArrayList<StaticObject> al) {
        al.add(this);
        return this;

    }
}
