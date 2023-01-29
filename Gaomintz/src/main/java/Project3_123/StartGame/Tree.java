
package Project3_123.StartGame;

import java.util.ArrayList;

public class Tree extends StaticObject {
    public Tree (String n, int x, int y, Project3_123.StartGame.gameManagement gm) {
        super(n, x, y, gm);

        setFileSize(15, 28);
        setFileScale(0.9);
        setFileScale_Height(0.9);
        setFileScale_Width(1.2);
        setSize_of_Block(1, 2);

        // Set Blocking Area
        BlockingTile block = new BlockingTile(gm);

        block.setTile_Location(curX, curY+gm.getTileSize());
        block.setWidth_Height_Block(1, 1);
        block.setBlock(true);
        setBlockingTile(block);
    }
    public Tree addInList(ArrayList<StaticObject> al) {
        al.add(this);
        return this;

    }
}
