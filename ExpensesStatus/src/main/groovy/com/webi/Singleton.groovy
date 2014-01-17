package com.webi
import groovy.transform.PackageScope;



//Perhaps the ugliest singleton implementation...but works for now.
class Singleton {
	ExpenseDetails expenseDetails = new ExpenseDetails()
	static Singleton inst= new Singleton()
	ConfigObject config
	private Singleton() {}
	
	static public Singleton instance() {
		inst
	}
	
	static public ConfigObject config( ) {
		String configFile = System.getenv('EXPENSES_PROD_CONFIG')+'\\\\ProdConfig.groovy'
		inst.config?: (inst.config= new ConfigSlurper().parse(new File(configFile).toURI().toURL()))
	}
	
	static ExpenseDetails expenseDetails() {
		inst.expenseDetails
	}
}
