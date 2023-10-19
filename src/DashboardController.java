import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class DashboardController {
	private User currentUser;
	
    @FXML
    private Label welcomeLabel;
    
    @FXML
    private Button logoutButton;
    
    @FXML
    private Button upgradeVIPButton;
    
    @FXML
    private Button dataVisualizationButton;

    @FXML
    private Button bulkImportButton;
    
    @FXML
    private PieChart pieChart;
    
    public void initData(User user) {
    	if (user != null) {
            this.currentUser = user;
            initialize(user.getUsername());
        } else {
            System.err.println("User object is null. Please provide a valid User object.");
        }
    }
    
    // You can initialize the welcomeLabel with the user's name
    public void initialize(String username) {
        welcomeLabel.setText("Welcome, " + currentUser.getUsername() + "!");
        boolean isVip = DatabaseManager.checkVipStatus(currentUser.getUsername());
        System.out.println("Vip status:" +isVip);
        hideVIPFunctions(isVip);
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
    
    public void openDataVisualization() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DataVisualizationView.fxml"));
            Parent dataVizRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Data Visualization");
            stage.setScene(new Scene(dataVizRoot));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void openBulkImportWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BulkImportView.fxml"));
            Parent bulkImportRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Bulk Import Social Media Posts");
            stage.setScene(new Scene(bulkImportRoot));
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
            MainMenuController controller = fxmlLoader.getController();
            controller.setMainMenuStage(stage);
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
    
    @FXML
    private void handleUpgradeVIP(ActionEvent event) {
        String username = currentUser.getUsername(); // Assuming currentUser is the currently logged-in user

        // Creating a confirmation dialog
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Subscription Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Would you like to subscribe to the application for a monthly fee of $0?");
        
        // Adding Yes and No buttons to the dialog
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        
        // Handling the user's choice
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            if (!DatabaseManager.checkVipStatus(username)) {
                DatabaseManager.upgradeUserToVIP(username);
                upgradeVIPButton.setDisable(true); // Disable the upgrade button after the user becomes VIP
                System.out.println("User " + username + " has been upgraded to VIP.");
                displayLogoutMessage();
            } else {
                System.out.println("User " + username + " is already a VIP.");
            }
        } else {
            // Close the dialog without taking any action
            alert.close();
        }
    }
    
    // Logout message for when user selects to upgrade to VIP
    private void displayLogoutMessage() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("VIP Upgrade Successful");
        alert.setHeaderText(null);
        alert.setContentText("Please log out and log in again to access VIP functionalities.");
        alert.showAndWait();
    }
    
    // Hiding VIP functions if user is not a VIP
    private void hideVIPFunctions(boolean isVip) {
        upgradeVIPButton.setVisible(!isVip); // Hide the VIP upgrade button if the user is already a VIP
        dataVisualizationButton.setVisible(isVip); // Hide the Data Visualization button if the user is not a VIP
        bulkImportButton.setVisible(isVip); // Hide the Bulk Import button if the user is not a VIP
    }
}
