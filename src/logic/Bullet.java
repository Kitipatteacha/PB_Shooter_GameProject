package logic;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import sharedObject.RenderableHolder;
import logic.Player1;

public class Bullet extends CollidableEntity {
	public boolean isShoot = false;
	private KeyCode b;
	private int Bullet_direction ;
	private int side;
	
	public Bullet(int side){
		this.z = -10;
		this.radius = 20;
		this.Bullet_direction = side;
		if(side == 0) {
			this.Bullet_direction = 1;
		}
		else {
			this.Bullet_direction = -1;
		}
		this.side = side;
	}
	
	public void onCollision(Player1 tank){
		RenderableHolder.explosionSound.play();
	}
	
	public void Shoot(int lane, int col) {
		this.x = Ground.getPosX(col,side);
		this.y = Ground.getPosY(lane);
		isShoot = true;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.LAWNGREEN);
		gc.fillOval(x-50, y-70, 25, 25);
		if(isShoot) {
			x+=30*Bullet_direction;
		}
		if(x>800 || x<0) {
			isShoot = false;
		}
	}
	
	public boolean getIsShoot() {
		return isShoot;
	}
}
