package com.kaustav_bose.kharchaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<Data_Model> arrayList=new ArrayList<>();
    public CustomAdapter(Context context, ArrayList<Data_Model> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        TextView textView;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;
        if(view==null){
            view=inflater.inflate(R.layout.single_view,null);
            viewHolder=new ViewHolder();
            viewHolder.textView=view.findViewById(R.id.category);
            view.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(arrayList.get(position).getCategory());
        return view;
    }
}
