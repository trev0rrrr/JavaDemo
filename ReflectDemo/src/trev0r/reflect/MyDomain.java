package trev0r.reflect;

public class MyDomain {
	
	
	
	public String Field1;
	private String Field2;
	
	public MyDomain(){
		System.out.println("public 默认无参构造函数");
	}
	@SuppressWarnings("unused")
	private MyDomain(int i){
		System.out.println("private 带参构造函数 --"+i);
	}
	
	public String toString(){
		return "Car: [ Field1 : "+getField1()+";Field2 : "+getField2()+" ]";
	}
	public String toString(int i){
		return "Car: [ Field1 : "+getField1()+";Field2 : "+getField2()+" ]";
	}
	public String getField1() {
		return Field1;
	}
	public void setField1(String field1) {
		Field1 = field1;
	}
	public String getField2() {
		return Field2;
	}
	public void setField2(String field2) {
		Field2 = field2;
	}
	
	
	
}
