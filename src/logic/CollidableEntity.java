package logic;

public abstract class CollidableEntity extends Entity{
	protected int radius;
	protected double hitboxX,hitboxY,hitboxW,hitboxH;
	
	protected boolean collideWith(CollidableEntity other){
		this.calculateHitbox();
		other.calculateHitbox();
		if(this.getLane()==other.getLane()) {
			return this.hitboxX < other.hitboxX + other.hitboxW && this.hitboxX + this.hitboxW >other.hitboxX
					&& this.hitboxY < other.hitboxY + other.hitboxH && this.hitboxY + this.hitboxH >other.hitboxY;
			
		}
		else {
			return false;
		}
	}
	
	public void calculateHitbox() {};
}
