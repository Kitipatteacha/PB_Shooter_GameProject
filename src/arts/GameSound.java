package arts;

import javafx.scene.media.AudioClip;

public class GameSound {

	public static AudioClip  shotSound;
	public static AudioClip  winSound;
	public static AudioClip  fightTheme;
	
	public GameSound() {
		shotSound = new AudioClip(ClassLoader.getSystemResource("Shot_sound.wav").toString());
		winSound = new AudioClip(ClassLoader.getSystemResource("Win_sound.wav").toString());
		fightTheme = new AudioClip(ClassLoader.getSystemResource("Fight_theme.mp3").toString());
	}
	
	public static void playShotSound() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				shotSound.play(0.1);
			}
			
		}).start();
	}
	
	public static void playWinSound() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				winSound.play(1);
			}
			
		}).start();
	}
	
	public static void playThemeSound() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				fightTheme.play();
			}
			
		}).start();
	}
}
