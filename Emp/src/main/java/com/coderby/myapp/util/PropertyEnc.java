package com.coderby.myapp.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class PropertyEnc {

	public static void writeProperties() {
		
	}
	
	public static void main(String[] args) {
		StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
		enc.setPassword("seunghee");
		System.out.println(enc.encrypt("jdbc:log4jdbc:oracle:thin:@localhost:1521/xepdb1"));
		System.out.println(enc.encrypt("hr"));
		System.out.println(enc.encrypt("hr"));
	}
}
