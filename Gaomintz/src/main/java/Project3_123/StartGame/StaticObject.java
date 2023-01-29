package Project3_123.StartGame;

import javax.swing.*;

public class StaticObject extends JLabel implements GameConfig {
    
    private String name;
    protected int curX, curY;
    protected double width, height;
    private int fileSize_width, fileSize_height;
    private double fileScale_width = 1.0;
    private double fileScale_height = 1.0;
    
    protected Project3_123.StartGame.gameManagement gameManagement;
     
    
    private boolean isHitObject = false;
   
    private String path = "src/main/java/Project3_123/StartGame/resources/StaticObject/";
    
    private MyImageIcon Obj_Img;
    
    private int hor_align = JLabel.CENTER;
    private int ver_align = JLabel.CENTER;
    
    private BlockingTile blockingTile;
    
    public StaticObject () {
    }
    public StaticObject (String n, String a, int x, int y, Project3_123.StartGame.gameManagement gm) {
        
        
        if (a.equals("l")){
            name = n +"_vertical";
            hor_align = JLabel.LEFT;
            ver_align = JLabel.CENTER;
        }
        else if (a.equals("r")){
            name = n + "_vertical";
            hor_align = JLabel.RIGHT;
            ver_align = JLabel.CENTER;
        }
        else if (a.equals("u")){
            name = n+"_horizontal";
            hor_align = JLabel.CENTER;
            ver_align = JLabel.TOP;
        }
        else if (a.equals("d")){
            name = n+"_horizontal";
            hor_align = JLabel.CENTER;
            ver_align = JLabel.BOTTOM;
        }

        curX = (x-1)*gm.getTileSize();
        curY = (y-1)*gm.getTileSize();
        gameManagement = gm;

        
        path = path + name + ".png";
        
    }
    
    public StaticObject(String n, int x, int y, Project3_123.StartGame.gameManagement gm){
        
        name = n;

        curX = (x-1)*gm.getTileSize();
        curY = (y-1)*gm.getTileSize();
        gameManagement = gm;
       
        
        path = path + name + ".png";
        
        
    }
    
    public void setHitObject(boolean h){
        isHitObject = h;
    }
    
    public boolean isHitObject(){
        return isHitObject;
    }
    
    
    public void setFileSize(int w, int h){
        
        width = w;
        height = h;
    }
    public void setFileScale_Width(double scw){
        fileScale_width = scw;
    }
    public void setFileScale_Height(double sch){
        fileScale_height = sch;
    }
    public void setFileScale(double scw){
        fileScale_width = scw;
        fileScale_height = scw;
    }
    public void setFileScale(double scw, double sch){
        fileScale_width = scw;
        fileScale_height = sch;
    }
    public void setSize_of_Block(int x, int y){
        double scale;
        
        if(height>=width) scale =  tileSize/ height;
        else scale = tileSize/width;
        
        width =  width * scale * x * fileScale_width;
        height = height * scale * y * fileScale_height;
        
        LabelSetting(x, y);
        
        
    }
    public void setBlockingTile(BlockingTile block){
        blockingTile = block;
    }
    
    public BlockingTile getBlockingTile(){
        return blockingTile;
    }
    
    public void LabelSetting(int x, int y){
        
        setBounds(curX, curY, tileSize * x, tileSize * y);
        setHorizontalAlignment(hor_align);
        setVerticalAlignment(ver_align);
        setObjectImage();
    }
    public void setObjectImage(){
        Obj_Img = new MyImageIcon(path).resize((int)width, (int)height);
        setIcon(Obj_Img);
    }
}
