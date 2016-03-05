import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.util.Observable;
import java.util.Observer;

public class MainFrame extends JFrame implements Observer {

    ImageCollectionModel model;
    Toolbar toolbar;
    JPanel mainPanel;
    public MainFrame(ImageCollectionModel m, Toolbar t){
        //this = new JFrame("A3");
        this.model = m;
        this.toolbar = t;

        mainPanel = new JPanel();
        this.getContentPane().add(mainPanel);
        this.setMinimumSize(new DimensionUIResource(300, 300));

        mainPanel.add(t);
        mainPanel.add(new JButton("Filler JComponent"));

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
