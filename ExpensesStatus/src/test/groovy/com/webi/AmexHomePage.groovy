package com.webi
import geb.Page

class AmexHomePage extends Page {
	static url = "https://www.americanexpress.com/"
	static at = { waitFor{ title ==~ /American Express Credit Cards, Rewards, Travel and Business Services/ } }

	static content = {
		login { String inputId, String password ->
			$('form', name: 'ssoform').with {
				UserID = inputId
				Password = password
                $('a', text: 'Log In').click()
			}
		}
	}
}