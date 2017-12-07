package logic;

public abstract class CollidableEntity extends Entity{
	protected int radius;
	
	protected boolean collideWith(CollidableEntity other){
		if(this.getLane()==other.getLane()) {
			return Math.hypot(this.x-other.x, this.y-other.y) <= this.radius+other.radius;
		}
		else {
			return false;
		}
	}
}
