package Project3_123.StartGame;

public class FPS {
    private double fps = 0;
    private double drawInterval, 
                   nextDrawTime, 
                   remainingTime;
    public FPS (){}
    public FPS(double f) {
        fps = f;
        setInitialTime();
    }
    
    public void setFPS(int f){
        fps = f;
    }
    public void implementFPS(){
       
        try{
            remainingTime = nextDrawTime - System.nanoTime();  //sleep Remaining Time to fit FPS
            remainingTime = remainingTime/1000000;   //convert Nanosec to Millisec
            
            if(remainingTime < 0) {
                remainingTime = 0;
            }
            
            Thread.sleep((long) remainingTime);
            setInitialTime();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        
    }
    
    public void setInitialTime(){
        drawInterval = 1000000000/fps;  // 0.01666 seconds
        nextDrawTime = System.nanoTime() + drawInterval;  // next Screen update time
    }
    
}
