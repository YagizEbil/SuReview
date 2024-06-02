package com.sabanciuniv.sureviewapp;

import android.os.Message;
import android.os.Handler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;


public class StartScreenRepository {

    public void singIn(ExecutorService srv, Handler uiHandler,String mail,String Username){
        srv.execute(()->{



            try{
                URL url = new URL("http://10.3.0.14:8080/api/users/profile");//This is not the correct API

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/JSON");

                JSONObject objToSend = new JSONObject();
                objToSend.put("mail",mail);
                objToSend.put("username",Username);

                BufferedOutputStream writer = new BufferedOutputStream(conn.getOutputStream());

                writer.write(objToSend.toString().getBytes(StandardCharsets.UTF_8));
                writer.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                String response = buffer.toString(); //response recieved from the backend
                Message msg = new Message();
                msg.obj = response;
                uiHandler.sendMessage(msg);


            } catch (IOException | JSONException e) {
                throw new RuntimeException(e);
            }


        });
    }
}
