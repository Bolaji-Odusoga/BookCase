package edu.temple.bookcase;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class ViewPage extends Fragment {

    private TextView t;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ViewPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_page, container, false);

        t = view.findViewById(R.id.bookTitle);
        ViewPageCollectionAdapter v = new ViewPageCollectionAdapter(getFragmentManager());
        //String book = getArguments().getString("message");

        String[] books = getActivity().getResources().getStringArray(R.array.arrayList);


        for (int i = books.length; i >= 0; i--) {

            switch (i) {
                case 0:
                    t.setText("Don Quixote");

                    break;
                case 1:
                    t.setText("The Great Gatsby");
                    break;
                case 2:
                    t.setText("Moby Dick");
                    break;
                case 3:
                    t.setText("War and Peace");
                    break;
                case 4:
                    t.setText("Hamlet");
                    break;
                case 5:
                    t.setText("The Odyssey");
                    break;
                case 6:
                    t.setText(" The Catcher in the Rye");
                    break;
                case 7:
                    t.setText("Pride and Prejudice");
                    break;
                case 8:
                    t.setText("Catch-22");
                    break;
                case 9:
                    t.setText("Gulliver's Travels");
                    break;


            }

            for (int j = books.length; j > 0; j--) {

                switch (j) {
                    case 0:
                        t.setText("Don Quixote");

                        break;
                    case 1:
                        t.setText("The Great Gatsby");
                        break;
                    case 2:
                        t.setText("Moby Dick");
                        break;
                    case 3:
                        t.setText("War and Peace");
                        break;
                    case 4:
                        t.setText("Hamlet");
                        break;
                    case 5:
                        t.setText("The Odyssey");
                        break;
                    case 6:
                        t.setText(" The Catcher in the Rye");
                        break;
                    case 7:
                        t.setText("Pride and Prejudice");
                        break;
                    case 8:
                        t.setText("Catch-22");
                        break;
                    case 9:
                        t.setText("Gulliver's Travels");
                        break;


                }
            }

        }

        return view;
    }
}
