public class Main {
    public static void main(String[] args){

        ImageCollectionModel theModel = new ImageCollectionModel();
        Toolbar toolbar = new Toolbar(theModel);
        MainFrame frame =  new MainFrame(theModel, toolbar);
        ImageCollectionView mainPanel =  new ImageCollectionView(theModel);

        theModel.addObserver(toolbar);
        theModel.addObserver(frame);
        theModel.addObserver(mainPanel);

        theModel.notifyObservers();
    }
}
