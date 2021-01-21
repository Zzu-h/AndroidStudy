package com.example.newstest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    final String clientID = "YSVB6dv5XquMli6FMyb3";
    final String clientPassword = "cFBxQ_EK93";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView = findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        queue = Volley.newRequestQueue(this);
        getNews();
    }

    public void getNews() {

        String Search = "강아지";

        String url = "https://openapi.naver.com/v1/search/book.json?query=" + Search;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //textView.setText("Response is: "+ response.substring(0,500));
                        // Log.d("NEWS", response);

                        try {
                            List<NewsData> newsList = new ArrayList<NewsData>();
                            JSONArray arrayArticles = new JSONObject(response).getJSONArray("items");

                            for (int i = 0; i < arrayArticles.length(); i++) {
                                JSONObject obj = arrayArticles.getJSONObject(i);

                                NewsData news = new NewsData();

                                news.setTitle(obj.getString("title"));
                                news.setUrlToImage(obj.getString("image"));
                                news.setContent(obj.getString("description"));
                                news.setLink(obj.getString("link"));

                                newsList.add(news);
                            }

                            // specify an adapter (see also next example)
                            mAdapter = new MyAdapter(newsList, MainActivity.this, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Object obj = v.getTag();
                                    if(obj != null){
                                        NewsData getNews = ((MyAdapter)mAdapter).getNewsData((int)v.getTag());
                                        try {
                                            Intent intent  = new Intent(MainActivity.this, NewsActivity.class);
                                            intent.putExtra("getNews",getNews);
                                            startActivity(intent);
                                        }catch (Exception e){
                                            e.getStackTrace();
                                        }
                                    }
                                }
                            });
                            recyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-Naver-Client-Id", clientID);
                headers.put("X-Naver-Client-Secret", clientPassword);
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}