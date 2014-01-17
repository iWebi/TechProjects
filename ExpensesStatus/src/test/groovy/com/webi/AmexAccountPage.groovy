package com.webi
import geb.Page
import static Singleton.* 

class AmexAccountPage extends Page {

	static at = { waitFor{ title ==~ /My American Express Account Summary/ } }

	static content = {
		balance { 
			def balanceAmt = ($('#outstandingAmtHeader').text() as Double) +
			(($('#outstandingAmtDecimal').text() as Double)/100)
			expenseDetails().costCo = balanceAmt;
		}
		
		logout() {
			$('input', value:'Log Out')?.click();
			return true
		}
	}
}
