package com.example.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    private List<ChatData> mDataset;
    private String myNickname;
    public static View.OnClickListener onClickListener;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView TextView_nickname;
        public TextView TextView_msg;
        public LinearLayout layout;


        public MyViewHolder(LinearLayout v) {
            super(v);
            TextView_nickname = v.findViewById(R.id.TextView_nickname);
            TextView_msg = v.findViewById(R.id.TextView_msg);
            layout = v;

            layout.setClickable(true);
            layout.setEnabled(true);
            layout.setOnClickListener(onClickListener);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ChatAdapter(List<ChatData> myDataset, Context context, String myNickname) {
        mDataset = myDataset;
        this.myNickname = myNickname;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_chat, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        ChatData chat = mDataset.get(position);
        holder.TextView_nickname.setText(chat.getNickname());
        holder.TextView_msg.setText(chat.getMsg());

/*        if(chat.getNickname().equals(myNickname)){
            holder.TextView_nickname.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            holder.TextView_msg.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        }
        else{
            holder.TextView_nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.TextView_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        }*/
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return (mDataset == null) ? 0 : mDataset.size();
    }

    public ChatData getNewsData(int position){return mDataset.get(position);}
    public void addChat(ChatData chat){
        mDataset.add((chat));
        notifyItemInserted(mDataset.size()-1); // 갱신용
    }
}