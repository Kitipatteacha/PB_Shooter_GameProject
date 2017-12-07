package logic;

import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import sharedObject.RenderableHolder;

public class Player2 extends CollidableEntity {
	
    private boolean warp = false;
    private boolean shoot = false;
	public Player2(double x, double y) {
		this.x = x;
		this.y = y;
		this.radius = 60;
	}

	private void Up() {
		if(y-90>200)
			this.y -=90;
	}
	private void Left() {
		if(x-130>400)
			this.x -= 130;
	}
	private void Right() {
		if(x+130<800)
			this.x += 130;
	}
	private void Down() {
		if(y+90<400)
			this.y += 90;
	}

	public void update() {
		if (InputUtility.getKeyPressed(KeyCode.UP)&&shoot==false) {
			warp = true;
			Up();
			InputUtility.remove(KeyCode.UP);
		}
		if (InputUtility.getKeyPressed(KeyCode.LEFT)&&shoot==false) {
			warp = true;
			Left();
			InputUtility.remove(KeyCode.LEFT);
		} 
		if (InputUtility.getKeyPressed(KeyCode.RIGHT)&&shoot==false) {
			warp = true;
			Right();
			InputUtility.remove(KeyCode.RIGHT);
		} 
		if (InputUtility.getKeyPressed(KeyCode.DOWN)&&shoot==false) {
			warp = true;
			Down();
			InputUtility.remove(KeyCode.DOWN);
		} 
	}

	@Override
	public void draw(GraphicsContext gc) {
		double t = System.nanoTime() / 1100000000.0;
		if(warp == false) 
			gc.drawImage(RenderableHolder.player2_animated.getFrame(t), x-radius,  y-radius );
	    if(warp == true) {
	    	gc.drawImage(RenderableHolder.warp_p2_animated.getFrame(t), x-radius,  y-radius );
	    	if(RenderableHolder.warp_p2_animated.getIndex()==4)
	    		warp = false;
	    }
	    
	    
	}
	
	public double get_x_position() {
		return this.x;
	}
	public double get_y_position() {
		return this.y;
	}
}