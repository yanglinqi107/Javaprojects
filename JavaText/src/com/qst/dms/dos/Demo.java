package com.qst.dms.dos;

public class Demo extends Thread{
    public void run(){
        for(int i=0;i<60;i++){
            System.out.println(this.getName()+":"+i);
        }
    }
}
