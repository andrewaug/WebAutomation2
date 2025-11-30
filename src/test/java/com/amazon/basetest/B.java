package com.amazon.basetest;

public class B extends  A{

//    public B() {
//        System.out.println("Inside B's Constructor");
//    }

    public B(String arg) {
        //super(arg);
        System.out.println("Inside B's Constructor with parameter: " + arg);
    }
}
