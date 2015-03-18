//*******************************************************************************
// Title:              	Slaughterhouse
// Author:             	Plexon21
// Programmed for:	   	SwissSMP.ch
// Class Description:	Main class for the plugin.
//-------------------------------------------------------------------------------
// History:
// 2015-03-17			First Implementation with Permissions and basic 
//						functionality 
//*******************************************************************************
package com.plexon21.Slaughterhouse;

import org.bukkit.plugin.java.JavaPlugin;

public class Slaughterhouse extends JavaPlugin {

	@Override
	public void onEnable() {
		//register a listener for the slaughterhouse
		getServer().getPluginManager().registerEvents(new SlaughterhouseListener(), this);
		saveDefaultConfig();
		getConfig();
	}

	@Override
	public void onDisable() {
	}	
}
