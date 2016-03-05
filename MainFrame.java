import javax.swing.*;
import java.util.jar.JarEntry;

public class MainFrame extends JFrame {

    ImageCollectionModel model;
    public MainFrame(ImageCollectionModel m){
        //this = new JFrame("A3");
        this.model = m;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new JButton("afef"));
        this.pack();
        this.setVisible(true);
    }
}
