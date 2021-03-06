package window;

import arts.GameSound;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.Credit;
import scene.HowTo;
import scene.MainMenu;
import scene.CharacterSelect;
import scene.Start;

public class SceneManager {
	private static Stage primaryStage;
	public static final int SCENE_WIDTH = 800;
	public static final int SCENE_HEIGHT = 600;
	public static GameSound gameSound = new GameSound();
	public static MainMenu mainMenu = new MainMenu();
	public static Credit credit = new Credit();
	public static HowTo howTo = new HowTo();
	public static CharacterSelect select = new CharacterSelect();
	public static Start start;
	
	public static Scene creditScene = new Scene(credit,SCENE_WIDTH,SCENE_HEIGHT);
	public static Scene mainMenuScene = new Scene(mainMenu,SCENE_WIDTH,SCENE_HEIGHT);
	public static Scene CharacterScene = new Scene(select,SCENE_WIDTH,SCENE_HEIGHT);
	public static Scene startScene;
	public static Scene howToScene = new Scene(howTo,SCENE_WIDTH,SCENE_HEIGHT);
	
	public static void initialize(Stage stage) {
		primaryStage = stage;
		primaryStage.show();
	}

	public static void gotoSceneOf(String goTo) {
		mainMenu.stopMovingBG();
		
		if(goTo.equals("MainMenu")) {
			mainMenu.startMovingBG();
			mainMenu.soundTheme();
			primaryStage.setScene(mainMenuScene);
			primaryStage.show();
		}
		else if(goTo.equals("Credit")){
			primaryStage.setScene(creditScene);
			primaryStage.show();
		}
		else if(goTo.equals("Start")){
			select.reset();
			select.requestFocus();
			primaryStage.setScene(CharacterScene);
			primaryStage.show();
		}
		else if(goTo.equals("Play")) {
			start = new Start();
			startScene = new Scene(start,SCENE_WIDTH,SCENE_HEIGHT);
			start.startGame();
			primaryStage.setScene(startScene);
			primaryStage.show();
			mainMenu.stopTheme();
		}
		else if(goTo.equals("HowTo")) {
			primaryStage.setScene(howToScene);
			primaryStage.show();
		}
	}
}
