import java.io.File;
import java.util.Scanner;

/**
 * Created by Mark Falconer on 23/12/2018.
 */


public class Readin {
    private String path;
    public Readin(){
        path = getFileName();
    }

    public String getFileName(){
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
        System.out.println("Path is " + path);
        File f = new File(path);
        return f.exists();
    }

    public boolean fileExists(String path){
        System.out.println("Path is " + path);
        File f = new File(path);
        return f.exists();
    }
}
