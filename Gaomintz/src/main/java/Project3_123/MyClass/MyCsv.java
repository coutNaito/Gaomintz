package Project3_123.MyClass;

import java.io.*;
import java.util.*;

public class MyCsv{
    private String path = "src/main/java/Project3_123/resource/";
    private String scorefile = path + "Score.csv";
    private Scanner keyboardScan;
    private String[][] data;
    private int countRows=0;
    private boolean bool_openfile;
    
    
       public MyCsv() throws IOException {
        bool_openfile = false;
        keyboardScan = new Scanner(System.in);
        //check file
        while (!bool_openfile) {
            try(FileReader fileR = new FileReader(scorefile);){
                bool_openfile = true;
            } catch (FileNotFoundException e) {
                System.out.print(e);
                System.out.println("\nNew file name = ");
                scorefile = path + keyboardScan.next();
            }
        }

        //set data array size
        try(BufferedReader reader_file = new BufferedReader(new FileReader(scorefile));){
            while (reader_file.readLine() != null) {
                countRows++;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        data = new String[countRows][3];
        
        
    }
       
       public void IncreaseArraySize(){
            countRows+=1;
            data = new String[countRows][3];
       }
       
    
        public void FileWriter(){
            try(BufferedWriter bf = new BufferedWriter(new FileWriter(scorefile,false));
                    PrintWriter pw = new PrintWriter(bf);){
                for(int i=0; i<countRows; i++){
                    pw.println(data[i][1]+","+data[i][2]);
                    pw.flush();
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }  
        }
        
        
        public void FileReader(){
            int temp=0;
            String line; 
            try(BufferedReader reader_file = new BufferedReader(new FileReader(scorefile));){
                while ((line=reader_file.readLine()) != null){
                String[] bufferString = line.split(",");
                data[temp][0] = String.valueOf(temp+1);
                data[temp][1] = bufferString[0];
                data[temp][2] = bufferString[1];
                temp++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
                
        }
            
        public String[][] getData(){
            return data;
        }
        
        public int getRows(){
            return countRows;
        }
        
        
        public void setData(int numRow,String name,int score){
            data[numRow][0] = String.valueOf(numRow+1);
            data[numRow][1] = name;
            data[numRow][2] = String.valueOf(score);
        }
        
        public void sortScore(String[][] data){
            int rank=1;
            int column=3;
            //sort score
            Arrays.sort(data, new Comparator<String[]>(){
                @Override
                public int compare(String[] o1, String[] o2) {
                    if(Integer.parseInt(o1[column-1])>Integer.parseInt(o2[column-1])){
                        return -1;
                    }
                    else{
                        return 1;
                    }
                }
            });
            //set rank
            for(int i=0; i<countRows; i++){
                for(int j=0; j<3; j++){
                    if(j==0){
                        data[i][j]=String.valueOf(rank);
                        rank++;
                    }                
                }
            }
            
        }


}
