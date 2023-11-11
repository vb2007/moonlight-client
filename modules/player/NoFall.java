package moonlight.modules.player;

import org.lwjgl.input.Keyboard;

import moonlight.events.Event;
import moonlight.events.listeners.EventUpdate;
import moonlight.modules.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module {
	
	public NoFall() {
		super("NoFall", Keyboard.KEY_N, Category.PLAYER);
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate && e.isPre()) {
			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
		}
	}
}
