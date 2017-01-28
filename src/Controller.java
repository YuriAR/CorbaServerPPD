import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable, UICommunication{

    @FXML
    Label resourceStatus;
    @FXML
    Label processQueue;

    CorbaManager manager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Server");
        dialog.setHeaderText("Porta do servidor");
        dialog.setContentText("Digite a porta: ");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            manager = new CorbaManager(this, new String[] {"localhost", result.get()});
            manager.initCorba();
        }
        else{
            manager = new CorbaManager(this, new String[] {"localhost", "1516"});
            manager.initCorba();
        }
    }

    @Override
    public void onResourceRequested(String pId) {
        manager.requestResource(pId);
    }

    @Override
    public void onResourceReleased() {
        manager.releaseResource();
    }

    @Override
    public void onStatusChanged(String status) {
        resourceStatus.setText(status);
    }

    @Override
    public void onQueueChanged(List<String> queue) {
        String queueString = "";
        for (String process : queue){
            queueString = queueString + process + "\n";
        }
        processQueue.setText(queueString);
    }
}
