package logic;

import Shooter.BaseShooter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HealthBar extends Entity{
	public double hpRatio;
	public int side;
	private BaseShooter shooter;
	
	public HealthBar(int side,BaseShooter sh){
		if(side == 0)this.x = 20;
		else this.x = 480;
		
		this.y = 20;
		this.z = -100;
		this.side = side;
		this.shooter = sh;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		hpRatio = shooter.getHp()/shooter.getMaxHp();
		
		if(side == 0) {
			
			gc.setFill(Color.RED);
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(3);
			gc.fillRect(x, y, 300, 25);
			gc.strokeRect(x, y, 300, 25);
			
			gc.setFill(Color.LAWNGREEN);
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(3);
			gc.fillRect(x, y, hpRatio*300, 25);
			gc.strokeRect(x, y, hpRatio*300, 25);
		}
		else {
			gc.setFill(Color.LAWNGREEN);
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(3);
			gc.fillRect(x, y, 300, 25);
			gc.strokeRect(x, y, 300, 25);
			
			gc.setFill(Color.RED);
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(3);
			gc.fillRect(x, y, (1-hpRatio)*300, 25);
			gc.strokeRect(x, y, (1-hpRatio)*300, 25);
			
		}
		
	}

}
