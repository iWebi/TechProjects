package com.webi
import geb.Page
import static Singleton.*


class MacysHomePage extends Page {
	static url = 'https://www.macys.com'
	static at = {
		title == 'Macy\'s - Shop Fashion Clothing & Accessories - Official Site - Macys.com'
	}
	
	static content = {
		login() {
			$('a',id:'globalMastheadSignIn').click()
			$('form', id:'signInForm').with {  
				email = config().macys.id
				password = config().macys.password
				$('input', id:'accountSignIn').click()
			}
		}
	}	
}
