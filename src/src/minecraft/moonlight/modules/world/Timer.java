package moonlight.modules.world;

import org.lwjgl.input.Keyboard;

import moonlight.modules.Module;

public class Timer extends Module {
	public Timer() {
		super("Timer", Keyboard.KEY_NONE, Category.WORLD);
	}
}
