import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Observable;

public class ImageCollectionModel extends Observable implements Serializable {

    public enum LayoutType{ LIST, GRID }

    LayoutType currLayout;
    Integer filter;
    ArrayList<ImageModel> imageList;
    ImageModel unaddedImage;

    public ImageCollectionModel(){
        currLayout = LayoutType.LIST;
        imageList = new ArrayList<>();
        loadData();
        unaddedImage = null;
        setChanged();
    }

    public Integer getFilter(){ return this.filter;}
    public void setFilter(Integer x){
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
            ImageModel curr = imageList.get(i);
            displayImageModel(curr);
            //Hacky way to call setChanged() property.
            //SetChanged() should be called in constructor but this was loaded from serialized data.
            curr.setRating(curr.getRating());
            curr.notifyObservers();
        }
    }
    public void addPicture(File input){
        //Scan image models to see if image already exists
        for(ImageModel i: imageList){
            String A = i.getFile().getPath();
            String B = input.getPath();
            if (i.getFile().getPath().equals(input.getPath())){
                JOptionPane.showMessageDialog(null, "Selected image is already in Fotag");
                return;
            }
        }
        addPicture( new ImageModel(input) );
    }

    public void addPicture(ImageModel addedPic){
        filter = null;
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