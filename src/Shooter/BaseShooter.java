package Shooter;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.CollidableEntity;
import logic.CoolDownDisplay;
import logic.GameLogic;
import logic.Ground;
import logic.HealthBar;
import sharedObject.AnimatedImage;

public abstract class BaseShooter extends CollidableEntity{
	private AnimatedImage normal_Animation = new AnimatedImage();
	private AnimatedImage warp_Animation = new AnimatedImage();
	private boolean warp = false;
	
    protected int side ;//0 = left, 1 = right
    protected HealthBar hpBar;
    protected CoolDownDisplay cdBar;
    protected KeyCode up,down,left,right,fire,bomb;
    protected int nNormalPic,nWarpPic;
    protected double imageW=110,imageH=210;
    protected boolean movable = true;
    protected boolean shootable = true;
    protected double atkTimer=0;
    protected double bombTimer=0;
    protected double immovableTimer;
    protected double unshootableTimer;
   
    public double maxHp;
    public double hp;
    public double atk;
    public double atkCD;
    public double bombDmg;
    public double bombCD;
    
	public BaseShooter(int side) {
		this.radius = 60;
		this.side = side;
		this.lane = 2;
		this.col = 2;
		this.x = Ground.getPosX(col, side)-imageW/2;//size of image
		this.y = Ground.getPosY(lane)-imageH;//size of image
		this.z = 10;
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
		
		//Character Status Area
		this.maxHp = 300;
		this.hp = this.maxHp;
		this.atk = 20;
		this.atkCD = 0.300;//Attack cool down (second)
		this.bombDmg = 50;
		this.bombCD = 3.00;
		//End
		
		this.atkTimer = -1;
		this.bombTimer = -1;
		this.hpBar = new HealthBar(side,this);
		this.cdBar = new CoolDownDisplay(side,this);
		
	}
	
	public void calculateHitbox() {
		this.hitboxX = Ground.getPosX(col, side)-50;
		this.hitboxY = Ground.getPosY(lane)-175;
		this.hitboxW = 100;
		this.hitboxH = 150;
	}

	protected void up() {
		if(lane-1>0&&movable) {
			this.lane -=1;
			this.y = Ground.getPosY(lane)-imageH;
		}
	}
	protected void down() {
		if(lane+1<=3&&movable) {
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
	
	protected void throwBomb() {
		if((System.nanoTime()-this.bombTimer)/1000000000 >= this.bombCD && shootable) {
			this.bombTimer = System.nanoTime();
			GameLogic.addNewObject(new Bomb(side,lane,col,this));
		}
	}

	protected void shoot() {
		if((System.nanoTime()-this.atkTimer)/1000000000 >= this.atkCD && shootable) {
			this.atkTimer = System.nanoTime();
			GameLogic.addNewObject(new Bullet(side,lane,col,this));
		}
	}

	protected void doDamage(BaseShooter other,CollidableEntity way) {
		double totalAtk;
		totalAtk = atk;//Attack Calculation
		if(way instanceof Bomb)totalAtk=bombDmg;
		if(way instanceof Bullet)totalAtk=atk;
		
		other.takeDamage(totalAtk);
	}
	
	protected double takeDamage(double dmg) {
		double totalDmg;
		totalDmg = dmg;//Damage Calculation
		
		if(this.hp-totalDmg>0)this.hp -= totalDmg;
		else this.hp = 0;
		
		return totalDmg;
	}
	
	protected void heal(double amount) {
		if(amount<0)amount=0;
		if(this.hp+amount>this.maxHp) {
			this.hp = this.maxHp;
		}
		else this.hp +=amount;
	}
	
	protected void stunt(double durationSecond) {
		this.movable = false;
		this.shootable = false;
		this.immovableTimer = System.nanoTime()+(durationSecond*1000000000);
		this.unshootableTimer = System.nanoTime()+(durationSecond*1000000000);
	}
	
	protected void snare(double durationSecond) {
		this.movable = false;
		this.immovableTimer = System.nanoTime()+(durationSecond*1000000000);
	}
	
	protected void disarm(double durationSecond) {
		this.shootable = false;
		this.unshootableTimer = System.nanoTime()+(durationSecond*1000000000);
	}
	
	protected void loadAnimate(String charName) {
		Image[] normalArray = new Image[nNormalPic];
		Image[] warpArray = new Image[nWarpPic];
		
		if(side == 0) {
			for (int i = 0; i < nNormalPic; i++) {
				normalArray[i] = new Image(ClassLoader.getSystemResource(charName + "_L_" + i + ".png").toString() );
				
			}
			for (int i = 0; i < nWarpPic; i++) {
				warpArray[i] = new Image(ClassLoader.getSystemResource(charName + "_L_" + "Warp_" + i + ".png").toString() );
	        }
		}
		else {
			for (int i = 0; i < nNormalPic; i++) {
				normalArray[i] = new Image(ClassLoader.getSystemResource(charName + "_R_" + i + ".png").toString() );
			}
			for (int i = 0; i < nWarpPic; i++) {
				warpArray[i] = new Image(ClassLoader.getSystemResource(charName + "_R_"+ "Warp_" + i + ".png").toString() );
	        }
		}
		normal_Animation.frames = normalArray;
		warp_Animation.frames = warpArray;
		this.normal_Animation.setDuration(0.100);
		this.warp_Animation.setDuration(0.100);
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
		else {
			gc.drawImage(normal_Animation.getFrame(1100000000), x,  y );
			gc.setFill(Color.WHITE);
			gc.setFont(new Font("Monospace", 30));
			gc.fillText("Stunt", x+20, y+60);
			
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(0.5);
			gc.strokeText("Stunt", x+20, y+60);
		}
		hpBar.draw(gc);
		cdBar.draw(gc);
	}

	public double getMaxHp() {
		return maxHp;
	}

	public double getHp() {
		return hp;
	}

	public int getSide() {
		return side;
	}

	public double getAtkTimer() {
		return atkTimer;
	}

	public double getBombTimer() {
		return bombTimer;
	}


	
}
