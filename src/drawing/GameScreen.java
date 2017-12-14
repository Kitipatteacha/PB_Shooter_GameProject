package drawing;

import input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import javafx.scene.input.KeyEvent;

public class GameScreen extends Canvas {
	GraphicsContext gc = this.getGraphicsContext2D();

	public GameScreen(double width, double height) {
		super(width, height);
		this.setVisible(true);
		addListerner();
	}

	public void addListerner() {
		this.setOnKeyPressed((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), true);
		});

		this.setOnKeyReleased((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), false);
		});
		
	}

	public void paintComponent() {
		gc.setFill(Color.BLACK);
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}
		}
	}

	public void drawResult(String winner) {
		if(winner.equals("DRAW")) {
			gc.drawImage(new Image(ClassLoader.getSystemResource("Result_Draw.png").toString()), 275, 150);
			gc.setStroke(Color.WHITE);
			gc.setLineWidth(5.0);
			gc.strokeRect(275, 150, 250, 300);
		}
		else {
			gc.drawImage(new Image(ClassLoader.getSystemResource("Result_"+winner+".png").toString()), 275, 150);
			gc.setStroke(Color.WHITE);
			gc.setLineWidth(5.0);
			gc.strokeRect(275, 150, 250, 300);
		}
	}
}
