package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    TextView txt2;
    TextView txt3;
    TextView txt4;
    TextView txt5;
    TextView txt6;
    Button btn;

    String futureJokeString = "";
    String futureJokeString2 = "";
    String futureJokeString3 = "";
    String futureJokeString4 = "";
    String futureJokeString5 = "";
    String futureJokeString6 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txtJoke);
        txt2 = findViewById(R.id.textView);
        txt3 = findViewById(R.id.textView2);
        txt4 = findViewById(R.id.textView3);
        txt5 = findViewById(R.id.textView4);
        txt6 = findViewById(R.id.textView5);
        btn = findViewById(R.id.btnClick);

        btn.setOnClickListener(view -> new JokeLoader().execute());
    }

    private class JokeLoader extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            futureJokeString = "";
            futureJokeString2 = "";
            futureJokeString3 = "";
            futureJokeString4 = "";
            futureJokeString5 = "";
            futureJokeString6 = "";
            txt.setText("Загрузка...");
            txt2.setText("Загрузка...");
            txt3.setText("Загрузка...");
            txt4.setText("Загрузка...");
            txt5.setText("Загрузка...");
            txt6.setText("Загрузка...");
        }
        @Override
        protected Void doInBackground(Void... voids) {
            String jsonString = getJson("https://api.chucknorris.io/jokes/random");

            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                futureJokeString = jsonObject.getString("value");
                futureJokeString2 = jsonObject.getString("created_at");;
                futureJokeString3 = jsonObject.getString("icon_url");;
                futureJokeString4 = jsonObject.getString("id");;
                futureJokeString5 = jsonObject.getString("updated_at");;
                futureJokeString6 = jsonObject.getString("url");;
            } catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (!futureJokeString.equals("")) {
                txt.setText(futureJokeString);
                txt2.setText(futureJokeString2);
                txt3.setText(futureJokeString3);
                txt4.setText(futureJokeString4);
                txt5.setText(futureJokeString5);
                txt6.setText(futureJokeString6);
            }
        }
    }


    private String getJson(String link)
    {
        String data = "";
        try {
            URL url = new URL(link);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),
                        "utf-8"));
                data = r.readLine();
                urlConnection.disconnect();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}