package com.qst.dms.service;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.qst.dms.db.DBUtil;
import com.qst.dms.entity.*;

public class TransportService {
    // 物流数据采集
    public Transport inputTransport() {
        Transport trans = null;

        // 建立一个从键盘接收数据的扫描器
        Scanner scanner = new Scanner(System.in);
        try {
            // 提示用户输入ID标识
            System.out.println("请输入ID标识：");
            // 接收键盘输入的整数
            int id = scanner.nextInt();
            // 获取当前系统时间
            Date nowDate = new Date();
            // 提示用户输入地址
            System.out.println("请输入地址：");
            // 接收键盘输入的字符串信息
            String address = scanner.next();
            // 数据状态是“采集”
            int type = DataBase.GATHER;

            // 提示用户输入登录用户名
            System.out.println("请输入货物经手人：");
            // 接收键盘输入的字符串信息
            String handler = scanner.next();
            // 提示用户输入主机IP
            System.out.println("请输入 收货人:");
            // 接收键盘输入的字符串信息
            String reciver = scanner.next();
            // 提示用于输入物流状态
            System.out.println("请输入物流状态：1发货中，2送货中，3已签收");
            // 接收物流状态
            int transportType = scanner.nextInt();
            // 创建物流信息对象
            trans = new Transport(id, nowDate, address, type, handler, reciver,
                    transportType);
        } catch (Exception e) {
            System.out.println("采集的日志信息不合法");
        }
        // 返回物流对象
        return trans;
    }

    // 物流信息输出
    public void showTransport(Transport... transports) {
        for (Transport e : transports) {
            if (e != null) {
                System.out.println(e.toString());
            }
        }
    }

    // 匹配的物流信息输出，可变参数
    public void showMatchTransport(MatchedTransport... matchTrans) {
        for (MatchedTransport e : matchTrans) {
            if (e != null) {
                System.out.println(e.toString());
            }
        }
    }

    // 匹配的物流信息输出，参数是集合
    public void showMatchTransport(ArrayList<MatchedTransport> matchTrans) {
        for (MatchedTransport e : matchTrans) {
            if (e != null) {
                System.out.println(e.toString());
            }
        }
    }

    // 匹配物流信息保存，参数是集合
    public void saveMatchedTransport(ArrayList<MatchedTransport> matchTrans) throws IOException {
        // 创建一个ObjectOutputStream对象输出流，并连接文件输出流
        // 以可追加的方式创建文件输出流，数据保存到MatchedTransports.txt文件中
        /*try (ObjectOutputStream obs = new ObjectOutputStream(
                new FileOutputStream("MatchedTransports.txt", true))) {
            // 循环保存对象数据
            for (MatchedTransport e : matchTrans) {
                if (e != null) {
                    // 把对象写入到文件中
                    obs.writeObject(e);
                    obs.flush();
                }
            }
            // 文件末尾保存一个null对象，代表文件结束
            obs.writeObject(null);
            obs.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/

        ObjectOutputStream aoos = null;
        File file = new File("MatchedTransports.txt");
        boolean isexist = false;//定义一个用来判断文件是否需要截掉头aced 0005的
        if (file.exists()) {    //文件是否存在
            isexist = true;
            FileOutputStream fo = new FileOutputStream(file, true);
            aoos = new ObjectOutputStream(fo);
            long pos = 0;
            if (isexist) {
                pos = fo.getChannel().position() - 4;//追加的时候去掉头部aced 0005
                fo.getChannel().truncate(pos);
            }
            for (MatchedTransport e : matchTrans) {
                if (e != null) {
                    //把对象写入到文件
                    aoos.writeObject(e);
                    aoos.flush();
                }
            }
        } else {//文件不存在
            file.createNewFile();
            FileOutputStream fo = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fo);
            for (MatchedTransport e : matchTrans) {
                if (e != null) {
                    //把对象写入到文件
                    aoos.writeObject(e);
                    aoos.flush();
                }
            }
        }
        matchTrans.clear();
    }

    public void saveAndAppandMatchedTransport(ArrayList<MatchedTransport> matchTrans) {
        AppendObjectOutputStream aoos = null;
        File file = new File("MatchedTransports.txt");
        try{
            AppendObjectOutputStream.file = file;
            aoos = new AppendObjectOutputStream(file);
            //循环保存对象数据
            for (MatchedTransport e : matchTrans) {
                if(e != null) {
                    //把对象写入到文件中
                    aoos.writeObject(e);
                    aoos.flush();
                }
            }
        }catch (Exception ex){
        }finally {
            if(aoos != null){
                try{
                    aoos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    // 读匹配物流信息保存，参数是集合
    public ArrayList<MatchedTransport> readMatchedTransport() {
        ArrayList<MatchedTransport> matchTrans = new ArrayList<>();
        // 创建一个ObjectInputStream对象输入流，并连接文件输入流，读MatchedTransports.txt文件中
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("MatchedTransports.txt"))) {
            MatchedTransport matchTran;
           /* // 循环读文件中的对象
            while ((matchTran = (MatchedTransport) ois.readObject()) != null) {
                // 将对象添加到泛型集合中
                matchTrans.add(matchTran);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
            while (true) {
                try {
                    //将对象添加到泛型集合中
                    matchTran = (MatchedTransport) ois.readObject();
                    matchTrans.add(matchTran);
                } catch (EOFException ex) {
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchTrans;
    }

    public void saveMatchTransportToDB(ArrayList<MatchedTransport> matchTrans){
        DBUtil db = new DBUtil();
        try{
            //获取数据库链接
            db.getConnection();
            for (MatchedTransport matchedTransport : matchTrans){
                //获取匹配的发送物流
                Transport send = matchedTransport.getSend();
                //获取匹配的运输物流
                Transport trans = matchedTransport.getTrans();
                //获取匹配的接收物流
                Transport receive = matchedTransport.getReceive();
                //保存匹配记录中的发送状态
                String sql = "INSERT INTO gather_transport(time,address,type,handler,reciver,transporttype) VALUES(?,?,?,?,?,?)";
                Object[] param = new Object[]{
                        new Timestamp(send.getTime().getTime()),
                        send.getAddress(),send.getType(),send.getHandler(),
                        send.getReciver(),send.getTransportType()};
                int sendkey = db.executeSQLAndReturnPrimaryKey(sql,param);
                send.setId(sendkey);
                //保存匹配记录中的运输状态
                param = new Object[] {
                        new Timestamp(trans.getTime().getTime()),
                        trans.getAddress(),trans.getType(),
                        trans.getHandler(),trans.getReciver(),
                        trans.getTransportType()};
                int transkey = db.executeSQLAndReturnPrimaryKey(sql,param);
                trans.setId(transkey);
                //保存匹配记录的接受状态
                param = new Object[] {
                        new Timestamp(receive.getTime().getTime()),
                        receive.getAddress(),receive.getType(),
                        receive.getHandler(),receive.getReciver(),
                        receive.getTransportType()};
                int receivekey = db.executeSQLAndReturnPrimaryKey(sql,param);
                receive.setId(receivekey);
                //保持匹配的日志的ID
                sql = "INSERT INTO matched_transport(sendid,transid,receiveid) VALUES(?,?,?)";
                param = new Object[] {send.getId(),trans.getId(),receive.getId() };
                db.executeUpdate(sql,param);
                //清空集合中物流的数据
                //matchTrans.clear();
            }
            //关闭数据库链接，释放资源
            db.closeAll();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MatchedTransport> readMatchedTransportFromDB(){
        ArrayList<MatchedTransport> matchedTransports = new ArrayList<MatchedTransport>();
        DBUtil db = new DBUtil();
        try{
            //获取数据库链接
            db.getConnection();
            //查询匹配的物流
            String sql = "SELECT s.ID,s.TIME,s.ADDRESS,s.TYPE,s.HANDLER,s.RECIVER,s.TRANSPORTTYPE,"
                    + "t.ID,t.TIME,t.ADDRESS,t.TYPE,t.HANDLER,t.RECIVER,t.TRANSPORTTYPE,"
                    + "r.ID,r.TIME,r.ADDRESS,r.TYPE,r.HANDLER,r.RECIVER,r.TRANSPORTTYPE "
                    + "FROM MATCHED_TRANSPORT m,GATHER_TRANSPORT s,GATHER_TRANSPORT t,GATHER_TRANSPORT r "
                    + "WHERE m.SENDID=s.ID AND m.TRANSID=t.ID AND m.RECEIVEID=r.ID";
            ResultSet rs = db.executeQuery(sql,null);
            while(rs.next()){
                //获取发送记录
                Transport send = new Transport(rs.getInt(1),rs.getDate(2),
                        rs.getString(3),rs.getInt(4),rs.getString(5),
                        rs.getString(6),rs.getInt(7));
                //获取运输记录
                Transport trans = new Transport(rs.getInt(8),rs.getDate(9),
                        rs.getString(10),rs.getInt(11),rs.getString(12),
                        rs.getString(13),rs.getInt(14));
                //获取接受记录
                Transport receive = new Transport(rs.getInt(15),
                        rs.getDate(16),rs.getString(17),rs.getInt(18),
                        rs.getString(19),rs.getString(20),rs.getInt(21));
                //添加匹配的登录信息到匹配集合
                MatchedTransport matchedTrans = new MatchedTransport(send,trans,receive);
                matchedTransports.add(matchedTrans);
            }
            //关闭数据库链接，释放资源
            db.closeAll();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return matchedTransports;
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
