import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DataVisualizationController implements Initializable {

    @FXML
    private PieChart pieChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Integer> shareCounts = DatabaseManager.getShareCounts();
        if (shareCounts != null && shareCounts.size() == 3) {
            setShareCounts(shareCounts);
        } else {
            System.out.println("Failed to retrieve share counts from the database.");
        }
    }

    public void setShareCounts(List<Integer> shareCounts) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("0-99 Shares", shareCounts.get(0)),
                new PieChart.Data("100-999 Shares", shareCounts.get(1)),
                new PieChart.Data("1000+ Shares", shareCounts.get(2))
        );
        pieChart.setData(pieChartData);
    }
}
