/**
 * Created by j on 2016-03-04.
 */
public class main {
    public static void main(String[] args){
        System.out.println("ageh");

        ImageCollectionModel theModel = new ImageCollectionModel();
        Toolbar toolbar = new Toolbar(theModel);
        MainFrame frame =  new MainFrame(theModel, toolbar);
        ImageCollectionView mainPanel =  new ImageCollectionView(theModel);

        theModel.addObserver(frame);
        theModel.addObserver(mainPanel);
    }
}
