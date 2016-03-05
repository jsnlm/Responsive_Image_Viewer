import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by j on 2016-03-05.
 */
public class Toolbar extends JPanel implements Observer {

    ImageCollectionModel model;
    JRadioButton listButton;
    JRadioButton gridButton;

    public Toolbar(ImageCollectionModel m) {
        this.model = m;
        //super();
        listButton = new JRadioButton(new ImageIcon(getClass().getResource("listIcon.png")));
        gridButton = new JRadioButton(new ImageIcon(getClass().getResource("gridIcon.png")));

        ButtonGroup layoutBG = new ButtonGroup();
        layoutBG.add(listButton);
        layoutBG.add(gridButton);

        this.add(listButton);
        this.add(gridButton);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
