package Shooter;

import logic.CollidableEntity;
import logic.GameLogic;
import logic.Ground;

public class Egg extends BaseShooter{

	public Egg(int side) {
		super(side);
		this.imageW=175;
		this.imageH=205;
		this.nNormalPic=6;
		this.nWarpPic = 6;
		this.bombDmg = 50;
		this.bombCD = 5.0;
		this.atk = 30;
		this.atkCD = 2.0;
		if(side==0)this.x = Ground.getPosX(col, side)-120;
		else this.x = Ground.getPosX(col, side)-50;
		loadAnimate("Egg");
	}

	protected void left() {
		if(col-1>0&&movable) {
			this.col-=1;
			if(side==0)this.x = Ground.getPosX(col, side)-120;
			else this.x = Ground.getPosX(col, side)-50;
		}
	}
	protected void right() {
		if(col+1<=3&&movable) {
			this.col+=1;
			if(side==0)this.x = Ground.getPosX(col, side)-120;
			else this.x = Ground.getPosX(col, side)-50;
		}
	}
	
	@Override
	public void shoot() {
		if((System.nanoTime()-this.atkTimer)/1000000000 >= this.atkCD) {
			this.atkTimer = System.nanoTime();
			GameLogic.addNewObject(new EggBullet(side,lane,col,this));
		}
	}
	
	protected void doDamage(BaseShooter other,CollidableEntity way) {
		double totalAtk;
		totalAtk = atk;//Attack Calculation
		if(way instanceof Bomb) {
			totalAtk=bombDmg;
			other.stun(1);
		}
		if(way instanceof Bullet) {
			totalAtk=atk;
		}
		
		other.takeDamage(totalAtk);
	}
}
