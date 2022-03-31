package com.qst.dms.service;
import java.io.*;
import java.net.InetAddress;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.File;

import com.qst.dms.db.DBUtil;
import com.qst.dms.entity.*;

//日志业务类
public class LogRecService {
    // 日志数据采
    public LogRec inputLog() {
        LogRec log = null;
        // 建立一个从键盘接收数据的扫描器
        Scanner scanner = new Scanner(System.in);
        try {
            //设置Boolean变量控制循环
            boolean judge;
            //ID标识
            int id = 0;

            //ID输入的交互
            do {
                judge = false;
                // 提示用户输入ID标识
                System.out.println("请输入ID标识：");
                // 接收键盘输入的整数
                if (scanner.hasNextInt()) {
                    id = scanner.nextInt();
                    if (id < 0) {
                        System.out.println("输入错误，请重输");
                        judge = true;
                    }
                } else {
                    System.out.println("输入错误，请重输");
                    judge = true;
                    scanner.next();
                }
            } while (judge);

            // 获取当前系统时间
            Date nowDate = new Date();

            // 提示用户输入地址的交互
            System.out.println("请输入地址：");
            // 接收键盘输入的字符串信息
            String address = scanner.next();

            // 数据状态是“采集”
            int type = DataBase.GATHER;

            // 提示用户输入登录用户名
            System.out.println("请输入登录用户名：");
            // 接收键盘输入的字符串信息
            String user = scanner.next();

            String ip= InetAddress.getLocalHost().getHostAddress().toString();
            System.out.println("登录IP：" + ip);
            // 提示用户输入主机IP
            /*System.out.println("请输入主机IP：");
            do {
                judge = false;
                //分别记录输入IP的数字和"."的个数，判断十进制IP的格式是否正确
                int digitnum = 0;
                int pointnum = 0;
                // 接收键盘输入的字符串信息
                ip = scanner.next();
                if (ip.length() > 15) {
                    System.out.println("ip过长，请重输（十进制）");
                    judge = true;
                } else {
                    for (int i = 0; i < ip.length(); ++i) {
                        if (Character.isDigit(ip.charAt(i))) {
                            ++digitnum;
                            if (digitnum > 3) {
                                System.out.println("输入错误，请重输（十进制）");
                                judge = true;
                                break;
                            }
                        } else if (ip.charAt(i) == '.') {
                            ++pointnum;
                            if ((digitnum < 1) || (pointnum > 3)) {
                                System.out.println("输入错误，请重输（十进制）");
                                judge = true;
                                break;
                            }
                            digitnum = 0;
                        } else {
                            System.out.println("输入错误，请重输（十进制）");
                            judge = true;
                            break;
                        }
                    }
                }
            } while (judge);*/

            //登录or登出
            int logType = 0;
            // 提示用户输入登录状态、登出状态
            do {
                judge = false;
                System.out.println("请输入登录状态:1是登录，0是登出");
                if (scanner.hasNextInt()) {
                    logType = scanner.nextInt();
                    if ((logType != 1) && (logType != 0)) {
                        System.out.println("输入错误，请重输");
                        judge = true;
                    }
                } else {
                    System.out.println("输入错误，请重输");
                    judge = true;
                    scanner.next();
                }
            } while (judge);

            // 创建日志对象
            log = new LogRec(id, nowDate, address, type, user, ip, logType);
        } catch (Exception e) {
            System.out.println("采集的日志信息不合法");
        }
        // 返回日志对象
        return log;
    }

    // 日志信息输出
    public void showLog(LogRec... logRecs) {
        for (LogRec e : logRecs) {
            if (e != null) {
                System.out.println(e.toString());
            }
        }
    }

    // 匹配日志信息输出，可变参数
    public void showMatchLog(MatchedLogRec... matchLogs) {
        for (MatchedLogRec e : matchLogs) {
            if (e != null) {
                System.out.println(e.toString());
            }
        }
    }

    // 匹配日志信息输出,参数是集合
    public void showMatchLog(ArrayList<MatchedLogRec> matchLogs) {
        for (MatchedLogRec e : matchLogs) {
            if (e != null) {
                System.out.println(e.toString());
            }
        }
    }

    public void saveMatchedLog(ArrayList<MatchedLogRec> matchLogs) throws IOException {
        //创建一个objectOutputStream对象输出，并连接文件输出流
        //以可追加的方式创建文件输出流，数据保存到MatchLogs.txt文件中
        /*try (ObjectOutputStream obs = new ObjectOutputStream(new FileOutputStream("MatchedLogs.txt",true))) {
            //循环保存数据
            for (MatchedLogRec e : matchLogs) {
                if (e != null) {
                    //把对象写入到文件
                    obs.writeObject(e);
                    obs.flush();
                }
            }
            //文件末尾保存一个null对象，代表文件结束
            obs.writeObject(null);
            obs.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
        ObjectOutputStream aoos = null;
        File file = new File("Matchedlogs.txt");
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
            for (MatchedLogRec e : matchLogs) {
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
            for (MatchedLogRec e : matchLogs) {
                if (e != null) {
                    //把对象写入到文件
                    aoos.writeObject(e);
                    aoos.flush();
                }
            }
        }
        //matchLogs.clear();
    }

    public void saveAndAppandMatchedLog(ArrayList<MatchedLogRec> matchlogs) {
        AppendObjectOutputStream aoos = null;
        File file = new File("MatchedLogs.txt");
        try{
            AppendObjectOutputStream.file = file;
            aoos = new AppendObjectOutputStream(file);
            //循环保存对象数据
            for (MatchedLogRec e : matchlogs) {
                if(e != null) {
                    //把对象写入到文件中
                    aoos.writeObject(e);
                    aoos.flush();
                }
            }
        }catch (Exception ex){
        }finally {
            if(aoos != null){
                try{ aoos.close(); }catch (IOException e){ e.printStackTrace(); }
            }
        }
    }

    public ArrayList<MatchedLogRec> readMatchedLog() {
        ArrayList<MatchedLogRec> matchLogs = new ArrayList<>();
        // 创建一个ObjectInputStream对象输入流，并连接文件输入流，读MatchedLogs.txt文件中
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("MatchedLogs.txt"))) {
            MatchedLogRec matchLog;
           /* //循环读文件中的对象
            while ((matchLog = (MatchedLogRec) ois.readObject()) != null) {
                //将对象添加到泛型集合中
                matchLogs.add(matchLog);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
            while (true) {
                try {
                    //将对象添加到泛型集合中
                    matchLog = (MatchedLogRec) ois.readObject();
                    matchLogs.add(matchLog);
                } catch (EOFException ex) {
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchLogs;
    }

    public void saveMatchLogToDB(ArrayList<MatchedLogRec> matchLogs){
        DBUtil db = new DBUtil();
        try {
            //获取数据库链接
            db.getConnection();
            for(MatchedLogRec matchedLogRec : matchLogs) {
                //获取匹配的登录日志
                LogRec login = matchedLogRec.getLogin();
                //获取匹配的登出日志
                LogRec logout = matchedLogRec.getLogout();

                //保存匹配记录中的登录日志
                String sql = "INSERT INTO gather_logrec(id,time,address,type,username,ip,logtype) VALUES(?,?,?,?,?,?,?)";
                Object[] param = new Object[] { login.getId(),
                        new Timestamp(login.getTime().getTime()),
                        login.getAddress(), login.getType(), login.getUser(),
                        login.getIp(), login.getLogType() };
                db.executeUpdate(sql, param);
                // 保存匹配记录中的登出日志
                param = new Object[] { logout.getId(),
                        new Timestamp(logout.getTime().getTime()),
                        logout.getAddress(), logout.getType(),
                        logout.getUser(), logout.getIp(), logout.getLogType() };
                db.executeUpdate(sql, param);
                // 保存匹配日志的ID
                sql = "INSERT INTO matched_logrec(loginid,logoutid) VALUES(?,?)";
                param = new Object[] { login.getId(), logout.getId() };
                db.executeUpdate(sql, param);

                //保存匹配记录中的登录日志
                /*String sql = "INSERT INTO gather_logrec(time,address,type,username,ip,logtype) VALUES(?,?,?,?,?,?)";
                Object[] parame = new Object[]{
                        new Timestamp(login.getTime().getTime()),
                        login.getAddress(), login.getType(), login.getUser(),
                        login.getIp(), login.getLogType()};
                int loginkey = db.executeSQLAndReturnPrimaryKey(sql,parame);
                login.setId(loginkey);
                //保存匹配记录中的登出日志
                parame = new Object[]{
                        new Timestamp(logout.getTime().getTime()),
                        logout.getAddress(), logout.getType(), logout.getUser(),
                        logout.getIp(), logout.getLogType()};
                int logoutkey = db.executeSQLAndReturnPrimaryKey(sql, parame);
                logout.setId(logoutkey);
                //保存匹配日志的ID
                sql = "INSERT INTO matched_logrec(loginid,logoutid) VALUES(?,?)";
                parame = new Object[]{login.getId(), logout.getId()};
                db.executeUpdate(sql,parame);*/
                //清空集合中日志数据
                //matchLogs.clear();
            }
            //关闭数据库链接，释放资源
            db.closeAll();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MatchedLogRec> readMatchedLogFromDB(){
        ArrayList<MatchedLogRec> matchedLogRecs = new ArrayList<MatchedLogRec>();
        DBUtil db = new DBUtil();
        try{
            //获取数据连接
            db.getConnection();
            //查询日志
            String sql = "SELECT i.id,i.time,i.address,i.type,i.username,i.ip,i.logtype,"
                    + "o.id,o.time,o.address,o.type,o.username,o.ip,o.logtype "
                    + "FROM matched_logrec m,gather_logrec i,gather_logrec o "
                    + "WHERE m.loginid=i.id AND m.logoutid=o.id";
            ResultSet rs = db.executeQuery(sql,null);
            while(rs.next()){
                //获取登录记录
                LogRec login = new LogRec(rs.getInt(1),rs.getDate(2),
                        rs.getString(3), rs.getInt(4),rs.getString(5),
                        rs.getString(6), rs.getInt(7));
                //获取登出记录
                LogRec logout = new LogRec(rs.getInt(8),rs.getDate(9),
                        rs.getString(10),rs.getInt(11),rs.getString(12),
                        rs.getString(13),rs.getInt(14));
                //添加匹配登录信息到匹配集合
                MatchedLogRec matchedLog = new MatchedLogRec(login,logout);
                matchedLogRecs.add(matchedLog);
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
    return matchedLogRecs;
    }

    //获取数据库中的所有匹配的日志信息，返回一个ResultSet
    public ResultSet readLogResult() {
        DBUtil db = new DBUtil();
        ResultSet rs=null;
        try {
            // 获取数据库链接
            Connection conn=db.getConnection();
            // 查询匹配日志，设置ResultSet可以使用除了next()之外的方法操作结果集
            Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String sql =
                    "select m.username,m.time,n.time,m.address "+
                            "from matched_logrec k,gather_logrec m,gather_logrec n "+
                            "where k.loginid=m.id and k.logoutid=n.id";
            rs = st.executeQuery(sql);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
