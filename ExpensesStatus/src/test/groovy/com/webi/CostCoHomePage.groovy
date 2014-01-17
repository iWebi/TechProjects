package com.webi
import geb.Page

class CostCoHomePage extends Page {
	static url = "https://costco.com"
	static at = { waitFor{ title ==~ /Welcome to Costco Wholesale/ } }

	static content = {
		launchLogin {
			$('a', text:'Sign In or Register').click()
			title == 'Sign In'
		}

		login { String inputId, String password ->
			$('form', name: 'LogonForm').with {
				logonId = inputId
				logonPassword = password
//                $('button', text: 'Sign In').click()
			}
		}
	}
}