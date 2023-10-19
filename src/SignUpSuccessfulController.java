import javafx.fxml.FXML;
import javafx.stage.Stage;

public class SignUpSuccessfulController {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleConfirmAction() {
        stage.close();
    }
}
