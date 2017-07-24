package dothing.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dothing.web.android.chat.AndroidChatServer;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
public class ContextListener implements ServletContextListener {
    /**
     * Default constructor. 
     */
    public ContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	//채팅서버 스타트
        Thread th = new Thread(new Runnable(){

			@Override
			public void run() {
				new AndroidChatServer();
				
			}
        	
        });
        th.start();
    	
    }
	
}
