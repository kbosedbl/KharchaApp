package com.kaustav_bose.kharchaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ArrayList<DataModel> arrayList=new ArrayList<>();
    ListView listView;
    FirebaseUser firebaseUser;
    CustomAdapter2 customAdapter2;
    DataModel dataModel;
    DatabaseReference databaseReference;
    DatePicker datePicker;
    androidx.appcompat.widget.Toolbar toolbar;
    String dt="",dt2="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search by Date");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*ActionBar actionBar=new actio
        ActionBar actionBar=(ActionBar) getSupportActionBar().setTitle("Search By Date");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        listView=findViewById(R.id.llv);
        datePicker=findViewById(R.id.pick2);
        //toolbar=findViewById(R.id.toolbar);

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

        arrayList=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("user_Data");
        findViewById(R.id.srchbydt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dt=getCurrentDate();
                dt2="0"+dt;

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        arrayList.clear();
                        int f=0;
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            DataModel dataModel = snapshot.getValue(DataModel.class);
                            String date=dataModel.getDate();
                            date=date.substring(8);
                           if(date.equals(dt)&&dataModel.getEmail().equals(firebaseUser.getEmail()))
                               arrayList.add(dataModel);
                        }
                        customAdapter2= new CustomAdapter2(Main2Activity.this,arrayList);
                        listView.setAdapter(customAdapter2);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            private String getCurrentDate() {
                StringBuilder builder=new StringBuilder();;
                int m=(datePicker.getMonth()+1);
                int n=(datePicker.getDayOfMonth());
                if(n>=10)
                    builder.append((datePicker.getDayOfMonth() )+"/");//month is 0 based
                else
                    builder.append("0"+(datePicker.getDayOfMonth() )+"/");//month is 0 based
                if(m>=10)
                    builder.append((datePicker.getMonth()+1)+"/");
                else
                    builder.append("0"+(datePicker.getMonth()+1)+"/");
                builder.append(datePicker.getYear());
                return builder.toString();
            }
        });

    }
}
