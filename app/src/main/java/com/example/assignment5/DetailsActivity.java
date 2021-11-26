package com.example.assignment5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {
    private Book details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getDataFromIntent();
    }

    private void getDataFromIntent() {
        if (getIntent().getExtras() != null &&
                getIntent().getExtras().getParcelable("BOOK_DETAILS") != null) {
            details = getIntent().getExtras().getParcelable("BOOK_DETAILS");
            setUpToolbar();
            setDataToViews();
        }
    }

    private void setUpToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(details.getTitle());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void setDataToViews() {
        ((TextView) findViewById(R.id.tv_author_name)).setText(String.format("Author Name: %s",
                details.getAuthorName()));
        ((TextView) findViewById(R.id.tv_publisher_name)).setText(String.format("Publisher Name: %s",
                details.getPublisherName()));
        ((TextView) findViewById(R.id.tv_available_copies)).setText(String.format("Available Copies: %s",
                details.getCopiesAvailable()));
        ((Button) findViewById(R.id.btn_borrow)).setOnClickListener(view -> {
            if (details.getCopiesAvailable() > 0) {
                details.setCopiesAvailable(details.getCopiesAvailable() - 1);
                ((TextView) findViewById(R.id.tv_available_copies)).setText(String.format("Available Copies: %s",
                        details.getCopiesAvailable()));
            } else {
                Toast.makeText(this, "No more books left of this type", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("TITLE", details.getTitle());
        returnIntent.putExtra("NO_OF_COPIES", details.getCopiesAvailable());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
        return true;
    }
}