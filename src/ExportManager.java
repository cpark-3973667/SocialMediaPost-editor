
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportManager {
	public static boolean exportPostToFile(int postId, String fileName, String directory) {
        // Retrieve the SocialMediaPost object based on the postId from the database or collection
        SocialMediaPost post = DatabaseManager.retrievePost(postId);
        if (post == null) {
            System.out.println("Post with ID " + postId + " not found.");
            return false;
        }

        File file = new File(directory, fileName + ".csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(post.toCSVString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
