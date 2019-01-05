package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;




public class Readin {
    private String file;

    public Readin(){
        //file name : 18_13_41.151135

    }

    public void setPath(String path){
        this.file = path;
    }

    /**
     * Helper method
     * Check if the expected location of the file holding the data is true
     */
    public boolean fileExists(String p){
        File f = new File("GPS/files/formatted data/"+p+".csv");
        return f.exists();
    }

    public ArrayList<String> getRawData(){
        assert fileExists(file) : "Error. File not found.";
        ArrayList<String> data= new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File("GPS/files/formatted data/"+file+".csv"));
            scan.useDelimiter("\n");
            while(scan.hasNext()){
                data.add(scan.next());

            }
            scan.close();





        } catch (FileNotFoundException e) {
            System.out.println("Error. File not found");
        }

        return data;
    }
}
