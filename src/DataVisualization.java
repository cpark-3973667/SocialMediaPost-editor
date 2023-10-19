import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DataVisualization extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DataVisualizationView.fxml"));
        Parent root = fxmlLoader.load();

        // Fetch share counts from the DatabaseManager
        List<Integer> shareCounts = DatabaseManager.getShareCounts();
        DataVisualizationController controller = fxmlLoader.getController();
        controller.setShareCounts(shareCounts);

        // Creating the layout and displaying the scene
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Data Visualization - Pie Chart");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
