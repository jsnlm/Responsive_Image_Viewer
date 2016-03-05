import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class Toolbar extends JPanel implements Observer {

    ImageCollectionModel model;
    JButton listButton;
    JButton gridButton;
    JLabel title;
    ToolbarFilter filter;
    public Toolbar(ImageCollectionModel m) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.model = m;
        listButton = new JButton(new ImageIcon(getClass().getResource("listIcon.png")));
        listButton.setDisabledIcon(new ImageIcon(getClass().getResource("listIconDisabled.png")));
        gridButton = new JButton(new ImageIcon(getClass().getResource("gridIcon.png")));
        gridButton.setDisabledIcon(new ImageIcon(getClass().getResource("gridIconDisabled.png")));
        title = new JLabel("Fotag!");

        filter = new ToolbarFilter(this.model);
        model.addObserver(filter);

        this.add(listButton);
        this.add(gridButton);
        this.add(title);
        this.add(Box.createHorizontalGlue());
        this.add(filter);


        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setLayout(ImageCollectionModel.LayoutType.LIST);
            }
        });
        gridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setLayout(ImageCollectionModel.LayoutType.GRID);
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if (model.getLayout() == ImageCollectionModel.LayoutType.GRID){
            listButton.setEnabled(true);
            gridButton.setEnabled(false);
        }
        else{
            listButton.setEnabled(false);
            gridButton.setEnabled(true);
        }
    }
}
