package Shooter;

import logic.Ground;

public class Robot extends BaseShooter{

	public Robot(int side) {
		super(side);
		this.imageW=107;
		this.imageH=184;
		this.nNormalPic=4;
		this.nWarpPic = 3;
		this.x = Ground.getPosX(col, side)-120;
		
		this.maxHp = 500;
		this.hp = 500;
		this.atk = 30;
		this.atkCD = 2.0;
		loadAnimate("Robot");
	}
}
