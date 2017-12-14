package scene;

import drawing.GameScreen;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.StackPane;
import logic.GameLogic;
import sharedObject.RenderableHolder;

public class Start extends StackPane{
	GameLogic logic;
	GameScreen gameScreen;
	AnimationTimer animation = new AnimationTimer() {
		public void handle(long now) {
			if(!logic.isGamePause())gameScreen.paintComponent();
			if(logic.isGameEnd()) {
				gameScreen.drawResult(logic.getWinner());
				stopGame();
			}
			
			logic.logicUpdate();
			RenderableHolder.getInstance().update();
		}
	};
	
	public Start() {
		logic = new GameLogic();
		gameScreen = new GameScreen(800,600);
		this.getChildren().add(gameScreen);
	}
	
	public void startGame() {
		animation.start();
		gameScreen.requestFocus();
	}
	
	public void stopGame() {
		animation.stop();
		BaseButton main = new BaseButton("Main");
		this.getChildren().add(main);
		main.setGoToScene("MainMenu");
		main.setTranslateY(50);
	}
}
