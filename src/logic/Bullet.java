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
	public Bullet(double x,double y,KeyCode b,int bullet_direction){
		this.x = x;
		this.y = y;
		this.z = -10;
		this.radius = 20;
		this.b = b;
		this.Bullet_direction = bullet_direction;
	}
	
	public void onCollision(Player1 tank){
		RenderableHolder.explosionSound.play();
	}
	
	private void Shoot() {
			isShoot = true;
	}
	public void update(double x,double y) {
		if (InputUtility.getKeyPressed(b)) {
			Shoot();
			InputUtility.remove(b);
		} 
		if(isShoot==false) {
			this.x = x;
			this.y = y;
		}
	}


	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.LAWNGREEN);
		gc.fillOval(x, y, 25, 25);
		if(isShoot) {
			x+=30*Bullet_direction;
		}
		if(x>800) {
			isShoot = false;
		}
		if(x<0) {
			isShoot = false;
		}
	}

}
