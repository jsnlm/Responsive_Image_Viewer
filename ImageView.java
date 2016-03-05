import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class ImageView extends JPanel implements Observer {

    ImageModel model;
    public ImageView(ImageModel m) {
        this.model = m;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
