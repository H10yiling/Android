package com.example.test1;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseActivity extends AppCompatActivity {
    private Connection connection;
    public ResultSet startSql(String sql){
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionclass();
            if(connection !=null){
//                String sql = "SELECT * FROM Audience";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                Log.e("TAG", "startSql: 有值" );
                return rs;
            }else {
                Log.e("TAG", "startSql: null" );
                return null;
            }
        }catch (Exception e){
            Log.e("TAG", "startSql: error"+e.getMessage());
            return null;
        }
    }
    public int startUpdateSql(String sql){
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionclass();
            if(connection !=null){
//                String sql = "SELECT * FROM Audience";
                Statement statement = connection.createStatement();
                int result = statement.executeUpdate(sql);
                Log.e("TAG", "startUpdateSql: 新增" );
                return result;
            }else {
                Log.e("TAG", "startUpdateSql: 新增null" );
                return -1;
            }
        }catch (Exception e){
            Log.e("TAG", "startUpdateSql: error"+e.getMessage());
            return -1;
        }
    }
}
