package com.aqm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;

public class Test1 {
	
	
	public int getuniqueApplicationNo() {
        String SALTCHARS = "0123456789";
      StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
       while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        int unique = Integer.parseInt(saltStr);
       return unique;

   }
	 
	 
	

	public static void main(String[] args)
	{
		
		Test1 obj = new Test1();
		int num = obj.getuniqueApplicationNo();
		
		System.out.println(num);
		System.out.println(num);
		
	
	}}