package com.webi
import geb.Page

class BOAHomePage extends Page {
	static url = "https://secure.bankofamerica.com/login/sign-in/signOnScreen.go"
	static at = { waitFor{ title == /Bank of America | Online Banking | Sign In | Online ID/ } }

	static content = {
		login { String inputId ->
			def form = $('form', name: 'enter-online-id-form').with {
				onlineId = inputId
				$('a', name: 'enter-online-id-submit').click()
				
				def verifyQuestion = $('label',text:Singleton.config().boa.verifyQuestion)
				if ( verifyQuestion?.displayed ) {
					$('input', id:'tlpvt-challenge-answer') << Singleton.config().boa.verifyAnswer
					$('a', name:'enter-online-id-submit').click()
				}
				return true
			}
		}
	}
}