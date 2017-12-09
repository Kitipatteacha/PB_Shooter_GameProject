package scene;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;

public class MainMenu extends VBox {
	
	private Image im = new Image(ClassLoader.getSystemResource("BG.jpg").toString());
	private Image Logo = new Image(ClassLoader.getSystemResource("Logo.png").toString());
	private WritableImage wim = new WritableImage(im.getPixelReader(),0,0,800,600);
	private BackgroundImage bg = new BackgroundImage(wim, null, null, null, null);
	private AnimationTimer movingBG = new AnimationTimer() {
		int posX=0;
		@Override
		public void handle(long now) {
			// TODO Auto-generated method stub
			posX+=1;
<<<<<<< HEAD
			if(posX>=980)posX=5;//if(repeatPixel)startLoopPixel
||||||| merged common ancestors
			if(posX>=980)posX=5;
			System.out.println(posX);
=======
			if(posX>=1520)posX=0;
			System.out.println(posX);
>>>>>>> a6f1fdb86aef8f760a776cc05071d2f5252f53e2
			WritableImage wim = new WritableImage(im.getPixelReader(),posX,0,800,600);
			BackgroundImage bg = new BackgroundImage(wim,null,null,null,null);
			moveBG(bg);
		}
		
	};
	
	public MainMenu() {
		

		this.setAlignment(Pos.CENTER);
		this.setBackground(new Background(bg));
		Canvas canvas = new Canvas(300, 300 );
	    GraphicsContext gc = canvas.getGraphicsContext2D();
	    gc.drawImage(Logo, 0, 0,300,300);
		VBox buttonBox = new VBox();
		BaseButton B1 = new BaseButton("Start");
		BaseButton B2 = new BaseButton("HowTo");
		BaseButton B3 = new BaseButton("Credit");
		buttonBox.setSpacing(2.0);
		buttonBox.getChildren().addAll(canvas,B1,B2,B3);
		this.getChildren().add(buttonBox);
		buttonBox.setAlignment(Pos.CENTER);
		movingBG.start();
	}

	protected void moveBG(BackgroundImage bg) {
		this.setBackground(new Background(bg));
	}

	public void stopMovingBG() {
		movingBG.stop();
	}
	
	public void startMovingBG() {
		movingBG.start();
	}
}
