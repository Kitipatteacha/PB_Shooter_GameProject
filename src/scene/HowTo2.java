package scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class HowTo2 extends AnchorPane{
	
	public HowTo2() {
		Image in = new Image(ClassLoader.getSystemResource("player2_tutorial.png").toString(),800,600,false,false);
		ImageView pic = new ImageView(in);
		this.getChildren().add(pic);
		
		BaseButton back = new BaseButton("Back");
		back.setGoToScene("MainMenu");
		
		this.getChildren().add(back);
		AnchorPane.setBottomAnchor(back, 5.0);
		AnchorPane.setLeftAnchor(back, 5.0);
		
		HBox control = new HBox();
		BaseButton prev = new BaseButton("prev");
		prev.setGoToScene("HowTo");
		BaseButton next = new BaseButton("next");
		next.setGoToScene("HowTo2");
		Text indi = new Text("--/1");
		control.setSpacing(20.0);
		control.getChildren().addAll(prev,indi,next);
		this.getChildren().add(control);
		AnchorPane.setBottomAnchor(control, 5.0);
		AnchorPane.setRightAnchor(control, 5.0);
	}
	
}
