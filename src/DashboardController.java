import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashboardController {
	private User currentUser;
	
    @FXML
    private Label welcomeLabel;
    
    public void initData(User user) {
        this.currentUser = user;
    }
    
    // You can initialize the welcomeLabel with the user's name
    public void initialize(String username) {
        welcomeLabel.setText("Welcome, " + username + "!");
    }

    public void openEditProfileWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditProfileView.fxml"));
            Parent editProfileRoot = fxmlLoader.load();
            EditProfileController editProfileController = fxmlLoader.getController();
            editProfileController.initData(currentUser); // Pass the current user to the edit profile window
            Stage stage = new Stage();
            stage.setTitle("Edit Profile");
            stage.setScene(new Scene(editProfileRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openAddPostWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPostView.fxml"));
            Parent addPostRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Post");
            stage.setScene(new Scene(addPostRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openRetrievePostWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RetrievePostView.fxml"));
            Parent retrievePostRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Retrieve Post");
            stage.setScene(new Scene(retrievePostRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openRemovePostWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RemovePostView.fxml"));
            Parent removePostRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Remove Post");
            stage.setScene(new Scene(removePostRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void openRetrieveTopNPostsWindow() {
        // Add logic to open the Retrieve Top N Posts window
    }

    public void openExportToCSVWindow() {
        // Add logic to open the Export Post to CSV window
    }

    public void handleLogout() {
        // Add logic to handle the logout action
    }
}
