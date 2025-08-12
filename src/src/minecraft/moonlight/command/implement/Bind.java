package moonlight.command.implement;

import org.lwjgl.input.Keyboard;

import moonlight.Client;
import moonlight.command.Command;
import moonlight.modules.Module;

public class Bind extends Command {
	
	public Bind() {
		super("Bind", "Binds a module by name", "bind <name>/<all> <key>/<clear>", "b");
	}

	@Override
	public void onCommand(String[] args, String command) {
		//2 Parameters -> bind module
		if(args.length == 2) {
			String moduleName = args[0];
			String keyName = args[1];
			
			if (moduleName.equalsIgnoreCase("all") && keyName.equalsIgnoreCase("clear")) {
				for(Module module : Client.modules) {
					module.keyCode.setKeyCode(Keyboard.KEY_NONE);
				}
				Client.addChatMessage("Cleared binds for all modules.");
			}
			
			boolean foundModule = false;
			for(Module module : Client.modules) {
				if(module.name.equalsIgnoreCase(moduleName)) {
					
					module.keyCode.setKeyCode(Keyboard.getKeyIndex(keyName.toUpperCase()));
					Client.addChatMessage(String.format("Bound %s to %s", module.name, Keyboard.getKeyName(module.getKey())));
					
					foundModule = true;
					break;
				}
			}
			
			if(!foundModule) {
				Client.addChatMessage("Couldn't find module.");
				return;
			}
		}
	}
}
