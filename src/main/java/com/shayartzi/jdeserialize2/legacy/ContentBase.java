package com.shayartzi.jdeserialize2.legacy;

/**
 * Provides a skeleton content implementation.
 */
public class ContentBase implements Content {
	
    public int handle;
    public boolean isExceptionObject;
    protected ContentTypeEnum type;
    
    public ContentBase(ContentTypeEnum type) {
        this.type = type;
    }
    
    public boolean isExceptionObject() {
        return isExceptionObject;
    }
    
    public void setIsExceptionObject(boolean value) {
        isExceptionObject = value;
    }
    
    public ContentTypeEnum getType() {
        return type;
    }
    
    public int getHandle() {
        return this.handle;
    }
    
    public void validate() throws ValidityException {
    	
    }
    
}

