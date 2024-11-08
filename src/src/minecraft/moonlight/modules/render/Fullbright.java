package moonlight.modules.render;

import org.lwjgl.input.Keyboard;

import moonlight.events.Event;
import moonlight.events.listeners.EventUpdate;
import moonlight.modules.Module;

public class Fullbright extends Module {
	private float originalGamma;
	
	public Fullbright() {
		super("Fullbright", Keyboard.KEY_B, Category.RENDER);
		Runtime.getRuntime().addShutdownHook(new Thread(this::onDisable));
	}
	
	public void onEnable() {
		originalGamma = mc.gameSettings.gammaSetting;
		mc.gameSettings.gammaSetting = 100;
	}
	
	public void onDisable() {
		mc.gameSettings.gammaSetting = originalGamma;
	}
	
	/*public void onEvent(Event e) {
		if(e instanceof EventUpdate && e.isPre()) {
			if(e.isPre()) {
				
			}
		}
	}*/
}
