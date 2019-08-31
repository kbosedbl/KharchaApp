package com.kaustav_bose.kharchaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.kaustav_bose.kharchaapp.DataModel;
import com.kaustav_bose.kharchaapp.ExpenseList;
import com.kaustav_bose.kharchaapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserInput extends AppCompatActivity {
    EditText et;
    Button button;
    Bundle bundle;
    DataModel dataModel;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);
        et=findViewById(R.id.amount_category);
        button=findViewById(R.id.submitvalues);
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();
        //databaseReference= FirebaseDatabase.getInstance().getReference("user_Data");
        bundle=getIntent().getExtras();
        dataModel=new DataModel();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string=et.getText().toString();
                if(!string.equals("")) {
                    float f = Float.valueOf(et.getText().toString());
                    databaseReference = FirebaseDatabase.getInstance().getReference("user_Data");
                    //String id = bundle.getString("auto_key");
                    //dataModel=new Data_Model(str)
                    //DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("user_Data").child(id);
                    if (bundle.getString("category").equals("Food")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String cat = bundle.getString("category");
                        String id = databaseReference.push().getKey();
                        dataModel.setCategory("Spent on :- " + cat);
                        dataModel.setDate("Date :- " + formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Home")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Spent on :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Clothes")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Spent on :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Gifts")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Spent on :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Travel")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Spent on :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Tax")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Spent on :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Shopping")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Spent on :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Entertainment")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Spent on :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Bills")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Spent on :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Education")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Spent on :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Health")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Spent on :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Insurance")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Spent on :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Donations")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Spent on :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Miscellaneous")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Spent on :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Rentals")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Earned from :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Lottery")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Earned from :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Investment")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Earned from :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Others")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Earned from :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Salary")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Earned from :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Awards")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Earned from :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Grants")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Earned from :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    if (bundle.getString("category").equals("Refunds")) {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String id = databaseReference.push().getKey();
                        String cat = bundle.getString("category");
                        dataModel.setCategory("Earned from :- " + cat);
                        dataModel.setDate("Date :- "+formattedDate);
                        dataModel.setValue(f);
                        dataModel.setId(id);
                        dataModel.setId(id);
                        databaseReference.child(id).setValue(dataModel);
                    }
                    Intent intent = new Intent(UserInput.this, Dashboard.class);
                    intent.putExtra("float", f);
                    Toast.makeText(UserInput.this, "Updated", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(UserInput.this, "Oops! It seems you haven't entered the data . Please enter the data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
