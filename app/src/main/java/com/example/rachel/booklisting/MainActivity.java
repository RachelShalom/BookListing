package com.example.rachel.booklisting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getName();
    EditText bookSearch;
    public static String bookSearchValue ;
    public static String BOOK_REQUEST_URL;
    String noSpaceBookResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookSearch = (EditText)findViewById(R.id.editText3);


    Button sButton = (Button)findViewById(R.id.button);
    sButton.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            bookSearch = (EditText)findViewById(R.id.editText3);

            bookSearchValue = bookSearch.getText().toString();
            //in case there is more than one search word this will change the space to +
            noSpaceBookResult=bookSearchValue.replaceAll(" ","+");
            Log.e(LOG_TAG,"you typed:"+bookSearchValue);
            // Start BooksActivity.class only if the user typedhe user types a search word. if the user did not type then a toast message apears
            if(bookSearchValue.trim().length()>0) {
                /** URL for books data from the google books dataset */
                BOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=" + noSpaceBookResult.trim();
                Intent myIntent = new Intent(MainActivity.this,
                        BooksActivity.class);
                startActivity(myIntent);
            }// in case the user did not type anyting 
            else{
                Toast.makeText(MainActivity.this,"Please type name of a book or author",Toast.LENGTH_LONG).show();}
        }
    });
}
}