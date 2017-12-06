package window;

import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.Credit;
import scene.HowTo;
import scene.MainMenu;

public class SceneManager {
	private static Stage primaryStage;
	public static final int SCENE_WIDTH = 800;
	public static final int SCENE_HEIGHT = 600;
	public static Scene MainMenu = new Scene(new MainMenu(),SCENE_WIDTH,SCENE_HEIGHT);
	public static Scene Credit = new Scene(new Credit(),SCENE_WIDTH,SCENE_HEIGHT);
	public static Scene HowTo = new Scene(new HowTo(),SCENE_WIDTH,SCENE_HEIGHT);
	
	public static void initialize(Stage stage) {
		primaryStage = stage;
		primaryStage.show();
	}

	public static void gotoSceneOf(String goTo) {
		//TODO Fill Code
		if(goTo.equals("MainMenu")) {
			Scene next = MainMenu;
			primaryStage.setScene(next);
			primaryStage.show();
		}
		else if(goTo.equals("Credit")){
			System.out.println("Credit");
			Scene next = Credit;
			primaryStage.setScene(next);
			primaryStage.show();
		}
		else if(goTo.equals("HowTo")) {
			Scene next = HowTo;
			primaryStage.setScene(next);
			primaryStage.show();
		}
		
	}
}
