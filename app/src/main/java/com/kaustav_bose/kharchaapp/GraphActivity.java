package com.kaustav_bose.kharchaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {
    ArrayList<DataModel> arrayList;
    androidx.appcompat.widget.Toolbar toolbar;
    FirebaseUser firebaseUser;
    //DataModel dataModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("\tGraph Reports");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //drawchart();

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
        drawchart(sp);
//        drawbar();
        /*findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GraphActivity.this,Dashboard.class));
            }
        });*/
    }

    private void drawchart(String ss) {
        DatabaseReference databaseReference;
        databaseReference= FirebaseDatabase.getInstance().getReference("user_Data");
        //dataModel=new DataModel();
        arrayList=new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //DataModel dataModel=new DataModel();
                arrayList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DataModel dataModel = snapshot.getValue(DataModel.class);
                    arrayList.add(dataModel);
                }
                drawchart(arrayList);
                //drawbarchart(arrayList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void drawchart(ArrayList<DataModel> arrayList){
        float ff[]=new float[22];
        PieChart pieChart=findViewById(R.id.pieChart);
        PieChart pieChart1=findViewById(R.id.pieChart2);
        pieChart.setUsePercentValues(true);
        pieChart1.setUsePercentValues(true);
        ArrayList<PieEntry> yvalues = new ArrayList<PieEntry>();
        ArrayList<PieEntry> yvalues1 = new ArrayList<PieEntry>();
        for(int i=0;i<arrayList.size();i++){
            DataModel dataModel=arrayList.get(i);
            String category=dataModel.getCategory();
            if(dataModel.getEmail().equals(firebaseUser.getEmail())) {
                if (category.equals("Spent on :- Food"))
                    ff[0] += dataModel.getValue();
                if (category.equals("Spent on :- Home"))
                    ff[1] += dataModel.getValue();
                if (category.equals("Spent on :- Clothes"))
                    ff[2] += dataModel.getValue();
                if (category.equals("Spent on :- Gifts"))
                    ff[3] += dataModel.getValue();
                if (category.equals("Spent on :- Travel"))
                    ff[4] += dataModel.getValue();
                if (category.equals("Spent on :- Tax"))
                    ff[5] += dataModel.getValue();
                if (category.equals("Spent on :- Shopping"))
                    ff[6] += dataModel.getValue();
                if (category.equals("Spent on :- Entertainment"))
                    ff[7] += dataModel.getValue();
                if (category.equals("Spent on :- Bills"))
                    ff[8] += dataModel.getValue();
                if (category.equals("Spent on :- Education"))
                    ff[9] += dataModel.getValue();
                if (category.equals("Spent on :- Health"))
                    ff[10] += dataModel.getValue();
                if (category.equals("Spent on :- Insurance"))
                    ff[11] += dataModel.getValue();
                if (category.equals("Spent on :- Donations"))
                    ff[12] += dataModel.getValue();
                if (category.equals("Spent on :- Miscellaneous"))
                    ff[13] += dataModel.getValue();
                if (category.equals("Earned from :- Salary"))
                    ff[14] += dataModel.getValue();
                if (category.equals("Earned from :- Awards"))
                    ff[15] += dataModel.getValue();
                if (category.equals("Earned from :- Grants"))
                    ff[16] += dataModel.getValue();
                if (category.equals("Earned from :- Refunds"))
                    ff[17] += dataModel.getValue();
                if (category.equals("Earned from :- Rentals"))
                    ff[18] += dataModel.getValue();
                if (category.equals("Earned from :- Lottery"))
                    ff[19] += dataModel.getValue();
                if (category.equals("Earned from :- Investment"))
                    ff[20] += dataModel.getValue();
                if (category.equals("Earned from :- Others"))
                    ff[21] += dataModel.getValue();
            }
        }
        if(ff[0]>0)
            yvalues.add(new PieEntry(ff[0], "Food", 0));
        if(ff[1]>0)
            yvalues.add(new PieEntry(ff[1], "Home", 1));
        if(ff[2]>0)
            yvalues.add(new PieEntry(ff[2], "Clothes", 2));
        if(ff[3]>0)
            yvalues.add(new PieEntry(ff[3], "Gifts", 3));
        if(ff[4]>0)
            yvalues.add(new PieEntry(ff[4], "Travel", 4));
        if(ff[5]>0)
            yvalues.add(new PieEntry(ff[5], "Tax", 5));
        if ((ff[6]>0))
            yvalues.add(new PieEntry(ff[6], "Shopping", 6));
        if(ff[7]>0)
            yvalues.add(new PieEntry(ff[7], "Entertainment", 7));
        if(ff[8]>0)
            yvalues.add(new PieEntry(ff[8], "Bills", 8));
        if(ff[9]>0)
            yvalues.add(new PieEntry(ff[9], "Education", 9));
        if(ff[10]>0)
            yvalues.add(new PieEntry(ff[10], "Health", 10));
        if(ff[11]>0)
            yvalues.add(new PieEntry(ff[11], "Insurance", 11));
        if(ff[12]>0)
            yvalues.add(new PieEntry(ff[12], "Donations", 12));
        if(ff[13]>0)
            yvalues.add(new PieEntry(ff[13], "Miscellaneous", 13));
        if(ff[14]>0)
            yvalues1.add(new PieEntry(ff[14], "Salary", 0));
        if(ff[15]>0)
            yvalues1.add(new PieEntry(ff[15], "Awards", 1));
        if(ff[16]>0)
            yvalues1.add(new PieEntry(ff[16], "Grants", 2));
        if(ff[17]>0)
            yvalues1.add(new PieEntry(ff[17], "Refunds", 3));
        if(ff[18]>0)
            yvalues1.add(new PieEntry(ff[18], "Rentals", 4));
        if(ff[19]>0)
            yvalues1.add(new PieEntry(ff[19], "Lottery", 5));
        if(ff[20]>0)
            yvalues1.add(new PieEntry(ff[20], "Investment", 6));
        if(ff[21]>0)
            yvalues1.add(new PieEntry(ff[21], "Others", 7));
        PieDataSet dataSet = new PieDataSet(yvalues, "");
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        pieChart1.setData(data);
        PieDataSet dataSet1 = new PieDataSet(yvalues1, "");
        PieData data5 = new PieData(dataSet1);
        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data5);
        final int[] MY_COLORS = {Color.rgb(192,0,0), Color.rgb(255,0,0), Color.rgb(255,192,0),
                Color.rgb(127,127,127), Color.rgb(146,208,80), Color.rgb(0,176,80), Color.rgb(79,129,189),Color.rgb(102,20,40), Color.rgb(200,150,0), Color.rgb(150,200,50),
                Color.rgb(107,177,107), Color.rgb(106,128,120), Color.rgb(40,186,80), Color.rgb(109,129,189),Color.rgb(127,107,129), Color.rgb(196,108,80), Color.rgb(40,86,80), Color.rgb(109,129,100)};
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int c: MY_COLORS) colors.add(c);
        dataSet.setColors(colors);
        for(int c: MY_COLORS) colors.add(c);
        dataSet1.setColors(colors);
    }
}
