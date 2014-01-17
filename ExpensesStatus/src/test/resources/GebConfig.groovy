/*
    This is the Geb configuration file.
   
    See: http://www.gebish.org/manual/current/configuration.html
*/
import java.util.logging.Level;

import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.phantomjs.PhantomJSDriverService
import org.openqa.selenium.remote.DesiredCapabilities


waiting {
    timeout = 2
}

environments {

    firefox {
        driver = { def driver  = new FirefoxDriver()
			driver.manage().window().maximize()
			driver
		}
    }

    phantom {
        driver = {
            DesiredCapabilities capabilities = new DesiredCapabilities()
            //Set user-agent to Chrome or lots of things don't like phantomJS :)
            capabilities.setCapability("phantomjs.page.settings.userAgent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36")
            capabilities.setCapability('takesScreenshot', true)
            /*
            //        These flags turn off all SSL errors - uncomment for this ability
            */

            capabilities.setCapability("acceptSslCerts", true);
            capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, ["--web-security=false", "--ssl-protocol=any", "--ignore-ssl-errors=true"] as String[]);
            PhantomJSDriver driver = new PhantomJSDriver(capabilities)
			driver.manage().window().maximize()
//			driver.logLevel = Level.INFO
			return driver
        }
    }
}