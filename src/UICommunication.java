import java.util.List;

/**
 * Created by yurir on 27/01/2017.
 */
public interface UICommunication {
    public void onResourceRequested(String pId);
    public void onResourceReleased();
    public void onStatusChanged(String status);
    public void onQueueChanged(List<String> queue);
}
