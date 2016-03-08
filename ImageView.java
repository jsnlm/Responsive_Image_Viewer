import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.EventListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

interface RatingListener extends EventListener {
    void onRating(ImageView v);
}

public class ImageView extends JPanel implements Observer {

    ImageModel model;
    ImageRating rating;
    JLabel imagePart, name, createdDate;
    LayoutManager listLayout, gridLayout;

    EventListenerList listeners;

    static int viewW = 200;
    static int viewH = 271;

    public ImageView(ImageModel m) {
        this.model = m;
        rating = new ImageRating(m);
        m.addObserver(rating);

        listeners = new EventListenerList();

        listLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        gridLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(listLayout);
//        this.setLayout(new BorderLayout());
        Random r = new Random();
        this.setBackground(new Color(r.nextInt(250), r.nextInt(250), r.nextInt(250)));

//        this.setPreferredSize(new Dimension(200, 250));
        Image img;
        try{
            img = ImageIO.read(m.getFile());
        }
        catch (Exception e){
            img = null;
        }
//        imagePart = new JLabel( new ImageIcon(Utilities.getScaledImage(img, 200, 200)) );
        imagePart = new JLabel( new ImageIcon(Utilities.scaleWithAspectRatio(img, viewW, 200)) );

        name = new JLabel("Name : " + m.getFileName());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        createdDate = new JLabel("Date Created : " + df.format(m.getCreatedDate().toMillis()) );

        imagePart.setAlignmentX(LEFT_ALIGNMENT);
        name.setAlignmentX(LEFT_ALIGNMENT);
        rating.setAlignmentX(LEFT_ALIGNMENT);

        JPanel notPictureContent = new JPanel();
        notPictureContent.setLayout(new BoxLayout(notPictureContent, BoxLayout.Y_AXIS));
        notPictureContent.add(name);
        notPictureContent.add(createdDate);
        notPictureContent.add(rating);

        this.add(imagePart, BorderLayout.PAGE_START);
        this.add(notPictureContent, BorderLayout.PAGE_START);

        imagePart.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("mousePressed of Imageview was run");
                new EnlargedImageFrame(model.getFile(), model.getFileName());
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("ImageView updated() was run");
        for (RatingListener l : listeners.getListeners(RatingListener.class)) {
            l.onRating(this);
        }
    }

    public void changeLayout(ImageCollectionModel.LayoutType newLayout){
        if (newLayout == ImageCollectionModel.LayoutType.LIST){
//            System.out.println("ImageView -> list");
            this.setLayout(listLayout);
            this.setMaximumSize(new Dimension(100000, 200));
        }
        else if (newLayout == ImageCollectionModel.LayoutType.GRID){
//            System.out.println("ImageView -> grid");
            this.setLayout(gridLayout);

//            ImageView.viewW = this.getWidth();
//            ImageView.viewH = this.getHeight();
//            System.out.println("ImageView.viewH : " + ImageView.viewH);
//            System.out.println("ImageView.viewW : " + ImageView.viewW);
//            this.setPreferredSize(new Dimension(200, 250));
        }
        else{
            System.out.println("model.Layout is set to something weird");
        }
        repaint();
        revalidate();
    }

    public void filterUpdated(Integer newFilterVal){
        Integer rating = model.getRating();
        if (newFilterVal == null){
            this.setVisible(true);
        }
        else if (rating == null){
            this.setVisible(false);
        }
        else if(rating < newFilterVal){
            this.setVisible(false);
        }
        else{
            this.setVisible(true);
        }
        repaint();
        revalidate();
    }

    public void addRatingListener(RatingListener l) {
        listeners.add(RatingListener.class, l);
    }

}
