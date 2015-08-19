package com.huifu.mina.filter;

import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class JustCloseWhileIdle extends IoFilterAdapter {

	private int sessionTimeOutTimeSec = 0;
	
	private IdleStatus interestedIdleStatus = IdleStatus.BOTH_IDLE;

	public int getSessionTimeOutTimeSec() {
		return sessionTimeOutTimeSec;
	}

	public void setSessionTimeOutTimeSec(int sessionTimeOutTimeSec) {
		this.sessionTimeOutTimeSec = sessionTimeOutTimeSec;
	}

	public IdleStatus getInterestedIdleStatus() {
		return interestedIdleStatus;
	}

	public void setInterestedIdleStatus(IdleStatus interestedIdleStatus) {
		this.interestedIdleStatus = interestedIdleStatus;
	}
	
	@Override
	public void sessionCreated(NextFilter nextFilter, IoSession session)
			throws Exception {
		if (sessionTimeOutTimeSec > 0)
		{
			session.getConfig().setIdleTime(interestedIdleStatus, sessionTimeOutTimeSec);
		}
		super.sessionCreated(nextFilter, session);
	}

	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		session.close(true);
	}

	
	@Override
	public void sessionIdle(NextFilter nextFilter, IoSession session,
			IdleStatus status) throws Exception {
		nextFilter.sessionIdle(session, status);
		session.close(true);
	}
}
