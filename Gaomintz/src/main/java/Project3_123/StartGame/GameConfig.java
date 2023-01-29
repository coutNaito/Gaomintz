package Project3_123.StartGame;


public interface GameConfig {
    final int originalTileSize = 16; // file tile size = 16 * 16 pixel^2
    final int tileScale = 4;
    final int tileSize = originalTileSize * tileScale; // 96*96 pixel^2
    
    final int maxScreenCol =  18;
    final int maxScreenRow = 10;
    final int screenWidth = tileSize * maxScreenCol; // 14; 1344 pixel // 576
    final int screenHeight = tileSize * maxScreenRow;  //8; 768 pixel //320
    
    
}
