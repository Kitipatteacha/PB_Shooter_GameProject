package window;

import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.Credit;
import scene.HowTo;
import scene.HowTo2;
import scene.MainMenu;
import scene.Start;

public class SceneManager {
	private static Stage primaryStage;
	public static final int SCENE_WIDTH = 800;
	public static final int SCENE_HEIGHT = 600;
	public static MainMenu mainMenu = new MainMenu();
	public static Credit credit = new Credit();
	public static HowTo howTo = new HowTo();
	public static HowTo2 howTo2 = new HowTo2();
	public static Start start = new Start();
	public static Scene creditScene = new Scene(credit,SCENE_WIDTH,SCENE_HEIGHT);
	public static Scene mainMenuScene = new Scene(mainMenu,SCENE_WIDTH,SCENE_HEIGHT);
	public static Scene howToScene = new Scene(howTo,SCENE_WIDTH,SCENE_HEIGHT);
	public static Scene startScene = new Scene(start,SCENE_WIDTH,SCENE_HEIGHT);
	public static Scene howToScene2 = new Scene(howTo2,SCENE_WIDTH,SCENE_HEIGHT);
	
	public static void initialize(Stage stage) {
		primaryStage = stage;
		primaryStage.show();
	}

	public static void gotoSceneOf(String goTo) {
		//TODO Fill Code
		mainMenu.stopMovingBG();
		if(goTo.equals("MainMenu")) {
			mainMenu.startMovingBG();
			primaryStage.setScene(mainMenuScene);
			primaryStage.show();
		}
		else if(goTo.equals("Credit")){
			primaryStage.setScene(creditScene);
			primaryStage.show();
		}
		else if(goTo.equals("HowTo")) {
			primaryStage.setScene(howToScene);
			primaryStage.show();
		}
		else if(goTo.equals("Start")) {
			start.focus();
			primaryStage.setScene(startScene);
			primaryStage.show();
		}
		else if(goTo.equals("HowTo2")) {
			start.focus();
			primaryStage.setScene(howToScene2);
			primaryStage.show();
		}
	}
}
