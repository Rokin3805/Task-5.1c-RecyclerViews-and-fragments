package com.example.newsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link articleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class articleFragment extends Fragment implements ArticleAdapter.OnRowClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
    private static final String ARG_PARAM6 = "param6";

    private String[] listHeadings;
    private String[] listBody;
    private int[] listImageRef;
    private int thisImageRef;
    private String thisHeading;
    private String thisBody;

    public articleFragment() {
        // Required empty public constructor
    }

    public static articleFragment newInstance(String[] listHeadings, String[] listBody, int[] listImageRef, int thisImageRef, String thisHeading, String thisBody) {
        articleFragment fragment = new articleFragment();
        Bundle args = new Bundle();
        args.putStringArray(ARG_PARAM1, listHeadings);
        args.putStringArray(ARG_PARAM2, listBody);
        args.putIntArray(ARG_PARAM3, listImageRef);
        args.putInt(ARG_PARAM4, thisImageRef);
        args.putString(ARG_PARAM5, thisHeading);
        args.putString(ARG_PARAM6, thisBody);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listHeadings = getArguments().getStringArray(ARG_PARAM1);
            listBody = getArguments().getStringArray(ARG_PARAM2);
            listImageRef = getArguments().getIntArray(ARG_PARAM3);
            thisImageRef = getArguments().getInt(ARG_PARAM4);
            thisHeading = getArguments().getString(ARG_PARAM5);
            thisBody = getArguments().getString(ARG_PARAM6);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflate the layout to create a new view
        View newView = inflater.inflate(R.layout.fragment_article, container, false);
        //get relevant widget ids from xml file
        ImageView imageView = newView.findViewById(R.id.imageView);
        TextView articleTitle = newView.findViewById(R.id.articleTitle);
        TextView articleBody = newView.findViewById(R.id.articleBody);

        //assign widgets relevant values passed to fragment
        articleTitle.setText(thisHeading);
        articleBody.setText(thisBody);
        imageView.setImageResource(thisImageRef);

        //recycler view widget assignment, layout manager and adapter
        RecyclerView recyclerView = newView.findViewById(R.id.relatedRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Article> articleList = new ArrayList<>();
        ArticleAdapter adapter = new ArticleAdapter(articleList, getActivity(), this);
        recyclerView.setAdapter(adapter);
        //articleList population for related articles
        for (int i = 0; i < listHeadings.length; i++)
        {
            Article article = new Article(listHeadings[i], listBody[i], listImageRef[i], i);
            articleList.add(article);
        }
        //ATTEMPT TO ADD HEADINGS, WHICH FOR SOME REASON DOES NOT WORK EVEN THOUGH THIS CODE WORKS IN MAIN
        //for (int i = 0; i < articleList.size(); i ++)
        //{
        //    Article article = articleList.get(i);
        //    article.setArticleHeading(listHeadings[i]);
        //    articleList.remove(i);
        //    articleList.add(article);
        //}
        return newView;
    }

    @Override
    public void onItemClick(int position) {
        //again,ensure it is working before continuing
        //Toast.makeText(this, "CLICKED TILE", Toast.LENGTH_SHORT).show();
        //declare fragment
        //again,ensure it is working before continuing
        //Toast.makeText(this, "CLICKED TILE", Toast.LENGTH_SHORT).show();
        //declare fragment
        Fragment fragment;
        //get new array length
        int newLength = listHeadings.length -1;
        //declare variables passed to fragment


        String[] relatedHeadings = new String[newLength];
        String[] relatedBody = new String[newLength];
        int[] relatedImages = new int[newLength];
        //index value for populating arrays
        int index = 0;


        //declared arbitrary values to eliminate may not have been declared error
        String body = null;
        int imageRef = 0;
        String heading = null;

        for (int i = 0; i < listHeadings.length; i++) {
            if (i == position) {
                heading = listHeadings[i];
                body = listBody[i];
                imageRef = listImageRef[i];
            } else {
                relatedHeadings[index] = listHeadings[i];
                relatedBody[index] = listBody[i];
                relatedImages[index] = listImageRef[i];
                index++;
            }
        }
        //use subclass articleFragment to pass parameters, fragment manager and transaction to instantiate
        fragment = articleFragment.newInstance(relatedHeadings, relatedBody, relatedImages, imageRef, heading, body);
        //use get childFragmentManager to continue generate fragment within fragment
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment)
                .commit();

    }
}



