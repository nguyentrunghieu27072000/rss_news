package com.example.rss_news.Model.Adapters;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rss_news.Category;
import com.example.rss_news.News;
import com.example.rss_news.NewsActivity;
import com.example.rss_news.NewsHelper;
import com.example.rss_news.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<News> mListNews;

    public NewsAdapter(Context mContext, ArrayList<News> mListTinTuc) {
        this.mContext = mContext;
        this.mListNews = mListTinTuc;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        NewsHelper newsHelper = new NewsHelper(mContext);
        News news = mListNews.get(position);
        holder.txtTitle.setText(news.getTitle());
        Log.d(TAG, "zzzzzzzzzz"+ news.getImg());
        if(news.getImg().isEmpty())
            holder.image.setImageResource(R.drawable.logo_24h);
        else
            Picasso.get().load(news.getImg()).into(holder.image);

        holder.cvLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsActivity.class);
                intent.putExtra("link", news.getLink());
                mContext.startActivity(intent);
            }
        });

        if(newsHelper.checkNewsFavorite(news.getLink())) {
            holder.imgFavorite.setColorFilter(Color.YELLOW);
        }
        holder.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsHelper newsHelper = new NewsHelper(mContext);
                if(newsHelper.checkNewsFavorite(news.getLink())) {
                    if(newsHelper.deleteNewsFavorite(news.getLink())) {
                        holder.imgFavorite.setColorFilter(Color.BLACK);
                        Toast.makeText(mContext, "Delete to favorites successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, "Delete to favorites fail", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (newsHelper.addNewsFavorite(news)) {
                        holder.imgFavorite.setColorFilter(Color.YELLOW);
                        Toast.makeText(mContext, "Add to favorites successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, "Add to favorites fail", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListNews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView txtTitle;
        CardView cvLayout;

        ImageView imgFavorite;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgNews);
            txtTitle = itemView.findViewById(R.id.tvTitle);
            cvLayout = itemView.findViewById(R.id.cvNews);
            imgFavorite = itemView.findViewById(R.id.imgFavorite);
        }
    }
}
