import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MainFrame extends JFrame implements Observer {

    ImageCollectionModel model;
    Toolbar toolbar;
    JPanel p;
    ImageCollectionView mainPanel;

    public MainFrame(ImageCollectionModel m, Toolbar t){
        super("A3");
        this.model = m;
        this.toolbar = t;

        p = new JPanel(new GridBagLayout());
        this.getContentPane().add(p);

        this.setMinimumSize(new DimensionUIResource(300, 300));

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.weightx = 1.0;
        p.add(t, c);

        mainPanel = new ImageCollectionView(m);
        m.addObserver(mainPanel);
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        p.add(mainPanel, c);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
