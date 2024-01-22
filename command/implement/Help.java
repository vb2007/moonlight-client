package moonlight.command.implement;

import moonlight.Client;
import moonlight.command.Command;
import moonlight.modules.Module;

public class Help extends Command {
	
	public Help() {
		super("Help", "Shows avalible commands.", "help", "h");
	}

	@Override
	public void onCommand(String[] args, String command) {
		Client.addChatMessage(
				"\n" + Client.nameWithClient + " " + Client.version
				+ "\n" + "---------------------"
				+ "\n" + "Avalible commands:"
				+ "\n"
				+ "\n" + "Help - displays this command: .h/.help "
				+ "\n" + "Toggle - toggles a module: .t/.toggle <cheat name>"
				+ "\n" + "Bind - binds a module to a key: .b/.bind <cheat name> <key> / <clear>");
	}
}
