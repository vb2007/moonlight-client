package moonlight;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.Display;

import moonlight.command.CommandManager;
import moonlight.events.listeners.EventChat;
import moonlight.events.listeners.EventKey;
import moonlight.modules.combat.KillAura;
import moonlight.modules.movement.*;
import moonlight.modules.player.*;
import moonlight.modules.render.*;
import moonlight.ui.HUD;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class Client {
	
	//kliens infó
	public static String name = "Moonlight";
	public static String nameWithClient = "Moonlight Client";
	public static String version = "v0.1";

	public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>(); //idk
	
	//egyedi hud
	public static HUD hud = new HUD();
	//egyedi chates commandok
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
	}
	
	public static void onEvent(Event e) {
		//chates commandok implementálása
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
	
	
	//saját chatüzenet kezelés
	public static void addChatMessage(String message) {
		//kék színnel kiírja a kliensnevet, majd szürkésen az üzenetet a : után
		message = "\2479" + nameWithClient + "\2477: " + message;
		
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));;
	}

}
