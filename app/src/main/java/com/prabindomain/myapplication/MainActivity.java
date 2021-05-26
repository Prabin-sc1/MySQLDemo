package com.prabindomain.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText t1, t2, t3;
    private TextView tv;

    private final String url = "http://10.0.2.2/all_document/insert_db.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);

        tv = findViewById(R.id.tv);

    }
    public void process(View view){
        String n1 = t1.getText().toString();
        String n2 = t2.getText().toString();
        String n3 = t3.getText().toString();

        String qryString = "?t1="+n1+"&t2="+n2+"&t3="+n3;

        class DbClass extends AsyncTask<String, Void, String>{

            protected void onPostExecute(String data){
                t1.setText("");
                t2.setText("");
                t3.setText("");
                tv.setText(data);
            }

            @Override
            protected String doInBackground(String... strings) {
                try{
                    URL url = new URL(strings[0]);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    return  br.readLine();

                }catch (Exception e){
                    return e.getMessage();
                }

            }
        }
        DbClass dbClass = new DbClass();
        dbClass.execute(url+qryString);

    }
}