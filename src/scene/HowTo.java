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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HowTo extends AnchorPane{
	private int page = 2;
	private int pageCount = 0 ;
	Text indi = new Text("1/"+page);
	
	public Image[] tutorial = new Image[page];
	public ImageView pic;
	
	public HowTo() {
		for (int i = 0; i < page; i++) {
			tutorial[i] = new Image(ClassLoader.getSystemResource("tutorial_" + i + ".png").toString(),800,600,false,false);
		}
		
		pic = new ImageView(tutorial[0]);
		this.getChildren().add(pic);
		
		BaseButton back = new BaseButton("Back");
		back.setGoToScene("MainMenu");
		this.getChildren().add(back);
		AnchorPane.setBottomAnchor(back, 5.0);
		AnchorPane.setLeftAnchor(back, 5.0);
		
		HBox control = new HBox();
		
		Button prev = new Button("Prev");
		prev.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(pageCount>0)pageCount--;
				pic.setImage(tutorial[pageCount]);
				indi.setText(""+(pageCount+1)+"/"+page);
			}
			
		});
		
		Button next = new Button("Next");
		next.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(pageCount<page-1)pageCount++;
				pic.setImage(tutorial[pageCount]);
				indi.setText(""+(pageCount+1)+"/"+page);
			}
			
		});
		
		indi.setFill(Color.WHITE);
		indi.setFont(new Font("Monospace", 30));
		control.setSpacing(20.0);
		control.getChildren().addAll(prev,indi,next);
		
		this.getChildren().add(control);
		AnchorPane.setBottomAnchor(control, 10.0);
		AnchorPane.setRightAnchor(control, 300.0);
	}
}
