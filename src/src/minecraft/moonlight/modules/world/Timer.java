package moonlight.modules.world;

import org.lwjgl.input.Keyboard;

import moonlight.modules.Module;
import moonlight.settings.NumberSetting;

public class Timer extends Module {
	public NumberSetting range = new NumberSetting("Speed", 2, 0.2, 6, 0.2);
	
	public Timer() {
		super("Timer", Keyboard.KEY_NONE, Category.WORLD);
        this.addSettings(range);
	}
	
	
}
