package moonlight;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.Display;

import moonlight.events.Event;
import moonlight.events.listeners.EventKey;
import moonlight.modules.Module;
import moonlight.modules.Module.Category;
import moonlight.modules.combat.KillAura;
import moonlight.modules.movement.*;
import moonlight.modules.player.*;
import moonlight.modules.render.*;
import moonlight.ui.HUD;

public class Client {
	
	public static String name = "Moonlight";
	public static String nameWithClient = "Moonlight Client";
	public static String version = "v0.1";

	public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();
	
	public static HUD hud = new HUD();
	
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

}
