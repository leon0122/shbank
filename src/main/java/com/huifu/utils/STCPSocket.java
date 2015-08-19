package com.huifu.utils;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class STCPSocket extends Socket
{

	private InputStream in;
	private OutputStream out;

	public STCPSocket()
	{
		in = null;
		out = null;
	}

	public void connectKeep(String ip, int port)
		throws IOException
	{
		InetSocketAddress sa = new InetSocketAddress(ip, port);
		super.connect(sa);
		in = super.getInputStream();
		out = super.getOutputStream();
	}

	public void connect(String ip, int port, int timeout)
		throws IOException
	{
		InetSocketAddress sa = new InetSocketAddress(ip, port);
		super.setSoTimeout(timeout * 1000);
		super.connect(sa, timeout * 1000);
		in = super.getInputStream();
		out = super.getOutputStream();
	}

	public int read(byte data[])
		throws IOException
	{
		int len = data.length;
		int realLen = 0;
		int readLen = 0;
		for (; realLen < len; realLen += readLen)
		{
			readLen = in.read(data, realLen, len - realLen);
			if (readLen == -1)
				throw new IOException("end of stream");
		}

		return len;
	}

	public int readBuffer(byte data[])
		throws IOException
	{
		return in.read(data);
	}

	public void write(byte data[])
		throws IOException
	{
		out.write(data);
	}
	
	
	public static void main(String[] args) throws Exception {
		STCPSocket sc= STCPSocketFactory.getSTCPSocketFactory().getSTCPSocket();
		sc.connect("127.0.0.1", 9995, 60);
		sc.write("123".getBytes());
	}
}
