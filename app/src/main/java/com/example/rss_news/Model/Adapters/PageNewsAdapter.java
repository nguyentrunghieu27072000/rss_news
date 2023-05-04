package com.example.rss_news.Model.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rss_news.NewsCategory;
import com.example.rss_news.NewsCategoryActivity;
import com.example.rss_news.PageNews;
import com.example.rss_news.R;

import java.util.List;

public class PageNewsAdapter extends RecyclerView.Adapter<PageNewsAdapter.PageNewsHolder>{

    private List<PageNews> mListPage;
    private Context mContext;

    public PageNewsAdapter(Context context, List<PageNews> mListPage) {
        this.mContext = context;
        this.mListPage = mListPage;
    }

    @NonNull
    @Override
    public PageNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_page_item, parent, false);
        return new PageNewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PageNewsHolder holder, int position) {
        PageNews pageNews = mListPage.get(position);
        if (pageNews == null) {
            return;
        }

        holder.imgPage.setImageResource(pageNews.getPageLogo());
        holder.tvPageName.setText(pageNews.getPageName());

        holder.cvPageItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoTOCategory(pageNews);
            }
        });
    }

    private void onClickGoTOCategory(PageNews pageNews) {
        Intent intent = new Intent(mContext, NewsCategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_page", pageNews);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (mListPage != null) {
            return  mListPage.size();
        }
        return 0;
    }

    public class PageNewsHolder extends RecyclerView.ViewHolder {
        private CardView cvPageItem;
        private ImageView imgPage;
        private TextView tvPageName;

        public PageNewsHolder(@NonNull View itemView) {
            super(itemView);

            imgPage = itemView.findViewById(R.id.imgPage);
            tvPageName = itemView.findViewById(R.id.tvPageName);
            cvPageItem = itemView.findViewById(R.id.cvPageItem);
        }
    }
}
