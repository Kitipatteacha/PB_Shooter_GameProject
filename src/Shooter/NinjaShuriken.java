package Shooter;

import arts.GameSound;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.Ground;

public class NinjaShuriken extends Bullet{

	private Image bullet ;
	protected int charge;
	public NinjaShuriken(int side, int lane, int col, BaseShooter owner,int charge) {
		super(side, lane, col, owner);
		bullet = new Image(ClassLoader.getSystemResource("Ninja_Bullet_"+ side +".png").toString());
		this.charge = charge;
		this.speed = 25;
	}

	public void shoot() {
		this.x = Ground.getPosX(col,side);
		this.y = Ground.getPosY(lane)-120;
		isShoot = true;
		GameSound.playShotSound();
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(bullet, x, y);
	}
	
	public int getCharge() {
		return charge;
	}
}
