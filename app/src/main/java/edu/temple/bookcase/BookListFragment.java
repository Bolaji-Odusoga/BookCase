package edu.temple.bookcase;


import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookListFragment extends Fragment {
    private static final String BOOKLIST_KEY = "booklist";

    ListView listView;
    private Book bookListObject;
    BookSelectedInterface parentActivity;

    public BookListFragment() {}

    public static BookListFragment newInstance(Book bookObject) {
        BookListFragment fragment = new BookListFragment();
        Bundle args = new Bundle();
        args.putParcelable(BOOKLIST_KEY, bookObject);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bookListObject = getArguments().getParcelable(BOOKLIST_KEY);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BookSelectedInterface)
            parentActivity = (BookSelectedInterface) context;
        else
            throw new RuntimeException("Please implement BookSelectedInterface");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parentActivity = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_booklist, container, false);
        listView = layout.findViewById(R.id.listView);
        ViewPageCollectionAdapter vp = new ViewPageCollectionAdapter(getFragmentManager(),bookListObject);
        listView.setAdapter(vp);
         final ImageView imageView = layout.findViewById(R.id.container_2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //parentActivity.bookSelected(Picasso.get().load(bookListObject.coverURL).into(imageView));
            }
        });

        return layout;
    }

    interface BookSelectedInterface {
        void bookSelected( int i);
    }

}