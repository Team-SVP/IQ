package com.example.cepl.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    // This is my Comment Parag
    DatabaseHelper mydb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TextView textView22 = (TextView)findViewById(R.id.textView22);
        //textView22.setText("Hello SVP Team");
        //textView22.setText(getIntent().getExtras().getString("Name"));

        LinearLayout dynamicview2 = (LinearLayout) findViewById(R.id.main2_layout);
        LinearLayout.LayoutParams lprams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);


        mydb2 = new DatabaseHelper(this);
        //String sec_name = getIntent().getExtras().getString("Name");
        Intent intent=this.getIntent();
        final String sec_name = intent.getStringExtra("Name");
        setTitle(sec_name);
        Cursor output = mydb2.getSubSectionData(sec_name);
        int i = 0;
        while (output.moveToNext())
        {
            Log.i("DB", output.getString(2));

            final Button btn1 = new Button(this);
            btn1.setId(i+100);
            btn1.setText(output.getString(2));
            btn1.setTextColor(Color.YELLOW);
            btn1.setBackgroundColor(Color.BLUE);
            btn1.setLayoutParams(lprams);


            btn1.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {

                    String subsec_name = btn1.getText().toString();
                    Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                    intent.putExtra("subName", subsec_name);
                    intent.putExtra("secName", sec_name);
                    startActivity(intent);
                }
            });
            dynamicview2.addView(btn1);
        }
    }

}
