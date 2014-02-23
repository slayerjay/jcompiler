
public class Test {
	
	public static void main(String[] args) {
		Float f = (float) 5.0;
		Object t = f;
		Integer a = Integer.parseInt(t.toString());
		System.out.println(a);
	}
}
