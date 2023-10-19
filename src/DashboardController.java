import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label welcomeLabel;

    // You can initialize the welcomeLabel with the user's name
    public void initialize(String username) {
        welcomeLabel.setText("Welcome, " + username + "!");
    }

    public void openEditProfileWindow() {
        // Add logic to open the Edit Profile window
    }

    public void openAddPostWindow() {
        // Add logic to open the Add Social Media Post window
    }

    public void openRetrievePostWindow() {
        // Add logic to open the Retrieve Post window
    }

    public void openRemovePostWindow() {
        // Add logic to open the Remove Post window
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
