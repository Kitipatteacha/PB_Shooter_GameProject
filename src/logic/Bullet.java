package logic;

import Shooter.BaseShooter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet extends CollidableEntity {
	public boolean isShoot = false;
	private int Bullet_direction ;
	private int side;
	private BaseShooter owner;
	private BaseShooter target;
	private double speed;
	
	public Bullet(int side,int lane, int col,BaseShooter owner){
		this.z = -10;
		this.radius = 20;
		this.speed = 20;
		this.side = side;
		this.lane = lane;
		this.col = col;
		this.owner = owner;
		this.target = GameLogic.getOpposite(owner);
		if(side == 0) {
			this.Bullet_direction = 1;
		}
		else {
			this.Bullet_direction = -1;
		}
		shoot();
	}
	
	public void shoot() {
		this.x = Ground.getPosX(col,side)-50;
		this.y = Ground.getPosY(lane)-70;
		isShoot = true;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.LAWNGREEN);
		gc.fillOval(x, y, 25, 25);
		if(isShoot) {
			x+=speed*Bullet_direction;
		}
		if(x>800 || x<0) {
			isShoot = false;
			destroyed = true;
		}
	}
	
	public void update() {
		if(this.collideWith(target)) {
			owner.attack(target,this);
			isShoot = false;
			destroyed = true;
		}
	}
	
	public void calculateHitbox() {
		this.hitboxX = this.x;
		this.hitboxY = this.y;
		this.hitboxW = 25;
		this.hitboxH = 25;
	};
	
}
