package com.webi
import java.text.NumberFormat;

import java.text.NumberFormat;

import geb.Page
import static Singleton.*

class BOAAccountOverviewPage extends Page {
	static at = { waitFor{ title == /Bank of America | Online Banking | Accounts Overview/ } }

	static content = {
		balance {
			def creditBalance = $('span.TL_NPI_L1').last().text()
			println 'creditBalance is '+creditBalance
			expenseDetails().boa = (NumberFormat.getCurrencyInstance().parse(creditBalance) as Double)
			return true
		}
		
		logout {
			$('a', text:'Sign Off').click()
			return true
		}
	}
}