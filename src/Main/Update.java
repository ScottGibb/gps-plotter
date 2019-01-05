package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Mark Falconer on 05/01/2019.
 */
public class Update implements Runnable {

    private float[] max;
    private float[] min;


    MainGUI gui;
    Readin input;
    public Update(MainGUI gui, Readin input){

        this.gui = gui;
        this.input = input;

        max = new float[4];
        min = new float[4];


    }
    @Override
    public void run() {
        int i = 0;
        while(true){
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            String[] data = extractData(i);
            gui.updateInfo(data[0],data[1],data[2],data[3],data[4],data[5]);
            i++;
        }

    }

    private String[] extractData(int index){
        //TODO make work with updating information
        String[] data = new String[6];


        //Update max and min values
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

        float newLatDeg = Float.parseFloat(info[1]);
        float newLatMin = Float.parseFloat(info[2]);
        float newLonDeg = Float.parseFloat(info[3]);
        float newLonMin = Float.parseFloat(info[4]);

        if(index == 0){
            max[0] = newLatDeg;
            min[0] = newLatDeg;

            max[1] = newLatMin;
            min[1] = newLatMin;

            max[2] = newLonDeg;
            min[2] = newLonDeg;

            max[3] = newLonMin;
            min[3] = newLonMin;
        }

        updateMaxMin(newLatDeg, newLatMin, newLonDeg, newLonMin);


        gui.clearImage();
        drawAllInfo(index);

        data[0] = info[0];
        data[1] = info[1];
        data[2] = info[2];
        data[3] = info[3];
        data[4] = info[4];
        data[5] = ""+index;

        return data;
    }

    private void drawAllInfo(int index){
        Scanner scan;
        float oldX=0;
        float oldY = 0;
        float newX, newY;

        float xChange = max[1] - min[1];
        float xSc = 290/ xChange;
        int xScale = Math.round(xSc);

        float yChange = max[3] - min[3];
        float ySc = 190/ yChange;
        int yScale = Math.round(ySc);

        for(int i = 0; i <= index; i++){
            //loop through all data

            //get information
            String data = input.getRawData().get(i);
            scan = new Scanner(data);
            scan.useDelimiter(",");
            String[] info = new String[5];
            for(int j =0;scan.hasNext();j++){
                info[j] = scan.next();
            }

            //make into x and y
            newX = Float.parseFloat(info[2]);
            newY = Float.parseFloat(info[4]);

            //draw to image
            if(i>0){ //only one position would just result in a point, not a line
                try {
                    createImage(oldX,oldY,newX,newY,xScale,yScale);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //update old information
            oldX = newX;
            oldY = newY;



        }
    }
    private void createImage(float oldX, float oldY, float newX, float newY,int xScale, int yScale) throws IOException {

        int minX = Math.round(Float.parseFloat(""+ min[1]));

//        System.out.println("Max " + max[1] + " min " + min[1]);


//        System.out.println("Change: " + xChange + " Scale: " + xScale + " (" + xSc + ") C?urrent effect (o): " + oldX + " -> " + ((oldX-minX))*xScale + " Current effect (n): " + newX + " -> " + ((newX-minX))*xScale );

        //todo: deal with bounds

        // Open a JPEG file, load into a BufferedImage.
        BufferedImage img = ImageIO.read(new File("image.jpg"));

        // Obtain the Graphics2D context associated with the BufferedImage.
        Graphics2D g = img.createGraphics();

        g.setColor(Color.BLUE);

        Random rand = new Random();

        int oX = Math.round((oldX-min[1])*xScale);
        int oY = Math.round((oldY-min[3])*yScale);
        int nX = Math.round((newX-min[1])*xScale);
        int nY = Math.round((newY-min[3])*yScale);

//        System.out.println("(" + oX + ", "+oY + ") ("+ nX + ", " + nY +")");


        g.drawLine(oX, oY,nX,nY);

        g.drawImage(img,null,0,0);

//        // Clean up -- dispose the graphics context that was created.
        g.dispose();
        try {
            File out = new File("image.jpg");

            ImageIO.write(img, "jpg", out);
        }catch(FileNotFoundException e){
            System.out.println("Attempt to kick out file");
//            gui.clearImage();
        }catch(Exception e){
            System.out.println("Attempt to kick out file");
//            gui.clearImage();
        }





    }


    private void updateMaxMin(float newLatDeg,float newLatMin, float newLonDeg, float newLonMin){
        /**max/min layout
         * [0] Latitude degrees
         * [1] Latitude minutes
         * [2] Longitude degrees
         * [3] Longitude minutes
         */

        //Max check
        if(newLatDeg > max[0]){
//            System.out.println("New max Lat deg: old: " + max[0] + " now: " + newLatDeg);
            System.out.println("CHANGE\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nCHANGE");
            max[0] = newLatDeg;
        }
        if(newLatMin > max[1]){
//            System.out.println("New max Lat min: old: " + max[1] + " now: " + newLatMin);
            max[1] = newLatMin;
        }
        if(newLonDeg > max[2]){
//            System.out.println("New max Lon deg: old: " + max[2] + " now: " + newLonDeg);
            System.out.println("CHANGE\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nCHANGE");
            max[2] = newLonDeg;
        }
        if(newLonMin > max[3]){
//            System.out.println("New max Lon min: old: " + max[3] + " now: " + newLonMin);
            max[3] = newLonMin;
        }

        //Min check
        if(newLatDeg < min[0]){
//            System.out.println("New min Lat deg: old: " + min[0] + " now: " + newLatDeg);
//            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            min[0] = newLatDeg;
        }
        if(newLatMin < min[1]){
//            System.out.println("New min Lat min: old: " + min[1] + " now: " + newLatMin);
            min[1] = newLatMin;
        }
        if(newLonDeg < min[2]){
//            System.out.println("New min Lon deg: old: " + min[2] + " now: " + newLonDeg);
//            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            min[2] = newLonDeg;
        }
        if(newLonMin < min[3]){
//            System.out.println("New min Lon min: old: " + min[3] + " now: " + newLonMin);
            min[3] = newLonMin;
        }
    }

}
