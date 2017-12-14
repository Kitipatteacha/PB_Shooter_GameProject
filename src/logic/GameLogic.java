package logic;

import java.util.ArrayList;
import java.util.List;

import Shooter.Balloon;
import Shooter.BaseShooter;
import Shooter.RockMan;
import input.InputUtility;
import javafx.scene.input.KeyCode;
import sharedObject.RenderableHolder;

public class GameLogic {
	private static List<Entity> gameObjectContainer;
	
	public boolean gameEnd,gamePause;
	public static Timer timer;
	private Ground ground;
	private static BaseShooter p1;
	private static BaseShooter p2;
	
	public GameLogic(){
		RenderableHolder.getInstance().newGame();
		GameLogic.gameObjectContainer = new ArrayList<Entity>();
	
		Field field = new Field();
		RenderableHolder.getInstance().add(field);
		
		gameEnd= false;
		gamePause = false;
		timer = new Timer(60);
		ground = new Ground(100,100);
		
		
		
		p1 = new RockMan(0);
		p2 = new Balloon(1);
		
		
		
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
		if (InputUtility.getKeyPressed(KeyCode.ESCAPE)) {
			if(gamePause == false)gamePause = true;
			else gamePause=false;
			InputUtility.remove(KeyCode.ESCAPE);
		}
		if(!gamePause) {
			for(int i = GameLogic.gameObjectContainer.size()-1;i>=0;i--) {
				if(GameLogic.gameObjectContainer.get(i).isDestroyed()) {
					GameLogic.gameObjectContainer.remove(i);
				}
				else {
					GameLogic.gameObjectContainer.get(i).update();
				}
			}
			if(p1.getHp()<=0 || p2.getHp()<=0 || timer.getTime()<=0) {
				gameEnd = true;
			}
		}
	}
	
	public boolean isGameEnd() {
		return gameEnd;
	}
	
	public boolean isGamePause() {
		return gamePause;
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
	
	public String getWinner() {
		RenderableHolder.winSound.play();
		if(p1.getHp()>p2.getHp()) {
			return "P1";
		}
		else if(p2.getHp()>p1.getHp()) {
			return "P2";
		}
		else {
			return "DRAW";
		}
	}
}
