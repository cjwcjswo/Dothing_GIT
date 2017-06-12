package dothing.web.service;

import org.springframework.stereotype.Service;

@Service
public class ChatService {
	
	public void write(String [] msg){
		System.out.println("wrtie!!!!");
		System.out.println("eno : " + msg[0]);
		System.out.println("sender : "+ msg[1]);
		System.out.println("msg : "+ msg[2]);
	}
	
	
}
