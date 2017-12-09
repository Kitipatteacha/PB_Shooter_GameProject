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
<<<<<<< HEAD
		
		p1 = new BaseShooter(0);
||||||| merged common ancestors
		player1 = new Player1(180,250);
=======
		player1 = new Player1(220,300);
>>>>>>> a6f1fdb86aef8f760a776cc05071d2f5252f53e2
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
<<<<<<< HEAD
		p1.update();
		p2.update();
		if(p1.getBullet().collideWith(p2)) {
			p1.attack(p2);
||||||| merged common ancestors
		player1.update();
		player2.update();
		bullet_p1.update(player1.get_x_position()+10,player1.get_y_position()+100,player1.getLane());
		bullet_p2.update(player2.get_x_position()-40,player2.get_y_position()+20,player2.getLane());
		if(!bullet_p1.isDestroyed() && bullet_p1.collideWith(player2)){
			healthbar_p2.ishit = true;
			bullet_p1.isShoot = false;
			System.out.println('d');
=======
		player1.update();
		player2.update();
		bullet_p1.update(player1.get_x_position()-30,player1.get_y_position()+50,player1.getLane());
		bullet_p2.update(player2.get_x_position()-30,player2.get_y_position()+50,player2.getLane());
		if(!bullet_p1.isDestroyed() && bullet_p1.collideWith(player2)){
			healthbar_p2.ishit = true;
			bullet_p1.isShoot = false;
			System.out.println('d');
>>>>>>> a6f1fdb86aef8f760a776cc05071d2f5252f53e2
		}
		if(p2.getBullet().collideWith(p2)) {
			p2.attack(p1);
		}
	}
}
