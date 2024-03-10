package me.poker;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

import java.io.File;
import java.util.Random;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {return true;}
        Player player = (Player) sender;



        if (args.length == 0) {
            player.sendMessage("Wrong usage.");
            return true;
        }
        File dir = new File("/home/minecraft/server/plugins/Poker");
        File[] files = dir.listFiles();

        try {
            for (int i = 0; i<Integer.parseInt(args[0]); i++) {
                Random rand = new Random();
                File file = files[rand.nextInt(files.length)];
                MapView view = Bukkit.createMap(player.getWorld());
                view.getRenderers().clear();
                CardRenderer renderer = new CardRenderer();
                if (!renderer.load(file)) {
                    player.sendMessage("Image could not be loaded.");
                    return true;
                }
                view.addRenderer(renderer);
                ItemStack map = new ItemStack(Material.FILLED_MAP);
                MapMeta meta = (MapMeta) map.getItemMeta();
                meta.setMapView(view);
                map.setItemMeta(meta);
                player.getInventory().addItem(map);
                player.sendMessage("Item created.");
            }
        } catch (NumberFormatException e) {
            player.sendMessage("Enter an int");
            return true;
        }


        return true;
    }
}
