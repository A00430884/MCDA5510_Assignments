
public enum TestEnum {
	
	TEA(4),
	COFFEE(8);
	int value;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	private TestEnum(int i) {
		this.value = i;
	}
public static void main(String[] args) {
	TestEnum t = TestEnum.COFFEE;
	System.out.println(t.name()+""+t.value);
	t.setValue(9);
	System.out.println(t.value);
	
}
}
