package com.example.jydobee.jaina_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EmotionsEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotions_edit);



        Intent intent = getIntent();
        //String [] stringArray = intent.getStringArrayExtra("comment");
        Button UpdateButton = (Button) findViewById(R.id.UpdateButton);
        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText) findViewById(R.id.edit_emotion);
                EditText input2 = (EditText) findViewById(R.id.edit_date);
                EditText input3 = (EditText) findViewById(R.id.edit_comment);

                Toast.makeText(getApplication(), "Submitted", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(), EmotionsEditActivity.class);
                intent1.putExtra("emotionedit", input.getText().toString());
                //String s = getIntent().getStringExtra("comment");
               // System.out.println(s);
                finish();



            }


      /*  EditText input = (EditText) findViewById(R.id.edit_text);
        String string = input.getText().toString();
        //Intent intent = new Intent(MainActivity.this, EmotionsEditActivity.class);
        // intent.putExtra("JAMES", string);
        // startActivity(intent);*/


         });
    }



}
