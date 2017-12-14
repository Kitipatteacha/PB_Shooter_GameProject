package scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CharacterSelect extends AnchorPane{
	private int page = 2;
	private int pageCount = 0 ;
	Text indi = new Text("1/"+page);
	
	public Image[] tutorial = new Image[page];
	public ImageView pic;
	public Image BG = new Image(ClassLoader.getSystemResource("select.png").toString(),800,600,false,false);
	public CharacterSelect() {
		for (int i = 0; i < page; i++) {
			tutorial[i] = new Image(ClassLoader.getSystemResource("tutorial_" + i + ".png").toString(),800,600,false,false);
		}
		
		pic = new ImageView(BG);
		this.getChildren().add(pic);
		
		BaseButton back = new BaseButton("Back");
		back.setGoToScene("MainMenu");
		
		VBox control = new VBox();
		
		
		BaseButton Play = new BaseButton("Play");
		
		indi.setFill(Color.WHITE);
		indi.setFont(new Font("Monospace", 30));
		control.getChildren().addAll(Play,back);
		
		this.getChildren().add(control);
		AnchorPane.setBottomAnchor(control, 10.0);
		AnchorPane.setRightAnchor(control, 340.0);
	}
}