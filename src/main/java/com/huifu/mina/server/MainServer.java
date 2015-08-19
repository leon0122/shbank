package com.huifu.mina.server;

import javax.annotation.Resource;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.huifu.mina.controller.impl.MainController;
public class MainServer extends IoHandlerAdapter {
	
	private boolean shortConnect;
	
	@Resource
	private MainController mainController;
	
	public boolean isShortConnect() {
		return shortConnect;
	}

	public void setShortConnect(boolean shortConnect) {
		this.shortConnect = shortConnect;
	}

	/**
	 * On idle, we just write a message on the console
	 */
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		if (shortConnect) 
			// 由于是短连接，就直接关闭，否则就不关，保持长连接状态
			session.close(true);
	}

	public void sessionCreated(IoSession session) throws Exception {
	}
	
	/**
	 * Trap exceptions.
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// client 关断时也会触发该异常
		// cause.printStackTrace();
		if (shortConnect) 
			// 由于是短连接，就直接关闭，否则就不关，保持长连接状态
			session.close(true);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		// 直接调用统一的业务接口
		String retMsg = mainController.processMessage(message.toString());
		System.out.println("我收到了消息:"+message.toString().trim());
		// 最后发回处理后的报文
		session.write(retMsg);

		if (shortConnect) {
			// 由于是短连接，就直接关闭，否则就不关，保持长连接状态
			session.close(true);
		}
	}
}