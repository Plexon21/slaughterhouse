package com.plexon21.Slaughterhouse;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Listener for slaughterhouse plugin, contains most of its functionality.
 * 
 * @author Plexon21
 *
 */
public class SlaughterhouseListener implements Listener {
	private List<Material> MeatList = new ArrayList<Material>();
	private List<EntityType> AnimalList = new ArrayList<EntityType>();

	public SlaughterhouseListener(FileConfiguration config) {
		// Initialize all meat-types and animals
		MeatList.add(Material.RAW_BEEF);
		MeatList.add(Material.COOKED_BEEF);
		MeatList.add(Material.RAW_CHICKEN);
		MeatList.add(Material.COOKED_CHICKEN);
		MeatList.add(Material.PORK);
		MeatList.add(Material.GRILLED_PORK);
		MeatList.add(Material.RABBIT);
		MeatList.add(Material.COOKED_RABBIT);
		MeatList.add(Material.MUTTON);
		MeatList.add(Material.COOKED_MUTTON);
		if (config.getBoolean("animals.cow", true))
			AnimalList.add(EntityType.COW);
		if (config.getBoolean("animals.chicken", true))
			AnimalList.add(EntityType.CHICKEN);
		if (config.getBoolean("animals.pig", true))
		AnimalList.add(EntityType.PIG);
		if (config.getBoolean("animals.rabbit", true))
		AnimalList.add(EntityType.RABBIT);
		if (config.getBoolean("animals.sheep", true))
		AnimalList.add(EntityType.SHEEP);
	}

	/**
	 * 
	 * @param deathEvent
	 */
	@EventHandler
	public void onAnimalDeath(EntityDeathEvent deathEvent) {
		// only fire if the killed entity is in AnimalList
		if (AnimalList.contains(deathEvent.getEntityType())) {
			// drops only double if the killer is a player who has permission
			Player killer = deathEvent.getEntity().getKiller();
			if (!killer.equals(null) && killer.hasPermission(SlaughterhousePermission.MEAT.getValue())) {
				if (killer.hasPermission(SlaughterhousePermission.OTHER.getValue())) {
					doubleAll(deathEvent);
				} else {
					doubleMeat(deathEvent);
				}
			} else if (!killer.equals(null) && killer.hasPermission(SlaughterhousePermission.OTHER.getValue())) {
				doubleOther(deathEvent);
			}
		}
	}

	/**
	 * double all other drops in deathEvent
	 * 
	 * @param deathEvent
	 */
	private void doubleOther(EntityDeathEvent deathEvent) {
		List<ItemStack> duplicatedItems = new ArrayList<>();
		for (ItemStack currentStack : deathEvent.getDrops()) {
			if (!MeatList.contains(currentStack.getType())) {
				duplicatedItems.add(currentStack.clone());
			}
		}
		deathEvent.getDrops().addAll(duplicatedItems);
	}

	/**
	 * double all meat-drops (contained in MeatList) in deathEvent
	 * 
	 * @param deathEvent
	 */
	private void doubleMeat(EntityDeathEvent deathEvent) {
		List<ItemStack> duplicatedItems = new ArrayList<>();
		for (ItemStack currentStack : deathEvent.getDrops()) {
			if (MeatList.contains(currentStack.getType())) {
				duplicatedItems.add(currentStack.clone());
			}
		}
		deathEvent.getDrops().addAll(duplicatedItems);
	}

	/**
	 * double all drops in deathEvent
	 * 
	 * @param deathEvent
	 */
	private void doubleAll(EntityDeathEvent deathEvent) {
		List<ItemStack> duplicatedItems = new ArrayList<>();
		for (ItemStack currentStack : deathEvent.getDrops()) {
			duplicatedItems.add(currentStack.clone());
		}
		deathEvent.getDrops().addAll(duplicatedItems);

	}
}
