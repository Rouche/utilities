package org.kitfox.derived;

public class VerifyDerived {

    public static class A {
        public String write() {
            return this.getClass().getCanonicalName();
        }
    }
    
    public static class Derived extends A {
    }
    
    public static void main(final String[] args) throws Exception {
        A a = new Derived();
        System.out.println(a.write());
    }    
}
