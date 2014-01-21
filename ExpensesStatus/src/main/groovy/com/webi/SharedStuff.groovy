package com.webi

class SharedStuff {

	static Map knownItems = ['SUBWAY':'RESTAURANTS', 'HEB':'GROCERIES','CARTER\'S':'VACHI', 'HIMALAYAN':'INDIA STORE','AMAZON':'AMAZON',
						'MUSTAFA':'INDIA STORE','COSTCO':'COSTCO', 'WAL':'WALMART', 'AMAZON.COM':'AMAZON',
						'BAWARCHI' : 'RESTAURANTS', 'BABIES' :'VACHI', 'WAL-MART':'WALMART',
						'ALIBABA' :  'INDIA STORE', 'CVSPHARMACY':'CVS', 'QI' : 'VACHI', 'SPROUTS':'GROCERIES',
						'PIZZA' : 'RESTAURANTS', 'AT&T*BILL':'PHONE',
						'TWC*TIME' : 'INTERNET', 'SHELL': 'GAS', 'CORNER':'COINS'  
						]
	static Set knownItemsKeys = knownItems.keySet()
	static String getTypeForDescription(String itemDescription) {
		String itemType
		switch ( itemDescription ) {
			case ~/(?i)COSTCO GAS .*/ :
				itemType = 'GAS'
				break
			
			default :
				def firstWord = itemDescription?.split(/\s/)[0]
				itemType = knownItems[firstWord]?: 'MISCELLANEOUS'
				break
		}
		return itemType
	}
}
