package scene;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;

public class Credit extends BorderPane {

	private Image im = new Image(ClassLoader.getSystemResource("pexels-photo-370799.jpg").toString(),800,600,false,false);
	private BackgroundImage bg = new BackgroundImage(im, null, null, null, null);
	
	public Credit() {
		this.setBackground(new Background(bg));
		this.setPadding(new Insets(0,10,10,10));
		BaseButton back = new BaseButton("back");
		back.setGoToScene("MainMenu");
		this.setBottom(back);
	}
}
