package fr.ozedev.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.ozedev.crea.Crea;

public class QuitEvent implements Listener{
	@EventHandler
	public void onPlayerJoin(PlayerQuitEvent event){
		Player player = event.getPlayer();
		Crea.useConfig().set(player.getName()+".lastX", player.getLocation().getBlockX());
		Crea.useConfig().set(player.getName()+".lastY", player.getLocation().getBlockY());
		Crea.useConfig().set(player.getName()+".lastZ", player.getLocation().getBlockZ());
	}
}
