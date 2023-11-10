package com.github.lipinskipawel.second;
import com.github.lipinskipawel.first.First;

public class Second {
	public String getInfo() {
		return "second " + getClass().getModule();
	}


	public static void main(String[] args) {
		System.out.println("Code is running");
		System.out.println(new Second().getInfo());
		System.out.println(new First().getInfo());
	}
}
