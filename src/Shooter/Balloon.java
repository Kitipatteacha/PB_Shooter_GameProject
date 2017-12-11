package Shooter;

public class Balloon extends BaseShooter{

	public Balloon(int side) {
		super(side,"Balloon");
		this.atk = 5;
		this.atkCD = 0.05;
	}

}
