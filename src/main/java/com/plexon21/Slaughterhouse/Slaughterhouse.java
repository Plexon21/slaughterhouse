package com.plexon21.Slaughterhouse;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class contains the initialization of the plugin.
 * 
 * @author Plexon21
 *
 */
public class Slaughterhouse extends JavaPlugin {

	/**
	 * Enable the plugin on serverstart or after reload
	 */
	@Override
	public void onEnable() {
		saveDefaultConfig();
		FileConfiguration config = getConfig();
		
		getServer().getPluginManager().registerEvents(new SlaughterhouseListener(config), this);
	}

	@Override
	public void onDisable() {
	}
}
