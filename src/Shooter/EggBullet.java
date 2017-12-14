package Shooter;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EggBullet extends Bullet{

	public EggBullet(int side, int lane, int col, BaseShooter owner) {
		super(side, lane, col, owner);
		this.speed = 15;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillOval(x, y, 35, 30);
	}
}
