package com.example.cepl.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.res.Resources;
import android.widget.EditText;
import android.util.TypedValue;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    //This is comment from Shyam
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mydb = new DatabaseHelper(this);


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
    }

    public void onClick(View v)
    {
        startActivity(new Intent(MainActivity.this, Main2Activity.class));

    }

}
