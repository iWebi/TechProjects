package com.webi
import geb.Page
import static Singleton.*

class BOAHomePage extends Page {
	static url = "https://secure.bankofamerica.com/login/sign-in/signOnScreen.go"
	static at = { waitFor{ title == /Bank of America | Online Banking | Sign In | Online ID/ } }

	static content = {
		login { 
			def form = $('form', name: 'enter-online-id-form').with {
				onlineId = config().boa.id
				$('a', name: 'enter-online-id-submit').click(BOASiteKeyChallengePage)
				
				def verifyQuestion = $('label',text:config().boa.verifyQuestion)
				if ( verifyQuestion?.displayed ) {
					$('input', id:'tlpvt-challenge-answer') << config().boa.verifyAnswer
					$('a', name:'enter-online-id-submit').click(BOASiteKeyChallengePage)
				}
				$('input', name:'password') << config().boa.password
				$('a', name:'confirm-sitekey-submit').click()
 			}
			return true
		}
	}
}