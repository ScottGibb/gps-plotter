package Main;

/**
 * Created by Mark Falconer on 05/01/2019.
 */
public class Update implements Runnable {

    MainGUI gui;
    Readin input;
    public Update(MainGUI gui, Readin input){
        this.gui = gui;
    }
    @Override
    public void run() {
        System.out.println("hello");
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
        String[] data = new String[6];

        return null;
    }
}
