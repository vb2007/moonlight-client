package moonlight.modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import moonlight.events.Event;
import moonlight.settings.Setting;
import net.minecraft.client.Minecraft;

public class Module {
	
	public String name;
	public boolean toggled;
	public int keyCode;
	public Category category;
	public Minecraft mc = Minecraft.getMinecraft();
	
	public boolean expanded;
	public int index;
	public List<Setting> settings = new ArrayList<Setting>();
	
	public Module(String name, int key, Category c) {
		this.name = name;
		this.keyCode = key;
		this.category = c;
	}
	
	public void addSettings(Setting... settings) {
		this.settings.addAll(Arrays.asList(settings));
	}
	
	public boolean isEnabled() {
		return toggled;
	}
	
	public int getKey() {
		return keyCode;
	}
	
	public void onEvent(Event e) {
		
	}
	
	public void toggle() {
		toggled = !toggled;
		
		if (toggled) {
			onEnable();
		}
		else {
			onDisable();
		}
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	public enum Category {
		COMBAT("Combat"),
		MOVEMENT("Movement"),
		PLAYER("Player"),
		RENDER("Render");
		
		public String name;
		public int moduleIndex;
		
		Category (String name){
			this.name = name;
		}
	}

}