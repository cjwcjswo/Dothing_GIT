package dothing.web.socket.handler;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler arg2,
			Map<String, Object> arg3) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ºñÆ÷");
		ServletServerHttpRequest ssreq = (ServletServerHttpRequest) request;
		System.out.println("URI:" + request.getURI());
		ServletServerHttpResponse ssrep = (ServletServerHttpResponse)response;
		HttpServletResponse res = ssrep.getServletResponse();
		res.setContentType("text/event-steram");

		return super.beforeHandshake(request, response, arg2, arg3);
	}
	
	@Override
    public void afterHandshake(ServerHttpRequest request,
            ServerHttpResponse response, WebSocketHandler wsHandler,
            Exception ex) {
        System.out.println("After Handshake");
  
        super.afterHandshake(request, response, wsHandler, ex);
    }



}
