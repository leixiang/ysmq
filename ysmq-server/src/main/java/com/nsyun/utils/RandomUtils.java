package com.nsyun.utils;

import java.util.Random;

public class RandomUtils {
	
	/**
	 * 
	 * @Title: RandomUtils
	 * @Description: 随机生成6位数字或字母
	 * @param: @param length
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String getStringRandom(int length) {  
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }
	
	/**
	 * 
	 * @Title: RandomUtils
	 * @Description: 生成六位随机数
	 * @param: @param length
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String getRandomNumber (int length) {
		String str = "";
		Random random = new Random();  
		for (int i = 0; i < length; i++) {
			str += String.valueOf(random.nextInt(10));  
		}
		return str;
	}
}
