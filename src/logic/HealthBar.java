package logic;

import Shooter.BaseShooter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

@SuppressWarnings("restriction")
public class HealthBar extends Entity{
	public double hpRatio;
	public int side;
	private BaseShooter owner;
	private Font TEXT_FONT = new Font("Monospace", 25);
	private double text_width;
	FontLoader fl= Toolkit.getToolkit().getFontLoader();
	
	public HealthBar(int side,BaseShooter sh){
		if(side == 0)this.x = 20;
		else this.x = 480;
		
		this.y = 20;
		this.z = -100;
		this.side = side;
		this.owner = sh;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		hpRatio = owner.getHp()/owner.getMaxHp();
		text_width = fl.computeStringWidth(""+(int)owner.getHp(), TEXT_FONT);
		if(side == 0) {
			
			gc.setFill(Color.CORNFLOWERBLUE);
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(3);
			gc.fillRect(x, y, 300, 25);
			gc.strokeRect(x, y, 300, 25);
			
			gc.setFill(Color.LAWNGREEN);
			if(owner.getHp()<owner.getMaxHp()*0.4)gc.setFill(Color.ORANGE);
			if(owner.getHp()<owner.getMaxHp()*0.2)gc.setFill(Color.RED);
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(3);
			gc.fillRect(x, y, hpRatio*300, 25);
			gc.strokeRect(x, y, hpRatio*300, 25);
			
			gc.setFont(TEXT_FONT);
			gc.setFill(Color.GREY);
			gc.fillText(""+(int)owner.getHp(), 25, 42);
		}
		else {
			gc.setFill(Color.LAWNGREEN);
			if(owner.getHp()<owner.getMaxHp()*0.4)gc.setFill(Color.ORANGE);
			if(owner.getHp()<owner.getMaxHp()*0.2)gc.setFill(Color.RED);
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(3);
			gc.fillRect(x, y, 300, 25);
			gc.strokeRect(x, y, 300, 25);
			
			gc.setFill(Color.CORNFLOWERBLUE);
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(3);
			gc.fillRect(x, y, (1-hpRatio)*300, 25);
			gc.strokeRect(x, y, (1-hpRatio)*300, 25);
			
			gc.setFont(TEXT_FONT);
			gc.setFill(Color.GREY);
			gc.fillText(""+(int)owner.getHp(), 775-text_width, 42);
		}
		
	}

}
