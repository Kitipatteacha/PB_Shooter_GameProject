package Shooter;

import logic.CollidableEntity;
import logic.GameLogic;

public class RockMan extends BaseShooter{

	public RockMan(int side) {
		super(side, "Balloon");
		this.bombDmg = 70;
		this.bombCD = 5.0;
		this.atk = 30;
		this.atkCD = 2.0;
	}

	@Override
	public void shoot() {
		if((System.nanoTime()-this.atkTimer)/1000000000 >= this.atkCD) {
			this.atkTimer = System.nanoTime();
			GameLogic.addNewObject(new RockManBullet(side,lane,col,this));
		}
	}
	
	protected void doDamage(BaseShooter other,CollidableEntity way) {
		double totalAtk;
		totalAtk = atk;//Attack Calculation
		if(way instanceof Bomb)totalAtk=bombDmg;
		if(way instanceof Bullet) {
			totalAtk=atk;
			//other.stunt();
		}
		
		other.takeDamage(totalAtk);
	}
}
