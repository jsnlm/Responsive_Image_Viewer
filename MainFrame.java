import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MainFrame extends JFrame implements Observer, ComponentListener {

    ImageCollectionModel model;
    Toolbar toolbar;
    JPanel p;
    ImageCollectionView mainPanel;
    JScrollPane mainPanelContainer;

    public MainFrame(ImageCollectionModel m, Toolbar t){
        super("A3");
        this.model = m;
        this.toolbar = t;

        p = new JPanel(new GridBagLayout());
        this.getContentPane().add(p);

        this.setMinimumSize(new DimensionUIResource(700, 700));

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.weightx = 1.0;
        p.add(t, c);

        mainPanel = new ImageCollectionView(m);
        m.addObserver(mainPanel);
//        mainPanel.addListener(this::fixTheSize);

        mainPanelContainer = new JScrollPane(mainPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanelContainer.getVerticalScrollBar().setUnitIncrement(10);
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        p.add(mainPanelContainer, c);

        this.pack();
        this.setVisible(true);

        this.addComponentListener(this);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (model.saveData())
                {
                    JOptionPane.showMessageDialog(MainFrame.this, "Model was saved successfuly");
                    System.exit(0);
                }
                else{
                    JOptionPane.showMessageDialog(MainFrame.this, "Saving was UNsuccessful.\n\rPlease try again.");
                }
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("MainFrame update()");
        fixTheSize();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        System.out.println("Frame was resized");
        fixTheSize();
    }

    public void fixTheSize(){
        if (model.getLayout() == ImageCollectionModel.LayoutType.GRID){
//            mainPanel.setPreferredSize(this.getSize());
//            mainPanel.setMaximumSize(this.getSize());
//            mainPanel.setMinimumSize(this.getSize());
            int w = (int)mainPanelContainer.getSize().getWidth();
            int h = 100000;



            int numVisible = 0;
            for(ImageView img : mainPanel.imagesList){
                boolean ajfeigr = img.isShowing();
                if (ajfeigr){
                    numVisible++ ;
                }
            }
            System.out.println("numVisible : " + numVisible);


            h = w/ImageView.viewW;
            h = (int)Math.ceil(mainPanel.imagesList.size()/(float)h);
            h = h * ImageView.viewH;
//
//            System.out.println("h : " + h);
////            h = mainPanel.imagesList.size()/(w/ImageView.viewW) * ImageView.viewH;

//            FlowLayout egth = (FlowLayout)mainPanel.getLayout();
            mainPanel.setMaximumSize(new Dimension(w, h));
            mainPanel.setPreferredSize(new Dimension(w, h));
        }
        else{
            mainPanel.setPreferredSize(null);
            mainPanel.setMaximumSize(null);
            mainPanel.setMinimumSize(null);
        }
        mainPanelContainer.revalidate();
        mainPanel.revalidate();
        revalidate();
    }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}
}
