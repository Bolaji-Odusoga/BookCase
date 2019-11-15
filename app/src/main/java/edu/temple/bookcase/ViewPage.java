package edu.temple.bookcase;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;



public class ViewPage extends Fragment {
    private static final String BOOKLIST_KEY = "booklist";

    ViewPager viewPager;
     private  Book viewPageBook;


    public ViewPage() {}

    public static ViewPage newInstance(Book bookObject) {
        ViewPage fragment = new ViewPage();
        Bundle args = new Bundle();
        args.putParcelable(BOOKLIST_KEY,bookObject);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            viewPageBook = getArguments().getParcelable(BOOKLIST_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_page, container, false);

        viewPager = v.findViewById(R.id.viewPager);

        viewPager.setAdapter(new BookFragmentAdapter(getChildFragmentManager(), viewPageBook));

        return v;
    }


    class BookFragmentAdapter extends FragmentStatePagerAdapter {


        private  Book viewPageBook;
        public BookFragmentAdapter(FragmentManager fm, Book bookObject) {
            super(fm);
            this.viewPageBook = bookObject;
        }

        @Override
        public Fragment getItem(int i) {
            return BookDetails.newInstance(viewPageBook);
        }

        @Override
        public int getCount() {
           // return viewPageBook.;
            return 0;
        }
    }

}





