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

}
