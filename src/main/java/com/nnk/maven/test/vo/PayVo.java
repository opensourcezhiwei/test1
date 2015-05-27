package com.nnk.maven.test.vo;


public class PayVo {

	private String payUrl;
	private String transName;
	private String plain;
	private String signature;

	public PayVo() {
		super();
	}

	public PayVo(String payUrl, String transName, String plain, String signature) {
		super();
		this.payUrl = payUrl;
		this.transName = transName;
		this.plain = plain;
		this.signature = signature;
	}

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public String getTransName() {
		return transName;
	}

	public void setTransName(String transName) {
		this.transName = transName;
	}

	public String getPlain() {
		return plain;
	}

	public void setPlain(String plain) {
		this.plain = plain;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
