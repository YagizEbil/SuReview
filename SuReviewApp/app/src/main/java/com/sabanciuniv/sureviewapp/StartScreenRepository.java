package com.sabanciuniv.sureviewapp;

import static android.content.ContentValues.TAG;

import android.os.Message;
import android.os.Handler;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
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

    public void singIn(ExecutorService srv, Handler uiHandler,String mail,String displayName){
        srv.execute(()->{

            Log.d("DEV", "Inside srv.execute");

            try{
                URL url = new URL("http://localhost:8080/api/auth/signin");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/JSON");

                JSONObject objToSend = new JSONObject();
                objToSend.put("email",mail);
                objToSend.put("displayName",displayName);


                int responseCode = conn.getResponseCode();

                if(responseCode == HttpURLConnection.HTTP_OK){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder buffer = new StringBuilder();
                    String line = "";

                    while((line=reader.readLine())!=null){

                        buffer.append(line);

                    }

                    JSONObject obj = new JSONObject(buffer.toString());

                    String strToken = obj.getString("token");


                    Message msg = new Message();//This is for testing
                    msg.obj = strToken;
                    uiHandler.sendMessage(msg);
                }
                else if (responseCode == HttpURLConnection.HTTP_FORBIDDEN)
                {
                    String response  = "WRONG";

                    Message msg = new Message();
                    msg.obj = response;
                    uiHandler.sendMessage(msg);
                }
                else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED)
                {
                    String response  = "EXIST";

                    Message msg = new Message();
                    msg.obj = response;
                    uiHandler.sendMessage(msg);
                }


            } catch (IOException | JSONException e) {
                throw new RuntimeException(e);
            }


        });
    }

    public void register(ExecutorService srv, Handler uiHandler,String mail,String displayName){
        srv.execute(()->{

            Log.d("DEV", "Inside srv.execute");

            try{
                URL url = new URL("http://localhost:8080/api/auth/register");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/JSON");


                JSONObject objToSend = new JSONObject();
                objToSend.put("email",mail);
                objToSend.put("displayName",displayName);


                BufferedOutputStream writer = new BufferedOutputStream(conn.getOutputStream());

                writer.write(objToSend.toString().getBytes(StandardCharsets.UTF_8));
                writer.flush();


                int responseCode = conn.getResponseCode();

                if(responseCode == HttpURLConnection.HTTP_OK){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder buffer = new StringBuilder();
                    String line = "";

                    while((line=reader.readLine())!=null){

                        buffer.append(line);

                    }

                    JSONObject obj = new JSONObject(buffer.toString());

                    String strEmail = obj.getString("email");


                    Message msg = new Message();//This is for testing
                    msg.obj = strEmail;
                    uiHandler.sendMessage(msg);
                }

                else if (responseCode == HttpURLConnection.HTTP_FORBIDDEN)
                {
                    String response  = "WRONG";

                    Message msg = new Message();
                    msg.obj = response;
                    uiHandler.sendMessage(msg);
                }



            } catch (IOException | JSONException e) {
                throw new RuntimeException(e);
            }


        });
    }
}
