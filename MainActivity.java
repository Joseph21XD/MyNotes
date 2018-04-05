package com.enigma.mynotes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId()== R.id.english){
            Toast.makeText(getApplicationContext(),"English", Toast.LENGTH_SHORT).show();
            chooseLanguage("English");
        }
        else{
            Toast.makeText(getApplicationContext(),"Spanish", Toast.LENGTH_SHORT).show();
            chooseLanguage("Español");
        }
        return true;
    }

    public void chooseLanguage(String s){
        sharedPreferences.edit().putString("language",s).apply();
        textView.setText(s);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences= this.getSharedPreferences("enigma.com", Context.MODE_PRIVATE);
        textView= findViewById(R.id.languageTxt);

        String language= sharedPreferences.getString("language", "");

        if(language.equals("")){
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_btn_speak_now)
                    .setTitle("Escoja su lenguaje")
                    .setMessage("Qué lenguaje quiere")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            chooseLanguage("English");
                        }
                    })
                    .setNegativeButton("Español", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            chooseLanguage("Español");
                        }
                    }).show();
        }
        else{
            textView.setText(language);
        }


        /*sharedPreferences.edit().putString("auth_token","2qwHJDSFIOIDwad").apply();

        String token= sharedPreferences.getString("auth_token", "");
        Log.d("TOKEN", token);

        ArrayList<String> students = new ArrayList<>();
        students.add("Carlos");
        students.add("Jose");
        students.add("Andrei");

        try {
            sharedPreferences.edit().putString("students",ObjectSerializer.serialize(students)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ArrayList<String> s=(ArrayList<String>)ObjectSerializer.deserialize(sharedPreferences.getString("students", ""));
            Log.d("ESTUDIANTE", s.get(0));
            Log.d("ESTUDIANTES",sharedPreferences.getString("students", ""));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        */


    }
}
