package moonlight.modules.combat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

import moonlight.events.Event;
import moonlight.events.listeners.EventMotion;
import moonlight.events.listeners.EventUpdate;
import moonlight.modules.Module;
import moonlight.settings.BooleanSetting;
import moonlight.settings.ModeSetting;
import moonlight.settings.NumberSetting;
import moonlight.util.Timer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.network.play.client.C0APacketAnimation;

public class KillAura extends Module {

	public Timer timer = new Timer();
	//beállítás Number/Boolean/ModeSetting.java alapján:
	//pl.: ("Beállítás neve", alapérték, minimum érték, maximum érték, változás mérete)
	public NumberSetting range = new NumberSetting("Range", 4, 1, 6, 0.1);
	public NumberSetting aps = new NumberSetting("APS", 10, 1, 20, 1);
	//pl.: ("Beállítás neve", alapérték, érték1, érték2, érték3, stb...)
	public ModeSetting target = new ModeSetting("Target", "All", "All", "Players", "Mobs (hostile)", "Animals");
	//pl.: ("Beállítás neve", true/false)
	public BooleanSetting noSwing = new BooleanSetting("NoSwing", false);
	
	public KillAura() {
		super("KillAura", Keyboard.KEY_R, Category.COMBAT);
		this.addSettings(range, aps, noSwing, target);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventMotion) {
			if(e.isPre()) {
				
				EventMotion event = (EventMotion)e;
				
				//lists living entities in the world
				List<EntityLivingBase> targets = (List<EntityLivingBase>) mc.theWorld.loadedEntityList.stream().filter(EntityLivingBase.class::isInstance).collect(Collectors.toList());
				
				//shortens the list to entities that are closer to the player than the given value
				targets = targets.stream().filter(entity -> entity.getDistanceToEntity(mc.thePlayer) < range.getValue() && entity != mc.thePlayer && !entity.isDead && entity.getHealth() > 0).collect(Collectors.toList());
				
				targets.sort(Comparator.comparingDouble(entity -> ((EntityLivingBase)entity).getDistanceToEntity(mc.thePlayer)));
				
				switch(target.getMode()) {
					case "Players":
						targets = targets.stream().filter(EntityPlayer.class::isInstance).collect(Collectors.toList());
						break;
					case "Mobs (hostile)":
						targets = targets.stream().filter(EntityMob.class::isInstance).collect(Collectors.toList());
						break;
					case "Animals":
						targets = targets.stream().filter(EntityAnimal.class::isInstance).collect(Collectors.toList());
						break;
					case "All":
						break;
				}
				
				//targets = targets.stream().filter(EntityX.class::isInstance).collect(Collectors.toList());
				//EntityX-et behelyettesíteni + importálni:
				//-EntityPlayer = csak játékost támad (játékos)
				//-EntityAnimal = csak állatot támad (passzív)
				//???-EntityMob = csak ellenséges mobot támad? (agresszív)
				
				if(!targets.isEmpty()) {
					EntityLivingBase target = targets.get(0);
					
					//teszt (client-side) tényleg arra néz-e a játékos server-side-on:
					//mc.thePlayer.rotationYaw = (getRotations(target)[0]);
					//mc.thePlayer.rotationPitch = (getRotations(target)[1]);
					
					event.setYaw(getRotations(target)[0]);
					event.setPitch(getRotations(target)[1]);
					
					if(timer.hasTimeElapsed((long) (1000 / aps.getValue()), true)) {
						//TODO: NoSwing beállítás / modul (készül)
						if(noSwing.isEnabled()) {
							mc.thePlayer.sendQueue.addToSendQueue(new C0APacketAnimation());
						}
						else {
							mc.thePlayer.swingItem();
						}
						mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(target, Action.ATTACK));
					}
				}
			}
		}
	}
	
	public float[] getRotations(Entity e) {
		double deltaX = e.posX + (e.posX - e.lastTickPosX) - mc.thePlayer.posX,
				deltaY = e.posY - 3.5 + e.getEyeHeight() - mc.thePlayer.posY + mc.thePlayer.getEyeHeight(),
				deltaZ = e.posZ + (e.posZ - e.lastTickPosZ) - mc.thePlayer.posZ,
				distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
		
		float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ)),
				pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));
		
		if(deltaX < 0 && deltaZ < 0) {
			yaw = (float) (90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
		}
		else if(deltaX > 0 && deltaZ < 0) {
			yaw = (float) (-90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
		}
		
		return new float[] {yaw, pitch};
	}
}
