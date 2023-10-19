
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashboardController {
	private User currentUser;
	
    @FXML
    private Label welcomeLabel;
    
    @FXML
    private Button logoutButton;
    
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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RetrieveTopNPostsView.fxml"));
            Parent retrieveTopNPostsRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Retrieve Top N Posts");
            stage.setScene(new Scene(retrieveTopNPostsRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openExportToCSVWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ExportManagerView.fxml"));
            Parent exportRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Export Post to CSV");
            stage.setScene(new Scene(exportRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenuView.fxml"));
            Parent mainMenuRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Main Menu");
            stage.setScene(new Scene(mainMenuRoot));
            stage.show();

            // Close the current dashboard window
            Stage currentStage = (Stage) logoutButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
