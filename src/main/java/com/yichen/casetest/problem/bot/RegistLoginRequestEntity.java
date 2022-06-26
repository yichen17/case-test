package com.yichen.casetest.problem.bot;


/**
 * 登录、注册短信验证
 */
public class RegistLoginRequestEntity {

	private String mobile;

	private String pwd;
	private String t;
	private String ticket;

	private String type;
	private String verifyCode;

	private String certSign;

	private String encryptedInfo;

	public String getEncryptedInfo() {
		return encryptedInfo;
	}

	public void setEncryptedInfo(String encryptedInfo) {
		this.encryptedInfo = encryptedInfo;
	}

	public String getCertSign() {
		return certSign;
	}

	public void setCertSign(String certSign) {
		this.certSign = certSign;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		return "RegistLoginRequestEntity [type=" + type + ", verifyCode=" + verifyCode + "]";
	}

}
