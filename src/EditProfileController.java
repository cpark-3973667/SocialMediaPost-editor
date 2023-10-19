
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditProfileController {
	@FXML
    private Label statusLabel;
	
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    private User currentUser;

    public void initData(User user) {
        this.currentUser = user;
        initializeFields();
    }

    private void initializeFields() {
        if (currentUser != null) {
            usernameField.setText(currentUser.getUsername());
            passwordField.setText(currentUser.getPassword());
            firstNameField.setText(currentUser.getFirstName());
            lastNameField.setText(currentUser.getLastName());
        }
    }

    @FXML
    private void handleSaveChanges() {
        if (currentUser != null) {
            String newUsername = usernameField.getText();
            if (!newUsername.equals(currentUser.getUsername())) { // Check if the username has changed
                if (DatabaseManager.usernameExists(newUsername)) { // Check if the new username already exists
                    statusLabel.setText("Username already exists. Please choose a different username.");
                    return;
                }
            }
            currentUser.setUsername(newUsername); // Update the currentUser's username with the new value
            currentUser.setPassword(passwordField.getText());
            currentUser.setFirstName(firstNameField.getText());
            currentUser.setLastName(lastNameField.getText());
            DatabaseManager.updateUser(currentUser, newUsername); // Update the user's information in the database
            closeEditProfileWindow();
        }
    }

    private void closeEditProfileWindow() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }
}
