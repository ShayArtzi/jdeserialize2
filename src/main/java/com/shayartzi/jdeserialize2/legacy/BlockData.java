package com.shayartzi.jdeserialize2.legacy;

import com.shayartzi.jdeserialize2.util.FormatUtil;

/**
 * Represents an opaque block of data written to the stream.  Primarily, these are used to
 * write class and object annotations by ObjectOutputStream overrides; they can also occur
 * inside an object, when the object overrides Serializable.writeObject().  Their
 * interpretation is hereby left to users.
 */
public class BlockData extends ContentBase {
	
    /**
     * The block data read from the stream.
     */
    public byte[] buf;

    /**
     * Constructor.
     *
     * @param buf the block data
     */
    public BlockData(byte[] buf) {
        super(ContentTypeEnum.BLOCKDATA);
        this.buf = buf;
    }
    public String toString() {
        return "[blockdata " + FormatUtil.hex(handle) + ": " + buf.length + " bytes]";
    }
    
}
