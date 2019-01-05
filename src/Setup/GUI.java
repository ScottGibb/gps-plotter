package Setup;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    private CreateListener createController;

    private JFrame createWindow;
    private JTextField openFileName;
    private JButton openFileButton;

    public GUI(){

        createController = new CreateListener(this);

        initWindow();
        addListeners();
    }

    private void initWindow(){
        //Create window
        createWindow = new  JFrame("File Name");
        createWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create main panel and add padding
        JPanel mainPanel = new JPanel(new GridLayout(3,1));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        mainPanel.add(new JLabel("Please enter file name below"));


        JPanel newFileName = new JPanel();

        openFileName = new JTextField();
        openFileName.setPreferredSize(new Dimension(100,20));
        newFileName.add(openFileName);

        newFileName.add(new JLabel(".csv"));

        mainPanel.add(newFileName);

        openFileButton = new JButton("Enter");
        mainPanel.add(openFileButton);


        createWindow.add(mainPanel);
        createWindow.setVisible(true);
        createWindow.setLocationRelativeTo(null);
        createWindow.setLocation(createWindow.getX(),createWindow.getY()-200);
        createWindow.pack();
    }

    public void closeCreateWindow(){
        createWindow.dispose();
    }

    public String getNewFileName(){
        String data = openFileName.getText();
        openFileName.setText("");
        return data;
    }

    private void addListeners(){
        openFileButton.setActionCommand("New File Name");
        openFileButton.addActionListener(createController);
    }

    public void invalidPath(){
        tempWindow("Invalid path","Please enter a valid path");
        openFileName.setText("18_13_41.151135");

    }

    private void tempWindow(String title, String message){
        JFrame frame = new JFrame(title);
        JPanel p = new JPanel(new GridLayout(2,1));

        p.add(new JLabel(message));
        JButton button = new JButton("Ok");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        p.add(button);

        frame.add(p);
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);
        frame.setLocation(createWindow.getX(),createWindow.getY()-200);
        frame.pack();
    }
}
