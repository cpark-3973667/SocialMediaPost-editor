import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RetrievePostController {

    @FXML
    private TextField postIdField;

    @FXML
    private Label contentLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label likesLabel;

    @FXML
    private Label sharesLabel;

    @FXML
    private Label dateTimeLabel;

    public void handleRetrievePost() {
        try {
            int postId = Integer.parseInt(postIdField.getText());
            SocialMediaPost post = DatabaseManager.retrievePost(postId);

            if (post != null) {
                contentLabel.setText("Content: " + post.getContent());
                authorLabel.setText("Author: " + post.getAuthor());
                likesLabel.setText("Likes: " + post.getLikes());
                sharesLabel.setText("Shares: " + post.getShares());
                dateTimeLabel.setText("Date and Time: " + post.getDateTime().toString());
            } else {
                contentLabel.setText("No post found with ID: " + postId);
                authorLabel.setText("");
                likesLabel.setText("");
                sharesLabel.setText("");
                dateTimeLabel.setText("");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid or existing numeric value for the post ID.");
        }
    }
}
