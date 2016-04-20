package com.tow.sso.codegears_sso_android.http;

public class HttpRS {

	public int response_code;
	public String response_text;
	
	public HttpRS() {
		
	}
	
	public HttpRS(int code, String text) {
		this.response_code = code;
		this.response_text = text;
	}
	
	public String toString() {
		return "{" + this.response_code + "}:" + this.response_text;
	}
	
}
