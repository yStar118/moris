package com.util.redis;

import java.io.*;

public class SerializeUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public static byte[] serialize(Object object) {
		ObjectOutputStream oos;
		ByteArrayOutputStream baos;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			return baos.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
