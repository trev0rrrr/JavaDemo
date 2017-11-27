package trev0r.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
    private Object proxied;

    private DynamicProxy(){}
    public DynamicProxy( Object proxied )
    {
        this.proxied = proxied;
    }

    public Object invoke(Object proxy, Method method, Object[] args ) throws Throwable
    {
        System.out.println("前置增强");
        Object toReturn=method.invoke( proxied, args);
        System.out.println(toReturn);
        System.out.println("后置增强");
        return toReturn;
    }
    public static void main(String args[]) throws Throwable {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz=loader.loadClass("trev0r.reflect.MyDomain");
        Constructor constructor =clazz.getDeclaredConstructor((Class[]) null);
        MyDomain myDomain=(MyDomain) constructor.newInstance();
        Constructor constructor2 =clazz.getDeclaredConstructor(int.class);
        constructor2.setAccessible(true);
        System.out.println(constructor2);
        //MyDomain myDomain2=(MyDomain) constructor2.newInstance(1);
        Field field1=clazz.getDeclaredField("Field1");
        Method method=clazz.getMethod("setField1", String.class);
        method.invoke(myDomain, "trev0r");
        Method method2=clazz.getMethod("setField2", String.class);
        method2.invoke(myDomain, "huanggengquan");

        Method method3=clazz.getMethod("toString", (Class[])null);
        new DynamicProxy(myDomain).invoke(myDomain,method3,(Class[])null);

    }
}
