import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class MainMenuController {
	private Stage mainMenuStage;
	
    @FXML
    private Button signUpButton;

    @FXML
    private Button loginButton;
    
    public void setMainMenuStage(Stage stage) {
        this.mainMenuStage = stage;
    }

    public void openSignUpWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUpView.fxml"));
            Parent signUpRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Sign Up");
            stage.setScene(new Scene(signUpRoot));
            stage.show();
            
            mainMenuStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openLoginWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
            Parent loginRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Log In");
            stage.setScene(new Scene(loginRoot));
            stage.show();
            
            mainMenuStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
