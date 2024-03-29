package moonlight.command.implement;

import org.lwjgl.input.Keyboard;

import moonlight.Client;
import moonlight.command.Command;
import moonlight.modules.Module;

public class Bind extends Command {
	
	public Bind() {
		super("Bind", "Binds a module by name", "bind <name> <key> | clear", "b");
	}

	@Override
	public void onCommand(String[] args, String command) {
		//ha 2 �rt�k van bind parancs
		if(args.length == 2) {
			String moduleName = args[0];
			String keyName = args[1];
			
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
			}
		}
		
		//ha 1 �rt�k clear parancs
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("clear")) {
				for(Module module : Client.modules) {
					module.keyCode.setKeyCode(Keyboard.KEY_NONE);
				}
				Client.addChatMessage("Cleared all binds.");
			}
			else {
				Client.addChatMessage("Invalid parameter(s). Type .h for help.");
			}
		}
	}
}
