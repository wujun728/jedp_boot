package cn.itcast.jpa;

@NewHelloWord
public class TestBean {

	public TestBean(String name) {
		this.name = name;
	}

	@NewHelloWord("ÄãºÃ")
	private String name;

	@Override
	@NewHelloWord
	public String toString() {
		System.out.println(this.name);
		return this.name;
	}
}
