import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    public void handleSignUpAction() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        // Check if fields are filled in
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please fill in all the fields.");
        } else {
        	DatabaseManager dbManager = new DatabaseManager();
        	// Check if the username already exists
            if (DatabaseManager.usernameExists(username)) {
                statusLabel.setText("Username already exists. Please choose a different username.");
            } else {
                // If the username does not exist, create the user and insert it into the database table
                dbManager.insertUser(username, password, firstName, lastName);
                statusLabel.setText("User created successfully!");
                closeSignUpWindow();
                openSignUpSuccessfulWindow();
                goBackToMainMenu();
            }
        }
    }
    
    private void closeSignUpWindow() {
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
    
    private void openSignUpSuccessfulWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUpSuccessfulView.fxml"));
            Parent signUpSuccessfulRoot = fxmlLoader.load();
            Stage signUpSuccessfulStage = new Stage();
            signUpSuccessfulStage.setTitle("Sign Up Successful");
            signUpSuccessfulStage.setScene(new Scene(signUpSuccessfulRoot));
            SignUpSuccessfulController controller = fxmlLoader.getController();
            controller.setStage(signUpSuccessfulStage); // Pass the stage to the controller
            signUpSuccessfulStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

            closeSignUpWindow(); // Close the current window
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
