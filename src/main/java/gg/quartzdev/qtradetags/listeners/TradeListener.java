package gg.quartzdev.qtradetags.listeners;

import gg.quartzdev.qtradetags.qTradeTags;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
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
            recipes.add(markRecipe(recipe, event.getPlayer().isSneaking()));
        }

        merchantInventory.getMerchant().setRecipes(recipes);
    }

    public boolean hasMarkedItem(Inventory inventory){
        for(ItemStack itemStack : inventory.getContents()){
            if(itemStack == null){
                continue;
            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
            if(pdc.has(key, PersistentDataType.BYTE)){
                return true;
            }
        }
        return false;
    }

    private List<ItemStack> getIngredients(List<ItemStack> ingredients, boolean isSneaking){
        List<ItemStack> markedIngredients = new ArrayList<>();
        for(ItemStack ingredient : ingredients){
            ItemStack markedIngredient = new ItemStack(ingredient);
            if(isSneaking){
                markItem(markedIngredient);
            } else {
                unmarkItem(markedIngredient);
            }
            markedIngredients.add(markedIngredient);
        }
        return markedIngredients;
    }

    private MerchantRecipe markRecipe(MerchantRecipe recipe, boolean isSneaking){

        ItemStack result = recipe.getResult();

//        Marks if needed
        markItem(result);

//        Copies recipe
//        - you can't modify an existing recipe, so we have to copy it with the new marked result
        MerchantRecipe copiedRecipe = new MerchantRecipe(result, recipe.getMaxUses());
        copiedRecipe.setDemand(recipe.getDemand());
        copiedRecipe.setExperienceReward(recipe.hasExperienceReward());
        copiedRecipe.setIgnoreDiscounts(recipe.shouldIgnoreDiscounts());

        copiedRecipe.setIngredients(getIngredients(recipe.getIngredients(), isSneaking));

        copiedRecipe.setMaxUses(recipe.getMaxUses());
        copiedRecipe.setPriceMultiplier(recipe.getPriceMultiplier());
        copiedRecipe.setSpecialPrice(recipe.getSpecialPrice());
        copiedRecipe.setUses(recipe.getUses());
        copiedRecipe.setVillagerExperience(recipe.getVillagerExperience());

        return copiedRecipe;
    }

    private boolean markItem(ItemStack itemStack){
        if(itemStack.getType() == Material.AIR){
            return false;
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        if(pdc.has(key, PersistentDataType.BYTE)){
            return false;
        }
        pdc.set(key, PersistentDataType.BYTE, (byte) 1);
        itemStack.setItemMeta(itemMeta);
        return true;
    }

    private void unmarkItem(ItemStack itemStack){
        if(itemStack.getType() == Material.AIR){
            return;
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        pdc.remove(key);
        itemStack.setItemMeta(itemMeta);
    }
}
