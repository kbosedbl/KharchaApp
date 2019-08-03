package com.kaustav_bose.kharchaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter2 extends BaseAdapter {

    Context context;
    ArrayList<DataModel> arrayList = new ArrayList<>();

    public CustomAdapter2(Context context, ArrayList<DataModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        TextView date,category_type,value;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;
        if(view==null){
            view=inflater.inflate(R.layout.singlefile2,null);
            viewHolder=new ViewHolder();
            viewHolder.date=view.findViewById(R.id.date);
            viewHolder.category_type=view.findViewById(R.id.category_type);
            viewHolder.value=view.findViewById(R.id.value);
            view.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.date.setText(arrayList.get(position).getDate());
        viewHolder.category_type.setText(arrayList.get(position).getCategory());
        viewHolder.value.setText("Amount :- "+String.valueOf(arrayList.get(position).getValue()));
        return view;
    }
}
