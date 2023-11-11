package moonlight.modules.render;

import java.util.List;

import org.lwjgl.input.Keyboard;

import moonlight.Client;
import moonlight.events.Event;
import moonlight.events.listeners.EventKey;
import moonlight.events.listeners.EventRenderGUI;
import moonlight.events.listeners.EventUpdate;
import moonlight.modules.Module;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class TabGUI extends Module {
	public int currentTab;
	public boolean expanded;
	
	public TabGUI() {
		super("TabGUI", Keyboard.KEY_NONE, Category.RENDER);
		toggled = true;
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventRenderGUI) {
			FontRenderer fr = mc.fontRendererObj;
			
			//TODO: A szélességet (80) kicserélni a leghosszab kategórianév +x értékre
			Gui.drawRect(5, 30.5, 70, 30 + Module.Category.values().length * 16 + 1.5, 0x90000000);
			Gui.drawRect(7, 33 + currentTab * 16, 7 + 61, 33 + currentTab * 16 + 12, -1);
			
			int count = 0;
			for(Category c : Module.Category.values()) {
				fr.drawStringWithShadow(c.name, 11, 34.5 + count*16, -1);
				
				count++;
			}
			
			if (expanded) {
				Category category = Module.Category.values()[currentTab];
				List<Module> modules = Client.getModulesByCategory(category);
				
				if (modules.size() == 0) {
					return;
				}
				
				Gui.drawRect(70, 30.5, 70 + 68, 30 + modules.size() * 16 + 1.5, 0x90000000);
				Gui.drawRect(70, 33 + category.moduleIndex * 16, 7 + 61 + 68, 33 + category.moduleIndex * 16 + 12, -1);
				
				count = 0;
				for(Module m : modules) {
					fr.drawStringWithShadow(m.name, 73, 34.5 + count*16, -1);
					
					count++;
				}
			}
		}
		
		if(e instanceof EventKey) {
			int code = ((EventKey)e).code;
			
			//List<Module> modules = Client.getModulesByCategory(Module.Category.values()[currentTab]);
			Category category = Module.Category.values()[currentTab];
			List<Module> modules = Client.getModulesByCategory(category);
			
			if(code == Keyboard.KEY_UP) {
				if(expanded) {
					if(category.moduleIndex <= 0) {
						category.moduleIndex = modules.size() - 1;
					}
					else {
						category.moduleIndex--;					
					}
				}
				else {
					if(currentTab <= 0) {
						currentTab = Module.Category.values().length - 1;
					}
					else {
						currentTab--;					
					}
				}
			}
			
			if(code == Keyboard.KEY_DOWN) {
				if(expanded) {
					if(category.moduleIndex >= modules.size() - 1) {
						category.moduleIndex = 0;
					}
					else {
						category.moduleIndex++;
					}
				}
				else {
					if(currentTab >= Module.Category.values().length - 1) {
						currentTab = 0;	
					}
					else {
						currentTab++;
					}
				}
			}
			
			if(code == Keyboard.KEY_RIGHT) {
				if(expanded && modules.size() != 0) {
					Module module = modules.get(category.moduleIndex);
					if(!module.name.equals("TabGUI")) {
						module.toggle();	
					}
				}
				else {
					expanded = true;
				}
			}
			
			if(code == Keyboard.KEY_LEFT) {
				expanded = false;
			}
		}
	}
}
