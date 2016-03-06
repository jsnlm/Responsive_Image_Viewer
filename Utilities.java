import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by j on 2016-03-06.
 */
public class Utilities {

    public static Image scaleWithAspectRatio(Image srcImg, int w, int h){
        int srcH = srcImg.getHeight(null);
        int srcW = srcImg.getWidth(null);

        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        if ( ((float)srcH)/srcW < ((float)h)/w)
            g2.drawImage(srcImg, 0, 0, w, srcH*w/srcW, null);
        else
            g2.drawImage(srcImg, 0, 0, srcW*h/srcH, h, null);
        g2.dispose();
        return resizedImg;

    }


//    private Image getScaledImage(Image srcImg, int w, int h){
//        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2 = resizedImg.createGraphics();
//
//        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        g2.drawImage(srcImg, 0, 0, w, h, null);
//        g2.dispose();
//
//        return resizedImg;
//    }
}
