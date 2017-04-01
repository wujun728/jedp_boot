package flyweight;

public class ConcreteFlyweight implements Flyweight {
	
	/**
	 * 示例参数，描述内部状态
	 */
	private String intrinsicState;
	
	public ConcreteFlyweight(String state) {
		this.intrinsicState = state;
	}

	@Override
	public void operation(String extrinsicState) {
		// TODO Auto-generated method stub

	}

}
