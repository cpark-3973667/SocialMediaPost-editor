import javafx.fxml.FXML;
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

    public void handleLoginAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (DatabaseManager.authenticateUser(username, password)) {
            statusLabel.setText("Login successful!");
            statusLabel.setText("Login successful!");
            openDashboardWindow(username);
            closeLoginWindow();
        } else {
            statusLabel.setText("Invalid username or password.");
        }
    }
    
    private void openDashboardWindow(String username) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DashboardView.fxml"));
            Parent dashboardRoot = fxmlLoader.load();
            DashboardController dashboardController = fxmlLoader.getController();
            dashboardController.initialize(username);
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
    
}
