package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	public static Image Background;
	public static Image Ground;
	public static AudioClip  shotSound;
	public static AudioClip  winSound;
	public static AudioClip  fightTheme;
	public static Image bomb_icon;
	public static Image bullet_icon;
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
		shotSound = new AudioClip(ClassLoader.getSystemResource("Shot_sound.wav").toString());
		winSound = new AudioClip(ClassLoader.getSystemResource("Win_sound.wav").toString());
		fightTheme = new AudioClip(ClassLoader.getSystemResource("Fight_theme.mp3").toString());
		bomb_icon  = new Image(ClassLoader.getSystemResource("bomb_icon.png").toString(),50,50,false,false);
		bullet_icon  = new Image(ClassLoader.getSystemResource("bullet_icon.png").toString(),50,50,false,false);
	}

	public void add(IRenderable entity) {
		entities.add(entity);
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed()) {
				entities.remove(i);
			}
		}
		Collections.sort(entities, comparator);
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
	
	public void newGame() {
		entities.clear();
	}
}
