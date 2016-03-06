import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class EnlargedImageFrame extends JFrame {

    public EnlargedImageFrame(File f, String fileName){
        super(fileName);

        JPanel p = new JPanel();
        this.getContentPane().add(p);

        Image img;
        try{
            img = ImageIO.read(f);
        }
        catch (Exception e){
            img = null;
        }

//        p.add(new JLabel( new ImageIcon(Utilities.getScaledImage(img, 500, 500)) ));
        p.add(new JLabel( new ImageIcon(Utilities.scaleWithAspectRatio(img, 500, 500)) ));

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE );

        this.pack();
        this.setVisible(true);
    }
}
