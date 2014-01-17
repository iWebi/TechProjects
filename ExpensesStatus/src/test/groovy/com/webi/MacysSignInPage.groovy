package com.webi
import geb.Page


class MacysSignInPage extends Page {
	static url = 'https://www.macys.com'
	static at = {
		title == 'Macy\'s - Shop Fashion Clothing & Accessories - Official Site - Macys.com'
	}
	
	static content = {
		login() { String userId, String passWord ->
			$('a',id:'globalMastheadSignIn').click()
			$('form', id:'signInForm').with {  
				email = userId
				password = passWord
				$('input', id:'accountSignIn').click()
			}
		}
	}	
}
