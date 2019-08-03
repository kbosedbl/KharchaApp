package com.kaustav_bose.kharchaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExpenseList extends AppCompatActivity {
    ListView listView;
    DatabaseReference databaseReference;
    ArrayList<Data_Model> arrayList=new ArrayList<>();
    CustomAdapter customAdapter;
    DataModel dataModel;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);
        //databaseReference= FirebaseDatabase.getInstance().getReference("user_Data");
        databaseReference= FirebaseDatabase.getInstance().getReference("user_Data");
        //dataModel=new DataModel();
        listView=findViewById(R.id.expense_list);
        final String id= databaseReference.push().getKey();
        //dataModel.setId(id);
        //ataModel.setUser("abc");
        sharedPreferences=getSharedPreferences("user_data",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        //databaseReference.child(id).setValue(dataModel);
        //Bundle bundle=getIntent().getExtras();
        arrayList.add(new Data_Model("Food",0));
        arrayList.add(new Data_Model("Home",0));
        arrayList.add(new Data_Model("Clothes",0));
        arrayList.add(new Data_Model("Gifts",0));
        arrayList.add(new Data_Model("Travel",0));
        arrayList.add(new Data_Model("Tax",0));
        arrayList.add(new Data_Model("Shopping",0));
        arrayList.add(new Data_Model("Entertainment",0));
        arrayList.add(new Data_Model("Bills",0));
        arrayList.add(new Data_Model("Education",0));
        arrayList.add(new Data_Model("Health",0));
        arrayList.add(new Data_Model("Insurance",0));
        arrayList.add(new Data_Model("Donations",0));
        arrayList.add(new Data_Model("Miscellaneous",0));
        customAdapter=new CustomAdapter(ExpenseList.this,arrayList);
        listView.setAdapter(customAdapter);
        // DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("user_Data").child(id);
        final Map<String, Object> updates = new HashMap<String,Object>();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent=new Intent(ExpenseList.this,UserInput.class);
                Toast.makeText(ExpenseList.this,arrayList.get(position).getCategory(),Toast.LENGTH_SHORT).show();
                intent.putExtra("category",arrayList.get(position).getCategory());
                intent.putExtra("auto_key",id);
                startActivity(intent);
                bundle=getIntent().getExtras();
                String category=arrayList.get(position).getCategory();
                DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("user_Data");
                final Map<String, Object> updates = new HashMap<String,Object>();

            }
        });
        //ref.setValue(dataModel);
    }
    boolean isConnectionPossible(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
