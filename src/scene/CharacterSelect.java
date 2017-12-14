package scene;


import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import window.SceneManager;


public class CharacterSelect extends AnchorPane{
	public ImageView pic;
	public Image BG = new Image(ClassLoader.getSystemResource("select.png").toString(),800,600,false,false);
	public Image confirm = new Image(ClassLoader.getSystemResource("confirm.png").toString());
	
	public ArrayList<String> name = new ArrayList<String>();

	public ImageView conP1 = new ImageView();
	public ImageView picP1 = new ImageView();
	public Image[] p1Array;
	public boolean p1Lock = false;
	public Text p1Status = new Text();
	
	public ImageView conP2 = new ImageView();
	public ImageView picP2 = new ImageView();
	public Image[] p2Array;
	public boolean p2Lock = false;
	public Text p2Status = new Text();
	
	public static int p1Choice = 0;
	public static int p2Choice = 0;
	
	public CharacterSelect() {
		
		pic = new ImageView(BG);
		this.getChildren().add(pic);
		loadImage();
		
		name.add("Balloon");
		name.add("Egg");
		name.add("Ninja");
		name.add("Robot");
		
		p1Status.setFill(Color.WHITE);
		p1Status.setFont(new Font("Tahoma",25));
		p1Status.setText(name.get(p1Choice));
		
		p2Status.setFill(Color.WHITE);
		p2Status.setFont(new Font("Tahoma",25));
		p2Status.setText(name.get(p2Choice));
		
		this.getChildren().addAll(picP1,picP2,conP1,conP2,p1Status,p2Status);
		AnchorPane.setTopAnchor(picP1, 120.0);
		AnchorPane.setLeftAnchor(picP1, 150.0);
		AnchorPane.setTopAnchor(picP2, 120.0);
		AnchorPane.setRightAnchor(picP2, 120.0);
		AnchorPane.setTopAnchor(conP1, 120.0);
		AnchorPane.setLeftAnchor(conP1, 150.0);
		AnchorPane.setTopAnchor(conP2, 120.0);
		AnchorPane.setRightAnchor(conP2, 120.0);
		AnchorPane.setTopAnchor(p1Status, 350.0);
		AnchorPane.setLeftAnchor(p1Status, 150.0);
		AnchorPane.setTopAnchor(p2Status, 350.0);
		AnchorPane.setRightAnchor(p2Status, 150.0);
		picP1.setImage(p1Array[p1Choice]);
		picP2.setImage(p2Array[p2Choice]);
		
		VBox control = new VBox();
		BaseButton back = new BaseButton("Back");
		back.setGoToScene("MainMenu");
		BaseButton Play = new BaseButton("Play");
		control.getChildren().addAll(Play,back);
		
		this.getChildren().add(control);
		AnchorPane.setBottomAnchor(control, 10.0);
		AnchorPane.setRightAnchor(control, 340.0);
		
		addEventHandler();
	}
	
	public void addEventHandler() {
		this.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.A && !p1Lock) {
					
					if(p1Choice>0)p1Choice--;
					picP1.setImage(p1Array[p1Choice]);
				}
				else if(event.getCode() == KeyCode.D && !p1Lock) {
					
					if(p1Choice<3)p1Choice++;
					picP1.setImage(p1Array[p1Choice]);
				}
				else if(event.getCode() == KeyCode.LEFT && !p2Lock) {
					
					if(p2Choice>0)p2Choice--;
					picP2.setImage(p2Array[p2Choice]);
				}
				else if(event.getCode() == KeyCode.RIGHT && !p2Lock) {
					
					if(p2Choice<3)p2Choice++;
					picP2.setImage(p2Array[p2Choice]);
				}
				else if(event.getCode() == KeyCode.B) {
					p1Lock = true;
					conP1.setImage(confirm);
					nextScene();
				}
				else if(event.getCode() == KeyCode.P) {
					p2Lock = true;
					conP2.setImage(confirm);
					nextScene();
				}
			}
			
		});
	}
	
	public void nextScene() {
		if(p1Lock && p2Lock) {
			SceneManager.gotoSceneOf("Play");
		}
	}
	
	public void loadImage() {
		p1Array = new Image[4];
		p2Array = new Image[4];
		
		p1Array[0] = new Image(ClassLoader.getSystemResource("P1_0.png").toString());
		p1Array[1] = new Image(ClassLoader.getSystemResource("P1_1.png").toString());
		p1Array[2] = new Image(ClassLoader.getSystemResource("P1_2.png").toString());
		p1Array[3] = new Image(ClassLoader.getSystemResource("P1_3.png").toString());
		
		p2Array[0] = new Image(ClassLoader.getSystemResource("P2_0.png").toString());
		p2Array[1] = new Image(ClassLoader.getSystemResource("P2_1.png").toString());
		p2Array[2] = new Image(ClassLoader.getSystemResource("P2_2.png").toString());
		p2Array[3] = new Image(ClassLoader.getSystemResource("P2_3.png").toString());
	}
}
