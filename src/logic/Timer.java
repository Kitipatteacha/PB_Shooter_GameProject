package logic;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

@SuppressWarnings("restriction")
public class Timer extends Entity{

	private double start;
	private int time;
	private double text_width;
	private Font TEXT_FONT = new Font("Monospace", 30);
	
	public Timer(int countDown) {
		z = 50;
		start = System.nanoTime();
		this.time = countDown;
		
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.BLUEVIOLET);
		gc.fillRect(330, 10, 140, 45);
		FontLoader fl= Toolkit.getToolkit().getFontLoader();
		text_width = fl.computeStringWidth("Time : "+time, TEXT_FONT);
		gc.setFill(Color.WHITE);
		gc.setFont(TEXT_FONT);
		gc.fillText("Time : "+time, 400-text_width/2, 40);
		if((System.nanoTime()-start) >= 1000000000) {
			this.time-=1;
			start = System.nanoTime();
		}
	}

	public int getTime() {
		return time;
	}
}
