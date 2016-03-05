import java.util.Observable;

public class ImageCollectionModel extends Observable {

    public enum LayoutType{ LIST, GRID }

    LayoutType currLayout;
    public ImageCollectionModel(){
        currLayout = LayoutType.LIST;
    }
}
