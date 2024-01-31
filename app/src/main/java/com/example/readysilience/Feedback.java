package com.example.readysilience;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {

    private EditText userCommentEditText, userSuggestionEditText;
    private RatingBar userRatingBar;
    private Button feedbackSubmitButton;

    private DatabaseReference feedbackReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            finish();
            return;
        }

        feedbackReference = FirebaseDatabase.getInstance().getReference("User Feedback").child(currentUser.getUid());

        ImageButton backButton = findViewById(R.id.back_button);

        userCommentEditText = findViewById(R.id.user_comment_edit_text);
        userSuggestionEditText = findViewById(R.id.user_suggestions_edit_text);
        userRatingBar = findViewById(R.id.user_rating_bar);
        feedbackSubmitButton = findViewById(R.id.feedback_submit_button);



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Feedback.this, MainActivity.class));
                finish();
            }
        });

        feedbackSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userComment = userCommentEditText.getText().toString().trim();
                String userSuggestions = userSuggestionEditText.getText().toString().trim();
                float userRating = userRatingBar.getRating();

                FeedbackModel feedbackModel = new FeedbackModel(userComment, userSuggestions, userRating);

                feedbackReference.push().setValue(feedbackModel);

                Intent successIntent = new Intent(Feedback.this, SuccessFeedbackSent.class);
                startActivity(successIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Start MainActivity when the back button is pressed
        super.onBackPressed();
        startActivity(new Intent(Feedback.this, MainActivity.class));
        finish();
    }

    public class FeedbackModel {
        private String comment;
        private String suggestions;
        private float rating;

        public FeedbackModel(String comment, String suggestions, float rating) {
            this.comment = comment;
            this.suggestions = suggestions;
            this.rating = rating;
        }

        public String getComment() {
            return comment;
        }

        public String getSuggestions() {
            return suggestions;
        }

        public float getRating() {
            return rating;
        }
    }

}