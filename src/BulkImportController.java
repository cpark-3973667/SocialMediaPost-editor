import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BulkImportController {

    @FXML
    public void handleImportButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("CSV Files", "*.csv")
        );
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            List<SocialMediaPost> importedPosts = importPostsFromCSV(selectedFile);
            if (importedPosts.isEmpty()) {
                displayErrorMessage("No posts were imported from the selected file.");
            } else {
                // Perform any necessary actions with the imported posts
                System.out.println("Successfully imported " + importedPosts.size() + " posts from the file.");
            }
        }
    }

    private List<SocialMediaPost> importPostsFromCSV(File file) {
    	List<SocialMediaPost> importedPosts = new ArrayList<>();
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
            displayErrorMessage("Error occurred during file read");
        }

        for (String[] record : records) {
            try {
                int id = Integer.parseInt(record[0]);
                String content = record[1];
                String author = record[2];
                int likes = Integer.parseInt(record[3]);
                int shares = Integer.parseInt(record[4]);
                String dateTimeString = record[5];
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                
                // Adding post to the database
                SocialMediaPost post = new SocialMediaPost(id, content, author, likes, shares, dateTime);
                boolean isAdded = DatabaseManager.addSocialMediaPost(post);
                if (isAdded) {
                    importedPosts.add(post);
                }

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                displayErrorMessage("Invalid data format in the CSV file");
                e.printStackTrace();
            } catch (java.time.format.DateTimeParseException e) {
                displayErrorMessage("Invalid date-time format in the CSV file. Please use the format: dd/MM/yyyy HH:mm");
                e.printStackTrace();
            }
        }
        return importedPosts;
    }

    private void displayErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
