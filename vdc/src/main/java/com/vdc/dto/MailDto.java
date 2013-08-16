package com.vdc.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 邮件dto
 * @author daniel
 */
@XmlRootElement(name="MailDto")
public class MailDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 641996587236478146L;

	/**
	 * 发送给谁
	 */
	private String mailTo;
	
	/**
	 * 邮件主题
	 */
	private String mailSubject;
	
	/**
	 * 邮件内容
	 */
	private String mailContent;

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	
	
}
