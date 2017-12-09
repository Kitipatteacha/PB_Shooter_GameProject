package scene;

import drawing.GameScreen;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.StackPane;
import logic.GameLogic;
import sharedObject.RenderableHolder;

public class Start extends StackPane{
	GameLogic logic = new GameLogic();
	GameScreen gameScreen = new GameScreen(800,600);
	AnimationTimer animation = new AnimationTimer() {
		public void handle(long now) {
			gameScreen.paintComponent();
			logic.logicUpdate();
			RenderableHolder.getInstance().update();
		}
	};
	
	public Start() {
		this.getChildren().add(gameScreen);
	}
	
	public void startGame() {
		animation.start();
		gameScreen.requestFocus();
	}
	
	public void stopGame() {
		animation.stop();
	}
}
