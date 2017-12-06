package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import window.SceneManager;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManager.initialize(primaryStage);
			SceneManager.gotoSceneOf("MainMenu");
			primaryStage.setTitle("Paint Ball");
			primaryStage.centerOnScreen();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
