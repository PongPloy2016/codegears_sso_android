package com.tow.sso.codegears_sso_android.model;

public class InputItem {

	public String key;
	public String value;
	
	public InputItem(String strKey, String strValue) {
		this.key = strKey;
		if (strValue != null) {
			this.value = strValue;
		} else {
			this.value = "";
		}
	}
	
}
