package com.github.lipinskipawel.second;
import com.github.lipinskipawel.first.First;

public class Second {
	public String getInfo() {
		return "second";
	}


	public static void main(String[] args) {
		System.out.println(new Second().getInfo());
		System.out.println(new First().getInfo());
	}
}
