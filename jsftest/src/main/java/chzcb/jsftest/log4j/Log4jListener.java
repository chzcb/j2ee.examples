package chzcb.jsftest.log4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

public class Log4jListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent event) {
	}

	public void contextInitialized(ServletContextEvent event) {
		String path = event.getServletContext().getRealPath("WEB-INF/classes/log4j.properties");    
		PropertyConfigurator.configure(path);  
	}

}
