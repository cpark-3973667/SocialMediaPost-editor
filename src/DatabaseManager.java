import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:database/myData.db";
    
    // Insert user information to users table in database
    public void insertUser(String username, String password, String firstName, String lastName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(DB_URL);
            String query = "INSERT INTO users (username, password, first_name, last_name) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, lastName);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Check database user table if username already exists
    public static boolean usernameExists(String username) {
        String DB_URL = "jdbc:sqlite:database/myData.db"; 
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // If a row is returned, the username exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Check database user table if user has signed up and exists
    public static boolean authenticateUser(String username, String password) {
        String DB_URL = "jdbc:sqlite:database/myData.db"; 
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // If a row is returned, user exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get user from database
    public static User getUser(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DB_URL);
            String query = "SELECT * FROM users WHERE username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // Retrieve user data from the result set and create a User object
                String fetchedUsername = resultSet.getString("username");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                return new User(fetchedUsername, password, firstName, lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null; // User not found
    }
    
    // Updates the user information in the database
    public static void updateUser(User user, String newUsername) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(DB_URL);
            String query = "UPDATE users SET username = ?, password = ?, first_name = ?, last_name = ? WHERE username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newUsername);
            System.out.println("New username: " + newUsername);
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getUsername()); // Use the old username for reference
            System.out.println("Current username: " + user.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Add social media post to the database
    public static boolean addSocialMediaPost(SocialMediaPost post) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(DB_URL);

            // Validate the social media post
            if (!validateSocialMediaPost(post)) {
                System.out.println("Invalid social media post. Please check the post details.");
                return false;
            }

            String query = "INSERT INTO social_media_posts (ID, content, author, likes, shares, date_time) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, post.getId());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setString(3, post.getAuthor());
            preparedStatement.setInt(4, post.getLikes());
            preparedStatement.setInt(5, post.getShares());
            preparedStatement.setString(6, post.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

            preparedStatement.executeUpdate();
            return true; // Return true if the execution was successful
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false; // Return false if any exceptions occurred
    }
    
    // Validation method for adding a post to the collection
    static boolean validateSocialMediaPost(SocialMediaPost post) {
        if (post.getId() < 0) {
            System.out.println("Invalid post ID. Please provide a non-negative integer.");
            return false;
        }
        if (post.getContent().contains(",")) {
            System.out.println("Invalid content. Content cannot contain a comma.");
            return false;
        }
        if (post.getAuthor().isEmpty()) {
            System.out.println("Invalid author. Please provide a valid author name.");
            return false;
        }
        if (post.getLikes() < 0) {
            System.out.println("Invalid number of likes. Please provide a non-negative integer.");
            return false;
        }
        if (post.getShares() < 0) {
            System.out.println("Invalid number of shares. Please provide a non-negative integer.");
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String dateTimeString = post.getDateTime().format(formatter);
            LocalDateTime parsedDateTime = LocalDateTime.parse(dateTimeString, formatter);
            System.out.println("Parsed LocalDateTime: " + parsedDateTime);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
    
    // Retrieval method for post by postID
    public static SocialMediaPost retrievePost(int postId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        SocialMediaPost post = null;

        try {
            connection = DriverManager.getConnection(DB_URL);

            String query = "SELECT * FROM social_media_posts WHERE ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, postId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String content = resultSet.getString("content");
                String author = resultSet.getString("author");
                int likes = resultSet.getInt("likes");
                int shares = resultSet.getInt("shares");
                LocalDateTime dateTime = LocalDateTime.parse(resultSet.getString("date_time"),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

                post = new SocialMediaPost(id, content, author, likes, shares, dateTime);
            }
        } catch (SQLException | DateTimeParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return post;
    }
    
    // Delete a post by post ID
    public static void removePost(int postId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(DB_URL);
            String query = "DELETE FROM social_media_posts WHERE ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, postId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Retrieve the top N posts with the most likes,for a given user or over ALL users, in a descending order of likes
    public static List<SocialMediaPost> retrieveTopNPosts(String author, int n) {
        List<SocialMediaPost> topPosts = new ArrayList<>();

        String query;
        if (author.equals("ALL")) {
            query = "SELECT * FROM social_media_posts ORDER BY likes DESC LIMIT ?";
        } else {
            query = "SELECT * FROM social_media_posts WHERE author = ? ORDER BY likes DESC LIMIT ?";
        }

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(query)) {
            if (!author.equals("ALL")) {
                statement.setString(1, author);
                statement.setInt(2, n);
            } else {
                statement.setInt(1, n);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String content = resultSet.getString("content");
                    String retrievedAuthor = resultSet.getString("author");
                    int likes = resultSet.getInt("likes");
                    int shares = resultSet.getInt("shares");
                    LocalDateTime dateTime = LocalDateTime.parse(resultSet.getString("date_time"),
                            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                    topPosts.add(new SocialMediaPost(id, content, retrievedAuthor, likes, shares, dateTime));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topPosts;
    }
    
    // Check VIP status of user
    public static boolean checkVipStatus(String username) {
        String query = "SELECT vip_status FROM users WHERE username = ?";
        boolean isVip = false;

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                isVip = resultSet.getBoolean("vip_status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isVip;
    }
    
    // Upgrade user to VIP
    public static void upgradeUserToVIP(String username) {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            String sql = "UPDATE users SET vip_status = ? WHERE username = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setBoolean(1, true); // Assuming the vip_status column is of type BOOLEAN
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Get the counts of different share ranges for populating the pie chart
    public static List<Integer> getShareCounts() {
        List<Integer> shareCounts = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement1 = connection.prepareStatement("SELECT COUNT(*) FROM social_media_posts WHERE shares < 100");
             PreparedStatement statement2 = connection.prepareStatement("SELECT COUNT(*) FROM social_media_posts WHERE shares >= 100 AND shares < 1000");
             PreparedStatement statement3 = connection.prepareStatement("SELECT COUNT(*) FROM social_media_posts WHERE shares >= 1000")) {
            try (ResultSet resultSet1 = statement1.executeQuery()) {
                if (resultSet1.next()) {
                    shareCounts.add(resultSet1.getInt(1));
                }
            }
            try (ResultSet resultSet2 = statement2.executeQuery()) {
                if (resultSet2.next()) {
                    shareCounts.add(resultSet2.getInt(1));
                }
            }
            try (ResultSet resultSet3 = statement3.executeQuery()) {
                if (resultSet3.next()) {
                    shareCounts.add(resultSet3.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shareCounts;
    }
}
