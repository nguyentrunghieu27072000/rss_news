package com.example.rss_news;

import static java.lang.Integer.parseInt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NewsHelper extends SQLiteOpenHelper {
    private static final String TAG = "ProductDbHelper";
    private static final String DATABASE_NAME = "News.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PAGES = "newsPages";
    private static final String TABLE_CATEGORY = "newsCategory";
    private static final String TABLE_CATEGORY_PAGE = "pageNewsCategory";

    private static final String TABLE_NEWS_FAVORITE = "newsFavorite";
    private Context context;

    public NewsHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCreateTablePage = "CREATE TABLE IF NOT EXISTS " + TABLE_PAGES +"(id INTEGER PRIMARY KEY AUTOINCREMENT, pageName varchar(200), url varchar(200), icon varchar(200), increment int)";
        String queryCreateTableCategory = "CREATE TABLE IF NOT EXISTS "+ TABLE_CATEGORY +"(id INTEGER PRIMARY KEY AUTOINCREMENT, categoryName varchar(200))";
        String queryCreateTableCategoryPage = "CREATE TABLE IF NOT EXISTS " + TABLE_CATEGORY_PAGE +"(pageId int, categoryId int, link varchar(200)) ";
        String queryCreateTableFavorite = "CREATE TABLE IF NOT EXISTS " + TABLE_NEWS_FAVORITE + "(id varchar(250), title varchar(250), image varchar(250), link varchar(250))";
        String queryInsertNewsData = "INSERT INTO "+ TABLE_PAGES +" VALUES (null, '24h.com.vn', 'http://www.24h.com.vn/guest/RSS', 'logo_24h', '1'), " +
                            "(null, 'Thanh Niên', 'http://thanhnien.vn/rss.html', 'logo_bao_thanh_nien', '2'), " +
                            "(null, 'VnExpress', 'http://vnexpress.net/rss', 'logo_vn_express', '3'), " +
                            "(null, 'Dân Trí', 'http://dantri.com.vn/rss.htm', 'logo_dan_tri', '4'), " +
                            "(null, 'Tuổi Trẻ Online', 'https://tuoitre.vn/rss.htm', 'logo_tuoi_tre', '5'), " +
                            "(null, 'SOHA', 'https://soha.vn/rss.htm', 'logo_soha', '6');";
        String queryInsertCategoryData = "INSERT INTO "+ TABLE_CATEGORY + " VALUES (null, 'Thế giới'), (null, 'Thời sự'), (null, 'Kinh tế'), (null, 'Giải trí'), (null, 'Thể thao'), (null, 'Giáo dục');";
        String queryInsertCategoryNewsData = "INSERT INTO "+ TABLE_CATEGORY_PAGE +" VALUES" +
                "(1, 1, 'https://cdn.24h.com.vn/upload/rss/amthuc.rss')," +
                "(1, 2, 'https://24h.com.vn/upload/rss/anninhhinhsu.rss')," +
                "(1, 3, 'https://24h.com.vn/upload/rss/taichinhbatdongsan.rss')," +
                "(1, 4, 'https://24h.com.vn/upload/rss/cuoi24h.rss')," +
                "(1, 5, 'https://24h.com.vn/upload/rss/thethao.rss')," +
                "(1, 6, 'https://24h.com.vn/upload/rss/giaoducduhoc.rss')," +
                "(2, 1, 'https://thanhnien.vn/rss/the-gioi.rss')," +
                "(2, 2, 'https://thanhnien.vn/rss/thoi-su.rss')," +
                "(2, 3, 'https://thanhnien.vn/rss/kinh-te.rss')," +
                "(2, 4, 'https://thanhnien.vn/rss/giai-tri.rss')," +
                "(2, 5, 'https://thanhnien.vn/rss/the-thao.rss')," +
                "(2, 6, 'https://thanhnien.vn/rss/giao-duc.rss')," +
                "(3, 1, 'https://vnexpress.net/rss/the-gioi.rss')," +
                "(3, 2, 'https://vnexpress.net/rss/thoi-su.rss')," +
                "(3, 3, 'https://vnexpress.net/rss/kinh-doanh.rss')," +
                "(3, 4, 'https://vnexpress.net/rss/giai-tri.rss')," +
                "(3, 5, 'https://vnexpress.net/rss/the-thao.rss')," +
                "(3, 6, 'https://vnexpress.net/rss/giao-duc.rss')," +
                "(4, 1, 'https://danviet.vn/rss/the-gioi-1007.rss')," +
                "(4, 2, 'https://danviet.vn/rss/tin-tuc-1001.rss')," +
                "(4, 3, 'https://danviet.vn/rss/kinh-te-1004.rss')," +
                "(4, 4, 'https://danviet.vn/rss/van-hoa-giai-tri-1006.rss')," +
                "(4, 5, 'https://danviet.vn/rss/the-thao-1035.rsshttps://danviet.vn/rss/giao-duc-1196.rss')," +
                "(4, 6, 'https://danviet.vn/rss/giao-duc-1196.rss')," +
                "(5, 1, 'https://tuoitre.vn/rss/the-gioi.rss')," +
                "(5, 2, 'https://tuoitre.vn/rss/thoi-su.rss')," +
                "(5, 3, 'https://tuoitre.vn/rss/kinh-doanh.rss')," +
                "(5, 4, 'https://tuoitre.vn/rss/giai-tri.rss')," +
                "(5, 5, 'https://tuoitre.vn/rss/the-thao.rss')," +
                "(5, 6, 'https://tuoitre.vn/rss/giao-duc.rss')," +
                "(6, 1, 'https://soha.vn/quoc-te.rss')," +
                "(6, 2, 'https://soha.vn/thoi-su-xa-hoi.rss')," +
                "(6, 3, 'https://soha.vn/kinh-doanh.rss')," +
                "(6, 4, 'https://soha.vn/giai-tri.rss')," +
                "(6, 5, 'https://soha.vn/the-thao.rss')," +
                "(6, 6, 'https://soha.vn/song-khoe.rss')";
        sqLiteDatabase.execSQL(queryCreateTablePage);
        sqLiteDatabase.execSQL(queryCreateTableCategory);
        sqLiteDatabase.execSQL(queryCreateTableCategoryPage);
        sqLiteDatabase.execSQL(queryInsertNewsData);
        sqLiteDatabase.execSQL(queryInsertCategoryData);
        sqLiteDatabase.execSQL(queryInsertCategoryNewsData);
        sqLiteDatabase.execSQL(queryCreateTableFavorite);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<PageNews> getAllPage() {
        Bundle tempBundle = new Bundle();

        List<PageNews> list = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor listPageNews = db.rawQuery("SELECT * FROM newsPages ORDER BY increment ASC", null);

        //Đến dòng đầu của tập dữ liệu
        listPageNews.moveToFirst();
        while (!listPageNews.isAfterLast()) {
            String pageId = listPageNews.getString(0);
            String pageName = listPageNews.getString(1);
            int pageLogo = context.getResources().getIdentifier(listPageNews.getString(3), "drawable", context.getPackageName());;
            list.add(new PageNews(parseInt(pageId), pageName, pageLogo));
            listPageNews.moveToNext();
        }

        listPageNews.close();

        return list;
    }

    public List<NewsCategory> getListCategory(int pageId) {
        List<NewsCategory> list = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor listCategory = db.rawQuery("SELECT newsCategory.*, pageNewsCategory.link FROM "+ TABLE_CATEGORY + " INNER JOIN pageNewsCategory ON newsCategory.id = pageNewsCategory.categoryId WHERE pageNewsCategory.pageId =" + pageId + ";", null);
        //Đến dòng đầu của tập dữ liệu
        Cursor listCategoryssss = db.rawQuery("SELECT * FROM "+ TABLE_CATEGORY +";", null);

        listCategory.moveToFirst();
        while (!listCategory.isAfterLast()) {
            String categoryId = listCategory.getString(0);
            String categoryName = listCategory.getString(1);
            String categoryLink = listCategory.getString(2);
            list.add(new NewsCategory(categoryId, categoryName, categoryLink));
            listCategory.moveToNext();
        }

        listCategory.close();

        return list;
    }

    public void delete() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATEGORY, " id > 6", null);
    }

    public void delAll(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

    public boolean addNewsFavorite(News news) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues favorite = new ContentValues();
        favorite.put("id", news.getLink());
        favorite.put("title", news.getTitle());
        favorite.put("image", news.getImg());
        favorite.put("link", news.getLink());
        Log.d(TAG, "vvvvvvvvvvvv"+ news.getImg());
        long result = db.insert(TABLE_NEWS_FAVORITE, null, favorite);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteNewsFavorite(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NEWS_FAVORITE, "id = ?", new String[]{id});
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkNewsFavorite(String id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor newsFavorite = db.rawQuery("SELECT * FROM "+ TABLE_NEWS_FAVORITE +" WHERE id = '" + id + "';", null);
        if(!newsFavorite.moveToFirst()) {
            return false;
        }
        return true;
    }

    public List<NewsFavorite> getListFavorite() {
        List<NewsFavorite> list = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor listPageNews = db.rawQuery("SELECT * FROM newsFavorite", null);

        listPageNews.moveToFirst();
        while (!listPageNews.isAfterLast()) {
            String title = listPageNews.getString(1);
            String image = listPageNews.getString(2);
            String link = listPageNews.getString(3);
            Log.d(TAG, "xxxxxxxxxxxxxxxxxxxxx: " + image);
            list.add(new NewsFavorite(link, title, link, image));
            listPageNews.moveToNext();
        }
        return list;
    }
}
