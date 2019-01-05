package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Mark Falconer on 05/01/2019.
 */
public class Update implements Runnable {

    private int maxX;
    private int maxY;
    private int minX;
    private int minY;


    MainGUI gui;
    Readin input;
    public Update(MainGUI gui, Readin input){

        this.gui = gui;
        this.input = input;


    }
    @Override
    public void run() {
        int i = 0;
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String[] data = extractData(i);
            gui.updateInfo(data[0],data[1],data[2],data[3],data[4],data[5]);
            i++;
        }

    }

    private String[] extractData(int index){
        //TODO make work with updating information
        String[] data = new String[6];
        String rawData = input.getRawData().get(index);

        Scanner scan = new Scanner(rawData);
        scan.useDelimiter(",");
        String[] info = new String[5];
        for(int i =0;scan.hasNext();i++){
            info[i] = scan.next();
        }

        String time = info[0];
        String lat = info[1];
        String lon = info[3];


        try {
            createImage(lat,lon,index);
        } catch (IOException e) {
            e.printStackTrace();
        }


        data[0] = info[0];
        data[1] = info[1];
        data[2] = info[2];
        data[3] = info[3];
        data[4] = info[4];
        data[5] = "6";

        return data;
    }

    private void createImage(String lat, String lon,int var) throws IOException {


        // Open a JPEG file, load into a BufferedImage.
        BufferedImage img = ImageIO.read(new File("image.jpg"));

        // Obtain the Graphics2D context associated with the BufferedImage.
        Graphics2D g = img.createGraphics();

        g.setColor(Color.BLUE);

        Random rand = new Random();



        g.drawLine(var+20, var+20, var+20, var+20);

        g.drawImage(img,null,0,0);

//        // Clean up -- dispose the graphics context that was created.
        g.dispose();
        File out = new File("image.jpg");
        ImageIO.write(img,"jpg",out);



    }


}
