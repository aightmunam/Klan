package com.example.klanblogger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {
    Context mContext;
    List<Topic> topicList;

    public TopicAdapter(Context mContext, List<Topic> topicList) {
        this.mContext = mContext;
        this.topicList = topicList;
    }

    @Override
    public int getItemCount() {
        return this.topicList.size();
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View topicView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_topic_tab, parent,false);
        TopicViewHolder topicVH = new TopicViewHolder(topicView);
        return topicVH;
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        holder.name.setText(this.topicList.get(position).getName());
    }

    class TopicViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.topic_name);
        }
    }
}
