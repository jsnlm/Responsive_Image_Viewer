import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class ImageCollectionView extends JPanel implements Observer {

    ImageCollectionModel model;
    public ImageCollectionView(ImageCollectionModel m){
        this.model = m;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}