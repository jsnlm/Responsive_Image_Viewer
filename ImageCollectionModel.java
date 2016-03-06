import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

public class ImageCollectionModel extends Observable {

    public enum LayoutType{ LIST, GRID }

    LayoutType currLayout;
    int filter;
    ArrayList<ImageModel> imageList;
    ImageModel unaddedImage;

    public ImageCollectionModel(){
        currLayout = LayoutType.LIST;
        imageList = new ArrayList<>();
        unaddedImage = null;
        setChanged();
    }

    public int getFilter(){ return this.filter;}
    public void setFilter(int x){
        this.filter = x;
        setChangedAndNotify();
    }

    public LayoutType getLayout(){return this.currLayout;}
    public void setLayout(LayoutType x){
        this.currLayout = x;
        setChangedAndNotify();
    }

    public void addPicture(File input){
        System.out.println("addPicture was run");
        ImageModel addedPic = new ImageModel(input);
        imageList.add(addedPic);
        unaddedImage = addedPic;
        setChangedAndNotify();
        unaddedImage = null;
    }

    private void setChangedAndNotify(){
        setChanged();
        notifyObservers();
    }
    public ImageModel getUnaddedImage(){
        return unaddedImage;
    }
}