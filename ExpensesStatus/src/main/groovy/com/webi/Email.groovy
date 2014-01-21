package com.webi

import groovy.text.GStringTemplateEngine
import org.apache.commons.io.IOUtils

import javax.activation.DataHandler
import javax.activation.DataSource
import javax.activation.FileDataSource
import javax.mail.BodyPart
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart
import javax.mail.util.ByteArrayDataSource

/**
 * Created by IntelliJ IDEA.
 * User: suman
 * Date: 1/9/14.
 * Time: 3:08 PM
 */
class Email {
    static void sendStatusEmail(ExpenseDetails expenseDetails) {
		GStringTemplateEngine engine = new GStringTemplateEngine()
		Map bindings = expenseDetails.properties as Map
		bindings.put('total', expenseDetails.total())
        def template = engine.createTemplate(new File('C:\\Users\\Webi\\workspace\\ExpensesStatus\\src\\main\\groovy\\com\\webi\\SuccessStatusReport.html')).make(bindings)
        sendMail(template.toString())
    }
	
// Testing purpose
    public static void main(String[] args) {
		GStringTemplateEngine engine = new GStringTemplateEngine()
		def itemizedExpensesByWeek = [], totalExpensesByWeek = []
		Random random = new Random()
		10.times { number ->
			ExpenseItem expenseItem = new ExpenseItem().with {
				itemType = number as String
				itemDescription = itemType + ' description'
				cost = random.nextFloat() *100
				return it
			}
			itemizedExpensesByWeek << new Time2TimeItemCompare().with {
				item = number as String
				currentTimeCost = random.nextFloat() *100
				previousTimeCost = random.nextFloat() *100
				return it
			}
			totalExpensesByWeek << new Time2TimeItemCompare().with {
				item = number as String
				currentTimeCost = random.nextFloat() *100*5
				previousTimeCost = random.nextFloat() *100*3
				return it
			}
			ExpenseUtil.addToExpensesByItemType(expenseItem)
		}

		Map bindings = ['expensesByItemTypes':ExpenseUtil.expensesByItemType,
				'itemizedExpensesByWeek':itemizedExpensesByWeek,
				'totalExpensesByWeek':totalExpensesByWeek,
				'yearlyExpensesByItemTypes':ExpenseUtil.expensesByItemType,
				'annualTotalExpeneses': '$2,000']
		def template = engine.createTemplate(new File('C:\\Users\\Webi\\workspace\\ExpensesStatus\\src\\main\\groovy\\com\\webi\\SuccessStatusReport.template')).make(bindings)
		new File('C:\\Users\\Webi\\workspace\\ExpensesStatus\\src\\main\\groovy\\com\\webi\\SuccessStatusReport_Generated.html').text = template.toString()

    }

    private static void sendMail(String bodyText) {
		ConfigObject config = Singleton.config()
		Properties props  = ['mail.smtp.host': 'smtp.gmail.com',
			'mail.smtp.socketFactory.port': '465',
			'mail.smtp.socketFactory.class': 'javax.net.ssl.SSLSocketFactory',
			'mail.smtp.auth': 'true',
			'mail.smtp.port': '465' ] as Properties

        Session mailSession = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(config.email.from.id, config.email.from.password);
                    }
                });

        MimeMultipart multipart = new MimeMultipart("related");
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(bodyText, "text/html");
        multipart.addBodyPart(messageBodyPart);
        try {
            DataSource fds = new FileDataSource(new File('C:\\Photos\\DesktopBackground\\1487830_703288283028808_1023524919_o.jpg'));
            messageBodyPart = new MimeBodyPart().with {
                dataHandler = new DataHandler(fds)
                disposition = MimeBodyPart.INLINE
                fileName= 'Vachi'
                setHeader("Content-ID", "<vachi>")
                it
            }
            multipart.addBodyPart(messageBodyPart);
            def message = new MimeMessage(mailSession).with {
                from = new InternetAddress(config.email.from.id)
                subject = "Expense Status Report "+new Date()
                setRecipients(Message.RecipientType.TO, config.email.to.ids.join(','))
                text = bodyText
                content = multipart
                it
            }
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}