import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RatingView extends JPanel{

    JLabel stars[];

//    int lastHovered;

    public RatingView(){

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        stars = new JLabel[5];
        for (int i = 0; i < stars.length; i++){
            stars[i] = new JLabel(new ImageIcon(getClass().getResource("activeStar.png")));
            stars[i].setDisabledIcon(new ImageIcon(getClass().getResource("disabledStar.png")));
            this.add(stars[i]);
        }
        //stars[0].addMouseListener(new MouseListener() );
    }


}