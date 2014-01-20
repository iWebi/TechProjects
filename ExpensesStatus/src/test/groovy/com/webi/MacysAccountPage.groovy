package com.webi
import geb.Page
import java.text.NumberFormat

import com.webi.Singleton;

class MacysAccountPage extends Page {
	static at = { 
		waitFor{ title == 'Macy\'s - Shop Fashion Clothing & Accessories - Official Site - Macys.com' } 
	}
	
	static content = {
		balance() {
			$('a', text:'my account').click()
			assert title == 'My Account - My Account - Macy\'s'
			
			$('a', text:'credit account summary').click()
			assert title == 'Credit Account Summary - Macy\'s Credit Card - Macy\'s'
			
			def balance = $('div.accountBalance').find('div.right').find('p.col3').find('span').text()
			Singleton.expenseDetails().macys = (NumberFormat.getCurrencyInstance().parse(balance) as Double)
			return true
		}
		
		logout() {
			$('a', id:'globalMastheadSignIn').click()
			assert title == 'Macy\'s - Shop Fashion Clothing & Accessories - Official Site - Macys.com'
			return true
		}
	}
}
