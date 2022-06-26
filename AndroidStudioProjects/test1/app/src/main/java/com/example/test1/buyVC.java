package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.sql.ResultSet;
import java.util.ArrayList;

public class buyVC extends BaseActivity {

    Spinner spinner, spinner2;
    Button buy_btn, skip_btn;
    TextView price_txt;
    ArrayList<String> sNumberList = new ArrayList<>();

    private String type = "VIP";
    private String sNumber = "S001";
    private String price;
    private ResultSet resultSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_vc);

        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        buy_btn = findViewById(R.id.buy_btn);
        skip_btn = findViewById(R.id.skip_btn);
        price_txt = findViewById(R.id.price_txt);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.VC_type, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    price_txt.setText("1000");
                    price = "1000";
                    type = "VIP";
                } else if (position == 1) {
                    price_txt.setText("500");
                    price = "500";
                    type = "general";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int number = getTicketNumber() + 1;
                    String sql = "INSERT INTO Ticket VALUES ('T00" + number + "','" + type + "','" + sNumber + "','" + price + "','" + GlobalSetting.getInstance().getAid() + "')";
                    Log.e("sjdk", "onClick: " + "INSERT INTO Ticket VALUES ('T00" + number + "','" + type + "','" + sNumber + "','" + price + "','" + GlobalSetting.getInstance().getAid() + "')");
                    startUpdateSql(sql);
                } catch (Exception e) {
                    Log.e("TAG", "buy_btn onClick: "+e.getMessage() );
                }
            }
        });

        skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(buyVC.this, showVC.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sNumberList.clear();
        try {
            String sql = "SELECT sNumber FROM Show";
            resultSet = startSql(sql);
            if (resultSet != null) {
                while (resultSet.next()) {
                    Log.e("TAG", "onResume: " + resultSet.getString(1));
//                    Log.e("TAG", "onResume: " + resultSet.getString(2));
//                    Log.e("TAG", "onResume: " + resultSet.getString(3));
//                    Log.e("TAG", "onResume: " + resultSet.getString(4));
                    Log.e("TAG", "onResume: sNumber成功");
                    sNumberList.add(resultSet.getString(1));
                }
            } else {
                Log.e("TAG", "onClick: ticket失敗");
            }
        } catch (Exception e) {
            Log.e("TAG", "onResume: " + e.getMessage());
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, sNumberList);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sNumber = sNumberList.get(position);
                //sql語法
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private int getTicketNumber() {
        try {
            int maxNumber = 0;
            String sql = "SELECT tNumber FROM Ticket";
            resultSet = startSql(sql);
            if (resultSet != null) {
                while (resultSet.next()) {
                    Log.e("TAG", "kkkkkkkk: " + resultSet.getString(1).split("")[3]);
//                    Log.e("TAG", "onResume: " + resultSet.getString(2));
//                    Log.e("TAG", "onResume: " + resultSet.getString(3));
//                    Log.e("TAG", "onResume: " + resultSet.getString(4));
                    Log.e("TAG", "onResume: sNumber成功");
                    if (maxNumber < Integer.parseInt(resultSet.getString(1).split("")[3])) {
                        maxNumber = Integer.parseInt(resultSet.getString(1).split("")[3]);
                    }
                }
                return maxNumber;
            } else {
                Log.e("TAG", "onClick: ticket失敗");
                return -1;
            }
        } catch (Exception e) {
            Log.e("TAG", "onResume: " + e.getMessage());
            return -1;
        }
    }
}