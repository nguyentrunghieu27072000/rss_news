package com.example.rss_news.Model.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rss_news.HomeActivity;
import com.example.rss_news.NewsActivity;
import com.example.rss_news.NewsCategory;
import com.example.rss_news.NewsCategoryActivity;
import com.example.rss_news.PageNews;
import com.example.rss_news.R;

import java.util.List;

public class NewsCategoryAdapter extends RecyclerView.Adapter<NewsCategoryAdapter.NewsCategoryViewHolder> {
    private Context mContext;
    private List<NewsCategory> newsCategories;

    private  PageNews pageNews;

    public NewsCategoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<NewsCategory> list,PageNews pageNews) {
        this.newsCategories = list;
        this.pageNews = pageNews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new NewsCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsCategoryViewHolder holder, int position) {
        NewsCategory newsCategory = newsCategories.get(position);
        if( newsCategory == null) {
            return;
        }

        holder.btnCategory.setText(newsCategory.getCategoryName());
        holder.btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.btnCategory.setTextColor(Color.RED);
                Intent intent = new Intent(mContext, NewsCategoryActivity.class);
                intent.putExtra("link", newsCategory.getCategoryLink().toString());
                intent.putExtra("object_page", pageNews);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(newsCategories != null) {
            return newsCategories.size();
        }
        return 0;
    }

    public class NewsCategoryViewHolder extends  RecyclerView.ViewHolder {

        private Button btnCategory;
        private ImageView imgPageBack;

        public NewsCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            btnCategory = itemView.findViewById(R.id.btn_category);
            imgPageBack = itemView.findViewById(R.id.imgPageBack);
        }
    }
}
