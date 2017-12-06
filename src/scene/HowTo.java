package scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class HowTo extends AnchorPane{
	private Image im = new Image(ClassLoader.getSystemResource("pexels-photo-370799.jpg").toString(),800,600,false,false);
	private BackgroundImage bg = new BackgroundImage(im, null, null, null, null);
	
	public HowTo() {
		this.setBackground(new Background(bg));
		
		BaseButton back = new BaseButton("Back");
		back.setGoToScene("MainMenu");
		this.getChildren().add(back);
		AnchorPane.setBottomAnchor(back, 5.0);
		AnchorPane.setLeftAnchor(back, 5.0);
		
		HBox control = new HBox();
		BaseButton prev = new BaseButton("prev");
		BaseButton next = new BaseButton("next");
		Text indi = new Text("--/1");
		control.setSpacing(20.0);
		control.getChildren().addAll(prev,indi,next);
		this.getChildren().add(control);
		AnchorPane.setBottomAnchor(control, 5.0);
		AnchorPane.setLeftAnchor(control, 300.0);
		
		Image in = new Image(ClassLoader.getSystemResource("pic.jpg").toString(),790,530,false,false);
		ImageView pic = new ImageView(in);
		this.getChildren().add(pic);
		AnchorPane.setTopAnchor(pic, 5.0);
		AnchorPane.setLeftAnchor(pic, 5.0);
	}
	
	
}
