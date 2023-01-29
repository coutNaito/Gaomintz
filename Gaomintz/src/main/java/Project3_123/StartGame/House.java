package Project3_123.StartGame;

import java.util.ArrayList;

public class House extends StaticObject {

    public House(String n, int x, int y, Project3_123.StartGame.gameManagement gm) {
        super(n, x, y, gm);

        setFileSize(43, 63);
        setFileScale(1.1);
        this.setFileScale_Height(0.9);
        setSize_of_Block(2, 2);

        // Set Blocking Area
        BlockingTile block = new BlockingTile(gm);

        block.setTile_Location(curX, curY+gm.getTileSize());
        block.setWidth_Height_Block(2, 1);
        block.setBlock(true);
        setBlockingTile(block);
    }
    public House addInList(ArrayList<StaticObject> al) {
        al.add(this);
        return this;

    }
}
