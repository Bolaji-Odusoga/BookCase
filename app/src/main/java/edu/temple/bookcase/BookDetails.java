package edu.temple.bookcase;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class BookDetails extends Fragment {

    private static final String BOOK_KEY = "bookKey";
    private Book book;

    ImageView imageView;
    Parcelable bookObjects;
    TextView textView;
    TextView authorText;

    public BookDetails() {}

    public static BookDetails newInstance(Book bookObject) {
        BookDetails fragment = new BookDetails();
        Bundle args = new Bundle();
        args.putParcelable(BOOK_KEY, bookObject);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bookObjects = getArguments().getParcelable(BOOK_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_details, container, false);
        imageView = v.findViewById(R.id.container_2);
        authorText= v.findViewById(R.id.authorTextView);
         textView = v.findViewById(R.id.titleTextView);
        if (book != null) {
            changeBook(book);
        }

        return v;
    }

    public void changeBook(Book b) {

        textView.setText(book.getTitle());
        authorText.setText(book.getAuthor());
        Picasso.get().load(b.coverURL).into(imageView);


    }

}

