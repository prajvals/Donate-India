package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane welcomeScreen = (AnchorPane)FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
			Scene welcomeScene = new Scene(welcomeScreen,500,400);
			primaryStage.setTitle("It is working");
			//welcomeScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(welcomeScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
