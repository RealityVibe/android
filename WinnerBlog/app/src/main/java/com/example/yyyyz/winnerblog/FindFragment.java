package com.example.yyyyz.winnerblog;

;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindFragment extends Fragment {
    private RecyclerView articleViews;
    private View view;
    private List<String> titleList = new ArrayList<String>();
    private List<String> contentList = new ArrayList<String>();
    private List<Article> articleList = new ArrayList<Article>();
    @Override
    public View onCreateView(LayoutInflater inflate, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflate.inflate(R.layout.activity_find_fragment, container, false);

//        initArticleView();
        Button btn = (Button)view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), WebViewActivity.class));
            }
        });
        return view;
    }

    private void initArticleView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("https://www.douban.com/explore").get();
                    Log.d("豆瓣链接测试成功", "豆瓣链接测试成功");
                    System.out.println("豆瓣链接测试成功");
                    Elements e = doc.getElementsByClass("item");
                    int count = 0;
                    for (Element item:e) {
                        Elements links = item.select("a[href]");
                        for (Element link : links) {
                            articleList.add(new Article(link.attr("href"), link.text() ));
                            count++;
                        }
                    }
                    System.out.print("***" + String.valueOf(count));
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    updateRecyclerView();
                }
            }
        }).start();

        for ( int i =0 ;i<10;++i) {
            articleList.add(new Article("标题", "内容"));
        }
        articleViews = (RecyclerView)view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        articleViews.setLayoutManager(layoutManager);
        ArticleAdapter adapter = new ArticleAdapter(articleList);
        articleViews.setAdapter(adapter);

    }

    private void updateRecyclerView(){
        getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run(){
                articleViews.notifyAll();
            }
        });
    }

}
