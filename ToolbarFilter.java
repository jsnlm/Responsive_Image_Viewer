import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class ToolbarFilter extends RatingView implements Observer {

    ImageCollectionModel model;

    public ToolbarFilter(ImageCollectionModel num){
        model = num;
        for (int i = 0; i < stars.length; i++){
            stars[i].addMouseListener(new starMouseListener(i));
        }

    }


    @Override
    public void update(Observable o, Object arg) {
        redraw(model.getFilter());
    }

    public class starMouseListener implements MouseListener{

        Integer starNumber;
        public starMouseListener(int i){
            starNumber = i;
        }

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            if (starNumber == model.getFilter())
                model.setFilter(null);
            else
                model.setFilter(starNumber);
        }

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

}
