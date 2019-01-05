package Setup;

import Main.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mark Falconer on 04/01/2019.
 */
public class CreateListener implements ActionListener{

    private Readin r;
    private GUI gui;
    public CreateListener(GUI gui){

        this.gui = gui;
        r = new Readin();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String event = e.getActionCommand();

        switch(event){
            case "New File Name":
                String path = gui.getNewFileName();
                if(r.fileExists(path)){
                    r.setPath(path);
                    gui.closeCreateWindow();

                }else{
                    gui.invalidPath();
                }
        }
    }
}
