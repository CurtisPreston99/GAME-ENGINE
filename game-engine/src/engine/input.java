package engine;

import java.util.Arrays;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.data.JSONObject;

public class input {

	HashMap<Character, Boolean> keyboard = new HashMap<>();
	public HashMap<String, Boolean> SpecialKeys = new HashMap<>();
	JSONObject SpecialKeysMap;
	int[] codeids;
	Boolean keymapLoaded = false;
	public mouse Mouse = new mouse();
	public PApplet Parrent;
	public Character Lastkey;
	public int LastkeyCode;

	input(PApplet p) {
		Parrent = p;
	}

	// loads special keys map
	private void loadKeymap() {
		SpecialKeysMap = Parrent.loadJSONObject("./keymap.json");
		codeids = new int[SpecialKeysMap.size()];
		int c = 0;
		for (Object s : SpecialKeysMap.keys()) {
			codeids[c] = SpecialKeysMap.getInt(s.toString());
			c++;
		}
		Arrays.sort(codeids);
	}

	// check if code is a special key
	private Boolean isCode(int code) {
		for (int c : codeids) {
			if (c == code) {
				return true;
			}
		}
		return false;
	}

	public void set(Character key, boolean pressed) {
		if (!keymapLoaded) {
			loadKeymap();
			keymapLoaded = true;
		}
		Lastkey = key;
		LastkeyCode = Parrent.keyCode;
		if (Parrent.keyCode >= 32 && Parrent.keyCode <= 126) {
			keyboard.put(PApplet.parseChar(Parrent.keyCode), pressed);
		}
		if (isCode(Parrent.keyCode)) {
			for (Object s : SpecialKeysMap.keys()) {
//				System.out.println(s.toString());
				if (Parrent.keyCode == SpecialKeysMap.getInt(s.toString())) {
					SpecialKeys.put(s.toString(), pressed);
				}
			}
		}
	}

	public boolean get(int key) {
		if (key >= 32 && key <= 126) {

			return get((Character) Character.toUpperCase((char) key));
		}
		if (isCode(key)) {
			for (Object s : SpecialKeysMap.keys()) {
//				System.out.println(s.toString());
				if (Parrent.keyCode == SpecialKeysMap.getInt(s.toString())) {
					return SpecialKeys.get(s.toString());
				}
			}
		}
		return false;
	}

	public boolean get(Character key) {
		try {

			boolean pressed = keyboard.get(key);
			return pressed;
		} // if key has not been pressed yet
		catch (Exception e) {
			return false;
		}
	}

	public boolean get(String Skey) {
		try {
			boolean pressed = SpecialKeys.get(Skey);
			return pressed;
		} // if key has not been pressed yet
		catch (Exception e) {
			return false;
		}
	}

	public class mouse {
		public Boolean left = false;
		public Boolean right = false;
		float scroll = 0;

		void press(int mouseButton, boolean pressed) {
			if (mouseButton == PConstants.LEFT) {
				left = pressed;
			}
			if (mouseButton == PConstants.RIGHT) {
				right = pressed;
			}
		}

		void scroll(float f) {
			scroll = f;
		}

		public int X() {
			return Parrent.mouseX;
		}

		public int Y() {
			return Parrent.mouseY;
		}

		public int prevX() {
			return Parrent.pmouseX;
		}

		public int prevY() {
			return Parrent.pmouseY;
		}

	}

}
