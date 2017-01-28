import CoordinatorResourceCommunication.CoordinatorInterfacePOA;
import javafx.application.Platform;

/**
 * Created by yurir on 27/01/2017.
 */
public class CoordinatorInterfaceImpl extends CoordinatorInterfacePOA{

    UICommunication listener;

    public CoordinatorInterfaceImpl(UICommunication listener){
        this.listener = listener;
    }

    @Override
    public void requestResource(String pId) {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                listener.onResourceRequested(pId);
            }
        });
    }

    @Override
    public void releaseResource() {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                listener.onResourceReleased();
            }
        });
    }
}
