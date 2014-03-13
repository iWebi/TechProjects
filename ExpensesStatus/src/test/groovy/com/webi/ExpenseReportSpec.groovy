package com.webi
import org.openqa.selenium.firefox.FirefoxDriver;

import com.webi.Email;
import com.webi.ExpenseDAO
import com.webi.ExpenseDetails;
import com.webi.ProdConfig
import geb.spock.GebReportingSpec
import geb.spock.GebSpec
import spock.lang.Shared
import spock.lang.Unroll;
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
		println 'expenseDetails is ' +expenseDetails
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

	@Unroll
	def "get #institute data"() {
		when:
		to startPage
		login
		
		then:
		at accountPage
		
		when:
		balance
		logout
		
		then:
		noExceptionThrown()
		
		where:
		institute 			| 	startPage	| 	accountPage	
		'Bank of America'	        |  	BOAHomePage	|	BOAAccountPage	
		'Amex'				|	AmexHomePage	|	AmexAccountPage
		'Target'			|	TargetHomePage	|	TargetAccountPage	
		'Macys'				|	MacysHomePage	|	MacysAccountPage 
	}
}
