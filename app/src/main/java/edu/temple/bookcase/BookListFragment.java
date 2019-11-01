package edu.temple.bookcase;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class BookListFragment extends Fragment {

    ListView listView;
    String send;
    Context context;
    TextView txt;
    public BookListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_booklist, container, false);
        listView = v.findViewById(R.id.listView);

        listView.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, context.getResources().getStringArray(R.array.arrayList)));

        txt =v.findViewById(R.id.texts);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> theList, View view, int position, long id) {
                String book = (String) theList.getItemAtPosition(position);

                txt.setText(book);


            }
        });

        //MainActivity.getBook(send);
        return v;
    }

    }
