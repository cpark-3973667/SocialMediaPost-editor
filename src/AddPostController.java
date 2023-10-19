import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddPostController {

    @FXML
    private TextField idField;

    @FXML
    private TextField contentField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField likesField;

    @FXML
    private TextField sharesField;

    @FXML
    private TextField dateTimeField;
    
    @FXML
    private void handleAddPost() {
        try {
            int id = Integer.parseInt(idField.getText());
            String content = contentField.getText();
            String author = authorField.getText();
            int likes = Integer.parseInt(likesField.getText());
            int shares = Integer.parseInt(sharesField.getText());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dateTime;
            try {
                dateTime = LocalDateTime.parse(dateTimeField.getText(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Please enter proper date/time values in the format (DD/MM/YYYY HH:MM).");
                return;
            }

            SocialMediaPost post = new SocialMediaPost(id, content, author, likes, shares, dateTime);

            if (DatabaseManager.validateSocialMediaPost(post) && DatabaseManager.addSocialMediaPost(post)) {
                closeAddPostWindow();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numeric values for ID, Likes, and Shares.");
        }
    }

    private void closeAddPostWindow() {
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.close();
    }
}
