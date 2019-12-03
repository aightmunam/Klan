package com.example.klanblogger;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView topicRecyclerView;
    private RecyclerView articleRecyclerView;
    private TopicAdapter topicAdapter;
    private ArticleAdapter articleAdapter;
    FirebaseFirestore databaseReference;
    FirebaseAuth auth;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        List<Topic> dummyTopicList = getDummyTopics();
        topicRecyclerView = (RecyclerView) view.findViewById(R.id.topics_recycler);
        topicAdapter = new TopicAdapter(getContext(), dummyTopicList);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        topicRecyclerView.setLayoutManager(horizontalLayoutManager);
        topicRecyclerView.setAdapter(topicAdapter);

        List<Article> dummyArticleList = getDummyArticles();
        articleRecyclerView = (RecyclerView) view.findViewById(R.id.article_recycler);
        articleRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        articleAdapter = new ArticleAdapter(getContext(), dummyArticleList);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        articleRecyclerView.setLayoutManager(verticalLayoutManager);
        articleRecyclerView.setAdapter(articleAdapter);

        return view;
    }

    private List<Topic> getDummyTopics() {
        List<Topic> temp = new ArrayList<Topic>();
        Topic t;
        String name;
        for (int i = 0; i < 15; i++) {
            name = "Topic " + (i + 1);
            t = new Topic(name);
            temp.add(t);
        }
        return temp;
    }

    private List<Article> getDummyArticles() {
        final List<Article> temp = new ArrayList<Article>();
        Article t;
        String name;
        String author;

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseFirestore.getInstance();
        CollectionReference collectionReference;
        collectionReference = databaseReference.collection("articles");
        Query userDetailQuery = collectionReference.whereEqualTo("author", auth.getCurrentUser().getUid());
        userDetailQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Article article = document.toObject(Article.class);
                        temp.add(article);
                    }
                }
            }
        });


        for (int i = 0; i < 25; i++) {
            name = "Article " + (i + 1);
            author = "Author " + (i + 1);
            t = new Article(name, "Some dummy description", author);
            temp.add(t);
        }
        return temp;

    }

}
