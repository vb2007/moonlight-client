package moonlight.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import moonlight.Client;
import moonlight.command.implement.Bind;
import moonlight.command.implement.Help;
import moonlight.command.implement.Say;
import moonlight.command.implement.Toggle;
import moonlight.events.listeners.EventChat;

public class CommandManager {

	public List<Command> commands = new ArrayList<Command>();
	// TODO: mikor a j�t�kos fell�p egy szerverre / egyj�t�kos vil�gba, a kliens ki�rhatja, hogy "you can get command info with .help" vagy valami
	public String prefix = ".";
	
	public CommandManager() {
		setup();
	}
	
	//command import a command.implement-b�l
	public void setup() {
		commands.add(new Toggle());
		commands.add(new Bind());
		commands.add(new Help());
		commands.add(new Say());
	}
	
	public void handleChat(EventChat event) {
		String message = event.getMessage();
		
		//ha az �zenet nem a prefixel kezd�dik akkor leszarjuk
		if(!message.startsWith(prefix)) {
			return;
		}
		
		//nem k�ldi el chatbe az �zenetet, csak a konzolos parancsot hatja v�gre
		event.setCancelled(true);
		
		message = message.substring(prefix.length());
		
		boolean foundCommand = false;
		
		if(message.split(" ").length > 0) {
			String commandName = message.split(" ")[0];
			
			for(Command c : commands) {
				if(c.aliases.contains(commandName) || c.name.equalsIgnoreCase(commandName)) {
					c.onCommand(Arrays.copyOfRange(message.split(" "), 1, message.split(" ").length), message);
					foundCommand = true;
					break;
				}
			}
		}
		
		if(!foundCommand) {
			Client.addChatMessage("Couldn't find command.");
		}
	}
}
