package dothing.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dothing.web.android.chat.AndroidChatServer;

/**
 * ������ �������� �� / �׾��� �� ������
 */
public class ContextListener implements ServletContextListener {
    
    public ContextListener() {
        // TODO Auto-generated constructor stub
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * ������ �ʱ�ȭ ���� ��
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	
    	//�ȵ���̵� ä�ü��� ��ŸƮ
        Thread th = new Thread(new Runnable(){
			@Override
			public void run() {
				new AndroidChatServer();
			}
        	
        });
        th.start();
    	
    }
	
}
