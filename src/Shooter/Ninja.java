package Shooter;

import logic.CollidableEntity;
import logic.GameLogic;
import logic.Ground;

public class Ninja extends BaseShooter{
	
	private int charge = 0;
	private final double damagePerCharge = 1.2; 
	
	public Ninja(int side) {
		super(side);
		this.imageW=132;
		this.imageH=201;
		this.nNormalPic=4;
		this.nWarpPic = 3;
		this.x = Ground.getPosX(col, side)-60;
		
		this.maxHp = 200;
		this.hp = 200;
		this.bombDmg = 15;
		this.atk = 8;
		this.atkCD = 0.8;
		loadAnimate("Ninja");
	}
	
	protected void up() {
		if(lane-1>0&&movable) {
			this.z-=10;
			this.lane -=1;
			this.y = Ground.getPosY(lane)-imageH;
			charge++;
		}
	}
	protected void down() {
		if(lane+1<=3&&movable) {
			this.z+=10;
			this.lane +=1;
			this.y = Ground.getPosY(lane)-imageH;
			charge++;
		}
	}
	protected void left() {
		if(col-1>0&&movable) {
			this.col-=1;
			this.x = Ground.getPosX(col, side)-imageW/2;
			charge++;
		}
	}
	protected void right() {
		if(col+1<=3&&movable) {
			this.col+=1;
			this.x = Ground.getPosX(col, side)-imageW/2;
			charge++;
		}
	}
	
	protected void throwBomb() {
		if((System.nanoTime()-this.bombTimer)/1000000000 >= this.bombCD && shootable) {
			this.bombTimer = System.nanoTime();
			GameLogic.addNewObject(new NinjaBomb(side,lane,col,this));
		}
	}

	protected void shoot() {
		if((System.nanoTime()-this.atkTimer)/1000000000 >= this.atkCD && shootable) {
			this.atkTimer = System.nanoTime();
			GameLogic.addNewObject(new NinjaShuriken(side,lane,col,this,charge));
			charge=0;
		}
	}
	
	protected void doDamage(BaseShooter other,CollidableEntity way) {
		double totalAtk;
		totalAtk = atk;//Attack Calculation
		if(way instanceof Bomb) {
			totalAtk=bombDmg;
			other.snare(1.0);
		}
		if(way instanceof NinjaShuriken) {
			totalAtk=atk+(((NinjaShuriken) way).getCharge()*damagePerCharge);
		}
		
		other.takeDamage(totalAtk);
	}
}
