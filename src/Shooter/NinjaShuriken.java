package Shooter;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.Ground;
import sharedObject.RenderableHolder;

public class NinjaShuriken extends Bullet{

	private Image bullet = new Image(ClassLoader.getSystemResource("Ninja_Bullet.png").toString());
	protected int charge;
	public NinjaShuriken(int side, int lane, int col, BaseShooter owner,int charge) {
		super(side, lane, col, owner);
		this.charge = charge;
		this.speed = 25;
	}

	public void shoot() {
		this.x = Ground.getPosX(col,side);
		this.y = Ground.getPosY(lane)-120;
		isShoot = true;
		RenderableHolder.shotSound.setVolume(0.1);
		RenderableHolder.shotSound.play();
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(bullet, x, y);
	}
	
	public int getCharge() {
		return charge;
	}
}
