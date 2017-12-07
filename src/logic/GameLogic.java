package logic;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.KeyCode;
import sharedObject.RenderableHolder;

public class GameLogic {
	private List<Entity> gameObjectContainer;
	
	private Player1 player1;
	private Ground ground;
	private Player2 player2;
	private Healthbar healthbar_p2;
	private Healthbar healthbar_p1;
	private Bullet bullet_p1;
	private Bullet bullet_p2;
	public GameLogic(){
		this.gameObjectContainer = new ArrayList<Entity>();
	
		Field field = new Field();
		RenderableHolder.getInstance().add(field);
		player1 = new Player1(180,250);
		ground = new Ground(100,100);
		player2 = new Player2(610,300);
		healthbar_p1 = new Healthbar(20,20,-100);
		healthbar_p2 = new Healthbar(480,20,-100);
		bullet_p1 = new Bullet(player1.get_x_position()+10,player1.get_y_position()+100,KeyCode.SPACE,1);
		bullet_p2 = new Bullet(player2.get_x_position()+10,player2.get_y_position()+100,KeyCode.L,-1);
		addNewObject(bullet_p1);
		addNewObject(bullet_p2);
		addNewObject(player1);
		addNewObject(ground);
		addNewObject(player2);
		addNewObject(healthbar_p1);
		addNewObject(healthbar_p2);
		
	}
	
	protected void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	
	public void logicUpdate(){
		player1.update();
		player2.update();
		bullet_p1.update(player1.get_x_position()+10,player1.get_y_position()+100,player1.getLane());
		bullet_p2.update(player2.get_x_position()-40,player2.get_y_position()+20,player2.getLane());
		if(!bullet_p1.isDestroyed() && bullet_p1.collideWith(player2)){
			healthbar_p2.ishit = true;
			bullet_p1.isShoot = false;
			System.out.println('d');
		}
		if(!bullet_p1.isDestroyed() && bullet_p2.collideWith(player1)){
			healthbar_p1.ishit = true;
			bullet_p2.isShoot = false;
			System.out.println('d');
		}
	}
}
