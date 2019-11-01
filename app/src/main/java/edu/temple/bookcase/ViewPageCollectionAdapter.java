package edu.temple.bookcase;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPageCollectionAdapter extends FragmentStatePagerAdapter {
    public ViewPageCollectionAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        ViewPage v = new ViewPage();
        return v;
    }

    Context context;

    public void getContext(Context context){
        this.context=context;
    }
    @NonNull

    public Fragment getI(int position,String book) {

        ViewPage v = new ViewPage();
      Bundle b = new Bundle();


        b.putString("message",book);
        v.setArguments(b);
        return v;
    }

    @Override
    public int getCount() {
        return 10;
    }
    public String getBok(int poisiton){

        //TextView textView ;


        MainActivity mn =new MainActivity();

        String [] array=mn.bookArray;

        return array[poisiton];
    }
}

  /*  public View getView(int i, View view, ViewGroup viewGroup) {


        textView.setText("yo");









        return textView;
    }
}*/
