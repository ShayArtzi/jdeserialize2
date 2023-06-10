package com.shayartzi.jdeserialize2.util;

import java.io.IOException;
import java.util.HashSet;

import com.shayartzi.jdeserialize2.legacy.ValidityException;
import com.shayartzi.jdeserialize2.legacy.FieldTypeEnum;

public class TypeUtil {
	
	public static final String[] keywords = new String[] {
	        "abstract", "continue", "for", "new", "switch", "assert", "default", "if",
	        "package", "synchronized", "boolean", "do", "goto", "private", "this",
	        "break", "double", "implements", "protected", "throw", "byte", "else",
	        "import", "public", "throws", "case", "enum", "instanceof", "return",
	        "transient", "catch", "extends", "int", "short", "try", "char", "final",
	        "interface", "static", "void", "class", "finally", "long", "strictfp",
	        "volatile", "const", "float", "native", "super", "while" }; 
	
	public static HashSet<String> keywordSet;
	
	static {
        keywordSet = new HashSet<String>();
        for(String kw: keywords) {
            keywordSet.add(kw);
        }
    }
	
	public static String resolveJavaType(FieldTypeEnum type, String classname, boolean convertSlashes, boolean fixname)  throws IOException {
        if(type == FieldTypeEnum.ARRAY) {
            StringBuffer asb = new StringBuffer("");
            for(int i = 0; i < classname.length(); i++) {
                char ch = classname.charAt(i);
                switch(ch) {
                    case '[':
                        asb.append("[]");
                        continue;
                    case 'L':
                        String cn = decodeClassName(classname.substring(i), convertSlashes);
                        if(fixname) {
                            cn = fixClassName(cn);
                        }
                        return cn + asb.toString();
                    default:
                        if(ch < 1 || ch > 127) {
                            throw new ValidityException("invalid array field type descriptor character: " + classname);
                        }
                        FieldTypeEnum ft = FieldTypeEnum.get((byte)ch);
                        if(i != (classname.length()-1)) {
                            throw new ValidityException("array field type descriptor is too long: " + classname);
                        }
                        String ftn = ft.getJavaType();
                        if(fixname) {
                            ftn = fixClassName(ftn);
                        }
                        return ftn + asb.toString();
                }
            }
            throw new ValidityException("array field type descriptor is too short: " + classname);
        } else if(type == FieldTypeEnum.OBJECT) {
            return decodeClassName(classname, convertSlashes);
        } else {
            return type.getJavaType();
        }
    }
	
	/**
     * Decodes a class name according to the field-descriptor format in the jvm spec,
     * section 4.3.2.
     * @param fdesc name in field-descriptor format (Lfoo/bar/baz;)
     * @param convertSlashes true iff slashes should be replaced with periods (true for
     * "real" field-descriptor format; false for names in classdesc)
     * @return a fully-qualified class name
     * @throws ValidityException if the name isn't valid
     */
    public static String decodeClassName(String fdesc, boolean convertSlashes) throws ValidityException {
        if(fdesc.charAt(0) != 'L' || fdesc.charAt(fdesc.length()-1) != ';' || fdesc.length() < 3) {
            throw new ValidityException("invalid name (not in field-descriptor format): " + fdesc);
        }
        String subs = fdesc.substring(1, fdesc.length()-1);
        if(convertSlashes) {
            return subs.replace('/', '.');
        } 
        return subs;
    }
    
    /**
     * "Fix" the given name by transforming illegal characters, such that the end result
     * is a legal Java identifier that is not a keyword.  
     * If the string is modified at all, the result will be prepended with "$__".
     *
     * @param name the name to be transformed
     * @return the unmodified string if it is legal, otherwise a legal-identifier version
     */
    public static String fixClassName(String name) {
        if(name == null) {
            return "$__null";
        }
        if(keywordSet.contains(name)) {
            return "$__" + name;
        }
        StringBuffer sb = new StringBuffer();
        int cplen = name.codePointCount(0, name.length());
        if(cplen < 1) {
            return "$__zerolen";
        }
        boolean modified = false;
        int scp = name.codePointAt(0);
        if(!Character.isJavaIdentifierStart(scp)) {
            modified = true;
            if(!Character.isJavaIdentifierPart(scp) || Character.isIdentifierIgnorable(scp)) {
                sb.append("x");
            } else {
                sb.appendCodePoint(scp);
            }
        } else {
            sb.appendCodePoint(scp);
        }

        for(int i = 1; i < cplen; i++) {
            int cp = name.codePointAt(i);
            if(!Character.isJavaIdentifierPart(cp) || Character.isIdentifierIgnorable(cp)) {
                modified = true;
                sb.append("x");
            } else {
                sb.appendCodePoint(cp);
            }
        }
        if(modified) {
            return "$__" + sb.toString();
        } else {
            return name;
        }
    }

}
