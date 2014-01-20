package com.webi
import geb.Page
import java.text.NumberFormat

import com.webi.Singleton;

import static Singleton.*


class TargetHomePage extends Page {
	static url = 'https://rcam.target.com/default.aspx'
	static at = { title == 'Log In' }
	static content = {
		login() {
			$('input', id:'Login_UserName') << config().target.id
			$('input', id:'Login_Password') << config().target.password
			$('input', id:'Login_btnSignIn_btnSignIn').click()
			driver.switchTo().activeElement()
			//Security Question launched??
			if ( $('p', text: 'Please answer the following security question and choose one of the options below:')) {
				def ssnField = $('input', id:'inputSSN')
				if ( ssnField?.displayed ) {
					ssnField << Singleton.config().target.ssnAns
					$('input', id:'btnModalSubmit_btnModalSubmit').click()
				}
				else {
					def inputPhone = $('input', id:'inputPhone')
					if ( inputPhone?.displayed) {
						inputPhone << Singleton.config().target.phoneAns
						$('input', id:'btnModalSubmit_btnModalSubmit').click()
					}
					else {
						def inputZip = $('input', id:'inputZip')
						if ( inputZip?.displayed) {							
							inputZip << Singleton.config().target.zipAns
							$('input', id:'btnModalSubmit_btnModalSubmit').click()
						}
					}
				}
 			}
			return true
		}
	}
}