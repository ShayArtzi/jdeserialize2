package com.shayartzi.jdeserialize2.helper;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

import com.shayartzi.jdeserialize2.example.obj.FlatObject;

public class TestSerializationHelper {
	
	public static void write(Object obj, Path file) throws IOException {		
		try (FileOutputStream fos = new FileOutputStream(file.toFile());
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(obj);
			oos.flush();			
		}
	}
	
	public static byte[] serialize(Object obj) throws IOException {		
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos)) {
			oos.writeObject(obj);
			oos.flush();			
			return bos.toByteArray();
		}
	}
	
	public static void main(String[] args) {
		FlatObject flatObject = new FlatObject();
		flatObject.setIntField(1);
		flatObject.setIntegerField(2);
		flatObject.setStrField("bar");
		try {
			write(flatObject, Path.of("flatObj.bin"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
