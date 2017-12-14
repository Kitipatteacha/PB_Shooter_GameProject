package Shooter;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RockManBullet extends Bullet{

	public RockManBullet(int side, int lane, int col, BaseShooter owner) {
		super(side, lane, col, owner);
		this.speed = 15;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillOval(x, y, 35, 30);
	}
}
