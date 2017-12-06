package scene;




import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import window.SceneManager;

@SuppressWarnings("restriction")
public class BaseButton extends Canvas {
	private String goToScene;
	private String text;
	private static double width=100;
	private static double height=50;
	private Font TEXT_FONT = new Font("Monospace", 25);
	private Color fill;
	private Color stroke;
	private Color textColor;
	private double text_width;
	GraphicsContext gc = this.getGraphicsContext2D();
	
	public BaseButton(String text) {
		super(width,height);
		FontLoader fl= Toolkit.getToolkit().getFontLoader();
		text_width = fl.computeStringWidth(text, TEXT_FONT);
		this.text = text;
		fill = Color.BLACK;
		stroke = Color.RED;
		textColor = Color.CYAN;
		drawButton();
		
		this.goToScene = text;
		this.addEventHandler();
	}

	public void setGoToScene(String goToScene) {
		this.goToScene = goToScene;
	}
	
	private void addEventHandler() {
		this.setOnMouseClicked((MouseEvent event) -> {
			SceneManager.gotoSceneOf(goToScene);
		});
		this.setOnMouseEntered((MouseEvent event) -> {
			drawButtonEnter();
		});
		this.setOnMouseExited((MouseEvent event) -> {
			drawButton();
		});
	} 
	
	private void drawButton() {
		gc.clearRect(0, 0, width, height);
		
		gc.setFill(fill);
		gc.fillRect(0, 0, width, height);
		
		gc.setStroke(stroke);
		gc.setLineWidth(5.0);
		gc.strokeRect(0, 0, width, height);
		
		gc.setFill(textColor);
		gc.setFont(TEXT_FONT);
		gc.fillText(text, (width-text_width)/2, 35);
	}
	
	private void drawButtonEnter() {
		gc.clearRect(0, 0, width, height);
		
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, width, height);
		
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(5.0);
		gc.strokeRect(0, 0, width, height);
		
		gc.setFill(Color.BLACK);
		gc.setFont(TEXT_FONT);
		gc.fillText(text, (width-text_width)/2, 35);
	}
}
