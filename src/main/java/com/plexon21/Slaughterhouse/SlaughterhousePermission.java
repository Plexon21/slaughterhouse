//*******************************************************************************
// Title:              	SlaughterhousePermission
// Author:             	Plexon21
// Programmed for:	   	SwissSMP.ch
// Class Description:	Enum of all Permissions for the Plugin to centralize 
//						changes.
//-------------------------------------------------------------------------------
// History:
// 2015-03-17			First Implementation with Permissions and basic 
//						functionality 
//*******************************************************************************
package com.plexon21.Slaughterhouse;

public enum SlaughterhousePermission {
	MEAT("p21.slaughterhouse.meat"), 
	OTHER("p21.slaughterhouse.other");
	private SlaughterhousePermission(String value) {
		this.value = value;
	}

	private final String value;

	public String getValue() {
		return value;
	}
}