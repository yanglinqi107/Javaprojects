package com.qst.dms.entity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.File;

public class AppendObjectOutputStream extends ObjectOutputStream {
    public static File file = null;

    public AppendObjectOutputStream(File file) throws IOException{
        super(new FileOutputStream(file,true));
    }

    public void writerStreamHeader() throws IOException{
        //判断文件是否存在
        if(file != null){
            //判断是否是空文件
            if(file.length() == 0){
                super.writeStreamHeader();
            }else {
                //System.out.println("文件夹不为空");  //当文件夹不为空
                //this.reset();
            }
        }else {
            //System.out.println("文件不存在");
            super.writeStreamHeader();
        }
    }
}
