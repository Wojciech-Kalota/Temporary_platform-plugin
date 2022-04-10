package me.regos.temporaryplatform;

import me.regos.temporaryplatform.listeners.RightClickListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class TemporaryPlatform extends JavaPlugin {

    //TODO: Prevent double-click

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new RightClickListener(), this);
        create_recipes();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void create_recipes(){
        ItemStack item = new ItemStack(Material.BONE);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Platform generator");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Creates 3x3 platform below the player");
        meta.setLore(lore);
        item.setItemMeta(meta);

        // Add the custom enchantment to make the emerald sword special
        // In this case, we're adding the permission that modifies the damage value on level 5
        // Level 5 is represented by the second parameter. You can change this to anything compatible with a sword
        item.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 3);


        // create a NamespacedKey for your recipe
        NamespacedKey key = new NamespacedKey(this, "Platform_generator");

        // Create our custom recipe variable
        ShapedRecipe recipe1 = new ShapedRecipe(key, item);

        // Here we will set the places. E and S can represent anything, and the letters can be anything. Beware; this is case sensitive.
        recipe1.shape("WWW", "WWW", "WWW");

        // Set what the letters represent.
        // E = Emerald, S = Stick
        //recipe.setIngredient('E', Material.EMERALD);
        //recipe.setIngredient('S', Material.STICK);
        recipe1.setIngredient('W', Material.WHITE_WOOL);

        // Finally, add the recipe to the bukkit recipes
        Bukkit.addRecipe(recipe1);

        NamespacedKey key2 = new NamespacedKey(this, "Platform_generator2");
        ShapedRecipe recipe2 = new ShapedRecipe(key2, item);
        recipe2.shape("SSS", "SSS", "SSS");
        recipe2.setIngredient('S', Material.STRING);
        Bukkit.addRecipe(recipe2);
    }
}
