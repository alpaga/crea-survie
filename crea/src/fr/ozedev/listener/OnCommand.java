package fr.ozedev.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.ozedev.crea.Crea;

public class OnCommand implements CommandExecutor {
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if(sender instanceof Player){
    		Player player = (Player) sender;
    		if(args.length >= 2){
    			if(args[0].equals("view")){
	    			if(args[1] != null){
	    				if(Crea.useConfig().get(args[1]+".world") != null){
	    					if(player.getWorld().getName().equals(Crea.useConfig().get(player.getName()+".world"))){
		    					player.teleport(new Location(Bukkit.getWorld(Crea.useConfig().getString(args[1]+".world")), 0,0,0));
		    					player.setGameMode(GameMode.SPECTATOR);
		    					return true;
	    					}else{
	    						player.sendMessage("§aCa ne sert a rien de te téléporter dans ton §cmonde§a alors que tu y es déjà");
	        					return true;
	    					}
	    				}else{
	    					player.sendMessage("§b[Crea] §aCe §cJoueur§a n'a pas de monde sur ce §cserveur");
	    					player.sendMessage("args 0 : "+args[0]+" args 1 : "+args[1]+" world name : "+Crea.useConfig().get(args[1]+".world"));
	    					return false;
	    				}
	    			}else{
	    				player.sendMessage("§b[Crea] §aLe nom d'un §cjoueur§a est nécessaire");
	    				return false;
	    			}
    			}else{
    				return false;
    			}
    		}else if(args.length >= 1){
    			if(args[0].equals("map")){
    				if(!Crea.useConfig().getString(player.getName()+".world").equals(player.getWorld().getName())){
	    				player.teleport(new Location(Bukkit.getWorld(Crea.useConfig().getString(player.getName()+".world")), Crea.useConfig().getInt(player.getName()+".lastX"), Crea.useConfig().getInt(player.getName()+".lastY"), Crea.useConfig().getInt(player.getName()+".lastZ")));
	    				player.setGameMode(GameMode.CREATIVE);
	    				return true;
    				}else{
    					player.sendMessage("§aCa ne sert a rien de te téléporter dans ton §cmonde§a alors que tu y es déjà");
    					return true;
    				}
    			}else{
    				return false;
    			}
    		}else{
    			return false;
    		}
    	}else{
    		sender.sendMessage("§b[Crea] §aSeul les §cjoueur §apeuvent faire cette commande");
    		return false;
    	}
    }
}
