package com.example.klanblogger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    Context mContext;
    List<Article> articleList;

    public ArticleAdapter(Context mContext, List<Article> articleList) {
        this.mContext = mContext;
        this.articleList = articleList;
    }

    @Override
    public int getItemCount() {
        return this.articleList.size();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View topicView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_article_tab, parent,false);
        ArticleViewHolder articleVH = new ArticleViewHolder(topicView);
        return articleVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.heading.setText(this.articleList.get(position).getTitle());
        holder.description.setText(this.articleList.get(position).getDescription());
        holder.author.setText(this.articleList.get(position).getAuthor());
        holder.picture.setImageResource(R.drawable.tool);
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView heading;
        TextView description;
        TextView author;
        ImageView picture;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = (TextView) itemView.findViewById(R.id.article_heading);
            description = (TextView) itemView.findViewById(R.id.article_description);
            author = (TextView) itemView.findViewById(R.id.article_author);
            picture = (ImageView) itemView.findViewById(R.id.article_picture);
        }
    }
}
