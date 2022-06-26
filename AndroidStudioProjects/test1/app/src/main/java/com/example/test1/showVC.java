package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.sql.ResultSet;
import java.util.ArrayList;

public class showVC extends BaseActivity {

    ListView listView;
    ArrayList<MyListData> myDataList = new ArrayList<>();
    private Adapter adapter = new Adapter();
    private ResultSet resultSet;
    private Button continue_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_vc);
        listView = findViewById(R.id.myList);
        continue_btn = findViewById(R.id.continue_btn);

        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            String sql = "SELECT tNumber,tType,sNumber,tPrice,T.aId,a.aName " +
                    "FROM Ticket as T inner join Audience AS a on T.aId = a.aId WHERE a.aId = '"
                    + GlobalSetting.getInstance().getAid() + "'";
            Log.e("TAG", "onClick: 1" + GlobalSetting.getInstance().getAid());
            resultSet = startSql(sql);
            Log.e("TAG", "onClick: 2");
            if (resultSet != null) {
                Log.e("TAG", "onClick: 3");
                while (resultSet.next()) {
                    Log.e("TAG", "onClick: 4");
                    Log.e("TAG", "showVC: " + resultSet.getString(1));
                    Log.e("TAG", "showVC: " + resultSet.getString(2));
                    Log.e("TAG", "showVC: " + resultSet.getString(4));
                    Log.e("TAG", "showVC: " + resultSet.getString(6));
                    Log.e("TAG", "showVC: ticket成功");
                    MyListData myListData = new MyListData();
                    myListData.settNumber(resultSet.getString(1));
                    myListData.settType(resultSet.getString(2));
                    myListData.settPrice(resultSet.getString(4));
                    myListData.setaName(resultSet.getString(6));
                    myDataList.add(myListData);
                }
            } else {
                Log.e("TAG", "onClick: ticket失敗");
            }
        } catch (Exception e) {
            Log.e("TAG", "showVC: " + e.getMessage());
        }
        adapter.setDataList(myDataList);
        listView.setAdapter(adapter);
    }
}