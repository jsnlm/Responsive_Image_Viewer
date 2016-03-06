import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class ImageView extends JPanel implements Observer {

    ImageModel model;
    ImageRating rating;
    JLabel imagePart, name, createdDate;
    public ImageView(ImageModel m) {
        this.model = m;

        rating = new ImageRating(m);
        m.addObserver(rating);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
//        this.setLayout(new BorderLayout());
        Random r = new Random();
        this.setBackground(new Color(r.nextInt(250), r.nextInt(250), r.nextInt(250)));

        this.setPreferredSize(new Dimension(200, 250));
        Image img;
        try{
            img = ImageIO.read(m.getFile());
        }
        catch (Exception e){
            img = null;
        }
//        imagePart = new JLabel( new ImageIcon(Utilities.getScaledImage(img, 200, 200)) );
        imagePart = new JLabel( new ImageIcon(Utilities.scaleWithAspectRatio(img, 200, 200)) );

        name = new JLabel("Name : " + m.getFileName());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        createdDate = new JLabel("Date Created : " + df.format(m.getCreatedDate().toMillis()) );

        imagePart.setAlignmentX(LEFT_ALIGNMENT);
        name.setAlignmentX(LEFT_ALIGNMENT);
        rating.setAlignmentX(LEFT_ALIGNMENT);

        this.add(imagePart, BorderLayout.PAGE_START);
        this.add(name, BorderLayout.CENTER);
        this.add(createdDate, BorderLayout.CENTER);
        this.add(rating, BorderLayout.PAGE_END);

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
    }

    public void changeLayout(ImageCollectionModel.LayoutType newLayout){
        if (newLayout == ImageCollectionModel.LayoutType.LIST){

        }
        else if (newLayout == ImageCollectionModel.LayoutType.GRID){

        }
        else{
            System.out.println("model.Layout is set to something weird");
        }
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

    }

}
