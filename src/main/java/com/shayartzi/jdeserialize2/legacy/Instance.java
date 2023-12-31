package com.shayartzi.jdeserialize2.legacy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.shayartzi.jdeserialize2.util.FormatUtil;

/**
 * Represents an instance of a non-enum, non-Class, non-ObjectStreamClass, 
 * non-array class, including the non-transient field values, for all classes in its
 * hierarchy and inner classes.
 */
public class Instance extends ContentBase {
	
    /**
     * Collection of field data, organized by class description.  
     */
    public Map<ClassDesc, Map<Field, Object>> fielddata;

    /**
     * Class description for this instance.
     */
    public ClassDesc classdesc;

    /**
     * Constructor.
     */
    public Instance() {
        super(ContentTypeEnum.INSTANCE);
        this.fielddata = new LinkedHashMap<ClassDesc, Map<Field, Object>>();
    }
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(classdesc.name).append(' ').append("_h").append(FormatUtil.hex(handle))
            .append(" = r_").append(FormatUtil.hex(classdesc.handle)).append(";  ");
        //sb.append("// [instance " + jdeserialize.hex(handle) + ": " + jdeserialize.hex(classdesc.handle) + "/" + classdesc.name).append("]");
        return sb.toString();
    }
    /**
     * Object annotation data.
     */
    public Map<ClassDesc, List<Content>> annotations;
    
}
