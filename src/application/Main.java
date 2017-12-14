package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import window.SceneManager;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManager.initialize(primaryStage);
			SceneManager.gotoSceneOf("MainMenu");
			primaryStage.setResizable(false);
			primaryStage.setTitle("Paint Ball");
			primaryStage.centerOnScreen();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void stop(Stage primaryStage) {
		Platform.exit();
	}
}
