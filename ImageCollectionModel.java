import java.io.*;
import java.util.ArrayList;
import java.util.Observable;

public class ImageCollectionModel extends Observable implements Serializable {

    public enum LayoutType{ LIST, GRID }

    LayoutType currLayout;
    int filter;
    ArrayList<ImageModel> imageList;
    ImageModel unaddedImage;

    public ImageCollectionModel(){
        currLayout = LayoutType.LIST;
        imageList = new ArrayList<>();
        loadData();
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

    public void loadUnloadedPics(){
        for(int i = 0; i < imageList.size(); i++){
            displayImageModel(imageList.get(i));
        }
    }
    public void addPicture(File input){
        System.out.println("addPicture was run");
        addPicture( new ImageModel(input) );
    }

    public void addPicture(ImageModel addedPic){
        imageList.add(addedPic);
        displayImageModel(addedPic);
    }

    public void displayImageModel(ImageModel Pic){
        unaddedImage = Pic;
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

    public boolean loadData(){
        try{
            FileInputStream fileIn = new FileInputStream("saveFile.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            ImageCollectionModel temp = (ImageCollectionModel) in.readObject();
            this.imageList = temp.imageList;
            in.close();
            fileIn.close();
            return true;
        }
        catch(Exception e){
            System.out.println("ERROR : " + e);
            return false;
        }
    }


    public boolean saveData(){
        try{
            FileOutputStream fileOut = new FileOutputStream("saveFile.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            return true;
        }
        catch(Exception e){
            System.out.println("ERROR : " + e);
            return false;
        }
    }
}