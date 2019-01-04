import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;




public class Readin {
    private String path;

    public Readin(String p){
//        path = "GPS/files/formatted data/18_13_41.151135.csv";
        path = p;

    }


    /**
     * Helper method
     * Check if the expected location of the file holding the data is true
     */
    private boolean fileExists(){
        File f = new File(path);
        return f.exists();
    }

    private ArrayList<String> getRawData(){
        assert fileExists() : "Error. File not found.";
        ArrayList<String> data= new ArrayList<>();
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
