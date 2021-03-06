package com.example.networktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendRequest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_request) {

//            sendRequestWithHttpURLConnection();

            sendRequestWithOkHttp();
        }
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("http://www.zhongkun.xyz")
                            .build();
                    Response response = client.newCall(request).execute();

                    String responseData = response.body().string();

                    showResponse(responseData);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void sendRequestWithHttpURLConnection() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {

                    URL url = new URL("http://www.baidu.com");

                    connection = (HttpURLConnection) url.openConnection();

                    connection.connect();

                    InputStream in = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(in);
                    reader = new BufferedReader(isr);

                    StringBuilder response = new StringBuilder();
                    String line = "";

                    while ((line = reader.readLine()) != null) {

                        Log.d(TAG, "run: line" + line);
                        response.append(line);
                        Log.d(TAG, "run: response" + response.toString());
                    }


                    Log.d(TAG, "run: " + response.toString());

                    showResponse(response.toString());
//                    System.out.println(response.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }

            }
        }).start();
    }

    private void showResponse(final String response) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行UI操作，将结果显示到界面上
                responseText.setText(response);

                Log.d(TAG, "run: textview" + response);
            }
        });
    }


}
