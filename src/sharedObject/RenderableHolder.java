package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import logic.Field;
import logic.Player2;
import logic.Player1;
import sharedObject.AnimatedImage;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	public static Image Background;
	public static Image Ground;
	public static AudioClip  explosionSound;
	public static AnimatedImage player1_animated = new AnimatedImage();
	public static Image[] imageArray1 = new Image[9];
	public static Image player2;
	public static Image[] imageArray2 = new Image[5];
	public static AnimatedImage warp_animated = new AnimatedImage();
	public static Image[] imageArray3 = new Image[9];
	public static AnimatedImage player2_animated = new AnimatedImage();
	public static Image[] imageArray4 = new Image[5];
	public static AnimatedImage warp_p2_animated = new AnimatedImage();
	
	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
		Background = new Image(ClassLoader.getSystemResource("Back.png").toString());
		Ground = new Image(ClassLoader.getSystemResource("ground.png").toString(),800,300,false,false);
		explosionSound = new AudioClip(ClassLoader.getSystemResource("Explosion.wav").toString());
		player2 = new Image(ClassLoader.getSystemResource("player2_00.png").toString(),150,100,false,false);
		for (int i = 0; i < 9; i++) {
	            imageArray1[i] = new Image(ClassLoader.getSystemResource(i + ".png").toString() );
	            imageArray3[i] = new Image(ClassLoader.getSystemResource("player2_" + i + ".png").toString() );
	        }
		for (int i = 0; i < 5; i++) {
            imageArray2[i] = new Image(ClassLoader.getSystemResource("Warp_" + i + ".png").toString() );
            imageArray4[i] = new Image(ClassLoader.getSystemResource("warp_p2_" + i + ".png").toString());
        }
		player1_animated.frames = imageArray1;
        player1_animated.duration = 0.100;
        warp_animated.frames = imageArray2;
        warp_animated.duration = 0.100;
        player2_animated.frames = imageArray3;
        player2_animated.duration = 0.100;
        warp_p2_animated.frames = imageArray4;
        warp_p2_animated.duration = 0.100;
	}

	public void add(IRenderable entity) {
		System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
		for(IRenderable x: entities){
			if(x instanceof Player1) System.out.println("Players1");
			if(x instanceof Field) System.out.println("field");
			if(x instanceof Player2) System.out.println("Player2");
		}
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
}
