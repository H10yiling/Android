package com.example.test1;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    ConnectionHelper con;
    String uname, pass, ip, port,database;
    @SuppressLint("NewApi")
    public Connection connectionclass(){

    //ip= "192.168.1.183"; //wifi
    ip= "172.20.10.6"; //YIIILN
    database="SqlFinalExam"; //DB的名字
    uname="sa";
    pass="Password.1";
    port="1433";

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);
    Connection connection= null;
    String ConnectionURL = null;

    try{
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        ConnectionURL = "jdbc:jtds:sqlserver://" + ip +":" + port+ ";" + "databasename=" + database +
        ";user=" +uname+ " ;password=" +pass+ ";";
        connection = DriverManager.getConnection(ConnectionURL);
        Log.e("tag", "connectionclass: 連線成功");
    }catch (Exception ex) {
        Log.e( "tag Error ", ex.getMessage());
    }

    return connection;
    }
}
