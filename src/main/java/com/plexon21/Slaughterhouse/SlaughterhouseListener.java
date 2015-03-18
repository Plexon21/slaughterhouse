//*******************************************************************************
// Title:              	SlaughterhouseListener
// Author:             	Plexon21
// Programmed for:	   	SwissSMP.ch
// Class Description:	Listener for slaughterhouse plugin, contains most of the
//						plugin functionality.
//-------------------------------------------------------------------------------
// History:
// 2015-03-17			First Implementation with Permissions and basic 
//						functionality 
//*******************************************************************************
package com.plexon21.Slaughterhouse;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class SlaughterhouseListener implements Listener {
	private List<Material> MeatList = new ArrayList<Material>();
	private List<EntityType> AnimalList = new ArrayList<EntityType>();

	public SlaughterhouseListener() {
		//Initialize all meat-types and animals
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
		AnimalList.add(EntityType.COW);
		AnimalList.add(EntityType.CHICKEN);
		AnimalList.add(EntityType.PIG);
		AnimalList.add(EntityType.RABBIT);
		AnimalList.add(EntityType.SHEEP);
	}

	@EventHandler
	public void onAnimalDeath(EntityDeathEvent event) {
		//only fire if the killed entity is in AnimalList
		if (AnimalList.contains(event.getEntityType())) {
			//drops only double if the killer is a player who has permission
			Player killer = event.getEntity().getKiller();
			if (!killer.equals(null)
					&& killer.hasPermission(SlaughterhousePermission.MEAT.getValue())) {
				if (killer.hasPermission(SlaughterhousePermission.OTHER.getValue())) {
					doubleAll(event);
				} else {
					doubleMeat(event);
				}
			} else if (!killer.equals(null)
					&& killer.hasPermission(SlaughterhousePermission.OTHER.getValue())) {
				doubleOther(event);
			}
		}
	}
	// doubles all drops other than meat
	private void doubleOther(EntityDeathEvent event) {
		List<ItemStack> duplicatedItems = new ArrayList<>();
		for (ItemStack currentStack : event.getDrops()) {
			if (!MeatList.contains(currentStack.getType())) {
				duplicatedItems.add(currentStack.clone());
			}
		}
		event.getDrops().addAll(duplicatedItems);
	}

	// double all meat drops
	private void doubleMeat(EntityDeathEvent event) {
		List<ItemStack> duplicatedItems = new ArrayList<>();
		for (ItemStack currentStack : event.getDrops()) {
			if (MeatList.contains(currentStack.getType())) {
				duplicatedItems.add(currentStack.clone());
			}
		}
		event.getDrops().addAll(duplicatedItems);
	}

	//double all drops
	private void doubleAll(EntityDeathEvent event) {
		List<ItemStack> duplicatedItems = new ArrayList<>();
		for (ItemStack currentStack : event.getDrops()) {
			duplicatedItems.add(currentStack.clone());
		}
		event.getDrops().addAll(duplicatedItems);

	}
}
