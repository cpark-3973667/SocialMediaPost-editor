import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class RetrieveTopNPostsController {

    @FXML
    private TextField authorField;

    @FXML
    private TextField numberOfPostsField;

    @FXML
    private Label postResultLabel;

    @FXML
    private void handleRetrieveTopNPosts() {
        String author = authorField.getText();
        int numberOfPosts = Integer.parseInt(numberOfPostsField.getText());
        List<SocialMediaPost> topPosts = DatabaseManager.retrieveTopNPosts(author, numberOfPosts);
        displayPosts(topPosts);
    }

    private void displayPosts(List<SocialMediaPost> posts) {
        StringBuilder result = new StringBuilder();
        for (SocialMediaPost post : posts) {
            result.append("Post ID: ").append(post.getId())
                    .append(", Content: ").append(post.getContent())
                    .append(", Author: ").append(post.getAuthor())
                    .append(", Likes: ").append(post.getLikes())
                    .append(", Shares: ").append(post.getShares())
                    .append(", Date Time: ").append(post.getDateTime())
                    .append("\n");
        }
        postResultLabel.setText(result.toString());
    }

    public void setPostResultLabel(String text) {
        postResultLabel.setText(text);
    }

    public void closeRetrieveTopNPostsWindow() {
        Stage stage = (Stage) authorField.getScene().getWindow();
        stage.close();
    }
}
