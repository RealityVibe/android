package com.example.yyyyz.winnerblog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Yyyyz on 2018/1/21.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder>{
    private List<Article> mArticleList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView content;
        TextView title;

        public ViewHolder(View view){
            super(view);
           title = (TextView)view.findViewById(R.id.title_textview);
           content = (TextView)view.findViewById(R.id.content_textview);
        }

    }

    public ArticleAdapter(List<Article> articleList){
        mArticleList = articleList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_view_item
                , parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Article article = mArticleList.get(position);

        holder.title.setText(article.getTitle());
        holder.content.setText(article.getContent());
    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }
}
