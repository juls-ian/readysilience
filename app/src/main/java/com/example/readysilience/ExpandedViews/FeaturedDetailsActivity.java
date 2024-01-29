package com.example.readysilience.ExpandedViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.readysilience.EmergencySupplyFrag;
import com.example.readysilience.MainActivity;
import com.example.readysilience.R;

import org.w3c.dom.Text;

public class FeaturedDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_featured_details);

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //1
        ImageView imageView = findViewById(R.id.article_pic);
        TextView titleTextView = findViewById(R.id.headline);
        TextView introTextView = findViewById(R.id.intro_text);

        //A
        TextView parentSubheadTV1 = findViewById(R.id.parent_subhead1);
        //B
        TextView childSubheadTV1 = findViewById(R.id.child_subhead1);
        //C
        TextView childSubheadTV1Details1 = findViewById(R.id.child_subhead1_details1);
        //D
        ImageView childSubhead1Pic = findViewById(R.id.child_subhead1_pic);
        //E
        TextView childSubheadTV1Details2 = findViewById(R.id.child_subhead1_details2);
        //F
        TextView childSubheadTV2 = findViewById(R.id.child_subhead2);
        //G
        TextView childSubheadTV2Details = findViewById(R.id.second_subheading2_text);
        //H
        TextView parentSubheadTV2 = findViewById(R.id.parent_subhead2);
        //I
        ImageView parentSubheadTV2Pic = findViewById(R.id.parent_subhead2_pic);
        //J
        TextView parentSubheadTV2Detail = findViewById(R.id.parent_subhead2_details);
        //K
        TextView parentSubheadTV3 = findViewById(R.id.parent_subhead3);
        //L
        ImageView parentSubheadTV3Pic = findViewById(R.id.parent_subhead3_pic);
        //M
        TextView parentSubheadTV3Detail = findViewById(R.id.parent_subhead3_details);
        //N
        TextView parentSubheadTV4 = findViewById(R.id.parent_subhead4);
        //O
        TextView parentSubheadTV4Child1 = findViewById(R.id.child_subhead3);
        //P
        TextView parentSubheadTV4Child1Detail = findViewById(R.id.child_subhead3_details);
        //Q
        TextView parentSubheadTV4Child2 = findViewById(R.id.child_subhead4);
        //R
        TextView parentSubheadTV4Child2Detail = findViewById(R.id.child_subhead4_details);
        //S
        TextView parentSubheadTV4Child3 = findViewById(R.id.child_subhead5);
        //T
        TextView parentSubheadTV4Child3Detail = findViewById(R.id.child_subhead5_details);
        //U
        TextView parentSubheadTV4Child4 = findViewById(R.id.child_subhead6);
        //V
        TextView parentSubheadTV4Child4Detail = findViewById(R.id.child_subhead6_details);






        //2
        // Retrieve data from Intent - CONNECTED TO ADAPTERFEATURED.JAVA
        Intent intent = getIntent();
        String articleImageString = intent.getStringExtra("articlePic");
        String headlineString = intent.getStringExtra("headline");
        String introString = intent.getStringExtra("introduction");

        //A
        String parentSubheadTV1String = intent.getStringExtra("parentSubhead1");
        //B
        String childSubheadTV1String = intent.getStringExtra("childSubhead1");
        //C
        String childSubheadTV1Details1String = intent.getStringExtra("childSubheadDetails1");
        //D
        String childSubhead1PicString = intent.getStringExtra("childSubhead1Pic");
        //E
        String childSubheadTV1Details2String = intent.getStringExtra("childSubheadDetails2");
        //F
        String childSubheadTV2String = intent.getStringExtra("childSubhead2");
        //G
        String childSubheadTV2DetailsString = intent.getStringExtra("childSubhead2Details");
        //H
        String parentSubheadTV2String = intent.getStringExtra("parentSubhead2");
        //I
        String parentSubheadTV2PicString = intent.getStringExtra("parentSubhead2Pic");
        //J
        String parentSubheadTV2DetailString = intent.getStringExtra("parentSubhead2Detail");
        //K
        String parentSubheadTV3String = intent.getStringExtra("parentSubhead3");
        //L
        String parentSubheadTV3PicString = intent.getStringExtra("parentSubhead3Pic");
        //M
        String parentSubheadTV3DetailString = intent.getStringExtra("parentSubhead3Detail");
        //N
        String parentSubheadTV4String = intent.getStringExtra("parentSubhead4");
        //O
        String parentSubheadTV4Child1String = intent.getStringExtra("subhead4ChildSubhead1");
        //P
        String parentSubheadTV4Child1DetailString = intent.getStringExtra("subhead4ChildSubhead1Detail");
        //Q
        String parentSubheadTV4Child2String = intent.getStringExtra("subhead4ChildSubhead2");
        //R
        String parentSubheadTV4Child2DetailString = intent.getStringExtra("subhead4ChildSubhead2Detail");
        //S
        String parentSubheadTV4Child3String = intent.getStringExtra("subhead4ChildSubhead3");
        //T
        String parentSubheadTV4Child3DetailString = intent.getStringExtra("subhead4ChildSubhead3Detail");
        //U
        String parentSubheadTV4Child4String = intent.getStringExtra("subhead4ChildSubhead4");
        //V
        String parentSubheadTV4Child4DetailString = intent.getStringExtra("subhead4ChildSubhead4Detail");


        //3 - GET THE VARIABLES FROM 1 & 2
        // Set data in TextViews
        Glide.with(this)
                .load(articleImageString)
                .placeholder(R.drawable.image_icon)
                .into(imageView);

        titleTextView.setText(headlineString);
        introTextView.setText(introString);

        //A
        parentSubheadTV1.setText(parentSubheadTV1String);
        //B
        childSubheadTV1.setText(childSubheadTV1String);
        //C
        childSubheadTV1Details1.setText(childSubheadTV1Details1String);
        //D
        Glide.with(this)
                .load(childSubhead1PicString)
                .placeholder(R.drawable.image_icon)
                .into(childSubhead1Pic);

        //E
        childSubheadTV1Details2.setText(childSubheadTV1Details2String);
        //F
        childSubheadTV2.setText(childSubheadTV2String);
        //G
        childSubheadTV2Details.setText(childSubheadTV2DetailsString);
        //H
        parentSubheadTV2.setText(parentSubheadTV2String);
        //I
        Glide.with(this)
                .load(parentSubheadTV2PicString)
                .placeholder(R.drawable.image_icon)
                .into(parentSubheadTV2Pic);
        //J
        parentSubheadTV2Detail.setText(parentSubheadTV2DetailString);
        //K
        parentSubheadTV3.setText(parentSubheadTV3String);
        //L
        Glide.with(this)
                .load(parentSubheadTV3PicString)
                .placeholder(R.drawable.image_icon)
                .into(parentSubheadTV3Pic);
        //M
        parentSubheadTV3Detail.setText(parentSubheadTV3DetailString);
        //N
        parentSubheadTV4.setText(parentSubheadTV4String);
        //O
        parentSubheadTV4Child1.setText(parentSubheadTV4Child1String);
        //P
        parentSubheadTV4Child1Detail.setText(parentSubheadTV4Child1DetailString);
        //Q
        parentSubheadTV4Child2.setText(parentSubheadTV4Child2String);
        //R
        parentSubheadTV4Child2Detail.setText(parentSubheadTV4Child2DetailString);
        //S
        parentSubheadTV4Child3.setText(parentSubheadTV4Child3String);
        //T
        parentSubheadTV4Child3Detail.setText(parentSubheadTV4Child3DetailString);
        //U
        parentSubheadTV4Child4.setText(parentSubheadTV4Child4String);
        //V
        parentSubheadTV4Child4Detail.setText(parentSubheadTV4Child4DetailString);

    }
}