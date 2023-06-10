package com.shayartzi.jdeserialize2.util;

public class FormatUtil {
	
	private static String linesep = null;
	
	public static String hex(long value) {
        return "0x" + hexnoprefix(value);
    }
	
	public static String hexnoprefix(long value) {
        return hexnoprefix(value, 2);
    }
	
    public static String hexnoprefix(long value, int len) {
        if(value < 0) {
            value = 256 + value;
        }
        String s = Long.toString(value, 16);
        while(s.length() < len) {
            s = "0" + s;
        }
        return s;
    }
    
    public static String getLineSeperator() {
    	if (linesep == null) {
    		linesep = System.getProperty("line.separator");
    	}
    	return linesep;    	
    }

}
