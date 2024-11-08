package moonlight.modules.movement;

import org.lwjgl.input.Keyboard;

import moonlight.events.Event;
import moonlight.events.listeners.EventUpdate;
import moonlight.modules.Module;

public class Fly extends Module {

	public Fly() {
		super("Fly", Keyboard.KEY_F, Category.MOVEMENT);
	}
	
	/*public void onEnable() {
		mc.thePlayer.capabilities.isFlying = true;
		mc.thePlayer.capabilities.allowFlying = true;
	}*/
	
	public void onDisable() {
		mc.thePlayer.capabilities.isFlying = false;
		//mc.thePlayer.capabilities.allowFlying = false;
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate && e.isPre()) {
			mc.thePlayer.capabilities.isFlying = true;
		}
	}
}
