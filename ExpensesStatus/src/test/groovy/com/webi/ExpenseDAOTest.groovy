package com.webi
import com.webi.ExpenseDAO
import com.webi.ExpenseDetails
import com.webi.Singleton;

import spock.lang.Specification

/**
 * Created by IntelliJ IDEA.
 * User: suman
 * Date: 1/9/14.
 * Time: 11:00 AM
 */
class ExpenseDAOTest extends Specification {
    def "test expense"() {
        given:
        def expenseDao = new ExpenseDAO()
//        expenseDao.save(new ExpenseDetails(boa: 532.2, costCo: 200))

        when:
        List<ExpenseDetails> expenses = expenseDao.findAll()

        then:
        assert expenses.size() > 0
    }
}