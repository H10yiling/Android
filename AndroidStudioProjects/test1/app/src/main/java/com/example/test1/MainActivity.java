package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends BaseActivity {

    Button login_btn;
    TextView result_txt;
    EditText name_edt, phone_edt;
    private ResultSet resultSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_btn = findViewById(R.id.login_btn);
        result_txt = findViewById(R.id.result_txt);

        name_edt = findViewById(R.id.name_edt);
        phone_edt = findViewById(R.id.phone_edt);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String sql = "SELECT * FROM Audience Where aName = '" + name_edt.getText().toString() + "'";
                    resultSet = startSql(sql);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            Log.e("TAG", "onResume: " + resultSet.getString(1));
                            Log.e("TAG", "onResume: " + resultSet.getString(2));
                            Log.e("TAG", "onResume: " + resultSet.getString(3));
                            Log.e("TAG", "onResume: " + resultSet.getString(4));
                            Log.e("TAG", "onResume: 成功");
                            if (resultSet.getString(4).equals(phone_edt.getText().toString())) {
                                GlobalSetting.getInstance().setAid(resultSet.getString(1));
                                Intent intent = new Intent(MainActivity.this, buyVC.class);
                                startActivity(intent);

                            }
                        }
                    } else {
                        Log.e("TAG", "onClick: 登入失敗");
                    }
                } catch (Exception e) {
                    Log.e("TAG", "onResume: " + e.getMessage());
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


}