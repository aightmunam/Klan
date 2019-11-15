package com.example.klanblogger.ReadingList;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.klanblogger.Article;
import com.example.klanblogger.ArticleAdapter;
import com.example.klanblogger.R;

import java.util.ArrayList;
import java.util.List;

public class ArchivedFragment extends Fragment {
    RecyclerView articleRecyclerView;
    ArticleAdapter articleAdapter;

    ArchivedFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.saved_article_layout, container, false);
        List<Article> dummyArticleList = getDummyArticles();
        articleRecyclerView = (RecyclerView) view.findViewById(R.id.article_recycler);
        articleRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        articleAdapter = new ArticleAdapter(getContext(), dummyArticleList);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        articleRecyclerView.setLayoutManager(verticalLayoutManager);
        articleRecyclerView.setAdapter(articleAdapter);

        return view;
    }

    private List<Article> getDummyArticles(){
        List<Article> temp = new ArrayList<Article>();
        Article t;
        String name;
        String author;
        for (int i = 0; i < 1; i++) {
            name = "Article " + (i + 1);
            author = "Author " + (i+1);
            t = new Article(name, "Some dummy description", author);
            temp.add(t);
        }
        return temp;
    }
}
