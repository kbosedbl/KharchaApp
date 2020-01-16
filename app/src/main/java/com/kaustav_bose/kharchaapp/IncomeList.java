package com.kaustav_bose.kharchaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class IncomeList extends AppCompatActivity {
    ListView listView;
    ArrayList<Data_Model> arrayList2=new ArrayList<>();
    CustomAdapter customAdapter;
    Bundle bundle;
    DatabaseReference databaseReference;
    DataModel dataModel;
    Data_Model data_model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_list);
        listView=findViewById(R.id.income_list);
        bundle=getIntent().getExtras();
        databaseReference= FirebaseDatabase.getInstance().getReference("user_Data");
        float f[]=new float[8];
        arrayList2.add(new Data_Model("Salary",0));
        arrayList2.add(new Data_Model("Awards",0));
        arrayList2.add(new Data_Model("Grants",0));
        arrayList2.add(new Data_Model("Refunds",0));
        arrayList2.add(new Data_Model("Rentals",0));
        arrayList2.add(new Data_Model("Lottery",0));
        arrayList2.add(new Data_Model("Investment",0));
        arrayList2.add(new Data_Model("Others",0));
        customAdapter=new CustomAdapter(IncomeList.this,arrayList2);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(IncomeList.this, UserInput.class);
                Toast.makeText(IncomeList.this, arrayList2.get(position).getCategory(), Toast.LENGTH_SHORT).show();
                intent.putExtra("category", arrayList2.get(position).getCategory());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
    boolean isConnectionPossible(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}