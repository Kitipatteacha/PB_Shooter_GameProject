package Shooter;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
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
    protected double imageW=110,imageH=210;
    protected double atkTimer=0;
    protected double bombTimer=0;
    protected boolean movable = true;
    protected boolean shootable = true;
   
    public double maxHp;
    public double hp;
    public double atk;
    public double atkCD;
    public double bombDmg;
    public double bombCD;
    
	public BaseShooter(int side,String pic) {
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
		
		loadAnimate(pic);
	}
	
	public void calculateHitbox() {
		// TODO Auto-generated method stub
		this.hitboxX = this.x + 10;
		this.hitboxY = this.y + 50;
		this.hitboxW = this.imageW*0.75;
		this.hitboxH = this.imageH*0.75;
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
	
	protected void stunt() {
		this.movable = false;
		this.shootable = false;
	}
	
	protected void snare() {
		this.movable = false;
	}
	
	private void loadAnimate(String charName) {
		Image[] normalArray = new Image[9];
		Image[] warpArray = new Image[5];
		
		if(side == 0) {
			for (int i = 0; i < 9; i++) {
				normalArray[i] = new Image(ClassLoader.getSystemResource(charName + "_L_" + i + ".png").toString() );
			}
			for (int i = 0; i < 5; i++) {
				warpArray[i] = new Image(ClassLoader.getSystemResource(charName + "_L_" + "Warp_" + i + ".png").toString() );
	        }
		}
		else {
			for (int i = 0; i < 9; i++) {
				normalArray[i] = new Image(ClassLoader.getSystemResource(charName + "_R_" + i + ".png").toString() );
			}
			for (int i = 0; i < 5; i++) {
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
