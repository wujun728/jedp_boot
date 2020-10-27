package com.youmeek.ssm.model;

/**
 * AJAX返回结果消息
 */
import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = -2l;

	/**
	 * 类型
	 */
	enum Type {
		/*成功*/
		success,
		/*错误*/
		error,
		/*警告*/
		warn
	}


	/**
	 * 初始化一个新创建的 Message 对象，使其表示一个空消息。
	 */
	public Message() {

	}

	/**
	 * 结果状态
	 */
	private Type type;

	/**
	 * 提示信息
	 */
	private String message;

	/**
	 * 返回对象
	 */
	private Object obj;

	/**
	 * 初始化一个新创建的 Message 对象
	 *
	 * @param type
	 *            类型
	 * @param message
	 *            内容
	 */
	public Message(Type type, String message) {
		this.type = type;
		this.message = message;
	}

	public Message(Type type, String message,Object obj) {
		this.type = type;
		this.message = message;
		this.obj = obj;
	}

	public static Message success(String message) {
		return new Message(Type.success,message);
	}

	public static Message success(String message, Object obj) {
		return new Message(Type.success,message,obj);
	}

	public static Message error(String message) {
		return new Message(Type.error,message);
	}

	public static Message error(String message, Object obj) {
		return new Message(Type.error,message,obj);
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
