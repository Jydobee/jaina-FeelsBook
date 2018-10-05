package com.example.jydobee.jaina_feelsbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import org.w3c.dom.Comment;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


// Implementation of the main activity
public class MainActivity extends AppCompatActivity {
    String comment;
    private static final String FILENAME = "file100000.sav";
    private EditText et;
    EditText comment_input;
    EditText emotionedit;
    String emotion_edit;
    private ArrayAdapter<String> adapter; // Declare the array adapter
    private ListView emotionViewList; // Declare the emotion view list

    // Declare all the required counts

    // Intialize all the counts to zero
    private int emotion_count;
    private int love_count = 0;
    private int anger_count = 0;
    private int joy_count = 0;
    private int sadness_count = 0;
    private int surprise_count = 0;
    private int fear_count = 0;
    ArrayList<String> emotion = new ArrayList<String>();
    ArrayList<String> onEmotionArray = new ArrayList<String>();
    ArrayList<String> count_array = new ArrayList<String>();

    SimpleDateFormat new_format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.CANADA);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        comment_input = (EditText) findViewById(R.id.commentInput);
        emotionViewList = (ListView) findViewById(R.id.EmotionList);
        emotionViewList.setAdapter(adapter);


        // Allows the user perfom edit list view upon click
        emotionViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d("TEST", "I shouldn't be here");
                // find out how to edit here
                /*EditText input = (EditText) findViewById(R.id.edit_emotion);
                EditText input2 = (EditText) findViewById(R.id.edit_date);
                EditText input3 = (EditText) findViewById(R.id.edit_comment);*/
                Intent intent = new Intent(getApplicationContext(), EmotionsEditActivity.class);

                // emotion_edit = getIntent().getExtras().getString("emotionedit");

                //intent.putExtra("comment", comment);
                startActivity(intent);
            }
        });


        // Love Button Onclick method
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "You're in love", Toast.LENGTH_SHORT).show();
                saveInFile(emotion);
                //saveCountInFile(onEmotionArray);


            }
        });


        // Count Button that displays the total emotion counts
        Button CountButton = (Button) findViewById(R.id.CountButton);
        CountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getApplication(), "Count Shown", Toast.LENGTH_SHORT).show();
                count_array.add("Love" + " (" + Integer.toString(love_count) + ")");
                count_array.add("Joy" + " (" + Integer.toString(joy_count) + ")");
                count_array.add("Sadness" + " (" + Integer.toString(sadness_count) + ")");
                count_array.add("Fear" + " (" + Integer.toString(fear_count) + ")");
                count_array.add("Anger" + " (" + Integer.toString(anger_count) + ")");
                count_array.add("Surprise" + " (" + Integer.toString(surprise_count) + ")");

                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, count_array);
                emotionViewList.setAdapter(adapter);
                emotionViewList.setAdapter(adapter);

            }
        });


        // Love Button Onclick method
        Button LoveButton = (Button) findViewById(R.id.LoveButton);
        LoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "You're in love", Toast.LENGTH_SHORT).show();
                emotion_count++;
                love_count++;
                comment = comment_input.getText().toString();
                emotion.add("Love" + "\n" + new_format.format(new Date(System.currentTimeMillis())) + "\n" + comment + "\n");
                //onEmotionArray.add("Love");
                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, emotion);
                emotionViewList.setAdapter(adapter);

            }
        });

        // Fear Button Onclick method
        Button JoyButton = (Button) findViewById(R.id.JoyButton);
        JoyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "You're afraid", Toast.LENGTH_SHORT).show();
                emotion_count++;
                joy_count++;
                comment = comment_input.getText().toString();
                emotion.add("Joy" + "\n" + new_format.format(new Date(System.currentTimeMillis())) + "\n" + comment + "\n");
                // onEmotionArray.add("Joy");
                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, emotion);
                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, emotion);
                emotionViewList.setAdapter(adapter);

            }

        });

        // Suprise bUtton Onclick method
        Button SurpriseButton = (Button) findViewById(R.id.SurpriseButton);
        SurpriseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "You're Suprised", Toast.LENGTH_SHORT).show();
                emotion_count++;
                surprise_count++;
                comment = comment_input.getText().toString();
                emotion.add("Surprise" + "\n" + new_format.format(new Date(System.currentTimeMillis())) + "\n" + comment + "\n");
                // onEmotionArray.add("Surprise");
                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, emotion);
                emotionViewList.setAdapter(adapter);


            }

        });

        // Sadness Button Onclick method
        Button SadnessButton = (Button) findViewById(R.id.SadnessButton);
        SadnessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "You're very sad", Toast.LENGTH_SHORT).show();
                emotion_count++;
                sadness_count++;
                comment = comment_input.getText().toString();
                emotion.add("Sad" + "\n" + new_format.format(new Date(System.currentTimeMillis())) + "\n" + comment + "\n");
                /// onEmotionArray.add("Sad");
                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, emotion);
                emotionViewList.setAdapter(adapter);


            }
        });

        // Anger bUtton Onclick method
        Button AngerButton = (Button) findViewById(R.id.AngerButton);
        AngerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "You're angry", Toast.LENGTH_SHORT).show();
                emotion_count++;
                anger_count++;
                comment = comment_input.getText().toString();
                emotion.add("Anger" + "\n" + new_format.format(new Date(System.currentTimeMillis())) + "\n" + comment + "\n");
                // onEmotionArray.add("Anger");
                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, emotion);
                emotionViewList.setAdapter(adapter);

            }
        });

        // Fear Button Onclick method
        Button FearButton = (Button) findViewById(R.id.FearButton);
        FearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "You're afraid", Toast.LENGTH_SHORT).show();
                emotion_count++;
                fear_count++;
                comment = comment_input.getText().toString();
                emotion.add("Fear" + "\n" + new_format.format(new Date(System.currentTimeMillis())) + "\n" + comment + "\n");
                //onEmotionArray.add("Fear");
                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, emotion);
                emotionViewList.setAdapter(adapter);

            }
        });


    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        // Count Button that displays the total emotion counts

        //deleteFile(FILENAME);
        String[] tweets = loadFromFile();
        //ArrayList<String> count_array = new ArrayList<String>();
        //String [] countString = loadCountFromFile();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                R.layout.support_simple_spinner_dropdown_item, tweets);
        emotionViewList.setAdapter(adapter);
    }


    private String[] loadFromFile() {
        //E4TdeleteFile(FILENAME)
        ArrayList<String> storage = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();
            while (line != null) {
                storage.add(line);
                line = in.readLine();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return storage.toArray(new String[storage.size()]);

    }

    private void saveInFile(ArrayList<String> comment) {

        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_APPEND);
            //fos.write(new String(comment + "")
            //      .getBytes());

            for (String s : emotion) {
                fos.write(s.getBytes());
            }
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}




