package com.huifu.mina.code;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineDecoder;
import org.apache.mina.filter.codec.textline.TextLineEncoder;

public class CodeFactory implements ProtocolCodecFactory {

	private final TextLineEncoder encoder;
	private final TextLineDecoder decoder;

	final static char endchar = 0x0d;

	public CodeFactory() {
		this(Charset.forName("gb2312"));
	}

	public CodeFactory(Charset charset) {
		encoder = new TextLineEncoder(charset, LineDelimiter.UNIX);
		decoder = new TextLineDecoder(charset, LineDelimiter.AUTO);
	}

	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

	public int getEncoderMaxLineLength() {
		return encoder.getMaxLineLength();
	}

	public void setEncoderMaxLineLength(int maxLineLength) {
		encoder.setMaxLineLength(maxLineLength);
	}

	public int getDecoderMaxLineLength() {
		return decoder.getMaxLineLength();
	}

	public void setDecoderMaxLineLength(int maxLineLength) {
		decoder.setMaxLineLength(maxLineLength);
	}

}