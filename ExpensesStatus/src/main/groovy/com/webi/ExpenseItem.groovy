package com.webi

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
class ExpenseItem {
	@Id
	Date purchaseDate = new Date()
	String itemType
	String itemDescription
	float cost
}
