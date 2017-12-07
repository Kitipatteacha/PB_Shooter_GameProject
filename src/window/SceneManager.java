package window;

import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.Credit;
import scene.HowTo;
import scene.MainMenu;
import scene.Start;

public class SceneManager {
	private static Stage primaryStage;
	public static final int SCENE_WIDTH = 800;
	public static final int SCENE_HEIGHT = 600;
	public static MainMenu mainMenu = new MainMenu();
	public static Credit credit = new Credit();
	public static HowTo howTo = new HowTo();
	public static Start start = new Start();
	
	public static void initialize(Stage stage) {
		primaryStage = stage;
		primaryStage.show();
	}

	public static void gotoSceneOf(String goTo) {
		//TODO Fill Code
		if(goTo.equals("MainMenu")) {
			Scene next = new Scene(mainMenu,SCENE_WIDTH,SCENE_HEIGHT);
			primaryStage.setScene(next);
			primaryStage.show();
		}
		else if(goTo.equals("Credit")){
			System.out.println("Credit");
			Scene next = new Scene(credit,SCENE_WIDTH,SCENE_HEIGHT);
			primaryStage.setScene(next);
			primaryStage.show();
		}
		else if(goTo.equals("HowTo")) {
			Scene next = new Scene(howTo,SCENE_WIDTH,SCENE_HEIGHT);
			primaryStage.setScene(next);
			primaryStage.show();
		}
		else if(goTo.equals("Start")) {
			Scene next = new Scene(start,SCENE_WIDTH,SCENE_HEIGHT);
			start.focus();
			primaryStage.setScene(next);
			primaryStage.show();
		}
	}
}
