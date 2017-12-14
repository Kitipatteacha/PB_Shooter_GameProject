package Shooter;

import logic.CollidableEntity;

public class Balloon extends BaseShooter{
	private int bulletCount=0;

	public Balloon(int side) {
		super(side);
		this.nNormalPic = 9;
		this.nWarpPic=5;
		this.atk = 5;
		this.atkCD = 0.4;
		loadAnimate("Balloon");
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
		}
	}
	
}
