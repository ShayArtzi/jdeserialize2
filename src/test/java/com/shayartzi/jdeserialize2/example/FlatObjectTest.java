package com.shayartzi.jdeserialize2.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.shayartzi.jdeserialize2.Jdeserialize2;
import com.shayartzi.jdeserialize2.example.obj.FlatObject;
import com.shayartzi.jdeserialize2.helper.TestSerializationHelper;
import com.shayartzi.jdeserialize2.legacy.GetOpt;

public class FlatObjectTest {
	
	@Test
	public void dump_givenFlatObject_whenCalled_createsCorrectDump() throws Exception {
		
		String expected = Files.readString(Paths.get(this.getClass().getResource("/output/FlatObjectExpected.txt").toURI()));		
		
		FlatObject flatObject = new FlatObject();
		flatObject.setIntField(1);
		flatObject.setIntegerField(2);
		flatObject.setStrField("bar");
		
		byte[] serializedBinary = TestSerializationHelper.serialize(flatObject);
		ByteArrayInputStream serializedBinaryInputStream = new ByteArrayInputStream(serializedBinary);
		
		GetOpt getOpt = new GetOpt();
		getOpt.parse(new String[]{""});
		
		StringWriter stringWriter = new StringWriter();
		
		Jdeserialize2 jd = new Jdeserialize2(null, stringWriter);
		jd.run(serializedBinaryInputStream, false);
		jd.dump(getOpt);
		
		// Validate
		assertEquals(expected, stringWriter.toString());		
	}

}
