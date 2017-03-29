package com.example.cepl.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.res.Resources;
import android.widget.EditText;
import android.util.TypedValue;

public class MainActivity extends AppCompatActivity {
    databaseCopy dbCopy;
    DatabaseHelper mydb;
    //This is comment from Shyam
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        mydb = new DatabaseHelper(this);
        Cursor output = mydb.getData();
        while (output.moveToNext()) {
            Log.i("DB", output.getString(1));
        }


        final Button button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        Resources r = getResources();
                        int px = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, r.getDisplayMetrics());

                        TextView textView2 = (TextView)findViewById(R.id.textView2);
                        textView2.setWidth(px);
                        textView2.setText("Hello SVP Team");
                        //startActivity(new Intent(MainActivity.this, Main2Activity.class);
                        String name = button5.getText().toString();
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        intent.putExtra("Name", name);
                        startActivity(intent);
                    }
                }
        );
        */


        LinearLayout dynamicview = (LinearLayout) findViewById(R.id.main_layout);
        LinearLayout.LayoutParams lprams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        dbCopy = new databaseCopy(this);
        mydb = new DatabaseHelper(this);
        Cursor output = mydb.getData();
        int i = 0;
        while (output.moveToNext())
        {
            //Log.i("DB", output.getString(1));

            final Button btn = new Button(this);
            btn.setId(i+1);
            btn.setText(output.getString(1));
            btn.setTextColor(Color.YELLOW);
            btn.setBackground(getResources().getDrawable(R.drawable.page1));
            //btn.setBackgroundColor(Color.BLUE);
            btn.setLayoutParams(lprams);


            btn.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    String name = btn.getText().toString();
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("Name", name);
                    startActivity(intent);
                }
            });
            dynamicview.addView(btn);
        }
    }


    public void onClick(View v)
    {
        startActivity(new Intent(MainActivity.this, Main2Activity.class));

    }

}