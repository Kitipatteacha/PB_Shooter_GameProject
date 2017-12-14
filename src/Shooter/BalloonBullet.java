package Shooter;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BalloonBullet extends Bullet{
	
	private Image bullet = new Image(ClassLoader.getSystemResource("Balloon_Bullet.png").toString());

	public BalloonBullet(int side, int lane, int col, BaseShooter owner) {
		super(side, lane, col, owner);
		this.speed = 10;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(bullet, x, y);
	}
}
