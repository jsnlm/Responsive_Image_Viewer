import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Observable;

public class ImageModel extends Observable implements Serializable {

    private File picFile;
    private Integer rating;

    public ImageModel(File picFile){
        this.picFile = picFile;
        this.setChanged();
    }

    public FileTime getCreatedDate(){
        try{
            BasicFileAttributes attr = Files.readAttributes(
                    picFile.toPath(),
                    BasicFileAttributes.class,
                    LinkOption.NOFOLLOW_LINKS);

            return attr.creationTime();
        }
        catch(Exception e){
            System.out.println("ERROR" + e);
            return null;
        }
    }

    public String getFileName(){
        return picFile.getName();
    }
    public File getFile(){
        return picFile;
    }

    public Integer getRating(){
        return this.rating;
    }
    public void setRating(Integer x){
        this.rating = x;
        setChanged();
        notifyObservers();
    }

    public URL getURL(){
        try{
            return this.picFile.toURL();
        }
        catch (Exception e){
            return null;
        }
    }
}
