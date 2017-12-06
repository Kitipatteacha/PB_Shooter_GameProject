package scene;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

@SuppressWarnings("restriction")
public class MyLabel extends Canvas {
	private Font TEXT_FONT = new Font("Monospace", 150);
	private final static int width = 700;
	private final static int height = 180;
	private Color textColor;
	
	public MyLabel(String text) {
		super(width,height);
		GraphicsContext gc = this.getGraphicsContext2D();
		FontLoader fl = Toolkit.getToolkit().getFontLoader();
		double text_width = fl.computeStringWidth(text, TEXT_FONT);
		
		textColor = Color.AQUAMARINE;
		gc.setFont(TEXT_FONT);
		gc.setFill(textColor);
		gc.fillText(text, (width - text_width)/2, 150);
	}
}
