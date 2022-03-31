package com.qst.dms.net;

import com.qst.dms.db.DBUtil;
import com.qst.dms.entity.MatchedLogRec;
import com.qst.dms.entity.MatchedTransport;
import com.qst.dms.service.LogRecService;
import com.qst.dms.service.TransportService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//服务器应用程序，接收客服端发送来的数据保存到数据库中
public class DmsNetServer {
    public DmsNetServer(){
        //通过使用不同的端口区分接收不同数据：6666端口是日志，6668端口是物流
        //开启监听6666端口的线程，接收日志数据
        new AcceptLogThread(6666).start();
        //开启监听6668端口的线程，接收物流数据
        new AcceptTranThread(6668).start();
        //
        //new DeliveryLogThread(6666).start();
        //
        //new DeliveryTranThread(6668).start();
        System.out.println("网络服务器已开启...");
    }

    //接收日志数据的线程类
    private class AcceptLogThread extends Thread{
        private ServerSocket serverSocket;
        private Socket socket;
        private LogRecService logRecService;
        private ObjectInputStream ois;

        public AcceptLogThread(int port){
            logRecService = new LogRecService();
            try{
                serverSocket = new ServerSocket(port);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        //重写run()方法，将日志数据保存到数据库中
        @Override
        public void run(){
            while(this.isAlive()){
                try{
                    //接收客服端发送过来的套接字
                    socket = serverSocket.accept();
                    if(socket != null){
                        ois = new ObjectInputStream(socket.getInputStream());
                        //反序列化，得到匹配日志列表
                        ArrayList<MatchedLogRec> matchedLogs = (ArrayList<MatchedLogRec>) ois.readObject();
                        //将客户端发送来的匹配日志信息保存到数据库
                        logRecService.saveMatchLogToDB(matchedLogs);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            try{
                ois.close();
                socket.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    //接收物流数据的线程类
    private class AcceptTranThread extends Thread{
        private ServerSocket serverSocket;
        private Socket socket;
        private TransportService transService;
        private ObjectInputStream ois;

        public AcceptTranThread(int port){
            transService = new TransportService();
            try{
                serverSocket = new ServerSocket(port);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        //重写run()方法，将物流数据保存到数据库中
        @Override
        public void run(){
            while(this.isAlive()){
                try{
                    //接收客服端发送过来的套接字
                    socket = serverSocket.accept();
                    if(socket != null){
                        ois = new ObjectInputStream(socket.getInputStream());
                        //反序列化，得到匹配日志列表
                        ArrayList<MatchedTransport> matchedTrans = (ArrayList<MatchedTransport>) ois.readObject();
                        //将客户端发送来的匹配日志信息保存到数据库
                        transService.saveMatchTransportToDB(matchedTrans);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            try{
                ois.close();
                socket.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        //获取数据库中的所有匹配的物流信息，返回一个ResultSet
        public ResultSet readTransResult() {
            DBUtil db = new DBUtil();
            ResultSet rs=null;
            try {
                // 获取数据库链接
                Connection conn=db.getConnection();
                // // 查询匹配物流，设置ResultSet可以使用除了next()之外的方法操作结果集
                Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

                String sql = "SELECT * from gather_transport";
                rs = st.executeQuery(sql);
            }catch (Exception e) {
                e.printStackTrace();
            }
            return rs;
        }
    }

    //主程序
    public static void main(String[] args){
        new DmsNetServer();
    }
}
