package com.shayartzi.jdeserialize2.util;

public class LogUtil {
	
	private static boolean debugEnabled;
	
	public static void debugerr(String message) {
        System.err.println(message);
    }
    
    public static void debug(String message) {
        if(debugEnabled) {
            System.out.println(message);
        }
    }
    
    public static void setDebugEnabled(boolean value) {
    	debugEnabled = value;
    }

}
