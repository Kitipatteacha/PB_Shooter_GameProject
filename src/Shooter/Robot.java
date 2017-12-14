package Shooter;

import logic.CollidableEntity;
import logic.GameLogic;
import logic.Ground;

public class Robot extends BaseShooter{

	public Robot(int side) {
		super(side);
		this.imageW=107;
		this.imageH=184;
		this.nNormalPic=4;
		this.nWarpPic = 3;
		this.x = Ground.getPosX(col, side)-imageW/2;
		this.y = Ground.getPosY(lane)-imageH;
		
		this.maxHp = 500;
		this.hp = 500;
		this.atk = 5;
		this.atkCD = 0.2;
		loadAnimate("Robot");
	}
	protected void up() {
		if(lane-1>0&&movable) {
			this.z-=10;
			this.lane -=1;
			this.y = Ground.getPosY(lane)-imageH;
		}
	}
	protected void down() {
		if(lane+1<=3&&movable) {
			this.z+=10;
			this.lane +=1;
			this.y = Ground.getPosY(lane)-imageH;
		}
	}
	protected void left() {
		if(col-1>0&&movable) {
			this.col-=1;
			this.x = Ground.getPosX(col, side)-imageW/2;
		}
	}
	protected void right() {
		if(col+1<=3&&movable) {
			this.col+=1;
			this.x = Ground.getPosX(col, side)-imageW/2;
		}
	}
	
}
