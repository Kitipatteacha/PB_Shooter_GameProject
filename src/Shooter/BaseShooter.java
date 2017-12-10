package Shooter;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import logic.Bomb;
import logic.Bullet;
import logic.CollidableEntity;
import logic.GameLogic;
import logic.Ground;
import logic.HealthBar;
import sharedObject.AnimatedImage;

public class BaseShooter extends CollidableEntity{
	protected AnimatedImage normal_Animation = new AnimatedImage();
	protected AnimatedImage warp_Animation = new AnimatedImage();
	private boolean warp = false;
    protected int side ;//0 = left, 1 = right
    protected HealthBar hpBar;
    protected KeyCode up,down,left,right,fire,bomb;
    protected double imageW=110,imageH=210;
    protected double atkTimer=0;
    protected double bombTimer=0;
   
    public double maxHp;
    public double hp;
    public double atk;
    public double atkCD;
    public double bombCD;
    
	public BaseShooter(int side) {
		this.radius = 60;
		this.side = side;
		this.lane = 2;
		this.col = 2;
		this.x = Ground.getPosX(col, side)-imageW/2;//size of image
		this.y = Ground.getPosY(lane)-imageH;//size of image
		if(side == 0) {
			up = KeyCode.W;
			down = KeyCode.S;
			left = KeyCode.A;
			right = KeyCode.D;
			fire = KeyCode.SPACE;
			bomb = KeyCode.B;
		}
		else {
			up = KeyCode.UP;
			down = KeyCode.DOWN;
			left = KeyCode.LEFT;
			right = KeyCode.RIGHT;
			fire = KeyCode.L;
			bomb = KeyCode.P;
		}
		
		this.maxHp = 300;
		this.hp = this.maxHp;
		this.atk = 20;
		this.atkCD = 0.500;//Attack cool down (second)
		this.bombCD = 1.00;
		this.atkTimer = System.nanoTime();
		this.bombTimer = System.nanoTime();
		
		this.hpBar = new HealthBar(side,this);
		
		loadAnimate();
	}
	
	public void calculateHitbox() {
		// TODO Auto-generated method stub
		this.hitboxX = this.x + 10;
		this.hitboxY = this.y + 50;
		this.hitboxW = this.imageW*0.75;
		this.hitboxH = this.imageH*0.75;
	}

	private void up() {
		if(lane-1>0) {
			this.lane -=1;
			this.y = Ground.getPosY(lane)-imageH;
		}
	}
	private void down() {
		if(lane+1<=3) {
			this.lane +=1;
			this.y = Ground.getPosY(lane)-imageH;
		}
	}
	private void left() {
		if(col-1>0) {
			this.col-=1;
			this.x = Ground.getPosX(col, side)-imageW/2;
		}
	}
	private void right() {
		if(col+1<=3) {
			this.col+=1;
			this.x = Ground.getPosX(col, side)-imageW/2;
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
	
	public void attack(BaseShooter other,CollidableEntity way) {
		double totalAtk;
		totalAtk = atk;//Attack Calculation
		if(way instanceof Bomb)totalAtk=atk*3;
		if(way instanceof Bullet)totalAtk=atk;
		
		other.takeDamage(totalAtk);
	}
	
	public double takeDamage(double dmg) {
		double totalDmg;
		totalDmg = dmg;//Damage Calculation
		
		if(this.hp-totalDmg>0)this.hp -= totalDmg;
		else this.hp = 0;
		
		return totalDmg;
	}
	
	public void heal(double amount) {
		if(amount<0)amount=0;
		if(this.hp+amount>this.maxHp) {
			this.hp = this.maxHp;
		}
		else this.hp +=amount;
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
			if((System.nanoTime()-this.atkTimer)/1000000000 >= this.atkCD) {
				System.out.println("shoot");
				this.atkTimer = System.nanoTime();
				GameLogic.addNewObject(new Bullet(side,lane,col,this));
			}
			
			InputUtility.remove(fire);
		}
		else if (InputUtility.getKeyPressed(bomb)) {
			if((System.nanoTime()-this.bombTimer)/1000000000 >= this.bombCD) {
				System.out.println("bomb");
				this.bombTimer = System.nanoTime();
				GameLogic.addNewObject(new Bomb(side,lane,col,this));
			}
			InputUtility.remove(bomb);
		} 
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		double t = System.nanoTime() / 1100000000.0;
		if(warp == false) 
			gc.drawImage(normal_Animation.getFrame(t), x,  y );
		else if(warp == true) {
	    		gc.drawImage(warp_Animation.getFrame(t), x,  y);
	    		if(warp_Animation.getIndex()==4)
	    			warp = false;
	    }
		
		hpBar.draw(gc);
	}

	public double getMaxHp() {
		return maxHp;
	}

	public double getHp() {
		return hp;
	}

	
}