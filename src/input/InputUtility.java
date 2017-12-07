package input;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public class InputUtility {

	private static ArrayList<KeyCode> keyPressed = new ArrayList<>(); 
	private static int count = 0;
	
	public static boolean getKeyPressed(KeyCode keycode) {
		return keyPressed.contains(keycode);
	}
	public static void setKeyPressed(KeyCode keycode,boolean pressed) {
		if(pressed){
			if(!keyPressed.contains(keycode)&& count==0){
				keyPressed.add(keycode);
			}
			else {
				keyPressed.remove(keycode);
			}
		}else{
			keyPressed.remove(keycode);
		}
		System.out.println(keyPressed);
	}
	
	public static void remove(KeyCode keycode){
		keyPressed.remove(keycode);
	}
	
}
