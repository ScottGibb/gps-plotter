
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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

    public String getNewFileName(){
        String data = openFileName.getText();
        openFileName.setText("");
        return data;
    }

    private void addListeners(){
        openFileButton.setActionCommand("New File Name");
        openFileButton.addActionListener(createController);
    }
}
