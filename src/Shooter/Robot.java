package Shooter;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.CollidableEntity;
import logic.GameLogic;
import logic.Ground;

public class Robot extends BaseShooter{

	private int maxBomb = 3;
	private int bombCount = 0;
	public Robot(int side) {
		super(side);
		this.imageW=107;
		this.imageH=184;
		this.nNormalPic=4;
		this.nWarpPic = 3;
		this.x = Ground.getPosX(col, side)-imageW/2;
		this.y = Ground.getPosY(lane)-imageH;
		
		this.maxHp = 300;
		this.hp = 300;
		this.atk = 3;
		this.atkCD = 1.2;
		this.bombCD = 4;
		loadAnimate("Robot");
	}
	protected void up() {
		if(lane-1>0&&movable) {
			this.z-=10;
			this.lane -=1;
			this.y = Ground.getPosY(lane)-imageH;
		}
	}
	protected void down() {
		if(lane+1<=3&&movable) {
			this.z+=10;
			this.lane +=1;
			this.y = Ground.getPosY(lane)-imageH;
		}
	}
	protected void left() {
		if(col-1>0&&movable) {
			this.col-=1;
			this.x = Ground.getPosX(col, side)-imageW/2;
		}
	}
	protected void right() {
		if(col+1<=3&&movable) {
			this.col+=1;
			this.x = Ground.getPosX(col, side)-imageW/2;
		}
	}
	
	protected void doDamage(BaseShooter other,CollidableEntity way) {
		double totalAtk;
		totalAtk = atk;//Attack Calculation
		if(way instanceof Bomb) {
			totalAtk=bombDmg;
			if(bombCount<maxBomb)bombCount++;
		}
		if(way instanceof Bullet)totalAtk=atk;
		
		other.takeDamage(totalAtk);
	}

	protected void throwBomb() {
		if(bombCount>0 && shootable) {
			bombCount--;
			GameLogic.addNewObject(new Bomb(side,lane,col,this));
		}
	}
	
	public void update() {
		if (InputUtility.getKeyPressed(up)) {
			warp = true;
			up();
			InputUtility.remove(up);
		}
		else if (InputUtility.getKeyPressed(left)) {
			warp = true;
			left();
			InputUtility.remove(left);
		} 
		else if (InputUtility.getKeyPressed(right)) {
			warp = true;
			right();
			InputUtility.remove(right);
		} 
		else if (InputUtility.getKeyPressed(down)) {
			warp = true;
			down();
			InputUtility.remove(down);
		} 
		else if (InputUtility.getKeyPressed(fire)) {
			shoot();
			InputUtility.remove(fire);
		}
		else if (InputUtility.getKeyPressed(bomb)) {
			throwBomb();
			InputUtility.remove(bomb);
		} 
		if(this.movable == false && this.immovableTimer-System.nanoTime()<0) {
			this.movable = true;
		}
		if(this.shootable == false && this.unshootableTimer-System.nanoTime()<0) {
			this.shootable = true;
		}
		if((System.nanoTime()-this.bombTimer)/1000000000 >= this.bombCD && bombCount<maxBomb) {
			this.bombTimer = System.nanoTime();
			bombCount++;
		}
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		double t = System.nanoTime();// / 1100000000.0;
		if(movable) {
			if(warp == false) 
				gc.drawImage(normal_Animation.getFrame(t), x,  y );
			else if(warp == true) {
		    		gc.drawImage(warp_Animation.getFrame(t), x,  y);
		    		if(warp_Animation.getIndex()==nWarpPic-1)
		    			warp = false;
		    }
		}
		else if(movable==false && shootable==false){
			gc.drawImage(normal_Animation.getFrame(1100000000), x,  y );
			gc.setFill(Color.WHITE);
			gc.setFont(new Font("Monospace", 30));
			gc.fillText("Stun", x+20, y+60);
			
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(0.5);
			gc.strokeText("Stun", x+20, y+60);
		}
		else if(movable==false){
			gc.drawImage(normal_Animation.getFrame(1100000000), x,  y );
			gc.setFill(Color.MEDIUMPURPLE);
			gc.setFont(new Font("Monospace", 30));
			gc.fillText("Snare", x+20, y+60);
			
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(0.5);
			gc.strokeText("Snare", x+20, y+60);
		}
		else if(shootable==false){
			gc.setFill(Color.WHITE);
			gc.setFont(new Font("Monospace", 30));
			gc.fillText("Disarm", x+20, y+60);
			
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(0.5);
			gc.strokeText("Disarm", x+20, y+60);
		}
		gc.setFill(Color.WHITE);
		gc.setFont(new Font("Monospace", 30));
		gc.fillText(""+bombCount, x+30, y+175);
		
		hpBar.draw(gc);
		cdBar.draw(gc);
	}
}
