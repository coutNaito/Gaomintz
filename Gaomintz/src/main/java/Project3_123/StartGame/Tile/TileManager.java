package Project3_123.StartGame.Tile;

import Project3_123.StartGame.gameManagement;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    gameManagement gm;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(gameManagement gm, String scene){
        this.gm = gm;
        tile = new Tile[10];
        mapTileNum = new int[gm.getMaxScreenCol()][gm.getMaxScreenRow()];
        getTileImage();
        loadMap(scene);
    }

    public void getTileImage(){
        try{

            File file = new File("src/main/java/Project3_123/StartGame/resources/Tile/grass.png");
            FileInputStream grass = new FileInputStream(file);
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(grass);        //ProblemCommand

            file = new File("src/main/java/Project3_123/StartGame/resources/Tile/path.png");
            FileInputStream path = new FileInputStream(file);
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(path);

            file = new File("src/main/java/Project3_123/StartGame/resources/Tile/water.png");
            FileInputStream water = new FileInputStream(file);
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(water);

            file = new File("src/main/java/Project3_123/StartGame/resources/Tile/street.png");
            FileInputStream street = new FileInputStream(file);
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(street);

            file = new File("src/main/java/Project3_123/StartGame/resources/Tile/goal.png");
            FileInputStream goal = new FileInputStream(file);
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(goal);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void loadMap(String scene){

        try{
            File file = new File("src/main/java/Project3_123/StartGame/resources/map/" + scene + ".txt");
            FileInputStream map = new FileInputStream(file);
            InputStream is = map;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gm.getMaxScreenCol() && row < gm.getMaxScreenRow()){

                String line = br.readLine();

                while(col < gm.getMaxScreenCol()){
                    String number[] = line.split(" ");
                    int num = Integer.parseInt(number[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gm.getMaxScreenCol()){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){

        int col = 0;
        int row = gm.getMaxScreenRow()-1;

        while(col < gm.getMaxScreenCol() && row >= 0){
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, col*gm.getTileSize(), row*gm.getTileSize(), gm.getTileSize(), gm.getTileSize(), null);
            col++;
            if(col == gm.getMaxScreenCol()) {
                col =0;
                row--;
            }
        }
    }
}
