package com.varun.android.listview.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.varun.android.listview.R;
import com.varun.android.listview.adapter.CustomListAdapter;
import com.varun.android.listview.app.AppController;
import com.varun.android.listview.model.Movie;
import com.varun.android.listview.model.Movie_Backup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MainActivity_Backup /*extends AppCompatActivity*/ {/*
    // Log tag
    private static final String TAG = MainActivity_Backup.class.getSimpleName();
    private static final int ADD_REMINDER = 0;
    // Movies json url
    private static final String url = "https://api.androidhive.info/json/movies.json";
    private ProgressDialog pDialog;
    private List<Movie_Backup> movieList = new ArrayList<Movie_Backup>();
    private ListView listView;
    private CustomListAdapter adapter;
    Button btnClosePopup;
    Button btnCreatePopup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_backup_2);



       *//* boolean isPaidVersion = false;

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, movieList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);


        if (!isPaidVersion) {
            displayLocalReminderList(1900, 0);
        } else {
            displayListFromWeb();
        }

        Button _addReminderLink = (Button) findViewById(R.id.add_reminder_button);
        _addReminderLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), AddReminderActivity.class);
                startActivityForResult(intent,ADD_REMINDER);
            }
        });
        hidePDialog();*//*
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    private void displayLocalReminderList(int year, int day) {
        String fileName = "cache_reminders.json";
        String content = "[{\n" +
                "        \"title\": \"Dawn of the Planet of the Apes\",\n" +
                "        \"image\": \"http://api.androidhive.info/json/movies/1.jpg\",\n" +
                "        \"rating\": 8.3,\n" +
                "        \"releaseYear\": 2014,\n" +
                "        \"genre\": [\"Action\", \"Drama\", \"Sci-Fi\"]\n" +
                "    },\n" +
                "    {\n" +
                "        \"title\": \"District 9\",\n" +
                "        \"image\": \"http://api.androidhive.info/json/movies/2.jpg\",\n" +
                "        \"rating\": 8,\n" +
                "        \"releaseYear\": 2009,\n" +
                "        \"genre\": [\"Action\", \"Sci-Fi\", \"Thriller\"]\n" +
                "    }]";
        try {
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = null;
        FileInputStream fis = null;
        try {
            fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader streamReader = new BufferedReader(isr);

            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            jsonArray = new JSONArray(responseStrBuilder.toString());
            streamReader.close();
            isr.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Parsing json
        for (int i = 0; i < jsonArray.length(); i++) {
            try {

                JSONObject obj = jsonArray.getJSONObject(i);
                Movie_Backup movie = new Movie_Backup();
                movie.setTitle(obj.getString("title"));
                movie.setThumbnailUrl(obj.getString("image"));
                movie.setRating(((Number) obj.get("rating"))
                        .doubleValue());
                movie.setYear(obj.getInt("releaseYear"));

                // Genre is json array
                JSONArray genreArry = obj.getJSONArray("genre");
                ArrayList<String> genre = new ArrayList<String>();
                for (int j = 0; j < genreArry.length(); j++) {
                    genre.add((String) genreArry.get(j));
                }
                movie.setGenre(genre);

                // adding movie to movies array
                movieList.add(movie);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // notifying list adapter about data changes
        // so that it renders the list view with updated data
        adapter.notifyDataSetChanged();
    }


    private void displayListFromWeb() {
        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Movie_Backup movie = new Movie_Backup();
                                movie.setTitle(obj.getString("title"));
                                movie.setThumbnailUrl(obj.getString("image"));
                                movie.setRating(((Number) obj.get("rating"))
                                        .doubleValue());
                                movie.setYear(obj.getInt("releaseYear"));

                                // Genre is json array
                                JSONArray genreArry = obj.getJSONArray("genre");
                                ArrayList<String> genre = new ArrayList<String>();
                                for (int j = 0; j < genreArry.length(); j++) {
                                    genre.add((String) genreArry.get(j));
                                }
                                movie.setGenre(genre);

                                // adding movie to movies array
                                movieList.add(movie);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);
        VolleyLog.d(TAG, "Error: " + movieReq);
    }*/
}

/*        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
              });*/

/*//Button click listeners
        Button addReminderButton = (Button) findViewById(R.id.add_reminder_button);

        addReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatePopupWindow();
            }

            private PopupWindow pwindo;

            private void initiatePopupWindow() {
                try {
                    // We need to get the instance of the LayoutInflater
                    LayoutInflater inflater = (LayoutInflater) MainActivity.this
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View layout = inflater.inflate(R.layout.add_reminder_layout_popup_backup,
                            (ViewGroup) findViewById(R.id.reminder_layout));
                    pwindo = new PopupWindow(layout, 300, 370, true);
                    pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

                    btnClosePopup = (Button) layout.findViewById(R.id.btn_close_popup);
                    btnClosePopup.setOnClickListener(cancel_button_click_listener);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private OnClickListener cancel_button_click_listener = new OnClickListener() {
                public void onClick(View v) {
                    pwindo.dismiss();

                }
            };

        });
*/