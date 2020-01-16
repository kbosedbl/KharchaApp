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
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    FirebaseUser firebaseUser;
    AlertDialog.Builder builder;
    DatePicker picker;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        exp=findViewById(R.id.addexpense);
        incm=findViewById(R.id.addincome);
        grph=findViewById(R.id.view_graph);
        listView=findViewById(R.id.listview);
        builder = new AlertDialog.Builder(this);
        dataModel=new DataModel();
        //DatabaseReference databaseReference;
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String mail=firebaseUser.getEmail();
        String ssp="";
        for(int i=mail.length()-1;i>=0;i--){
            char ch=mail.charAt(i);
            if(ch=='.'||ch=='@'||ch=='#')
                continue;
            else
                ssp=ssp+ch;
        }
        final String sp=ssp;
        databaseReference= FirebaseDatabase.getInstance().getReference("user_Data");
        exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Dashboard.this,ExpenseList.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                //finish();
            }
        });
        incm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Dashboard.this,IncomeList.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                //finish();
            }
        });
        grph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Dashboard.this,GraphActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                //finish();
            }
        });
        findViewById(R.id.todate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,Main2Activity.class));
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                //startActivity(intent);
                //finish();
            }
        });
        findViewById(R.id.logout2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setMessage("Wanna logout?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(Dashboard.this,StartActivity.class));
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("LOGOUT");
                alert.show();
            }
        });
        databaseReference= FirebaseDatabase.getInstance().getReference("user_Data");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DataModel dataModel = snapshot.getValue(DataModel.class);
                    if(dataModel.getEmail().equals(firebaseUser.getEmail()))
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
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                final String idd=arrayList.get(position).getId();
                builder.setMessage("What would you want to do ?").setCancelable(false).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference("user_Data").child(idd);
                        databaseReference1.removeValue();
                        dialogInterface.cancel();
                    }
                }).setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference databaseReference2=FirebaseDatabase.getInstance().getReference("user_Data");
                        String date=arrayList.get(position).getDate();
                        int oos=position;
                        float oldamt=arrayList.get(position).getValue();
                        DataModel dataModel1=new DataModel();
                        dataModel1.setCategory(arrayList.get(position).getCategory());
                        String id=arrayList.get(position).getId();
                        String cat=arrayList.get(position).getCategory();
                        Intent intent=new Intent(Dashboard.this,EditRecord1.class);
                        intent.putExtra("userid",sp);
                        intent.putExtra("id",id);
                        intent.putExtra("category",cat);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("Delete/Edit the Record");
                alert.show();
            }
        });
    }
}
