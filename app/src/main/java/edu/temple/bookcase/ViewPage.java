package edu.temple.bookcase;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;



public class ViewPage extends Fragment {
    private static final String BOOKLIST_KEY = "booklist";

    ViewPager viewPager;
    private Library bookList;
    BookListFragment.BookSelectedInterface parentActivity;


    public ViewPage() {
    }

    public static ViewPageCollectionAdapter newInstance(Library bookList) {
        ViewPageCollectionAdapter fragment = new ViewPageCollectionAdapter();
        Bundle args = new Bundle();
        args.putParcelable(BOOKLIST_KEY, bookList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bookList = getArguments().getParcelable(BOOKLIST_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_page, container, false);

        viewPager = v.findViewById(R.id.viewPager);

        viewPager.setAdapter(new BookFragmentAdapter(getChildFragmentManager(), bookList));

        return v;
    }


    public Library getBooks() {
        return bookList;
    }


    public void setBooks(Library books) {
        bookList = books;
        viewPager.getAdapter().notifyDataSetChanged();
    }

    class BookFragmentAdapter extends FragmentStatePagerAdapter {

        Library bookList;

        public BookFragmentAdapter(FragmentManager fm, Library bookList) {
            super(fm);
            this.bookList = bookList;
        }

        @Override
        public Fragment getItem(int i) {
            return BookDetails.newInstance(bookList.getBookAt(i));
        }

        @Override
        public int getCount() {
            return bookList.size();
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }
}





