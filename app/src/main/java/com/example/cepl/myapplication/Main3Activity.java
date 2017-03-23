package com.example.cepl.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
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
        //EditText editText2 = (EditText) findViewById(R.id.editText2);
        //editText2.setEnabled(false);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //onBackPressed();

        DatabaseHelper mydb2;

        mydb2 = new DatabaseHelper(this);
        //String sec_name = getIntent().getExtras().getString("Name");
        Intent intent=this.getIntent();
        String sec_name = intent.getStringExtra("secName");
        String sub_name = intent.getStringExtra("subName");
        setTitle(sec_name + " | " + sub_name);

        final Cursor output = mydb2.getQuestionData(sec_name,sub_name);

        output.moveToNext();

        // Initializing question with option

        final String qid = output.getString(0);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        editText2.setText(output.getString(1));
        RadioButton radioButton1 = (RadioButton) findViewById(R.id.radioButton);
        radioButton1.setText(output.getString(5));

        RadioButton radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton2.setText(output.getString(6));

        RadioButton radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton3.setText(output.getString(7));

        RadioButton radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        radioButton4.setText(output.getString(8));

        Button viewButton = (Button) findViewById(R.id.viewButton);

        viewButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                DatabaseHelper mydb2;

                mydb2 = new DatabaseHelper(Main3Activity.this);
                Cursor output1 = mydb2.getAnswerData(qid);

                output1.moveToNext();
                TextView textViewAnswer = (TextView) findViewById(R.id.textViewAnswer);
                textViewAnswer.setText("Answer is : " + output1.getString(1));

                TextView textViewAnswerDesc = (TextView) findViewById(R.id.textViewAnswerDesc);
                textViewAnswerDesc.setText(output1.getString(2));

            }
        });








        // Next button click


        final Button nextButton = (Button) findViewById(R.id.nextButton);
        final Button previousButton = (Button) findViewById(R.id.previousButton);
        previousButton.setEnabled(false);
        nextButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if(!output.moveToNext())
                {
                    nextButton.setEnabled(false);
                    return;
                }
                previousButton.setEnabled(true);
                final String qid = output.getString(0);
                EditText editText2 = (EditText) findViewById(R.id.editText2);
                editText2.setText(output.getString(1));
                RadioButton radioButton1 = (RadioButton) findViewById(R.id.radioButton);
                radioButton1.setText(output.getString(5));
                radioButton1.setChecked(false);

                RadioButton radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
                radioButton2.setText(output.getString(6));
                radioButton2.setChecked(false);

                RadioButton radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
                radioButton3.setText(output.getString(7));
                radioButton3.setChecked(false);

                RadioButton radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
                radioButton4.setText(output.getString(8));
                radioButton4.setChecked(false);

                TextView textViewAnswer = (TextView) findViewById(R.id.textViewAnswer);
                textViewAnswer.setText("");

                TextView textViewAnswerDesc = (TextView) findViewById(R.id.textViewAnswerDesc);
                textViewAnswerDesc.setText("");

                Button viewButton = (Button) findViewById(R.id.viewButton);

                viewButton.setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v) {
                        DatabaseHelper mydb2;

                        mydb2 = new DatabaseHelper(Main3Activity.this);
                        Cursor output1 = mydb2.getAnswerData(qid);

                        output1.moveToNext();
                        TextView textViewAnswer = (TextView) findViewById(R.id.textViewAnswer);
                        textViewAnswer.setText("Answer is : " + output1.getString(1));

                        TextView textViewAnswerDesc = (TextView) findViewById(R.id.textViewAnswerDesc);
                        textViewAnswerDesc.setText(output1.getString(2));

                    }
                });
            } });

        //Previous Button code
        previousButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if(!output.moveToPrevious())
                {
                    previousButton.setEnabled(false);
                    return;
                }

                nextButton.setEnabled(true);
                final String qid = output.getString(0);
                EditText editText2 = (EditText) findViewById(R.id.editText2);
                editText2.setText(output.getString(1));
                RadioButton radioButton1 = (RadioButton) findViewById(R.id.radioButton);
                radioButton1.setText(output.getString(5));
                radioButton1.setChecked(false);

                RadioButton radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
                radioButton2.setText(output.getString(6));
                radioButton2.setChecked(false);

                RadioButton radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
                radioButton3.setText(output.getString(7));
                radioButton3.setChecked(false);

                RadioButton radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
                radioButton4.setText(output.getString(8));
                radioButton4.setChecked(false);

                TextView textViewAnswer = (TextView) findViewById(R.id.textViewAnswer);
                textViewAnswer.setText("");

                TextView textViewAnswerDesc = (TextView) findViewById(R.id.textViewAnswerDesc);
                textViewAnswerDesc.setText("");

                Button viewButton = (Button) findViewById(R.id.viewButton);

                viewButton.setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v) {
                        DatabaseHelper mydb2;

                        mydb2 = new DatabaseHelper(Main3Activity.this);
                        Cursor output1 = mydb2.getAnswerData(qid);

                        output1.moveToNext();
                        TextView textViewAnswer = (TextView) findViewById(R.id.textViewAnswer);
                        textViewAnswer.setText("Answer is : " + output1.getString(1));

                        TextView textViewAnswerDesc = (TextView) findViewById(R.id.textViewAnswerDesc);
                        textViewAnswerDesc.setText(output1.getString(2));

                    }
                });
            } });

            }

    }
