package scene;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;

public class Credit extends BorderPane {

	private Image im = new Image(ClassLoader.getSystemResource("BG.jpg").toString(),800,600,false,false);
	private BackgroundImage bg = new BackgroundImage(im, null, null, null, null);
	
	public Credit() {
		Image in = new Image(ClassLoader.getSystemResource("credit.png").toString(),800,600,false,false);
		ImageView pic = new ImageView(in);
		this.getChildren().add(pic);
		this.setBackground(new Background(bg));
		this.setPadding(new Insets(0,10,10,10));
		BaseButton back = new BaseButton("back");
		back.setGoToScene("MainMenu");
		this.setBottom(back);
	}
}
