package com.kaustav_bose.kharchaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    Button exp,incm,grph;
    ArrayList<DataModel> arrayList=new ArrayList<>();
    ListView listView;
    CustomAdapter2 customAdapter2;
    DataModel dataModel;
    DatabaseReference databaseReference;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        exp=findViewById(R.id.addexpense);
        incm=findViewById(R.id.addincome);
        grph=findViewById(R.id.view_graph);
        listView=findViewById(R.id.listview);
        builder = new AlertDialog.Builder(this);
        dataModel=new DataModel();
        //DatabaseReference databaseReference;
        databaseReference= FirebaseDatabase.getInstance().getReference("user_Data");
        exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,ExpenseList.class));

            }
        });
        incm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,IncomeList.class));

            }
        });
        grph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,GraphActivity.class));
            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DataModel dataModel = snapshot.getValue(DataModel.class);
                    arrayList.add(dataModel);
                }
                customAdapter2= new CustomAdapter2(Dashboard.this,arrayList);
                listView.setAdapter(customAdapter2);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final String idd=arrayList.get(position).getId();
                builder.setMessage("Do you want to delete this record ?").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference("user_Data").child(idd);
                        databaseReference1.removeValue();
                        dialogInterface.cancel();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("Delete Record");
                alert.show();
            }
        });
    }
}
