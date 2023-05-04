package com.example.rss_news;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        NewsHelper newsHelper = new NewsHelper(this, "News.sqlite", null, 1);
//        newsHelper.QueryData("CREATE TABLE IF NOT EXISTS newsPages(Id INTEGER PRIMARY KEY AUTOINCREMENT, pageName varchar(200), url varchar(200), icon varchar(200), increment int)");
//
//        newsHelper.QueryData("INSERT INTO newsPages VALUES " +
//                "(null, '24h.com.vn', 'http://www.24h.com.vn/guest/RSS', 'logo_24h.png', '1'), " +
//                "(null, 'Thanh Niên', 'http://thanhnien.vn/rss.html', 'logo_bao_thanh_nien.jpg', '2'), " +
//                "(null, 'VnExpress', 'http://vnexpress.net/rss', 'logo_vn_express.png', '3'), " +
//                "(null, 'Dân Trí', 'http://dantri.com.vn/rss.htm', 'logo_dan_tri.jpg', '4'), " +
//                "(null, 'Tuổi Trẻ Online', 'https://tuoitre.vn/rss.htm', 'logo_tuoi_tre.png', '5'), " +
//                "(null, 'SOHA', 'https://soha.vn/rss.htm', 'logo_soha.webp', '6');");
//newsHelper.QueryData("DELETE FROM newsPages");
//        Cursor listPageNews = newsHelper.GetData("SELECT * FROM newsPages ORDER BY increment ASC");
//        while (listPageNews.moveToNext()) {
//            String pageId = listPageNews.getString(0);
//            String pageName = listPageNews.getString(1);
//            String pageLogo = listPageNews.getString(4);
//            Toast.makeText(this,pageName, Toast.LENGTH_SHORT).show();
//        }
    }
}