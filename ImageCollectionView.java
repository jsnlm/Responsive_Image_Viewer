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
        this.model = m;
        this.setBackground(new Color(100, 100, 255));
        imagesList = new ArrayList<ImageView>();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("ImageCollectionView update() called");
        ImageModel newImageModel =  model.getUnaddedImage();
        if (newImageModel != null){
            System.out.println("new image was added");
            ImageView newImageView = new ImageView(newImageModel);
            newImageModel.addObserver(newImageView);
            newImageModel.notifyObservers();

            newImageView.addRatingListener(this::onRate);

            imagesList.add(newImageView);
            this.add(newImageView);
            revalidate();
        }

        for(ImageView img : imagesList){
            img.changeLayout(model.getLayout());
            img.filterUpdated(model.getFilter());
        }

        if (model.getLayout() == ImageCollectionModel.LayoutType.LIST){
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

//            this.setLayout(new GridLayout(0, 1));

//            int numVisible = 0;
//            for(ImageView img : imagesList){
//                boolean ajfeigr = img.isShowing();
//                if (ajfeigr){
//                    numVisible++ ;
//                }
//            }
//            System.out.println("numVisible : " + numVisible);
//            this.setSize();

//            this.setLayout(new FlowLayout());
        }
        else if (model.getLayout() == ImageCollectionModel.LayoutType.GRID){
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            System.out.println("getParent().getSize() : " + getParent().getSize());


//            this.setMaximumSize(new Dimension( (int)getParent().getSize().getWidth(), 100000));
        }
        this.revalidate();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    private void onRate(ImageView img) {
        img.filterUpdated(model.getFilter());
    }
}