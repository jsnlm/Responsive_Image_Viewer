import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
        imagePart = new JLabel( new ImageIcon(getScaledImage(img, 200, 200)) );
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
    }

    @Override
    public void update(Observable o, Object arg) {

    }

//    public void filterUpdateAction(int newFilter){
//        if
//    }

    public void changeLayout(ImageCollectionModel.LayoutType newLayout){
        if (newLayout == ImageCollectionModel.LayoutType.LIST){

        }
        else if (newLayout == ImageCollectionModel.LayoutType.GRID){

        }
        else{
            System.out.println("model.Layout is set to something weird");
        }
    }

    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}
