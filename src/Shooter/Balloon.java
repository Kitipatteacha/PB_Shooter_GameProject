package Shooter;

public class Balloon extends BaseShooter{

	public Balloon(int side) {
		super(side);
		this.nNormalPic = 9;
		this.nWarpPic=5;
		this.atk = 5;
		this.atkCD = 0.3;
		loadAnimate("Balloon");
	}

}
