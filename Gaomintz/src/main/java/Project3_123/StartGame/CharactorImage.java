
package Project3_123.StartGame;

public class CharactorImage implements GameConfig {

    private String name;
    private int width = 16;
    private int height = 32;
    private int scale;
    private double userScale = 1.0;
    private String walk; 
    private String[] availDirection;

    private int row = 1, col = 1;

    private String path;
    private String[][] imgFile;

    private MyImageIcon[][] allImg;
    private MyImageIcon[] allDeadImg;
    
    public CharactorImage(){}
    public CharactorImage(double sc){
        path = "src/main/java/Project3_123/StartGame/resources/Dead/";
        walk = "";
        availDirection = new String[]{""};
        width = 128;
        height = 128;
        row = 1;
        col = 14;
        scale = 1;
        userScale = sc;
        imgFile = new String[row][col];
        fillArray();
        setAllImg();
    }

    public CharactorImage(String n, String[] d,boolean isWalk, int num, int[] size, double sc) {
        name = n;
        availDirection = d;
        width = size[0];
        height = size[1];
        row = d.length;
        col = num;
        userScale = sc;
        
        path = "src/main/java/Project3_123/StartGame/resources/" + name + "/";
        
        if(height>=width) scale = tileSize / height;
        else scale = tileSize/width;

        
        if(isWalk) walk = "w/";
        else walk = "i/";

        imgFile = new String[row][col];
        fillArray();
        setAllImg();
    }

    public void fillArray() {
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                 imgFile[i][j] = path + walk +availDirection[i] +"/"+ Integer.toString(j + 1) + ".png";
            }
        }
    }

    public void setAllImg() {
        allImg = new MyImageIcon[row][col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                allImg[r][c] = new MyImageIcon(imgFile[r][c]).
                        resize((int) getScaledWidth(), (int) getScaledHeight());
            }
        }

    }
    public void SetUserScale(double sc){
        userScale = sc;
    }
    public MyImageIcon[][] getAllImg(){
        return allImg;
    }

    public double getScaledWidth() {
        return width * scale * userScale;
    }

    public double getScaledHeight() {
        return height * scale * userScale;
    }
    
    public int getColumn(){
        return col;
    }
    

};
