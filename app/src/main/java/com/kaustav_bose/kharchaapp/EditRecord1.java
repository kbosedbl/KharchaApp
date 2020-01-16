package com.kaustav_bose.kharchaapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditRecord1 extends AppCompatActivity{
    String old_date="";
    float prev_amt=0;
    final Calendar myCalendar = Calendar.getInstance();
    DatePicker datePicker;
    EditText et1;
    FirebaseUser firebaseUser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record1);
        Bundle bundle=getIntent().getExtras();
        datePicker=findViewById(R.id.pick);

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
        //drawchart(sp);

        findViewById(R.id.submitvalues1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                old_date=getCurrentDate();
                et1=findViewById(R.id.amount_category1);
                if(valid()) {
                    prev_amt = Float.valueOf(et1.getText().toString());
                    String id = getIntent().getStringExtra("id");
                    String category = getIntent().getStringExtra("category");
                    final String ssp=getIntent().getStringExtra("userid");
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("user_Data").child(id);
                    DataModel dataModel = new DataModel();
                    dataModel.setId(id);
                    dataModel.setValue(prev_amt);
                    dataModel.setDate("Date :- " + old_date);
                    dataModel.setCategory(category);
                    dataModel.setEmail(firebaseUser.getEmail());
                    databaseReference.setValue(dataModel);
                    Toast.makeText(EditRecord1.this, "Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditRecord1.this, Dashboard.class));
                    finish();
                }
            }

            private boolean valid() {
                if(et1.getText().toString().isEmpty()) {
                    Toast.makeText(EditRecord1.this, "Please enter the New value", Toast.LENGTH_SHORT).show();
                    return false;
                }
                else
                    return true;
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
}
