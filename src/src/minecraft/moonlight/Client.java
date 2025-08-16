package moonlight;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.Display;

import moonlight.command.CommandManager;
import moonlight.events.Event;
import moonlight.events.listeners.EventChat;
import moonlight.events.listeners.EventKey;
import moonlight.modules.Module;
import moonlight.modules.Module.Category;
import moonlight.modules.combat.*;
import moonlight.modules.movement.*;
import moonlight.modules.player.*;
import moonlight.modules.render.*;
import moonlight.modules.world.*;
import moonlight.ui.HUD;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class Client {
	
	//Client info
	public static String name = "Moonlight";
	public static String nameWithClient = "Moonlight Client";
	public static String version = "v0.1";

	public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();
	
	//Custom HUD & chat commands
	public static HUD hud = new HUD();
	public static CommandManager commandManager = new CommandManager();
	
	public static void startup() {
		System.out.println("Starting " + nameWithClient + " " + version);
		Display.setTitle(nameWithClient + " " + version);
		
		modules.add(new TabGUI());
		modules.add(new Fly());
		modules.add(new Sprint());
		modules.add(new Fullbright());
		modules.add(new NoFall());
		modules.add(new KillAura());
		modules.add(new Timer());
	}
	
	public static void onEvent(Event e) {
		//Handling chat commands
		if(e instanceof EventChat) {
			commandManager.handleChat((EventChat)e);
		}
		
		for(Module m : modules) {
			if(!m.toggled)
				continue;
				
			m.onEvent(e);
		}
	}
	
	public static void keyPress(int key) {
		Client.onEvent(new EventKey(key));
		
		for(Module m : modules) {
			if(m.getKey() == key) {
				m.toggle();
			}
		}
	}
	
	public static List<Module> getModulesByCategory(Category c){
		List<Module> modules = new ArrayList<Module>();
		
		for(Module m : Client.modules) {
			if(m.category == c) {
				modules.add(m);
			}
		}
		
		return modules;
	}
	
	
	//Handling custom chat messages
	public static void addChatMessage(String message) {
		//Writes out client name in blue, then the message in gray
		message = "\2479" + nameWithClient + "\2477: " + message;
		
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));;
	}

}
