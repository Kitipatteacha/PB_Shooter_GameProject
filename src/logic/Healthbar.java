package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Healthbar extends CollidableEntity{
	public boolean ishit = false;
	public int Damage = 0;
	public Healthbar(int x,int y,int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.LAWNGREEN);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(3);
		gc.fillRect(x, y, 300, 25);
		gc.strokeRect(x, y, 300, 25);
		if(ishit == true&& Damage!=300) {
			Damage+=10;
			ishit = false;
		}
		gc.setFill(Color.RED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(3);
		gc.fillRect(x, y, Damage, 25);
		gc.strokeRect(x, y, Damage, 25);
	}

}
