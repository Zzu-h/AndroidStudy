package com.example.newstest;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<NewsData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView TextView_Title;
        public TextView TextView_Content;
        public SimpleDraweeView ImageView_title;

        public MyViewHolder(LinearLayout v) {
            super(v);
            TextView_Title = v.findViewById(R.id.TextView_Title);
            TextView_Content = v.findViewById(R.id.TextView_Content);
            ImageView_title = v.findViewById(R.id.ImageView_Title);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<NewsData> myDataset, Context context) {
        mDataset = myDataset;
        Fresco.initialize(context);
        //fresco initialize는 기본적으로 이런 데이터 클래스에서 사용 못하고 activity에서 사용해야한다.
        //따라서 activity 주소를 받아와서 지정해준다.
        //메모리 누수의 가능성이 있어 추천하지 않음
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_news, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        NewsData news = mDataset.get(position);

        holder.TextView_Title.setText(news.getTitle());

        String content = (news.getContent() == null) ? "-" : news.getContent();
        holder.TextView_Content.setText(content);

        //image를 로딩해야한다. 하지만 이미지는 url만 가지고있음.
        //따라서, fresco를 이용하여 로딩한다.

        Uri uri = Uri.parse(news.getUrlToImage());
        holder.ImageView_title.setImageURI(uri);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return (mDataset == null) ? 0 : mDataset.size();
    }
}
