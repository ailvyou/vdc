package com.vdc.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

/**
 * 对象转化工具
 * 
 * @author kevin
 * 
 */
public class ObjectUtil {
	private static final Logger log = Logger.getLogger(ObjectUtil.class);

	public static void copyProperties(Object dest, Object orig) {
		if (orig == null) {
			return;
		}
		try {
			PropertyUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			log.error("copyProperties Exception :", e);
		}
	}

	public java.lang.Object ByteToObject(byte[] bytes) {
		java.lang.Object obj = null;
		try {
			// bytearray to object
			ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
			ObjectInputStream oi = new ObjectInputStream(bi);
			obj = oi.readObject();
			bi.close();
			oi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public byte[] ObjectToByte(java.lang.Object obj) {
		byte[] bytes = null;
		try {
			// object to bytearray
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);

			bytes = bo.toByteArray();

			bo.close();
			oo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (bytes);
	}
}
