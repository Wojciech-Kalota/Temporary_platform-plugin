package me.regos.temporaryplatform.listeners;

import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerInteractEvent;
import sun.jvm.hotspot.opto.Block;

public class RightClickListener implements Listener {

    static int blocks_placed;

    @EventHandler
    public void onClickEvent(PlayerInteractEvent e){

        Player p = e.getPlayer();

        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (p.getItemInHand().getType().equals(Material.BONE)) {
                blocks_placed = 0;

                Location location = p.getLocation();
                location.setY(location.getY() - 1);
                setBlock(location);

                Location temp_location = location;

                temp_location.setZ(location.getZ() + 1);
                setBlock(temp_location);
                temp_location.setZ(location.getZ() - 2);
                setBlock(temp_location);

                temp_location.setX(location.getX() + 1);
                temp_location.setZ(location.getZ());
                setBlock(temp_location);
                temp_location.setZ(location.getZ() + 2);
                setBlock(temp_location);
                temp_location.setZ(location.getZ() - 1);
                setBlock(temp_location);

                temp_location.setX(location.getX() - 2);
                temp_location.setZ(location.getZ());
                setBlock(temp_location);
                temp_location.setZ(location.getZ() + 1);
                setBlock(temp_location);
                temp_location.setZ(location.getZ() - 2);
                setBlock(temp_location);

                if(blocks_placed>0){
                    p.getItemInHand().setAmount(p.getItemInHand().getAmount()-1);
                }
            }
        }
    }

    private void setBlock(Location location){
        if(!location.getBlock().getType().isSolid()) {
            location.getBlock().setType(Material.WHITE_WOOL);
            blocks_placed++;
        }
    }
}
