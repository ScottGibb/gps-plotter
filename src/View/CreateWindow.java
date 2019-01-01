package View;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CreateWindow {
    private JFrame window;
    private JLabel label1;
    private JLabel label2;
    private JTextField text;
    private JButton button;

    public CreateWindow(){
        initWindow();
        addListeners();
    }

    private void initWindow(){
        //Create window
        window = new  JFrame("File Name");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create main panel and add padding
        JPanel mainPanel = new JPanel(new GridLayout(3,1));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        label1 = new JLabel("Please enter file name below");
        mainPanel.add(label1);


        JPanel newFileName = new JPanel();

        text = new JTextField();
        text.setPreferredSize(new Dimension(100,20));
        newFileName.add(text);

        label2 = new JLabel(".csv");
        newFileName.add(label2);

        mainPanel.add(newFileName);

        button = new JButton("Enter");
        mainPanel.add(button);

        window.add(mainPanel);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setLocation(window.getX(),window.getY()-200);
        window.pack();
    }

    private void addListeners(){

    }
}
