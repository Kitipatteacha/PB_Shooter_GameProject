package logic;

import java.util.ArrayList;
import java.util.List;

import Shooter.BaseShooter;
import javafx.scene.input.KeyCode;
import sharedObject.RenderableHolder;

public class GameLogic {
	private List<Entity> gameObjectContainer;
	
	private Ground ground;
	private BaseShooter p1;
	private BaseShooter p2;
	
	public GameLogic(){
		this.gameObjectContainer = new ArrayList<Entity>();
	
		Field field = new Field();
		RenderableHolder.getInstance().add(field);
		p1 = new BaseShooter(0);
		ground = new Ground(100,100);
		p2 = new BaseShooter(1);
		addNewObject(ground);
		addNewObject(p1);
		addNewObject(p2);
	}
	
	protected void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	
	public void logicUpdate(){
		p1.update();
		p2.update();
		if(p1.getBullet().collideWith(p2)) {
			p1.attack(p2);
		}
		if(p2.getBullet().collideWith(p2)) {
			p2.attack(p1);
		}
	}
}
