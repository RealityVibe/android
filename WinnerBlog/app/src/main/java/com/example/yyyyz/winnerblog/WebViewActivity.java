package com.example.yyyyz.winnerblog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class WebViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Article> articleList = new ArrayList<Article>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

      /*  WebView webView = (WebView)findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.baidu.com");*/

        initData();


    }
    private void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    org.jsoup.nodes.Document doc = Jsoup.connect("https://www.douban.com/explore").get();
                    Elements e = doc.getElementsByClass("item");
                    System.out.println("lenth:" + String.valueOf(e.size()));
                    for (Element item : e){
                        Elements links = item.select("a[href]");
                        for (Element link : links) {
                            articleList.add(new Article(link.attr("href"), link.text() ));
                            System.out.println("jsoupInfo" + link.attr("href"));
//                            count++;
                        }
                    }
                    updateView();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void updateView(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView = (RecyclerView) findViewById(R.id.recyclerview_test);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WebViewActivity.this);
                ArticleAdapter adapter = new ArticleAdapter(articleList);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
                ListView tmp_view = (ListView)findViewById(R.id.recyclerview_test);
            }
        });
    }
}
