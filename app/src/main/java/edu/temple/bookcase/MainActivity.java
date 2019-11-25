package edu.temple.bookcase;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements BookListFragment.BookSelectedInterface {

    FragmentManager fm;
    BookDetails bookDetailsFragment;
    boolean singlePane;
    Library library;
    Fragment container1;
        Fragment container2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        library = new Library();

        // Check for fragments in both containers
        container1 = fm.findFragmentById(R.id.container_1);
        container2 = fm.findFragmentById(R.id.container_2);

        singlePane = findViewById(R.id.container_2) == null;

        if (container1 == null) {
            fetchBooks(null);
        } else {
            updateDisplay();
        }

        findViewById(R.id.searchButton).setOnClickListener(v -> fetchBooks(((EditText) findViewById(R.id.searchBox)).getText().toString()));
    }
    private void setDisplay() {
        // If there are no fragments at all (first time starting activity)

        if (singlePane) {
            container1 = ViewPage.newInstance(library);
            fm.beginTransaction()
                    .add(R.id.container_1, container1)
                    .commit();
        } else {
            container1 = BookListFragment.newInstance(library);
            bookDetailsFragment = new BookDetails();
            fm.beginTransaction()
                    .add(R.id.container_1, container1)
                    .add(R.id.container_2, bookDetailsFragment)
                    .commit();
        }

    }

    private void updateDisplay () {
        Fragment tmpFragment = container1;;
        library = ((Displayable) container1).getBooks();
        if (singlePane) {
            if (container1 instanceof BookListFragment) {
                container1 = ViewPage.newInstance(library);
                // If we have the wrong fragment for this configuration, remove it and add the correct one
                fm.beginTransaction()
                        .remove(tmpFragment)
                        .add(R.id.container_1, container1)
                        .commit();
            }
        } else {
            if (container1 instanceof ViewPage) {
                container1 = BookListFragment.newInstance(library);
                fm.beginTransaction()
                        .remove(tmpFragment)
                        .add(R.id.container_1, container1)
                        .commit();
            }
            if (container2 instanceof BookDetails)
                bookDetailsFragment = (BookDetails) container2;
            else {
                bookDetailsFragment = new BookDetails();
                fm
                        .beginTransaction()
                        .add(R.id.container_2, bookDetailsFragment)
                        .commit();
            }
        }

        bookDetailsFragment = (BookDetails) container2;
    }

    private void updateBooks() {
        ((Displayable) container1).setBooks(library);
    }

    @Override
    public void bookSelected(Book book) {
        if (bookDetailsFragment != null)
            bookDetailsFragment.changeBook(book);
    }

    private boolean isNetworkActive() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private final String SEARCH_URL = "https://kamorris.com/lab/audlib/booksearch.php?search=";

    Handler bookHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            try {
                library.clear();
                JSONArray booksArray = new JSONArray((String) message.obj);
                for (int i = 0; i < booksArray.length(); i++) {
                    library.addBook(new Book(booksArray.getJSONObject(i)));
                }

                if(fm.findFragmentById(R.id.container_1) == null)
                    setDisplay();
                else
                    updateBooks();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }
    });

    private void fetchBooks(final String searchString) {
        new Thread() {
            @Override
            public void run() {
                if (isNetworkActive()) {

                    URL url;

                    try {
                        url = new URL(SEARCH_URL + (searchString != null ? searchString : ""));
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(
                                        url.openStream()));

                        StringBuilder response = new StringBuilder();
                        String tmpResponse;

                        while ((tmpResponse = reader.readLine()) != null) {
                            response.append(tmpResponse);
                        }

                        Message msg = Message.obtain();

                        msg.obj = response.toString();

                        Log.d("Books RECEIVED", response.toString());

                        bookHandler.sendMessage(msg);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    Log.e("Network Error", "Cannot download books");
                }
            }
        }.start();
    }



}
