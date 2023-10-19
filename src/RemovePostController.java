import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RemovePostController {

    @FXML
    private TextField postIdField;

    @FXML
    private void handleRemovePost() {
        try {
            int postId = Integer.parseInt(postIdField.getText());
            DatabaseManager.removePost(postId);
            closeRemovePostWindow();
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid or existing numeric value for the post ID.");
        }
    }

    private void closeRemovePostWindow() {
        Stage stage = (Stage) postIdField.getScene().getWindow();
        stage.close();
    }
}
