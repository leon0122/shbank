package com.huifu.utils;

import java.io.IOException;


public class STCPSocketFactory
{

	private static STCPSocketFactory factory = null;
	private int socketCurNum;
	private Integer socketCurNumLock;
	private int socketMaxNum;

	public STCPSocketFactory()
	{
		socketCurNum = 0;
		socketCurNumLock = new Integer(0);
		socketMaxNum = -1;
	}

	public STCPSocket getSTCPSocket()
		throws Exception
	{
		if (socketMaxNum != -1 && socketCurNum >= socketMaxNum)
			throw new Exception("socket num limit!");
		STCPSocket socket = new STCPSocket();
		synchronized (socketCurNumLock)
		{
			socketCurNum++;
		}
		return socket;
	}

	public static synchronized STCPSocketFactory getSTCPSocketFactory()
	{
		if (factory == null)
			factory = new STCPSocketFactory();
		return factory;
	}

	public void releaseSTCPSocket(STCPSocket socket)
	{
		try
		{
			socket.close();
		}
		catch (IOException ioexception) { }
		synchronized (socketCurNumLock)
		{
			socketCurNum--;
		}
	}

}
