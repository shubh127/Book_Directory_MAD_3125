package com.example.assignment5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Map<String, List<Book>> booksMap = new HashMap<>();
    private Spinner spCategories;
    private Spinner spBooks;
    private ImageView ivBookCover;
    private List<Book> selectedCategory;
    private Button btnSeeMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Books");
        }

        populateData();
        initViews();
        configViews();
    }

    private void populateData() {
        //SciFi
        List<Book> SciFi = new ArrayList<>();
        SciFi.add(new Book("Empire of the Vampire",
                R.drawable.scifi1,
                "Jay Kristoff",
                "St. Martin's Press",
                10));
        SciFi.add(new Book("Nothing But Blackened Teeth",
                R.drawable.scifi2,
                "Cassandra Khaw",
                "Doherty Associates, LLC, Tom",
                2));
        SciFi.add(new Book("Mile High with a Vampire",
                R.drawable.scifi3,
                "Lynsay Sands",
                "HarperCollins Publishers",
                4));
        SciFi.add(new Book("A Light in the Mist",
                R.drawable.scifi4,
                "Erin Hunter",
                "HarperCollins Publishers",
                10));

        //comics
        List<Book> comics = new ArrayList<>();
        comics.add(new Book("1984: The Graphic Novel",
                R.drawable.comic1,
                "George Orwell",
                "Houghton Mifflin Harcourt Publishing Company",
                10));
        comics.add(new Book("Mycelium Wassonii",
                R.drawable.comic2,
                "Brian Blomerth",
                "Anthology Editions, LLC",
                2));
        comics.add(new Book("Attack on Titan",
                R.drawable.comic3,
                "Hajime Isayama",
                "Kodansha America, Incorporated",
                4));
        comics.add(new Book("Birds of Shangri-La",
                R.drawable.comic4,
                "Ranmaru Zariya",
                "Viz Media",
                10));
        comics.add(new Book("On Tyranny",
                R.drawable.comic5,
                "Timothy Snyder",
                "Potter/Ten Speed/Harmony/Rodale",
                3));

        //history
        List<Book> history = new ArrayList<>();
        history.add(new Book("The Audacity of Hope",
                R.drawable.history1,
                "Barack Obama",
                "Potter/Ten Speed/Harmony/Rodale",
                2));

        //Art and music
        List<Book> artAndMusic = new ArrayList<>();
        artAndMusic.add(new Book("Hand Lettering for Relaxation",
                R.drawable.art1,
                "Amy Latta",
                "Page Street Publishing Company",
                6));
        artAndMusic.add(new Book("Mastering Modern Calligraphy",
                R.drawable.art2,
                "Molly Suber Thorpe",
                "St. Martin's Press",
                2));
        artAndMusic.add(new Book("Boys' Guide to Drawing",
                R.drawable.art3,
                "Aaron Sautter",
                "Capstone",
                4));
        artAndMusic.add(new Book("Making Faces",
                R.drawable.art4,
                "Kevyn Aucoin",
                "Little Brown & Company",
                10));
        artAndMusic.add(new Book("Coco Chanel",
                R.drawable.art5,
                "Mª Isabel Sánchez Vegara",
                "Alba Editorial",
                3));

        //biographies
        List<Book> biographies = new ArrayList<>();
        biographies.add(new Book("Into the Wild",
                R.drawable.bio1,
                "Jon Krakauer",
                "Knopf Doubleday Publishing Group",
                6));
        biographies.add(new Book("Narrative of the Life of Frederick Douglass",
                R.drawable.bio2,
                "Molly Suber Thorpe",
                "Bedford/Saint Martin's",
                2));
        biographies.add(new Book("The Glass Castle - A Memoir",
                R.drawable.bio3,
                "Jeannette Walls",
                "Scribner",
                4));
        biographies.add(new Book("Think and Grow Rich",
                R.drawable.bio4,
                "Napoleon Hill",
                "TarcherPerigee",
                10));
        biographies.add(new Book("Liar's Poker",
                R.drawable.bio5,
                "Michael Lewis",
                "W. W. Norton & Company",
                3));

        //entertainment
        List<Book> entertainment = new ArrayList<>();
        entertainment.add(new Book("One Fish Two Fish Red Fish Blue Fish ",
                R.drawable.entertainment1,
                "Dr. Seuss",
                "Random House Children's Books",
                6));
        entertainment.add(new Book("If You Give a Mouse a Cookie",
                R.drawable.entertainment2,
                "Laura Joffe Numeroff",
                "HarperCollins Publishers",
                2));

        booksMap.put("Comics", comics);
        booksMap.put("Art & Music", artAndMusic);
        booksMap.put("Biographies", biographies);
        booksMap.put("Entertainment", entertainment);
        booksMap.put("History", history);
        booksMap.put("SciFi & Fantasy", SciFi);


    }


    private void initViews() {
        spCategories = findViewById(R.id.sp_categories);
        spBooks = findViewById(R.id.sp_books);
        ivBookCover = findViewById(R.id.iv_book_cover);
        btnSeeMore = findViewById(R.id.btn_see_details);
    }

    private void configViews() {
        List<String> categories = new ArrayList<>(booksMap.keySet());
        ArrayAdapter<String> aa = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategories.setAdapter(aa);

        spCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCategory = booksMap.get((String) spCategories.getSelectedItem());
                List<String> booksName = new ArrayList<>();
                if (selectedCategory != null) {
                    for (Book book : selectedCategory) {
                        booksName.add(book.getTitle());
                    }
                    ArrayAdapter<String> aa = new ArrayAdapter<>(MainActivity.this,
                            android.R.layout.simple_spinner_item, booksName);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spBooks.setAdapter(aa);

                    ivBookCover.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,
                            selectedCategory.get(0).getCoverImgId()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //no op
            }
        });

        spBooks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ivBookCover.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,
                        selectedCategory.get(i).getCoverImgId()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //no op
            }
        });

        btnSeeMore.setOnClickListener(view -> navigateToDetailsPage());
    }

    private void navigateToDetailsPage() {
        Intent detailsActivity = new Intent(this, DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("BOOK_DETAILS",
                selectedCategory.get(spBooks.getSelectedItemPosition()));
        detailsActivity.putExtras(bundle);
        startActivity(detailsActivity);
    }
}