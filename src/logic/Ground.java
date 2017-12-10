package logic;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class Ground extends CollidableEntity{

	public Ground(int x,int y){
		this.x = x;
		this.y = y;
		this.z = -20;
	}
	
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.Ground, 0, 300);
	}
	
	public static double getPosY(int lane) {
		if(lane==1) {
			return 350;
		}
		else if (lane == 2) {
			return 440;
		}
		else {
			return 530;
		}
	}

	public static double getPosX(int col,int side) {
		if(side == 0) {
			return 80+(col-1)*132;
		}
		else {
			return 465+(col-1)*132;
		}
	}
}
