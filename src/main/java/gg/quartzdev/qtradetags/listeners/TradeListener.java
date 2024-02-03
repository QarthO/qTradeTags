package gg.quartzdev.qtradetags.listeners;

import com.google.common.collect.Lists;
import gg.quartzdev.qtradetags.qTradeTags;
import gg.quartzdev.qtradetags.util.Sender;
import io.papermc.paper.event.player.PlayerTradeEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantInventory;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class TradeListener implements Listener {

    qTradeTags plugin;
    NamespacedKey key;

    public TradeListener(){
        this.plugin = qTradeTags.instance;
        this.key = new NamespacedKey(plugin, "quartzdev.gg");
    }

    @EventHandler
    public void onPlayerOpenInventory(InventoryOpenEvent event){
        if(!(event.getInventory() instanceof MerchantInventory merchantInventory)){
            return;
        }



        List<MerchantRecipe> recipes = new ArrayList<>();
        for(MerchantRecipe recipe : merchantInventory.getMerchant().getRecipes()){
            recipes.add(markRecipe(recipe));
        }

        merchantInventory.getMerchant().setRecipes(recipes);
    }

    private MerchantRecipe markRecipe(MerchantRecipe recipe){

        ItemStack result = recipe.getResult();
        ItemMeta itemMeta = result.getItemMeta();

//        Marks if needed
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        if(pdc.has(key, PersistentDataType.BYTE)){
            return recipe;
        }
        pdc.set(key, PersistentDataType.BYTE, (byte) 1);
        result.setItemMeta(itemMeta);

//        Copies recipe
//        - you can't modify an existing recipe, so we have to copy it with the new marked result
        MerchantRecipe copiedRecipe = new MerchantRecipe(result, recipe.getMaxUses());
        copiedRecipe.setDemand(recipe.getDemand());
        copiedRecipe.setExperienceReward(recipe.hasExperienceReward());
        copiedRecipe.setIgnoreDiscounts(recipe.shouldIgnoreDiscounts());
        copiedRecipe.setIngredients(recipe.getIngredients());
        copiedRecipe.setMaxUses(recipe.getMaxUses());
        copiedRecipe.setPriceMultiplier(recipe.getPriceMultiplier());
        copiedRecipe.setSpecialPrice(recipe.getSpecialPrice());
        copiedRecipe.setUses(recipe.getUses());
        copiedRecipe.setVillagerExperience(recipe.getVillagerExperience());

        return copiedRecipe;
    }
}
