import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ExportManagerController {

    @FXML
    private Label postIdLabel;

    @FXML
    private Label fileLocationLabel;

    @FXML
    private TextField postIdField;

    @FXML
    private TextField fileNameField;

    private String directory;

    @FXML
    private void handleChooseFileLocation() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directory for File");
        File selectedDirectory = directoryChooser.showDialog(new Stage());

        if (selectedDirectory != null) {
            directory = selectedDirectory.getAbsolutePath();
            fileLocationLabel.setText(directory);
        }
    }

    @FXML
    private void handleExportPost() {
        try {
            int postId = Integer.parseInt(postIdField.getText());
            String fileName = fileNameField.getText();
            if (ExportManager.exportPostToFile(postId, fileName, directory)) {
                closeExportManagerWindow();
            } else {
                postIdLabel.setText("Post ID doesn't exist");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid numeric value for the post ID.");
        }
    }
    
    private void closeExportManagerWindow() {
        Stage stage = (Stage) postIdField.getScene().getWindow();
        stage.close();
    }
}
