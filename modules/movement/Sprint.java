package moonlight.modules.movement;

import org.lwjgl.input.Keyboard;

import moonlight.events.Event;
import moonlight.events.listeners.EventUpdate;
import moonlight.modules.Module;

public class Sprint extends Module {

	public Sprint() {
		super("Sprint", Keyboard.KEY_M, Category.MOVEMENT);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		mc.thePlayer.setSprinting(mc.gameSettings.keyBindSprint.getIsKeyPressed());
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate && e.isPre()) {
			if(mc.thePlayer.moveForward > 0
					&& !mc.thePlayer.isUsingItem()
					&& !mc.thePlayer.isSneaking()
					&& !mc.thePlayer.isCollidedHorizontally) {
				mc.thePlayer.setSprinting(true);
			}
		}
	}
}
