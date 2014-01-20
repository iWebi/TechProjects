package com.webi
import geb.Page
import java.text.NumberFormat
import static Singleton.*


class TargetAccountPage extends Page {
	static at = { waitFor { title == 'View Account Summary' } }
	static content = {
		balance() {
			def balance = $('span', id:'AcctSummaryRCAM_AcctTbl_CrntBal').text()
			expenseDetails().target = (NumberFormat.getCurrencyInstance().parse(balance) as Double)
			return true
		}
		
		logout() {
			$('input', id:'LeftSideNav_NavSignout1_NavSignout1').click()
			assert title == 'Sign Out'
			return true
		}
	}
}