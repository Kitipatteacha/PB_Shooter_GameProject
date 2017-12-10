package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
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
		
	}

	public void add(IRenderable entity) {
		System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed()) {
				entities.remove(i);
			}
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
}
