package com.example.rss_news;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rss_news.Model.Adapters.NewsAdapter;
import com.example.rss_news.Model.Adapters.NewsCategoryAdapter;
import com.example.rss_news.utils.XMLDOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsCategoryActivity extends AppCompatActivity {
    private RecyclerView rcvNewsCategory;
    private NewsCategoryAdapter newsCategoryAdapter;

    private RecyclerView rcvNews;
    private ArrayList<News> mListNews;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_category);
        NewsHelper newsHelper = new NewsHelper(this);
        Bundle bundle = getIntent().getExtras();
        if( bundle == null) {
            return;
        }

        PageNews pageNews = (PageNews) bundle.get("object_page");
        TextView tvPageName = findViewById(R.id.tvPageName);
        tvPageName.setText(pageNews.getPageName());
        ImageView imgPage = findViewById(R.id.imgPageLeft);
        imgPage.setImageResource(pageNews.getPageLogo());

        rcvNewsCategory = findViewById(R.id.rcv_category);
        newsCategoryAdapter = new NewsCategoryAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcvNewsCategory.setLayoutManager(linearLayoutManager);
        List<NewsCategory> listCategoryCurrent = newsHelper.getListCategory(pageNews.getId());
        newsCategoryAdapter.setData(listCategoryCurrent, pageNews);
        rcvNewsCategory.setAdapter(newsCategoryAdapter);

        BackToHome();

        InitializeUI();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String CategoryLinkCurrent = listCategoryCurrent.get(0).categoryLink.toString();
                String newsCategory = (String) bundle.get("link");
                if(newsCategory != null) {
                    CategoryLinkCurrent = newsCategory;
                }
                new getListNews().execute(CategoryLinkCurrent);
            }
        });
    }

    private void BackToHome() {
        ImageView imgBackHome = findViewById(R.id.imgPageBack);
        imgBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            };
        });
    }

    private void InitializeUI() {
        rcvNews = findViewById(R.id.rcv_news);
        mListNews = new ArrayList<>();
        newsAdapter = new NewsAdapter(this, mListNews);

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        rcvNews.setLayoutManager(linearLayout);
        rcvNews.setAdapter(newsAdapter);
    }

    class getListNews extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            return getData(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeListItem = document.getElementsByTagName("item");
            NodeList nodeListDescription = document.getElementsByTagName("description");
            int countItem =  nodeListItem.getLength();
            int countDescription = nodeListDescription.getLength();
            int diff = countDescription - countItem;
            for (int i = 0; i < nodeListItem.getLength(); i++) {
                Element element = (Element) nodeListItem.item(i);
                NodeList titleNode = element.getElementsByTagName("title");
                Element TitleEle = (Element) titleNode.item(0);
                String title = TitleEle.getFirstChild().getNodeValue();
                NodeList linkNode = element.getElementsByTagName("link");
                Element LinkEle = (Element) linkNode.item(0);
                String link = LinkEle.getFirstChild().getNodeValue();
                String img = "";
                String description = nodeListDescription.item(i + diff).getTextContent();
                Log.d(TAG, "aaaaaaaaaaaaa: "+ description);
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(description);
                if (matcher.find())
                    img = matcher.group(1);
                Log.d(TAG, "bbbbbbbb" + img);
                News tinTuc = new News(title, link, img);
                mListNews.add(tinTuc);
            }
            newsAdapter.notifyDataSetChanged();
            super.onPostExecute(s);
        }

        protected String getData(String theUrl) {
            StringBuilder content = new StringBuilder();
            try {
                // create a url object
                URL url = new URL(theUrl);
                // create a urlconnection object
                URLConnection urlConnection = url.openConnection();
                // wrap the urlconnection in a bufferedreader
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                // read from the urlconnection via the bufferedreader
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line + "\n");
                }
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return content.toString();
        }
    }
}