package com.sabanciuniv.sureviewapp;

import android.os.Message;

import org.json.JSONArray;
import org.json.JSONObject;
import android.os.Handler;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;


public class HomeScreenRepository {

    public void getReviews(ExecutorService srv, Handler uiHandler, String courseOfferingId, String bearerToken) {
        srv.execute(() -> {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            List<Review> reviews = new ArrayList<>();

            try {
                URL url = new URL("http://localhost:8080/api/reviews/course/" + courseOfferingId);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Authorization", "Bearer " + bearerToken);

                // Connect to the server
                urlConnection.connect();

                // Get the response code
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Read the response
                    reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder responseStringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        responseStringBuilder.append(line);
                    }

                    // Parse the JSON response
                    JSONArray jsonArray = new JSONArray(responseStringBuilder.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String displayName = jsonObject.getString("displayName");
                        String content = jsonObject.getString("content");
                        int rating = jsonObject.getInt("rating");
                        String professor = jsonObject.getString("professor");
                        String course = jsonObject.getString("course");

                        Review review = new Review(displayName, content, rating, professor, course);
                        reviews.add(review);
                    }

                    // Send the result back to the UI thread
                    Message message = new Message();
                    message.obj = reviews;
                    uiHandler.sendMessage(message);

                } else {
                    // Handle non-200 response codes here
                    Message message = new Message();
                    message.obj = "Error: " + responseCode;
                    uiHandler.sendMessage(message);
                }

            } catch (Exception e) {
                e.printStackTrace();
                Message message = new Message();
                message.obj = "Exception: " + e.getMessage();
                uiHandler.sendMessage(message);
            } finally {
                // Close the BufferedReader
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // Disconnect the HttpURLConnection
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        });
    }


    public void createReview(ExecutorService executor, Handler uiHandler, Review review, String bearerToken) {
        executor.execute(() -> {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL("http://localhost:8080/api/reviews/course/" + review.getCourseOfferingId());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Authorization", "Bearer " + bearerToken);
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setDoOutput(true);

                // Create the JSON payload
                JSONObject reviewJson = new JSONObject();
                reviewJson.put("displayName", review.getDisplayName());
                reviewJson.put("content", review.getContent());
                reviewJson.put("rating", review.getRating());
                reviewJson.put("professor", review.getProfessor());
                reviewJson.put("course", review.getCourse());
                reviewJson.put("userId", review.getUserId());

                // Write the JSON payload to the output stream
                OutputStream os = urlConnection.getOutputStream();
                os.write(reviewJson.toString().getBytes());
                os.flush();
                os.close();

                // Get the response code
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Read the response
                    reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder responseStringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        responseStringBuilder.append(line);
                    }

                    // Parse the JSON response
                    JSONObject responseJson = new JSONObject(responseStringBuilder.toString());
                    Review createdReview = new Review(
                            responseJson.getString("displayName"),
                            responseJson.getString("content"),
                            responseJson.getInt("rating"),
                            responseJson.getString("professor"),
                            responseJson.getString("course"),
                            review.getCourseOfferingId(),
                            responseJson.getString("userId")
                    );

                    // Send the result back to the UI thread
                    Message message = new Message();
                    message.obj = createdReview;
                    uiHandler.sendMessage(message);

                } else {
                    // Handle non-200 response codes here
                    Message message = new Message();
                    message.obj = "Error: " + responseCode;
                    uiHandler.sendMessage(message);
                }

            } catch (Exception e) {
                e.printStackTrace();
                Message message = new Message();
                message.obj = "Exception: " + e.getMessage();
                uiHandler.sendMessage(message);
            } finally {
                // Close the BufferedReader
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // Disconnect the HttpURLConnection
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        });
    }
}
