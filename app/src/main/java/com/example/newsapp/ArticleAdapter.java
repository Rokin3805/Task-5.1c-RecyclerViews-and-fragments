package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<Article> articleList;
    private Context context;

    private OnRowClickListener listener;

    public ArticleAdapter(List<Article> articleList, Context context, OnRowClickListener clickListener) {
        this.articleList = articleList;
        this.context = context;
        this.listener = clickListener;
    }

    @NonNull
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //new view which uses layout inflater to inflate our banner_layout xml
        View articleView = LayoutInflater.from(context).inflate(R.layout.banner_layout, parent, false);
        return new ViewHolder(articleView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder holder, int position) {
        holder.heading.setText(articleList.get(position).getArticleHeading());
        holder.body.setText(articleList.get(position).getArticleBody());
        holder.image.setImageResource(articleList.get(position).getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView heading, body;
        ImageView image;
        OnRowClickListener onRowClickListener;

        public ViewHolder(@NonNull View articleView, OnRowClickListener onRowClickListener) {
            super(articleView);
            heading = articleView.findViewById(R.id.bannerHeading);
            body = articleView.findViewById(R.id.bannerTextBody);
            image = articleView.findViewById(R.id.bannerImage);
            this.onRowClickListener = onRowClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRowClickListener.onItemClick(getAdapterPosition());
        }
    }
    public interface OnRowClickListener{
        void onItemClick (int position);
    }
}
