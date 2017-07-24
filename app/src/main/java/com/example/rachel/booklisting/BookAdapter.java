package com.example.rachel.booklisting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rachel on 07/07/2017.
 */

public class BookAdapter extends ArrayAdapter<BookVolume> {
    /**
     * Constructs a new {@link BookAdapter}.
     *
     * @param context of the app
     * @param books   is the list of books, which is the data source of the adapter
     */
    public BookAdapter(Context context, List<BookVolume> books) {
        super(context, 0, books);
    }

    /**
     * Returns a list item view that displays information about the earthquake at the given position
     * in the list of earthquakes.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list_item, parent, false);
        }


        // Find the earthquake at the given position in the list of earthquakes
        BookVolume currentBook = getItem(position);

        TextView TitleView = (TextView) listItemView.findViewById(R.id.book_title);
        TextView AuthorView = (TextView) listItemView.findViewById(R.id.book_author);
        TextView LanguageView = (TextView) listItemView.findViewById(R.id.book_language);
        ImageView BookView = (ImageView)listItemView.findViewById(R.id.book_image);
        TitleView.setText(currentBook.getTitle());
        AuthorView.setText(currentBook.getAuthor());
        LanguageView.setText("("+currentBook.getLanguage()+")");
        Picasso.with(getContext()).load(currentBook.getBookImage()).into(BookView);

        return listItemView;

    }
}