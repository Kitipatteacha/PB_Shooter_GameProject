package logic;

import Shooter.BaseShooter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CoolDownDisplay extends Entity{

	private double atkRatio;
	private double bombRatio;
	public int side;
	private BaseShooter owner;
	private final double iconSize = 25;
	
	public CoolDownDisplay(int side,BaseShooter owner){
		if(side == 0)this.x = 20;
		else this.x = 725;
		
		this.y = 50;
		this.z = -80;
		this.side = side;
		this.owner = owner;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		//shoot bomb ability
		atkRatio = ((System.nanoTime()-owner.getAtkTimer())/1000000000)/owner.atkCD;
		gc.setFill(Color.GOLD);//For insert icon
		gc.fillRoundRect(x, y, iconSize, iconSize, 5, 5);
		
		if(atkRatio<1) {
			gc.setFill(Color.LIGHTSKYBLUE);
			gc.fillRect(x, y, iconSize, iconSize*(1-atkRatio));
			
			gc.setStroke(Color.RED);
			gc.setLineWidth(1.5);
			gc.strokeRoundRect(x, y, iconSize, iconSize, 5, 5);
			
		}
		else {
			gc.setStroke(Color.GREEN);
			gc.setLineWidth(1.5);
			gc.strokeRoundRect(x, y, iconSize, iconSize, 5, 5);
		}
		
		bombRatio = ((System.nanoTime()-owner.getBombTimer())/1000000000)/owner.bombCD;
		gc.setFill(Color.BLACK);//For Insert Icon
		gc.fillRoundRect(x + (iconSize+5), y, iconSize, iconSize, 5, 5);
		
		if(bombRatio<1) {
			gc.setFill(Color.LIGHTSKYBLUE);
			gc.fillRect(x + (iconSize+5), y, iconSize, iconSize*(1-bombRatio));
			
			gc.setStroke(Color.RED);
			gc.setLineWidth(1.5);
			gc.strokeRoundRect(x + (iconSize+5), y, iconSize, iconSize, 5, 5);
			
		}
		else {
			gc.setStroke(Color.GREEN);
			gc.setLineWidth(1.5);
			gc.strokeRoundRect(x + (iconSize+5), y, iconSize, iconSize, 5, 5);
		}
	}
}
