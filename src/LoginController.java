import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    private User currentUser; // Add a field to store the current user

    @FXML
    private Button loginButton;


    public void handleLoginAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (DatabaseManager.authenticateUser(username, password)) {
            statusLabel.setText("Login successful!");
            currentUser = DatabaseManager.getUser(username); // Fetch the user from the database
            openDashboardWindow(currentUser);
            closeLoginWindow();
        } else {
            statusLabel.setText("Invalid username or password.");
        }
    }


    private void openDashboardWindow(User user) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DashboardView.fxml"));
            Parent dashboardRoot = fxmlLoader.load();
            DashboardController dashboardController = fxmlLoader.getController();
            dashboardController.initData(user);
            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(dashboardRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void closeLoginWindow() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void goBackToMainMenu() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenuView.fxml"));
            Parent root = fxmlLoader.load();      
            Stage stage = new Stage();
            MainMenuController controller = fxmlLoader.getController();
            controller.setMainMenuStage(stage); // Pass the stage to the controller
            stage.setTitle("Main Menu");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();

            closeLoginWindow(); // Close the current window
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
