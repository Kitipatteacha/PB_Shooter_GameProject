package Shooter;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.CollidableEntity;
import logic.GameLogic;
import logic.Ground;
import sharedObject.AnimatedImage;

public class Bomb extends CollidableEntity{
	private AnimatedImage bomb_Animation = new AnimatedImage();
	private Image bomb = new Image(ClassLoader.getSystemResource("bomb.png").toString());

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
		loadAnimate();
		bomb();
	}
	
	private void bomb() {
		this.x = Ground.getPosX(col,side);
		this.y = Ground.getPosY(lane)-70;
		isThrow = true;
	}

	protected void loadAnimate() {
		Image[] bombArray = new Image[25];
		
		for (int i = 1; i <= 24; i++) {
			bombArray[i] = new Image(ClassLoader.getSystemResource("Bomb_" + i + ".png").toString() );
		}
		
		bomb_Animation.frames = bombArray;
		this.bomb_Animation.setDuration(0.01);
	}
	
	public void update() {
		if(this.y > Ground.getPosY(lane)&&isThrow) {
			
			if(target.getCol()==this.col && target.getLane() == this.lane) {
				owner.doDamage(target,this);
			}
			isThrow = false;
		}
		if(this.speedY<0) {
			if(this.lane == 1) {
				this.z = 5;
			}
			else if(this.lane == 2) {
				this.z = 15;
			}
			else if(this.lane == 3) {
				this.z = 25;
			}
		}
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		double t = (System.nanoTime()-posYTimer)/1000000000;
		double t2 = System.nanoTime();// / 1100000000.0;
		if(isThrow) {
			gc.drawImage(bomb, x, y);
			
			y-=speedY;
			x+=speedX*Bomb_direction;
			speedY -= (0.5)*t;
		}
		else {
			gc.drawImage(bomb_Animation.getFrame(t2), Ground.getPosX(col, target.getSide())-30,  Ground.getPosY(lane)-175 );
			if(bomb_Animation.getIndex()>=24) {
				destroyed = true;
			}
		}
	}
}
