package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ArticleAdapter.OnRowClickListener{
    //widgets on page
    RecyclerView bannerArticles;
    //initialize list variable
    List<Article> articleList = new ArrayList<>();


    //declare list variables for each article element (Lorem ipsum text generated via https://www.lipsum.com)
    String[] headingList = {"Lorem", "Lorem Ipsum", "Lorem IPSUM", "LOREM ipsum", "LOREM IPSUM", "LoReM iPsUm"};
    String[] articleBodyList =
            {
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer arcu tellus, posuere ac " +
                            "lacinia a, laoreet eu eros. Curabitur tempor augue in augue facilisis posuere. " +
                            "Nunc nisl est, commodo non fringilla ac, aliquet a neque. Proin molestie felis " +
                            "eget enim volutpat, in aliquam turpis accumsan. Aliquam erat volutpat. Vestibulum" +
                            " a massa non dui blandit semper sit amet nec lorem. Quisque scelerisque euismod " +
                            "suscipit. Aenean dapibus eleifend ligula, a finibus massa scelerisque non. " +
                            "Maecenas maximus vulputate ligula id rhoncus. In et.",

                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nisi eros, auctor sed facilisis nec, " +
                            "porttitor nec ante. Sed at vehicula magna. Vestibulum eu mattis nunc, cursus ultrices lorem. Vestibulum.",

                    "Nam sodales massa ac nibh maximus, ac imperdiet mi imperdiet. Praesent interdum facilisis tortor, at egestas metus mollis et. " +
                            "Mauris vehicula, orci eu semper porta, sem risus accumsan velit, sit amet dapibus justo nisl nec sapien. Nulla commodo, est " +
                            "eu sodales scelerisque, elit dolor varius ligula, vitae posuere ligula augue non massa. Proin eu tristique sem, nec placerat ante. " +
                            "Phasellus vestibulum venenatis nulla, nec maximus elit vehicula id. Nulla a accumsan lacus",

                    "Interdum et malesuada fames ac ante ipsum primis in faucibus. Aliquam finibus bibendum sollicitudin. Etiam vel molestie diam, eu dictum sapien. " +
                            "Curabitur vitae risus a nisi cursus ultrices. Duis id augue elit. Donec ornare posuere gravida. Nunc leo enim, placerat id libero a, tempus " +
                            "malesuada justo. Etiam vitae viverra justo. Vestibulum blandit nec felis vel dapibus. Aenean viverra augue ac ipsum dictum pharetra. In id dui " +
                            "ultricies nulla volutpat auctor vel sed velit. Curabitur quis vehicula libero, vitae scelerisque felis.",

                    "Sed posuere justo tortor, a egestas est finibus commodo. Nam tempor felis quam, in malesuada odio porta quis. Proin egestas, purus et faucibus vestibulum, " +
                            "neque arcu bibendum eros, eget posuere leo ligula ac nibh. Mauris venenatis consectetur magna ut fermentum. Quisque scelerisque ligula eros, id molestie " +
                            "libero malesuada et. Donec ultricies a metus sed volutpat. Fusce ut nunc vel elit malesuada sodales. Curabitur et sapien ipsum.",

                    "Praesent id diam et leo feugiat gravida. Nullam pharetra justo sed massa eleifend rhoncus. Vivamus volutpat ornare nunc. Donec a nulla vel lectus mollis congue eu vitae " +
                            "diam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Vestibulum scelerisque at nisi sit amet porttitor. Nam a nunc velit. " +
                            "Nam nisl urna, pellentesque vitae augue et, feugiat euismod nulla. Quisque volutpat lacus magna, in faucibus nisl vehicula quis. Aliquam egestas eu ante quis pellentesque. " +
                            "Duis sed dolor vel enim scelerisque vestibulum. Cras dignissim semper purus vel molestie. Pellentesque ut nulla non augue varius consectetur. Duis lobortis rhoncus nunc, " +
                            "sed elementum nulla elementum a. Nullam viverra, sem ac scelerisque accumsan, leo dui suscipit tortor, eget egestas lacus purus ut enim."
            };

    //get integer id values for images in Resoources.mipmap
    int[] imageIds = {R.mipmap.ic_image1, R.mipmap.ic_image2, R.mipmap.ic_image3, R.mipmap.ic_image4, R.mipmap.ic_image5, R.mipmap.ic_image6};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bannerArticles = findViewById(R.id.bannerRecycler);




        //onclick listener for each tile which passes its id to the onTileClick method
        LinearLayout topLeftLayout = findViewById(R.id.topLeftLayout);
        topLeftLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTileClick(view);
            }
        });

        LinearLayout topRightLayout = findViewById(R.id.topRightLayout);
        topRightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTileClick(view);
            }
        });

        LinearLayout bottomLeftLayout = findViewById(R.id.bottomLeftLayout);
        bottomLeftLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTileClick(view);
            }
        });

        LinearLayout bottomRightLayout = findViewById(R.id.bottomRightLayout);
        bottomRightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTileClick(view);
            }
        });


        //create the adapter and set it on the RecyclerView
        ArticleAdapter articleAdapter = new ArticleAdapter(articleList, this, this);
        bannerArticles.setAdapter(articleAdapter);

        //set the layout manager with a horizontal orientation
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        bannerArticles.setLayoutManager(layoutManager);

        //add articles to the list using variables declared earlier (lists of article elements required for constructor)
        for (int i = 0; i < headingList.length; i++) {
            Article article = new Article(headingList[i], articleBodyList[i], imageIds[i], i);
            articleList.add(article);
        }
    }
    //tileClick called with the clicked tile in each on click listener (and commented out original method to make sure this worked before progressing
    //private void onTileClick(View view) {
    //    Toast.makeText(this, "Tile clicked:", Toast.LENGTH_SHORT).show();
    //}
    private void onTileClick(View view) {
        //declare fragment
        Fragment fragment;
        //declare variables passed to fragment
        String[] relatedHeadings = new String[5];
        String[] relatedBody = new String[5];
        int[] relatedImages = new int[5];
        //index value for populating arrays
        int index = 0;

        String heading;
        //declared arbitrary values to eliminate may not have been declared error
        String body = null;
        int imageRef = 0;
        //get id to determine which tile was clicked
        int id = view.getId();
        if (id == R.id.topLeftLayout)
        {
            heading = "Lorem";
        }
        else if(id == R.id.topRightLayout)
        {
            heading = "Lorem Ipsum";
        }
        else if (id == R.id.bottomLeftLayout)
        {
            heading = "Lorem IPSUM";
        }
        else
        {
            heading = "LOREM ipsum"   ;
        }
        //add the values to the corresponding tile clicked to the relevant variables, add all other to the list for related
        for (int i= 0; i < headingList.length; i++)
        {
            if (headingList[i] == heading)
            {
                body = articleBodyList[i];
                imageRef = imageIds[i];
            }
            else
            {
                relatedHeadings[index] = headingList[i];
                relatedBody[index] = articleBodyList[i];
                relatedImages[index] = imageIds[i];
                index++;
            }
        }
        //use subclass articleFragment to pass parameters, fragment manager and transaction to instantiate
        fragment = articleFragment.newInstance(relatedHeadings, relatedBody, relatedImages, imageRef, heading, body);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment)
                //add to backstack to articles can be back traversed, commit
                .addToBackStack(null)
                .commit();

    }


    @Override
    public void onItemClick(int position) {
        //again,ensure it is working before continuing
        //Toast.makeText(this, "CLICKED TILE", Toast.LENGTH_SHORT).show();
        //declare fragment
        Fragment fragment;
        //declare variables passed to fragment
        String[] relatedHeadings = new String[5];
        String[] relatedBody = new String[5];
        int[] relatedImages = new int[5];
        //index value for populating arrays
        int index = 0;


        //declared arbitrary values to eliminate may not have been declared error
        String body = null;
        int imageRef = 0;
        String heading = null;

        for (int i = 0; i < headingList.length; i++) {
            if (i == position) {
                heading = headingList[i];
                body = articleBodyList[i];
                imageRef = imageIds[i];
            } else {
                relatedHeadings[index] = headingList[i];
                relatedBody[index] = articleBodyList[i];
                relatedImages[index] = imageIds[i];
                index++;
            }
        }
        //use subclass articleFragment to pass parameters, fragment manager and transaction to instantiate
        fragment = articleFragment.newInstance(relatedHeadings, relatedBody, relatedImages, imageRef, heading, body);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment)
                .commit();
    }
}
