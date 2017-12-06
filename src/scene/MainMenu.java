package scene;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;

public class MainMenu extends VBox {
	private Image im = new Image(ClassLoader.getSystemResource("BG.jpg").toString());
	private WritableImage wim = new WritableImage(im.getPixelReader(),0,0,800,600);
	private BackgroundImage bg = new BackgroundImage(wim, null, null, null, null);
	
	public MainMenu() {
		
		this.setAlignment(Pos.CENTER);
		this.setBackground(new Background(bg));
		
		MyLabel L1 = new MyLabel("PB:GO");
		this.getChildren().add(L1);
		
		VBox buttonBox = new VBox();
		BaseButton B1 = new BaseButton("Start");
		BaseButton B2 = new BaseButton("HowTo");
		BaseButton B3 = new BaseButton("Credit");
		buttonBox.setSpacing(2.0);
		buttonBox.getChildren().addAll(B1,B2,B3);
		this.getChildren().add(buttonBox);
		buttonBox.setAlignment(Pos.CENTER);
		
		animateBG();
	}

	private void animateBG() {
		// TODO Auto-generated method stub
		new AnimationTimer() {
			int posX=0;
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				posX+=1;
				System.out.println(posX);
				if(posX>=980)posX=5;
				WritableImage wim = new WritableImage(im.getPixelReader(),posX,0,800,600);
				BackgroundImage bg = new BackgroundImage(wim,null,null,null,null);
				moveBG(bg);
			}
			
		}.start();
	}

	protected void moveBG(BackgroundImage bg) {
		// TODO Auto-generated method stub
		this.setBackground(new Background(bg));
	}
}
