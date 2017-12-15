package Shooter;

import java.io.FileNotFoundException;

import logic.CollidableEntity;
import logic.GameLogic;

public class Balloon extends BaseShooter{
	private int bulletCount=0;

	public Balloon(int side) {
		super(side);
		this.nNormalPic = 9;
		this.nWarpPic=5;
		
		this.atk = 15;
		this.atkCD = 0.5;
		try {
			loadAnimate("Balloon");
		} catch (FileNotFoundException e) {
			System.out.println("Error");
		}
	}

	protected void doDamage(BaseShooter other,CollidableEntity way) {
		double totalAtk;
		totalAtk = atk;
		if(way instanceof Bomb)totalAtk=bombDmg;
		if(way instanceof Bullet) {
			totalAtk=atk;
			bulletCount++;
		}
		
		other.takeDamage(totalAtk);
		
		if(bulletCount==3) {
			heal(10);
			bulletCount=0;
		}
	}
	
	protected void shoot() {
		if((System.nanoTime()-this.atkTimer)/1000000000 >= this.atkCD && shootable) {
			this.atkTimer = System.nanoTime();
			GameLogic.addNewObject(new BalloonBullet(side,lane,col,this));
		}
	}
}
