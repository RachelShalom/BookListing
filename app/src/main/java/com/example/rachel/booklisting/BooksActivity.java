package com.example.rachel.booklisting;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.rachel.booklisting.MainActivity.BOOK_REQUEST_URL;

public class BooksActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BookVolume>> {
    private static final String LOG_TAG = BooksActivity.class.getName();

    TextView noBooks;
    BookAdapter mAdapter;
    ProgressBar progress;
    boolean isConnected;

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int BOOK_LOADER_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();


        // Find a reference to the {@link ListView} in the layout
        ListView BooksListView = (ListView) findViewById(R.id.list);
        //find a reference of progres bar
        progress = (ProgressBar) findViewById(R.id.loading_spinner);
        //find a refrence to the empty view in the layout
        noBooks = (TextView) findViewById(R.id.empty_view);
        BooksListView.setEmptyView(noBooks);


        // Create a new adapter that takes an empty list of books as input
        mAdapter = new BookAdapter(this, new ArrayList<BookVolume>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        BooksListView.setAdapter(mAdapter);
        BooksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookVolume currentBook = mAdapter.getItem(position);
                Uri WebPreview =Uri.parse(currentBook.getPreview());
                Intent webPreviewIntent = new Intent(Intent.ACTION_VIEW,WebPreview);
                startActivity(webPreviewIntent);
            }
        });

        if (isConnected){
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(BOOK_LOADER_ID, null, this);
    }else{
            noBooks.setText(R.string.no_iternet);
            progress.setVisibility(View.GONE);
        }
    }

    @Override
    public Loader<List<BookVolume>> onCreateLoader(int i, Bundle bundle) {
        Log.e(LOG_TAG,"url is:"+BOOK_REQUEST_URL);
        // Create a new loader for the given URL
        return new BooksLoader(this,BOOK_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<BookVolume>> loader, List<BookVolume> books) {
        // Clear the adapter of previous earthquake data
        mAdapter.clear();
        progress.setVisibility(View.GONE);

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }
        noBooks.setText("Sorry we didn't find any books");
    }

    @Override
    public void onLoaderReset(Loader<List<BookVolume>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    }

