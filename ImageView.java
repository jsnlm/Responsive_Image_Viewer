import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

public class ImageView extends JPanel implements Observer {

    ImageModel model;
    public ImageView(ImageModel m) {
        this.model = m;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        BufferedImage img;
        try{
            img = ImageIO.read(m.getFile());
        }
        catch (Exception e){
            img = null;
        }
        this.add(new JLabel( new ImageIcon(getScaledImage(img, 200, 200))));
        this.add(new JLabel("Name : " + m.getFileName()));
        this.add(new JLabel("Rating : " + m.getRating()));
    }

    @Override
    public void update(Observable o, Object arg) {

    }

//    public void filterUpdateAction(int newFilter){
//        if
//    }

    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}
