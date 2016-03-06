import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class ImageRating extends RatingView implements Observer {

    ImageModel model;

    public ImageRating(ImageModel x){
        model = x;
        for (int i = 0; i < stars.length; i++){
            stars[i].addMouseListener(new starMouseListener(i));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        int newFilterNum = model.getRating();
        for (int i = 0; i < stars.length; i++){
            stars[i].setEnabled(i <= newFilterNum);
        }
    }

    public class starMouseListener implements MouseListener {

        int starNumber;
        public starMouseListener(int i){
            starNumber = i;
        }

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            model.setRating(starNumber);
        }

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

}