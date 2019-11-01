package edu.temple.bookcase;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
    BookDetails bd;
    BookListFragment bl;
    FragmentManager fm;
    static String boo;
    private ViewPager viewPager;
    boolean singlePane;
    String[] bookArray;
    private ViewPageCollectionAdapter adapter;



    public static void getBook(String book){
        boo=book;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       Context context= getApplicationContext();

        Resources res = context.getResources();
        String [] list =res.getStringArray(R.array.arrayList);

            bookArray= new String[list.length];

        for(int i = 0 ; i < list.length;i++){

            bookArray[i]= list[i];
        }



        boolean flag=true;
        singlePane = findViewById(R.id.fragment_bookDetails) == null;


        if (singlePane) {
          flag = false;
        }

        bd = new BookDetails();
        bl = new BookListFragment();


        fm = getSupportFragmentManager();




        if (flag==false) {//single pane

            viewPager=findViewById(R.id.pager);
            adapter= new ViewPageCollectionAdapter((getSupportFragmentManager()));

            ViewPageCollectionAdapter vc=new ViewPageCollectionAdapter(fm);
                vc.getContext(context);
                viewPager.setAdapter(adapter);

       // fm.beginTransaction().replace(R.id.viewPage, bl).commit();

        }
        else {
            //2 pane
            fm.beginTransaction().replace(R.id.bookList, bl).commit();

        }




    }


    public void bookSelected(String book) {



        if (singlePane) {

           // BookDetails newFragment = BookDetails.newInstance(book);
           // fm.beginTransaction().replace(R.id.bookList, bd).addToBackStack(null).commit();
        } else {
            bd.changeBook(book);
        }
    }
}

