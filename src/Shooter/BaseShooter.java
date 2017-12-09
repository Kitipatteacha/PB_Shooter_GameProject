package Shooter;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import logic.Bullet;
import logic.CollidableEntity;
import logic.Ground;
import logic.HealthBar;
import sharedObject.AnimatedImage;

public class BaseShooter extends CollidableEntity{
	protected AnimatedImage normal_Animation = new AnimatedImage();
	protected AnimatedImage warp_Animation = new AnimatedImage();
	private boolean warp = false;
    protected int side ;//0 = left, 1 = right
    protected Bullet bullet;
    protected HealthBar hpBar;
    protected KeyCode up,down,left,right,fire;

    public double maxHp;
    public double hp;
    public double atk;
    
	public BaseShooter(int side) {
		this.radius = 60;
		this.side = side;
		this.lane = 2;
		this.col = 2;
		this.x = Ground.getPosX(col, side);
		this.y = Ground.getPosY(lane);
		if(side == 0) {
			up = KeyCode.W;
			down = KeyCode.S;
			left = KeyCode.A;
			right = KeyCode.D;
			fire = KeyCode.SPACE;
		}
		else {
			up = KeyCode.UP;
			down = KeyCode.DOWN;
			left = KeyCode.LEFT;
			right = KeyCode.RIGHT;
			fire = KeyCode.L;
		}
		
		this.maxHp = 300;
		this.hp = this.maxHp;
		this.atk = 10;
		
		this.hpBar = new HealthBar(side,this);
		this.bullet = new Bullet(side);
		
		loadAnimate();
	}
	
	private void up() {
		if(lane-1>0) {
			this.lane -=1;
			this.y = Ground.getPosY(lane);
		}
	}
	private void down() {
		if(lane+1<=3) {
			this.lane +=1;
			this.y = Ground.getPosY(lane);
		}
	}
	private void left() {
		if(col-1>0) {
			this.col-=1;
			this.x = Ground.getPosX(col, side);
		}
	}
	private void right() {
		if(col+1<=3) {
			this.col+=1;
			this.x = Ground.getPosX(col, side);
		}
	}
	
	private void loadAnimate() {
		Image[] normalArray = new Image[9];
		Image[] warpArray = new Image[5];
		
		if(side == 0) {
			for (int i = 0; i < 9; i++) {
				normalArray[i] = new Image(ClassLoader.getSystemResource(i + ".png").toString() );
			}
			for (int i = 0; i < 5; i++) {
				warpArray[i] = new Image(ClassLoader.getSystemResource("Warp_" + i + ".png").toString() );
	        }
		}
		else {
			for (int i = 0; i < 9; i++) {
				normalArray[i] = new Image(ClassLoader.getSystemResource("player2_" + i + ".png").toString() );
			}
			for (int i = 0; i < 5; i++) {
				warpArray[i] = new Image(ClassLoader.getSystemResource("warp_p2_" + i + ".png").toString() );
	        }
		}
		normal_Animation.frames = normalArray;
		warp_Animation.frames = warpArray;
		this.normal_Animation.setDuration(0.100);
		this.warp_Animation.setDuration(0.100);
	}
	
	public void attack(BaseShooter other) {
		double totalAtk;
		totalAtk = atk;//Attack Calculation
		
		other.takeDamage(totalAtk);	
	}
	
	public double takeDamage(double dmg) {
		double totalDmg;
		totalDmg = dmg;//Damage Calculation
		
		if(this.hp-totalDmg>0)this.hp -= totalDmg;
		else this.hp = 0;
		
		return totalDmg;
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
			if(bullet.getIsShoot()==false)bullet.Shoot(lane, col);
			InputUtility.remove(fire);
		}
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		double t = System.nanoTime() / 1100000000.0;
		if(warp == false) 
			gc.drawImage(normal_Animation.getFrame(t), x-50,  y-220 );
		else if(warp == true) {
	    		gc.drawImage(warp_Animation.getFrame(t), x-50,  y-220 );
	    		if(warp_Animation.getIndex()==4)
	    			warp = false;
	    }
		
		if(bullet.getIsShoot()==true) {
			bullet.draw(gc);
		}
		
		hpBar.draw(gc);
	}

	public Bullet getBullet() {
		return bullet;
	}

	public double getMaxHp() {
		return maxHp;
	}

	public double getHp() {
		return hp;
	}

}
