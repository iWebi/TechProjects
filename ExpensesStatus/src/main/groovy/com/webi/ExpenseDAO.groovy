package com.webi

import org.hibernate.Query;
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.cfg.AnnotationConfiguration

/**
 * Created by IntelliJ IDEA.
 * User: suman
 * Date: 1/9/14.
 * Time: 10:24 AM
 */
class ExpenseDAO {
    private static SessionFactory sessionFactory
    def hibProps = [
            'hibernate.dialect': 'org.hibernate.dialect.H2Dialect',
            'hibernate.connection.driver_class': 'org.h2.Driver',
            'hibernate.connection.url': 'jdbc:h2:~/expenses',
            'hibernate.connection.username': 'sa',
            'hibernate.connection.password': '',
            'hibernate.connection.pool_size': '1',
            'hibernate.connection.autocommit': 'true',
            'hibernate.cache.provider_class': 'org.hibernate.cache.NoCacheProvider',
            'hibernate.hbm2ddl.auto': 'update',
            'hibernate.show_sql': 'true',
            'hibernate.transaction.factory_class': 'org.hibernate.transaction.JDBCTransactionFactory',
            'hibernate.current_session_context_class': 'thread'
    ]

    def configureHibernate(props) {
        def config = new AnnotationConfiguration()
        props.each { k, v -> config.setProperty(k, v) }
        config.addAnnotatedClass(ExpenseDetails)
        return config
    }

    Session session() {
        sessionFactory? sessionFactory.currentSession :
                ( sessionFactory = configureHibernate(hibProps).buildSessionFactory()).currentSession
    }

    def save(ExpenseDetails expenseDetails) {
        def session = session()
        def tx = session.beginTransaction()
        session.save(expenseDetails)
        tx.commit()
    }
	
	ExpenseDetails findLatestRecord() {
		def session = session()
		def tx = session.beginTransaction()
		Query expenseRecord = session.createQuery('from ExpenseDetails order by date desc fetch 1 row').setMaxResults(1)
		ExpenseDetails data = expenseRecord.list().get(0)
		tx.commit()
		data
	}

    List<ExpenseDetails> findAll() {
        def session = session()
        def tx = session.beginTransaction()
        def expenses = session.createQuery('from ExpenseDetails').list()
        tx.commit()
        return expenses
    }
}