package trev0r.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;



public class ReflectDemo {

	public static MyDomain init() throws Exception{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		System.out.println(loader);
		Class clazz=loader.loadClass("trev0r.reflect.MyDomain");
		Constructor constructor =clazz.getDeclaredConstructor((Class[]) null);
		MyDomain myDomain=(MyDomain) constructor.newInstance();
		Constructor constructor2 =clazz.getDeclaredConstructor(int.class);
		constructor2.setAccessible(true);
		System.out.println(constructor2);
		MyDomain myDomain2=(MyDomain) constructor2.newInstance(1);
		Field field1=clazz.getDeclaredField("Field1");
		Method method=clazz.getMethod("setField1", String.class);
		method.invoke(myDomain, "trev0r");
		
		
		return myDomain;
	}
	public static void main(String arg[]) throws Exception{
		System.out.println(init());
	}
}
