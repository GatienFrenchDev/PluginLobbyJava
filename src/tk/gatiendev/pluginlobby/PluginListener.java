package tk.gatiendev.pluginlobby;



import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class PluginListener implements Listener {
	
	public static Main plugin1;
	
	public PluginListener(Main plugin) {
		plugin1 = plugin;
	}


	public ItemStack IconMenu(Material item, String nom) {
		
		ItemStack items = new ItemStack(item);
		ItemMeta Mitems = items.getItemMeta();
		Mitems.setDisplayName(nom);
		items.setItemMeta(Mitems);
		
		return items;
	}
	
	@EventHandler
	public void onRightClick(PlayerInteractEntityEvent e) {
		e.getRightClicked().setPassenger(e.getPlayer());
	}
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage("§6>> "+e.getPlayer().getDisplayName()+" a rejoint le lobby <<");
		Player p = e.getPlayer();
		p.setLevel(69);
		Location spawn = new Location(Bukkit.getWorld("world"), 0, 69, 0);
		ItemStack boussole = new ItemStack(Material.COMPASS);
		ItemMeta Mboussole = boussole.getItemMeta();
		Mboussole.setDisplayName("§aMenu Des Jeux §7(clic droit)");
		boussole.setItemMeta(Mboussole);
		
		
		p.getInventory().clear();
		p.getInventory().setItem(0, new ItemStack(Material.GOLD_BLOCK, 64));
		p.getInventory().setItem(4, boussole);
		p.teleport(spawn);
		p.setGameMode(GameMode.SURVIVAL);
	}
	
	@EventHandler
	public void dropevent(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void bloccasser(BlockBreakEvent e) {
		if (e.getPlayer().isOp()) return;
		e.setCancelled(true);
	}
	
	@EventHandler
	public void degat(EntityDamageEvent e) {
		if (e.getEntity() instanceof  Player) {
			e.setCancelled(true);			
		}

	}
	
	
	@EventHandler
	public void blocplacer(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		p.getInventory().setItem(0, new ItemStack(Material.GOLD_BLOCK, 64));
		{
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin1 , new Runnable()
            {
                @Override
                public void run()
                {
                	b.setType(Material.AIR);
 
                }
            }, 2*20L);
        }
	}

	@EventHandler
	public void invclick(InventoryClickEvent e) {
		if (!e.getWhoClicked().isOp()) {
			e.setCancelled(true);	
		}
		Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem().getType() == Material.DIAMOND_PICKAXE) {
			p.performCommand("server survie");
		}
	}
	
	@EventHandler
	public void clickitem(PlayerInteractEvent e) {
		
		
		// MENU QUAND CLIC BOUSSOLE
		if(e.getPlayer().getItemInHand().getType() == Material.COMPASS) {
			Inventory menu = Bukkit.createInventory(null, 9*4, "Menu Des Jeux");
			menu.setItem(11, IconMenu(Material.DIAMOND_PICKAXE, "§aSurvie §6§lNOUVEAU SPAWN"));
			menu.setItem(13, IconMenu(Material.DIAMOND_SPADE, "§aSpleef"));
			e.getPlayer().openInventory(menu);
		}
	}
}
