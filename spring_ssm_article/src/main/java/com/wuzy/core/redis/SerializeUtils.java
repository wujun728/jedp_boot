package com.wuzy.core.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializeUtils {
	
	private static Logger logger=LoggerFactory.getLogger(SerializeUtils.class);
	/**
	 * 反序列化
	 * @param bytes
	 * @return
	 */
	public static Object deserialize(byte[] bytes){
		Object result=null;
		if(isEmpty(bytes)){
			return null;
		}
		
		try {
			ByteArrayInputStream bytesStream = new ByteArrayInputStream(bytes);
			try {
				ObjectInputStream objectInputStream = new ObjectInputStream(bytesStream);
				try {
					result = objectInputStream.readObject();
				} catch (ClassNotFoundException e) {
					throw new Exception("Failed to deserialize object type:SerializeUtils未找到类》",e);
				}
			} catch (Throwable e) {
				throw new Exception("Failed to 反序列化:",e);
			}
		} catch (Exception e) {
			logger.error("Failed to 反序列化  > ",e);
		}
		return result;
		
	}
	
	
	public static boolean isEmpty(byte[] data){
		return (data ==null|| data.length==0);
	}
	/**
	 * 序列化
	 * @param object
	 * @return
	 */
	public static byte[] serialize (Object object){
		byte[] result =null;
		if(object ==null){
			return new byte[0];
		}
		
		try {
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream(128);
			try {
				if(!(object instanceof Serializable)){
					throw new IllegalArgumentException(SerializeUtils.class.getSimpleName() + " requires a Serializable payload :必须序列化类型》" +
							"but received an object of type [" + object.getClass().getName() + "]");
				}
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
				objectOutputStream.writeObject(object);
				objectOutputStream.flush();
				result =  byteStream.toByteArray();
			} catch (Throwable e) {
				throw new Exception("Failed to serialize>", e );
			}
		} catch (Exception e) {
			logger.error("Failed to serialize",e);
		}
		
		return result;
		
	}
	

}
