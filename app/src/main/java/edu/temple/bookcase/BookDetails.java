package edu.temple.bookcase;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class BookDetails extends Fragment {

   static TextView textView;
    String book = "";
    static   Bundle bundle;

    public static final String BOOK_KEY = "book";

    public BookDetails() {
        // Required empty public constructor
    }

    public static BookDetails newInstance (String book) {
        BookDetails bookDetails = new BookDetails();

       bundle = new Bundle();
        bundle.putString(BOOK_KEY, book);

        bookDetails.setArguments(bundle);

        return bookDetails;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            book = getArguments().getString(BOOK_KEY);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_book_details, container, false);
        textView = v.findViewById(R.id.textView);

        change(book);

        return v;
    }

    public static void changeBook(String book) {
        change(book);
    }

    private static void change(String book) {

             switch (book) {

                 case "Don Quixote":
                     textView.setText("Don Quixote");
                     ;
                     break;
                 case "The Great Gatsby":
                     textView.setText("The Great Gatsby");
                     break;
                 case "Moby Dick":
                     textView.setText("Moby Dick");
                     break;
                 case "War and Peace":
                     textView.setText("War and Peace");
                     break;
                 case "Hamlet":
                     textView.setText("Hamlet");
                     break;
                 case "The Odyssey":
                     textView.setText("The Odyssey");
                     break;
                 case " The Catcher in the Rye":
                     textView.setText(" The Catcher in the Rye");
                     break;
                 case "Pride and Prejudice":
                     textView.setText("Pride and Prejudice");
                     break;
                 case "Catch-22":
                     textView.setText("Catch-22");
                     break;
                 case "Gulliver's Travels":
                     textView.setText("Gulliver's Travels");
                     break;


             }
    }

    public Bundle getBundle() {
        return bundle;
    }
}
