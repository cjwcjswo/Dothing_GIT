package dothing.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dothing.web.android.chat.AndroidChatServer;

/**
 * 서버가 실행됬을 때 / 죽었을 때 리스너
 */
public class ContextListener implements ServletContextListener {
    
    public ContextListener() {
        // TODO Auto-generated constructor stub
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * 서버가 초기화 됬을 때
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	
    	//안드로이드 채팅서버 스타트
        Thread th = new Thread(new Runnable(){
			@Override
			public void run() {
				new AndroidChatServer();
			}
        	
        });
        th.start();
    	
    }
	
}
