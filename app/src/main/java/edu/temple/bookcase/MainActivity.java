package edu.temple.bookcase;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements BookListFragment.BookSelectedInterface {

    FragmentManager fm;
    BookDetails bookDetailsFragment;
    boolean singlePane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();

        singlePane = findViewById(R.id.container_2) == null;

        try {
            separatesURLData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Collections.addAll(bookList, getResources().getStringArray(R.array.arrayList));

        // Check for fragments in both containers

        Fragment container1 = fm.findFragmentById(R.id.container_1);
        Fragment container2 = fm.findFragmentById(R.id.container_2);


        if (container1 == null) {

            if (singlePane) {
                fm.beginTransaction().add(R.id.container_1, ViewPage.newInstance(bk)).commit();
            } else {
                bookDetailsFragment = new BookDetails();
                fm.beginTransaction().add(R.id.container_1, BookListFragment.newInstance(bk)).add(R.id.container_2, bookDetailsFragment).commit();
            }
        } else {
            // Fragments already exist (activity was restarted)
            if (singlePane) {
                if (container1 instanceof BookListFragment) {
                    // If we have the wrong fragment for this configuration, remove it and add the correct one
                    fm.beginTransaction().remove(container1).add(R.id.container_1, ViewPage.newInstance(bk)).commit();
                }
            } else {
                if (container1 instanceof ViewPage) {
                    fm.beginTransaction().remove(container1).add(R.id.container_1, BookListFragment.newInstance(bk)).commit();
                }
                if (container2 instanceof BookDetails)
                    bookDetailsFragment = (BookDetails) container2;
                else {
                    bookDetailsFragment = new BookDetails();
                    fm.beginTransaction().add(R.id.container_2, bookDetailsFragment).commit();
                }
            }

            bookDetailsFragment = (BookDetails) container2;

        }

        final EditText searchQuery = (EditText) findViewById(R.id.search_query);
        searchQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String searchData = searchQuery.getText().toString();
                    showResults(searchData); //passing string to search in your database to your method
                    return true;
                }
                return false;
            }
        });
    }

    private void showResults(String searchData) {

       for(int i =0 ;i<b.size();i++) {
           if (searchData.matches(b.get(i).title)){
               //b.get(i).
           }

       }
    }

    class RetrieveFeedTask extends AsyncTask<String,Void,JSONObject> {


        public  JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
            InputStream is = new URL(url).openStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String jsonText = readAll(rd);
                JSONArray json = new JSONArray(jsonText);
                return json;
            } finally {
                is.close();
            }
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            return null;
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }



    static ArrayList<Book> b;
   static Book bk =new Book();
    public void separatesURLData() throws IOException, JSONException {

        RetrieveFeedTask rt = new RetrieveFeedTask();

        JSONArray json = rt.readJsonFromUrl("https://kamorris.com/lab/audlib/booksearch.php");

        Parcel parcel=null;



        for(int i=0;i< json.length();i++){
            JSONObject o=json.getJSONObject(i);
            b.add(bk.setObject(o));


        }




    }




    public void bookSelected(int i) {

        if (bookDetailsFragment != null)
            bookDetailsFragment.changeBook(b.get(i));
    }



    public void bookSelected() {

    }
}

