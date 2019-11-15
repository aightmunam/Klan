package com.example.klanblogger;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class StoriesFragment extends Fragment {
    private RecyclerView articleRecyclerView;
    private ArticleAdapter articleAdapter;

    public StoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stories, container, false);
        List<Article> dummyArticleList = getDummyArticles();
        articleRecyclerView = (RecyclerView) view.findViewById(R.id.stories_recycler);
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
        for (int i = 0; i < 8; i++) {
            name = "Article " + (i + 1);
            author = "Profile name ";
            t = new Article(name, "Some dummy description", author);
            temp.add(t);
        }
        return temp;
    }

}
