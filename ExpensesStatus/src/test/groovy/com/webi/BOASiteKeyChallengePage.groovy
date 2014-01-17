package com.webi
import geb.Page

/**
 * Created by IntelliJ IDEA.
 * User: suman
 * Date: 1/9/14.
 * Time: 9:02 AM
 */
class BOASiteKeyChallengePage extends Page {
	static at = {
		waitFor {
			title == /Bank of America | Online Banking | SiteKey | Confirm SiteKey/
			$('div.sitekey-title').text() == 'mountain'
		}
	}

	static content = {
		signIn() {
			String password->
				$('input', name:'password') << password
				$('a', name:'confirm-sitekey-submit').click()
		}
	}
}
