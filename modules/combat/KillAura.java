package moonlight.modules.combat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

import moonlight.events.Event;
import moonlight.events.listeners.EventMotion;
import moonlight.events.listeners.EventUpdate;
import moonlight.modules.Module;
import moonlight.util.Timer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;

public class KillAura extends Module {

	public Timer timer = new Timer();
	
	public KillAura() {
		super("KillAura", Keyboard.KEY_R, Category.COMBAT);
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
				
				//shortens the list to entities that are closer to the player than 4 blocks
				targets = targets.stream().filter(entity -> entity.getDistanceToEntity(mc.thePlayer) < 4 && entity != mc.thePlayer && !entity.isDead && entity.getHealth() > 0).collect(Collectors.toList());
				
				targets.sort(Comparator.comparingDouble(entity -> ((EntityLivingBase)entity).getDistanceToEntity(mc.thePlayer)));
				
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
					
					if(timer.hasTimeElapsed(1000 / 10, true)) {
						//TODO: NoSwing beállítás / modul
						mc.thePlayer.swingItem();
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
