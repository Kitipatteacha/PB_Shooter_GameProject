package logic;

import java.util.ArrayList;
import java.util.List;

import Shooter.BaseShooter;
import sharedObject.RenderableHolder;

public class GameLogic {
	private static List<Entity> gameObjectContainer;
	
	public static Timer timer;
	private Ground ground;
	private static BaseShooter p1;
	private static BaseShooter p2;
	
	public GameLogic(){
		GameLogic.gameObjectContainer = new ArrayList<Entity>();
	
		Field field = new Field();
		RenderableHolder.getInstance().add(field);
		
		timer = new Timer(60);
		p1 = new BaseShooter(0);
		ground = new Ground(100,100);
		p2 = new BaseShooter(1);
		addNewObject(ground);
		addNewObject(p1);
		addNewObject(p2);
		addNewObject(timer);
	}
	
	public static void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	
	public void logicUpdate(){
		for(int i = GameLogic.gameObjectContainer.size()-1;i>=0;i--) {
			if(GameLogic.gameObjectContainer.get(i).isDestroyed()) {
				GameLogic.gameObjectContainer.remove(i);
			}
			else GameLogic.gameObjectContainer.get(i).update();
		}
	}
	
	public static BaseShooter getOpponentOf(BaseShooter shooter) {
		if(shooter.equals(p1)) {
			return p2;
		}
		else if(shooter.equals(p2)) {
			return p1;
		}
		return null;
	}
}
