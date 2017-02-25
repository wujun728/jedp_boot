package flyweight;

public interface Flyweight {
	/**
	 * 示例操作，传入外部状态
	 * @param extrinsicState 示例参数，外部状态 
	 */
	void operation(String extrinsicState);
}
