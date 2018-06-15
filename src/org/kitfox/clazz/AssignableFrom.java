package org.kitfox.clazz;

public class AssignableFrom {
    public static void main(String[] args)
    {
    	AssignableFrom nm = new AssignableFrom();
        nm.doit();
    }

    public void doit()
    {
        A myA = new A();
        B myB = new B();
        A[] aArr = new A[0];
        B[] bArr = new B[0];

        System.out.println("b instanceof a: " + (myB instanceof A));
        System.out.println("b isInstance a: " + B.class.isInstance(myA));
        System.out.println("a isInstance b: " + A.class.isInstance(myB));
        System.out.println("b isAssignableFrom a: " + B.class.isAssignableFrom(A.class));
        System.out.println("a isAssignableFrom b: " + A.class.isAssignableFrom(B.class));
        System.out.println("bArr isInstance A: " + A.class.isInstance(bArr));
        System.out.println("bArr isInstance aArr: " + aArr.getClass().isInstance(bArr));
        System.out.println("bArr isAssignableFrom aArr: " + aArr.getClass().isAssignableFrom(bArr.getClass()));
    }

    class A
    {
    }

    class B extends A
    {
    }
}
