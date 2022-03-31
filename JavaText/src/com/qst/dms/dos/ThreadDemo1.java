package com.qst.dms.dos;

public class ThreadDemo1 {
    public static void main(String[] args){
        Demo d = new Demo();
        d.start();
        for(int i=0;i<60;i++){
            System.out.println(Thread.currentThread().getName()+i);
        }
    }
}
