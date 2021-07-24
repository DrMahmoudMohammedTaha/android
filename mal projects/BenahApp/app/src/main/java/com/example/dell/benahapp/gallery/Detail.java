package com.example.dell.benahapp.gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.benahapp.R;
import com.example.dell.benahapp.font_factory;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class Detail extends AppCompatActivity implements Serializable{

    ImageView imageView;
    TextView description;
    TextView loves;
    TextView hates;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_detail);

        imageView = (ImageView) findViewById(R.id.image);
        description = (TextView) findViewById(R.id.description);
        loves = (TextView) findViewById(R.id.lovesTV);
        hates = (TextView) findViewById(R.id.hatesTV);

        if(getIntent().getSerializableExtra("item") != null) {

            Item item = (Item) getIntent().getSerializableExtra("item");

            Picasso.with(this)
                    .load(item.img)
                    .placeholder(R.drawable.rogue)
                    .error(android.R.drawable.stat_notify_error)
                    .into(imageView);


            description.setText(item.text);
            loves.setText(item.loves + " People love this.");
            hates.setText(item.hates + " People hate this.");

            font_factory.stylish_it2(this,loves , hates);
        }
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
