package logic;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import sharedObject.RenderableHolder;

public class Player1 extends CollidableEntity {
	
    private boolean warp = false;
    private boolean shoot = false;
    
	public Player1(double x, double y) {
		this.x = x;
		this.y = y;
		this.radius = 60;
		this.lane = 2;
	}

	private void up() {
		if(y-180>100) {
			this.y -=90;
			this.lane -=1;
		}
	}
	private void Left() {
		if(x-130>0)
			this.x -= 130;
	}
	private void Right() {
		if(x+130<400)
			this.x += 130;
	}
	private void Down() {
		if(y+90<400) {
			this.y += 90;
			this.lane+=1;
		}
	}

	public void update() {
		if (InputUtility.getKeyPressed(KeyCode.W)&&shoot==false) {
			warp = true;
			up();
			InputUtility.remove(KeyCode.W);
		}
		if (InputUtility.getKeyPressed(KeyCode.A)&&shoot==false) {
			warp = true;
			Left();
			InputUtility.remove(KeyCode.A);
		} 
		if (InputUtility.getKeyPressed(KeyCode.D)&&shoot==false) {
			warp = true;
			Right();
			InputUtility.remove(KeyCode.D);
		} 
		if (InputUtility.getKeyPressed(KeyCode.S)&&shoot==false) {
			warp = true;
			Down();
			InputUtility.remove(KeyCode.S);
		} 
	}

	@Override
	public void draw(GraphicsContext gc) {
		double t = System.nanoTime() / 1100000000.0;
		if(warp == false) 
			gc.drawImage(RenderableHolder.player1_animated.getFrame(t), x-radius,  y-radius );
		else if(warp == true) {
	    		gc.drawImage(RenderableHolder.warp_animated.getFrame(t), x-radius,  y-radius );
	    		if(RenderableHolder.warp_animated.getIndex()==4)
	    			warp = false;
	    }
	}
	
	public double get_x_position() {
		return this.x;
	}
	
	public double get_y_position() {
		return this.y;
	}
	
	public int getLane() {
		return this.lane;
	}
}
