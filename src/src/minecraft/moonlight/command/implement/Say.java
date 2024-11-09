package moonlight.command.implement;

import moonlight.command.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class Say extends Command {
	
	public Say() {
		super("Say", "Says things in chat.", "say", "s");
	}

	@Override
	public void onCommand(String[] args, String command) {
		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(String.join(" ", args)));
	}
}
