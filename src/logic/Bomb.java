package logic;

import Shooter.BaseShooter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bomb extends CollidableEntity{

	public boolean isThrow = false;
	private int Bomb_direction ;
	private int side;
	private BaseShooter owner;
	private BaseShooter target;
	private double posYTimer=0;
	private double speed;
	private double speedX;
	private double speedY;
	
	public Bomb(int side,int lane, int col,BaseShooter owner){
		this.z = -15;
		this.speed = 10;
		this.speedX = Math.cos(Math.PI/3)*speed;
		this.speedY = Math.sin(Math.PI/3)*speed;
		this.side = side;
		this.lane = lane;
		this.col = col;
		
		this.owner = owner;
		this.target = GameLogic.getOpponentOf(owner);
		this.posYTimer = System.nanoTime();
		if(side == 0) {
			this.Bomb_direction = 1;
		}
		else {
			this.Bomb_direction = -1;
		}
		bomb();
	}
	
	private void bomb() {
		this.x = Ground.getPosX(col,side);
		this.y = Ground.getPosY(lane)-70;
		isThrow = true;
	}

	public void update() {
		if(this.y > Ground.getPosY(lane)) {
			
			if(target.getCol()==this.col && target.getLane() == this.lane) {
				owner.attack(target,this);
			}
			isThrow = false;
			destroyed = true;
		}
		if(speedY<0)this.z=90;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		double t = (System.nanoTime()-posYTimer)/1000000000;
		if(isThrow) {
			gc.setFill(Color.ORANGE);
			gc.fillOval(x, y, 25, 25);
			y-=speedY;
			x+=speedX*Bomb_direction;
			speedY -= (0.5)*t;
			if(y-speedY > Ground.getPosY(lane)) {
				gc.fillRect(Ground.getPosX(col, target.getSide())-50,Ground.getPosY(lane)-70, 100, 100);
			}
		}
	}
}
