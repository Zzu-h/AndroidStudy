package com.example.newstest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.drawee.view.SimpleDraweeView;

public class NewsActivity extends AppCompatActivity {
    public TextView TextView_Title;
    public TextView TextView_Content;
    public SimpleDraweeView ImageView_Title;
    public LinearLayout LinearLayout_goChrome;

    public String url = "www.google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Fresco.initialize(this);

        TextView_Title = findViewById(R.id.TextView_Title);
        TextView_Content = findViewById(R.id.TextView_Content);
        ImageView_Title = findViewById(R.id.ImageView_Title);
        LinearLayout_goChrome = findViewById(R.id.LinearLayout_goChrome);

        LinearLayout_goChrome.setClickable(true);
        LinearLayout_goChrome.setEnabled(true);


        Intent intent = getIntent();
        NewsData getNews = (NewsData) intent.getExtras().get("getNews");
        url = getNews.getLink();
        TextView_Title.setText(getNews.getTitle());
        TextView_Content.setText(getNews.getContent());

        Uri uri = Uri.parse(getNews.getUrlToImage());
        ImageView_Title.setImageURI(uri);

        LinearLayout_goChrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.setPackage("com.android.chrome");
                startActivity(intent);
            }
        });
    }
}
