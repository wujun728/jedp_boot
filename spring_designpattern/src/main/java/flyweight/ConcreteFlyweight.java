package flyweight;

public class ConcreteFlyweight implements Flyweight {
	
	/**
	 * ʾ�������������ڲ�״̬
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
