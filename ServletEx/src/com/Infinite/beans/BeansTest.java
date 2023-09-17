package com.Infinite.beans;

import java.util.Date;

public class BeansTest {
	public static void main(String[] args)
	{
		User nithin=new User();
		nithin.setUsername("NITHIN");
		nithin.setPassword("nithin123");
		nithin.setLogindate(new Date());
		nithin.display();
		System.out.println(nithin.getUsername());
	}

}
