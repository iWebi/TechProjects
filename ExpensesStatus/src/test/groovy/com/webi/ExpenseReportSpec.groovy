package com.webi
import org.openqa.selenium.firefox.FirefoxDriver;

import com.webi.Email;
import com.webi.ExpenseDAO
import com.webi.ExpenseDetails;
import com.webi.ProdConfig
import geb.spock.GebReportingSpec
import geb.spock.GebSpec
import spock.lang.Shared
import static Singleton.*

/**
 * Created by IntelliJ IDEA.
 * User: suman
 * Date: 1/9/14.
 * Time: 8:28 AM
 */
class ExpenseReportSpec extends GebReportingSpec {
	
    def cleanupSpec() {
        //shoot the email at the end of the test run
		ExpenseDetails expenseDetails = expenseDetails()
		def expenseDao = new ExpenseDAO()
		//persist and send email only if the current data is different from the 
		//latest data in DB. Else no point in cluttering mail box
		if ( !expenseDetails.equals(expenseDao.findLatestRecord() )) {
			expenseDao.save(expenseDetails)
			Email.sendStatusEmail(expenseDetails)
		}
    }
	
	def cleanup() {
		if ( driver instanceof FirefoxDriver ) {
			sleep 5*1000
		}
	}

    def "get bank of america data"() {
        when:
        to BOAHomePage
        login(config().boa.id )

        then:
        at BOASiteKeyChallengePage
		
		when:
		signIn (config().boa.password)
		
		then:
		at BOAAccountOverviewPage
		balance
		logout
    }
	
	def "get Amex data"() {	
        when:
        to AmexHomePage
        login(config().amex.id, config().amex.password)

        then:
        at AmexAccountPage
		balance()
		
		when:
		logout
		
		then:
		waitFor(2) {
			title == 'American Express Login'
        }
    }
	
	def "get Macy's data"() {
		when:
		to MacysSignInPage
		login(config().macys.id, config().macys.password)

		then:
		at MacysCreditAccountSummaryPage
		balance()
		logout()
	}
	
	def "get Target's data"() {
		when:
		to TargetHomePage
		login(config().target.id, config().target.password)

		then:
		at TargetAccountSummaryPage
		balance()
		logout()
	}
}