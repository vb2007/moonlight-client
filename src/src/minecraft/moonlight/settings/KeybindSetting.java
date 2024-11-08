package moonlight.settings;

public class KeybindSetting extends Setting {

	public int keyCode;
	
	public KeybindSetting(int keyCode) {
		this.name = "Keybind";
		this.keyCode = keyCode;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}
}
