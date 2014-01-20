package com.webi

class SharedStuff {

	static String getTypeForDescription(String itemDescription) {
		String itemType
		switch ( itemDescription ) {
			case ~/(?i)COSTO GAS .*/ :
				itemType = 'GAS'
				break
			case ~/(?i)SUBWAY .*/ :
				itemType = 'SUBWAY'
				break
			case ~/(?i)COSTCO WHSE .*/ :
				itemType = 'COSTCO'
				break
			default :
				itemType = 'MISCELLANEOUS'
				break
		}
		return itemType
	}
}
