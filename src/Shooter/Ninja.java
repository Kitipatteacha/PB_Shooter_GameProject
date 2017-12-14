package Shooter;

import logic.Ground;

public class Ninja extends BaseShooter{

	public Ninja(int side) {
		super(side);
		this.imageW=132;
		this.imageH=201;
		this.nNormalPic=4;
		this.nWarpPic = 3;
		this.x = Ground.getPosX(col, side)-120;
		
		this.maxHp = 200;
		this.hp = 200;
		this.atk = 10;
		this.atkCD = 0.1;
		loadAnimate("Ninja");
	}
}
