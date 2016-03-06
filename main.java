public class Main {
    public static void main(String[] args){

        ImageCollectionModel theModel = new ImageCollectionModel();
        Toolbar toolbar = new Toolbar(theModel);
        MainFrame frame =  new MainFrame(theModel, toolbar);


        theModel.addObserver(toolbar);
        theModel.addObserver(frame);

        theModel.loadUnloadedPics();
        theModel.notifyObservers();
    }
}
