import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ImageCollectionView extends JPanel implements Observer {

    ImageCollectionModel model;
    ArrayList<ImageView> imagesList;
    public ImageCollectionView(ImageCollectionModel m){
        super();
//        this.add(new JLabel("abcdef"));
        this.model = m;
        imagesList = new ArrayList<ImageView>();
    }

    @Override
    public void update(Observable o, Object arg) {
        ImageModel newImageModel =  model.getUnaddedImage();
        if (newImageModel != null){
            System.out.println("new image was added");
            ImageView newImageView = new ImageView(newImageModel);
            newImageModel.addObserver(newImageView);

            imagesList.add(newImageView);
            this.add(newImageView);
            revalidate();
        }

        for(ImageView img : imagesList){
            img.changeLayout(model.getLayout());
        }

        if (model.getLayout() == ImageCollectionModel.LayoutType.LIST){
            //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            this.setLayout(new GridLayout(0, 1));

            this.revalidate();
        }
        else if (model.getLayout() == ImageCollectionModel.LayoutType.GRID){
            this.setLayout(new FlowLayout());
            this.revalidate();
        }
    }
}