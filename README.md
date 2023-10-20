# AssignmentTwo
 Advanced Programming s3973667
 LINK TO GITHUB:
https://github.com/cpark-3973667/s3973667-advanced-programming-assignment-two/tree/main

 IDE USED:
 Eclipse IDE

 JAVA VERSION:
 JDK-20

 JAVAFX VERSION:
 SDK-21.0.1

 DATABASED USED:
 SQLITE

 INSTALLATION AND RUNNING INSTRUCTIONS:
 1. Clone the repository from GitHub
 2. Open the project in Eclipse IDE
 3. Configure the project SDK to use Java JDK-20
 4. Set up the JavaFX SDK in the project structure
 5. Configure the SQLite database connection. Add SQLite JDBC.jar file to build path. I used (sqlite-jdbc-3.43.0.0.jar)
 6. Run the application from the MainMenu class

OO Design:
I have implemented a Model-View-Controller (MVC) design pattern as it allows for better code organization and maintainability.
1. Model Classes:
- SocialMediaPost: Represents a social media post with attributes such as ID, content, author, likes, shares, and date-time.

2. Controller Classes:
- AddPostController: Manages the addition of a new social media post.
- BulkImportController: Handles the bulk import of social media posts from a CSV file.
- DashboardController: Controls the main dashboard and associated actions.
- DatabaseManager: Manages the interaction with the SQLite database.
- DataVisualizationController: Handles data visualization tasks.
- EditProfileController: Manages the user profile editing functionality.
- ExportManagerController: Handles the export of data tasks.
- LoginController: Controls user login functionality.
- MainMenuController: Controls the main menu and associated actions.
- RemovePostController: Manages the removal of a social media post.
- RetrievePostController: Handles the retrieval of a specific social media post.
- RetrieveTopNPostsController: Manages the retrieval of the top N social media posts.
- SignUpController: Controls user sign-up functionality.
- SignUpSuccessfulController: Manages the successful sign-up view.

3. View Classes:
The classes suffixed with "View" represent the various views associated with each controller.

4. Utility Classes:
- BulkImport: Handles the bulk import functionality.
- DataVisualization: Handles data visualization tasks as a pie chart
- ExportManager: Manages the export of data into a csv file
