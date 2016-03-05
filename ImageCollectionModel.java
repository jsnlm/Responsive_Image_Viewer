import java.util.Observable;

public class ImageCollectionModel extends Observable {

    public enum LayoutType{ LIST, GRID }

    LayoutType currLayout;
    int filter;

    public ImageCollectionModel(){
        currLayout = LayoutType.LIST;
        setChanged();
    }

    public int getFilter(){ return this.filter;}
    public void setFilter(int x){
        this.filter = x;
        setChanged();
        notifyObservers();
    }

    public LayoutType getLayout(){return this.currLayout;}
    public void setLayout(LayoutType x){
        this.currLayout = x;
        setChanged();
        notifyObservers();
    }
}