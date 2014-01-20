package com.webi
import geb.Page
import static Singleton.*

class AmexHomePage extends Page {
	static url = "https://www.americanexpress.com/"
	static at = { waitFor{ title ==~ /American Express Credit Cards, Rewards, Travel and Business Services/ } }

	static content = {
		login {
			$('form', name: 'ssoform').with {
				UserID = config().amex.id
				Password = config().amex.password
                $('a', text: 'Log In').click()
			}
		}
	}
}