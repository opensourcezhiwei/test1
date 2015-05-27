package com.nnk.maven.test.handler;

import nnk.msgsrv.server.Request;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.hrb.payment.client.SignatureService;

@Component
public class Handler {
	private Logger log = Logger.getLogger(Handler.class);

	public void sign(Request request) {
		String content = request.getContent();
		log.info("接收到的msgsrv内容" + content);
		String data[] = content.split(" ");
		if (data.length < 2) {
			request.response("400 内部服务器出错");
			log.info("400 内部服务器出错");
			return;
		}
		String plain = new String(Base64.decodeBase64(data[1]));
		String sign = SignatureService.sign(plain);
		if (sign == null) {
			request.response("400 签名错误");
			log.info("400 签名错误");
		} else {
			request.response("200 " + Base64.encodeBase64String(sign.getBytes()));
		}
	}

	public String sign(String content) {
		log.info("接收到的msgsrv内容" + content);
		String sign = SignatureService.sign(content);
		return new String(sign.getBytes());
	}

	public void verify(Request request) {
		String content = request.getContent();
		log.info("接收到的msgsrv内容" + content);
		String data[] = content.split(" ");
		if (data.length < 2) {
			request.response("400 内部服务器出错");
			log.info("400 内部服务器出错");
			return;
		}

		String plain = new String(Base64.decodeBase64(data[2]));
		String sign = new String(Base64.decodeBase64(data[3]));
		boolean result = SignatureService.verify(plain, sign);
		if (result) {
			request.response("200 " + sign);
		} else {
			request.response("400 验签错误");
			log.info("400 签名错误");
		}
	}
}
