import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Mark Falconer on 23/12/2018.
 */


public class Readin {
    private String path;

    public Readin(){
//        path = getFileName();
        path = "GPS/files/formatted data/18_13_41.151135.csv";
        getRawData();
    }

    private String getFileName(){
        Scanner scan = new Scanner(System.in);
        String possiblePath;
        do{
            System.out.println("Please enter the file name");
            possiblePath = scan.next();
            possiblePath = "GPS/files/formatted data/" + possiblePath + ".csv";
        }while(!fileExists(possiblePath));

        return possiblePath;
    }

    public boolean fileExists(){
        File f = new File(path);
        return f.exists();
    }

    private boolean fileExists(String path){
        System.out.println("Path is " + path);
        File f = new File(path);
        return f.exists();
    }

    private ArrayList<String> getRawData(){
        assert fileExists() : "Error. File not found.";
        ArrayList<String> data= new ArrayList<>();;
        try {
            Scanner scan = new Scanner(new File(path));
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
