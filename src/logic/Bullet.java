package logic;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import sharedObject.RenderableHolder;
import logic.Player1;

public class Bullet extends CollidableEntity {
	private boolean isShoot = false;
	private KeyCode fire;
	private int Bullet_direction ;
	
	public Bullet(double x,double y,KeyCode fire,int a){
		this.x = x;
		this.y = y;
		this.z = -10;
		this.radius = 20;
		this.fire = fire;
		this.Bullet_direction = a;
	}
	
	public void onCollision(Player1 tank){
		RenderableHolder.explosionSound.play();
	}
	
	private void Shoot() {
			isShoot = true;
	}
	public void update(double x,double y) {
		if (InputUtility.getKeyPressed(fire)) {
			Shoot();
			InputUtility.remove(fire);
		} 
		if(isShoot==false) {
			this.x = x;
			this.y = y;
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.LAWNGREEN);
		gc.fillOval(x, y, 25, 15);
		if(isShoot) {
			x+=40*Bullet_direction;
		}
		if(x>800) {
			isShoot = false;
		}
		if(x<0) {
			isShoot = false;
		}
	}

}
