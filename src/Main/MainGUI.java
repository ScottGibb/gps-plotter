package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Mark Falconer on 05/01/2019.
 */
public class MainGUI {
    private Readin input;
    private JLabel picLabel;

    private JLabel speed;
    private JLabel lapCount;
    private JLabel lapTime;
    private JLabel totalTime;
    private JLabel battery;
    private JLabel lastUpdate;


    private JFrame window;

    public MainGUI(Readin input){
        this.input = input;
        initWindow();

        Thread updater = new Thread(new Update(this,input));
        updater.start();


    }

    private void initWindow(){
        speed = new JLabel("speed");
        lapCount = new JLabel("lapCount");
        lapTime = new JLabel("lapTime");
        totalTime = new JLabel("totalTime");
        battery = new JLabel("battery");
        lastUpdate = new JLabel("lastUpdate");


        window = new JFrame("GPS Plotter");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create main panel and add padding
        JPanel mainPanel = new JPanel(new GridLayout(2,1));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        try{
            BufferedImage myPicture = ImageIO.read(new File("image.png"));
            picLabel = new JLabel(new ImageIcon(myPicture));

        }catch(Exception e){
            picLabel = new JLabel("File not found");
            picLabel.setPreferredSize(new Dimension(300,200));
            e.printStackTrace();
        }
        mainPanel.add(picLabel);

        //Data area
        JPanel data = new JPanel(new GridLayout(2,3));

        //speed
        JPanel temp = new JPanel(new GridLayout(1,2));
        temp.add(new JLabel("Speed: "));
        temp.add(speed);
        data.add(temp);

        //lap count
        temp = new JPanel(new GridLayout(1,2));
        temp.add(new JLabel("Lap count: "));
        temp.add(lapCount);
        data.add(temp);

        //lap time
        temp = new JPanel(new GridLayout(1,2));
        temp.add(new JLabel("Lap Time: "));
        temp.add(lapTime);
        data.add(temp);

        //total time
        temp = new JPanel(new GridLayout(1,2));
        temp.add(new JLabel("Total Time: "));
        temp.add(totalTime);
        data.add(temp);

        //Battery
        temp = new JPanel(new GridLayout(1,2));
        temp.add(new JLabel("Battery: "));
        temp.add(battery);
        data.add(temp);

        //Recent time
        temp = new JPanel(new GridLayout(1,2));
        temp.add(new JLabel("Last update: "));
        temp.add(lastUpdate);
        data.add(temp);


        mainPanel.add(data);


        window.add(mainPanel);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setLocation(window.getX(),window.getY()-200);
        window.pack();
    }

    public void updateInfo(String speedString, String lapCountString,String lapTimeString, String totalTimeString,String batteryString, String lastUpdateString){
        speed.setText(speedString);
        lapCount.setText(lapCountString);
        lapTime.setText(lapTimeString);
        totalTime.setText(totalTimeString);
        battery.setText(batteryString);
        lastUpdate.setText(lastUpdateString);

        try{
            BufferedImage myPicture = ImageIO.read(new File("image1.png"));
            picLabel.setIcon(new ImageIcon(myPicture));

        }catch(Exception e){
            picLabel.setText("File not found");
            picLabel.setPreferredSize(new Dimension(300,200));
            e.printStackTrace();
        }
    }

}
