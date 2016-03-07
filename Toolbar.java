import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


public class Toolbar extends JPanel implements Observer {

    ImageCollectionModel model;
    JButton listButton;
    JButton gridButton;
    JButton openButton;
    JLabel title;
    JLabel filterLabel;
    ToolbarFilter filter;

    public Toolbar(ImageCollectionModel m) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.model = m;
        listButton = new JButton(new ImageIcon(getClass().getResource("listIcon.png")));
        listButton.setDisabledIcon(new ImageIcon(getClass().getResource("listIconDisabled.png")));
        gridButton = new JButton(new ImageIcon(getClass().getResource("gridIcon.png")));
        gridButton.setDisabledIcon(new ImageIcon(getClass().getResource("gridIconDisabled.png")));
        openButton = new JButton(new ImageIcon(getClass().getResource("openIcon.png")));
        title = new JLabel("Fotag!");
        Font f = title.getFont();
        title.setFont(new Font(f.getName(), f.getStyle(), 30));
        filterLabel = new JLabel("Filter by:");
        filter = new ToolbarFilter(this.model);
        model.addObserver(filter);

        this.add(listButton);
        this.add(gridButton);
        this.add(title);

        this.add(Box.createHorizontalGlue());
        this.add(openButton);
        this.add(filterLabel);
        this.add(filter);

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter() );
                fileChooser.setFileFilter(new FileNameExtensionFilter( "Images", ImageIO.getReaderFileSuffixes()));
                int someVal = fileChooser.showDialog(openButton, "Choose file to Open");

                if (someVal == JFileChooser.APPROVE_OPTION) {
                    model.addPicture(fileChooser.getSelectedFile());
                }
                if (someVal == JFileChooser.CANCEL_OPTION) {
                    System.out.println("No file was selected");
                }
            }
        });
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
