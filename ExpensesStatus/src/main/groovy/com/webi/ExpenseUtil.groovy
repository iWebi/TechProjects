package com.webi

/**
 * Created by IntelliJ IDEA.
 * User: suman
 * Date: 1/20/14.
 * Time: 9:10 AM
 */
class ExpenseUtil {
    static Map<String, ExpenseItem> expensesByItemType = [:]

    static void addToExpensesByItemType(ExpenseItem expenseItem) {
        def entry = expensesByItemType.get(expenseItem.itemType)
        entry? entry.cost =+ expenseItem.cost : ( expensesByItemType[expenseItem.itemType] = expenseItem )
    }
}