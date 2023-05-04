package com.example.rss_news.Model.Adapters;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rss_news.News;
import com.example.rss_news.NewsActivity;
import com.example.rss_news.NewsFavorite;
import com.example.rss_news.NewsHelper;
import com.example.rss_news.PageNews;
import com.example.rss_news.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>{
    private List<NewsFavorite> mListFavorite;
    private Context mContext;

    public FavoriteAdapter( Context mContext, List<NewsFavorite> mListFavorite) {
        this.mListFavorite = mListFavorite;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_favorite_item, parent, false);
        return new FavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteHolder holder, int position) {
        NewsFavorite newsFavorite = mListFavorite.get(position);
        if (newsFavorite == null) {
            return;
        }

        if(newsFavorite.getImg().isEmpty())
            holder.imgNewsFavorite.setImageResource(R.drawable.logo_24h);
        else
            Picasso.get().load(newsFavorite.getImg()).into(holder.imgNewsFavorite);
        holder.tvTitleFavorite.setText(newsFavorite.getTitle());

        holder.imgRemoveFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsHelper newsHelper = new NewsHelper(mContext);
                if(newsHelper.deleteNewsFavorite(newsFavorite.getId())) {
                    mListFavorite.remove(newsFavorite);
                    notifyDataSetChanged();
                    Toast.makeText(mContext, "Delete News Favorite successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(mContext, "Delete News Favorite fail", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.cvFavoriteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsActivity.class);
                intent.putExtra("link", newsFavorite.getLink());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListFavorite != null) {
            return mListFavorite.size();
        }
        return 0;
    }

    public class FavoriteHolder extends RecyclerView.ViewHolder {
        private CardView cvFavoriteItem;
        private ImageView imgNewsFavorite;
        private  ImageView imgRemoveFavorite;
        private TextView tvTitleFavorite;

        public FavoriteHolder(@NonNull View itemView) {
            super(itemView);

            imgRemoveFavorite = itemView.findViewById(R.id.imgRemoveFavorite);
            imgNewsFavorite = itemView.findViewById(R.id.imgNewsFavorite);
            tvTitleFavorite = itemView.findViewById(R.id.tvTitleFavorite);
            cvFavoriteItem = itemView.findViewById(R.id.cvNewsFavorite);
        }
    }
}
