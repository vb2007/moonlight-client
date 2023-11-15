package moonlight.ui;

import java.util.Collections;
import java.util.Comparator;

import org.lwjgl.util.Color;

import moonlight.Client;
import moonlight.modules.Module;
import moonlight.events.listeners.EventRenderGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class HUD {
	
	public Minecraft mc = Minecraft.getMinecraft();
	int moduleShadowColor = 0x80AA00AA;
	int moduleListColor = -1;
	
	/*public static class ModuleComparator implements Comparator<Module> {

		@Override
		public int compare(Module arg0, Module arg1) {
			if(Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg0.name) < Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg1.name)) {
				return 1;
			}
			if(Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg0.name) > Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg1.name)) {
				return -1;
			}
			return 0;
		}
		
	}*/
	
	public void draw() {
		//In 1.8.8:
		//ScaledResolution sr = new ScaledResolution(mc);
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		FontRenderer fr = mc.fontRendererObj;
		
		//Collections.sort(Client.modules, new ModuleComparator());
		
		Client.modules.sort(Comparator.comparingInt(m -> 
			fr.getStringWidth(((Module)m).name))
				.reversed()
				);
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(4, 4, 0);
		GlStateManager.scale(2, 2, 1);
		GlStateManager.translate(-4, -4, 0);
		fr.drawStringWithShadow(Client.name + " " + Client.version, 4, 4, -1);
		/*GlStateManager.translate(4, 4, 0);
		GlStateManager.scale(0.5, 0.5, 1);
		GlStateManager.translate(-4, -4, 0);*/
		GlStateManager.popMatrix();

		int count = 0;
		
		for(Module m : Client.modules) {
			if(!m.toggled || m.name.equals("TabGUI")) {
				continue;
			}
			
			double offset = count*(fr.FONT_HEIGHT + 6);
			
			//displays a border around the active modules
			Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(m.name) - 10, offset, sr.getScaledWidth() - fr.getStringWidth(m.name) - 8, 6 + fr.FONT_HEIGHT + offset,  moduleListColor);
			Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(m.name) - 8, offset, sr.getScaledWidth(), 6 + fr.FONT_HEIGHT + offset,  moduleShadowColor);
			//displays the client's name
			fr.drawStringWithShadow(m.name, sr.getScaledWidth() - fr.getStringWidth(m.name) - 4, 4 + offset, moduleListColor);
			
			count++;
		}
		
		Client.onEvent(new EventRenderGUI());
	}
}
