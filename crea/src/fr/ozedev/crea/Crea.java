package fr.ozedev.crea;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.ozedev.listener.JoinEvent;
import fr.ozedev.listener.OnCommand;
import fr.ozedev.listener.QuitEvent;

public class Crea extends JavaPlugin{
	private static Plugin instance;
	private static FileConfiguration config;
	
	public static FileConfiguration useConfig(){return config;}
	public static Plugin usePlugin(){return instance;}
	
	public void onEnable(){
		instance = this;
		config = getConfig();
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new JoinEvent(), instance);
		pm.registerEvents(new QuitEvent(), instance);
		
		getCommand("crea").setExecutor(new OnCommand());
		}
	
	public static void initPlayer(Player player){
		if(useConfig().get(player.getName()+".world") == null){
			player.sendMessage("null");
			
			WorldCreator world = new WorldCreator(player.getName());
			Bukkit.createWorld(world);
			Bukkit.getWorld(player.getName());
			player.teleport(new Location(Bukkit.getWorld(player.getName()),0,0,0));
			useConfig().set(player.getName()+".world", player.getName());
		}else{
			player.teleport(new Location(Bukkit.getWorld(player.getName()), useConfig().getInt(player.getName()+".lastX"), useConfig().getInt(player.getName()+".lastY"), useConfig().getInt(player.getName()+".lastZ")));
		}
		player.setGameMode(GameMode.CREATIVE);
		
		player.sendMessage("§aHey salut je suis §cla voix");
		player.sendMessage("§aje suis la en autre pour gérer le créa");
		player.sendMessage("§aTu es ici sur ta map personnel");
		player.sendMessage("§aEnfin si tu veux voir une map de quelqu'un d'autre fais §c/crea view <nom du joueur>");
		player.sendMessage("§a Si tu veux rentrer sur ta map fais §c/crea map");
		//player.sendMessage("§aSi tu veux jouer avec une personne fais §c/crea join <nom du joueur>");
		//player.sendMessage("§aIl devra faire la commande §c/crea accept§a et tu seras téléporter et ça seras ton monde");
	}
}
