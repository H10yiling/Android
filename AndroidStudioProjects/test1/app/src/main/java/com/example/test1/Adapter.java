package com.example.test1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private ArrayList<MyListData> myListData = new ArrayList<>();
    @Override
    public int getCount() {
        return myListData.size();
    }

    @Override
    public Object getItem(int position) {
        return myListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setDataList(ArrayList<MyListData> dataList){
        myListData = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview,null);
        }

        TextView tNumber = convertView.findViewById(R.id.tNumber_txt);
        TextView tType = convertView.findViewById(R.id.tType_txt);
        TextView tPrice = convertView.findViewById(R.id.tPrice_txt);
        TextView aName = convertView.findViewById(R.id.aName_txt);

        tNumber.setText(myListData.get(position).gettNumber());
        tType.setText(myListData.get(position).gettType());
        tPrice.setText(myListData.get(position).gettPrice());
        aName.setText(myListData.get(position).getaName());

        return convertView;
    }
}
