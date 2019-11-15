package edu.temple.bookcase;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.squareup.picasso.Picasso;

public class ViewPageCollectionAdapter extends FragmentStatePagerAdapter implements ListAdapter {


    Book bookListObject;
    public ViewPageCollectionAdapter(@NonNull FragmentManager fm, Book bookListObject) {
        super(fm);
        this.bookListObject=bookListObject;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        ViewPage v = new ViewPage();
        return v;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v =view.findViewById(R.id.imageView);


        Picasso.get().load(bookListObject.coverURL).into((ImageView) v);

        return null;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    Context context;

    @Override
    public int getCount() {
        return 0;
    }

    public int getItemPosition(@NonNull Object bookObject) {

        return ViewPageCollectionAdapter.POSITION_NONE;
    }

    public void getContext(Context context) {
        this.context = context;
    }

    @NonNull

    public Fragment get(int position) {

        ViewPage v = new ViewPage();
        Bundle b = new Bundle();


        // b.putString("message",book);
        v.setArguments(b);
        return v;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }
}


